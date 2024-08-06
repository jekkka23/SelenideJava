public class GradleForAllure {

    //Илюхин билд.градл начало
    plugins {
        id 'java-library' // Применяет плагин Java Library для сборки Java библиотек.
        id "io.qameta.allure" version "2.11.2" // Применяет плагин Allure для генерации отчетов о тестировании.
    }

    repositories {
        mavenCentral() // Использует репозиторий Maven Central для разрешения зависимостей.
    }


    // Определяет версии для библиотек Allure и Selenide.
    def allureVersion = "2.27.0",
            selenideVersion = "7.2.3"

    allure {
        report {
            version.set(allureVersion) // Устанавливает используемую версию Allure report.
        }
        adapter {
            aspectjWeaver.set(true) // Включает AspectJ Weaver для Allure.
            frameworks {
                junit5 {
                    adapterVersion.set(allureVersion) // Устанавливает версию адаптера Allure для JUnit5.
                }
            }
        }
    }

    dependencies {
        testImplementation(
                "com.codeborne:selenide:$selenideVersion", // Добавляет библиотеку Selenide для автоматизации браузера.
                "org.junit.jupiter:junit-jupiter:5.10.2", // Добавляет JUnit Jupiter для написания тестов.
                "com.github.javafaker:javafaker:1.0.2", // Добавляет JavaFaker для генерации фейковых данных.
                "io.qameta.allure:allure-selenide:$allureVersion", // Добавляет интеграцию Allure Selenide.
                'org.slf4j:slf4j-simple:2.0.7' // Добавляет SLF4J Simple для логирования.
        )
    }

// Настраивает задачи тестирования.
tasks.withType(Test) {
        systemProperties(System.getProperties()) // Передает системные свойства задачам тестирования.
        useJUnitPlatform() // Использует JUnit Platform для запуска тестов.

        testLogging {
            lifecycle {
                events "started", "skipped", "failed", "standard_error", "standard_out" // Логирует эти события во время жизненного цикла теста.
                exceptionFormat "short" // Использует короткий формат для логирования исключений.
            }
        }
    }

// Дополнительная настройка задач тестирования.
tasks.withType(Test) {
        systemProperties(System.getProperties()) // Передает системные свойства задачам тестирования.
        useJUnitPlatform {
            if (System.getProperty("type") != null && !System.getProperty("type").isEmpty()) {
                includeTags System.getProperty("type") // Включает тесты с определенными тегами, если установлено свойство 'type'.
            }
        }
    }

    //конец





}

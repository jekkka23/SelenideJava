// сначала создаем файл build.gradle и прописываем то значение которое в уже созданном файле

// для открытия сайта нужно использовать слежующий код

// Селениум инструмент для общения с драйвером
// Селенид инструмент для работы тестировщика
// Если не получается найти локатор, можно запустить Selenide IDE и посмотреть значение локатора внутри

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.commands.Util.size;
import static com.codeborne.selenide.files.DownloadActions.click;
import static com.codeborne.selenide.impl.Html.text;
import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;
import static org.openqa.selenium.bidi.script.LocalValue.setValue;

public class Snippets {

    void browser_command_examples () {
        open("https://google.com");
        open("/customer/orders");
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("user", "password"));
//команда позволяет перейти на сайт в котором перед входом вылазит поп ап (вверху посередине)
//для ввода логина и пароля


        Selenide.back(); //команда что бы нажать на стрелку назад в браузере
        Selenide.refresh(); //команда что бы обновить страницу в браузере кнопкой
        Selenide.clearBrowserCookies(); //чистка куки браузера
        Selenide.clearBrowserLocalStorage(); //очестка истории браузера
        Selenide.confirm(); //нажать ОК в поп апах которые вылазят от браузера
        Selenide.dismiss(); //нажать Отмена в поп апах которые вылазят от браузера
        Selenide.closeWindow(); //закрыть активную вкладку
        Selenide.closeWebDriver(); //полностью закрыть браузер

        Selenide.switchTo().frame("new"); // фрейм это много страниц в одной странице.каждая страница это отдельный фрейм
        Selenide.switchTo().defaultContent(); // вернуться обратно в DOM (обязательно после посещения каждого фрейма)

        Selenide.switchTo().window("3"); //переместиться в другое окно

        var cookie = new Cookie("foo", "bar"); //что бы добавить куки
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }
//работа с командами для браузера
    void selectors_examples() {
            $("div").click(); //что бы вызвать css селектор с помошью $
            element("div").click(); //что бы вызвать css селектор с помошью element, одно и то же
            $("div", 2).click(); // это когда задача найти и кликнуть не первый div а например третий
            $x("//*[@class='nav-contact-us']").click(); //пример клика на xpath селектор
            $x("//h1/div").click(); //пример клика на xpath только написано через $
            $(byText("full text")).click(); // ищет полный текст
            $(withText("ull tex")).click(); //  ищет частично даже если текст не совпадает полностью
            $(byTagAndText("div", "full text")); // искать элемент который содержит определенный текст
            $(withTagAndText("div", "full text")); // искать элемент который содержит частично текст
            $("").parent(); // искать родительский элемент
            $("").sibling(1); // брат сестра вниз по дереву
            $(""). preceding (1); //
            $("").closest("div"); // то же самое что и ансестор
            $("").ancestor("div"); // ищем предка имеющего тег например div (в превые кавычки отца во вторые кавычки тег который выше)

            //пример применения родительского элемента parent()
            $("[id=password]").setValue("jekapeka");
            $("[id=loginByLogin]").setValue("jekapeka").pressEnter();
            Selenide.sleep(3000);
            $(byName("storeLogin")).parent().click(); //parent родительский / byName дочерний
            Selenide.sleep(3000);
        }
//работа с селекторами
    void actions_examples() { //когда нашли элемент и что то хотим с ним сделать
        $("").click(); // клик на элемент
        $("").doubleClick(); //двойной клик на элемент
        $("").contextClick(); //клик правой кнопкой мыши
        $("").hover(); //поднести мышку но не нажимать ее

        $("").setValue("text"); //ввести текст в поле для ввода (команда стирает старый текст и впишет тот что в сет валью)
        $("").append("text"); //ввести текст в поле для ввода (команда лоавляет к уже написаному тексту текст тот что в аппенд)
        $("").clear(); //стереть текст с поля для ввода
        $("").setValue(""); //стирает все с поля если в кавычках сет валью указать пустое поле
        $("div").sendKeys("c"); // hotkey c on element actions().sendKeys( keys: "c").perform(); //hotkey c on whole application
        actions().sendKeys("c").perform(); //hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // нажать клиавиши Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); //когда actions для нажатия клавишь не отрабатывает можно в корневой html указать страницу
        $("").pressEnter(); //нажать ентер
        $("").pressEscape(); //нажать ескейп
        $("").pressTab(); //нажать таб

        //сложные комбинации с клаиатурой+мышкой
        actions().moveToElement($ ("div")).clickAndHold().moveByOffset(300, 200).release().perform(); //передвигаю мышку к элементу, кликаю и зажимаю перемещаю, отпускаю и завершаю операцию

        //всплывающие списки
        $("").selectOption("dropdown_option"); //всплывающий список для работы со старым вариантом списка, с новым вариантом где всплывает список можно сделать кликами
        $("").selectRadio("radio_options"); //радио жто круглешки, так же чекбоксы, в радио указать слово
    }
//работа с элементом который нашли
    void assertions_examples() { // для проверки условия того что написано
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);
//longer timeouts
        $("").shouldBe(visible, timeout: Duration.ofSeconds(30));
    } //внутри проверок есть болк, conditions- это то что мы проверяем
// для проверки условия того что написан
    void conditions_examples () {
        //shouldBe должен быть элемент
        //shouldHave должен обладать текст
        $("").shouldBe(visible); //должен быть видимым проверка на видимость
        $("").shouldBe(hidden); //должен быть скрыт проверка на видимость
        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc")); //проверяет что текст только abc и ничего больше
        //это самыме популярные кондишены

        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$")); //проверка что все написано в правлином формате, например длата
        //в примере выше на regex написано что текст должен заканчиваться на abc а перед ним любая цифра 0-9
        $("").shouldHave(cssClass("red")); //проверка на то содержит ли элемент класс red или нет
        $("").shouldHave(cssValue("font-size", "12")); //проверка размера текста //еще она называется property //искать во складке Computet в DevTools (Prorerty name то что в таблице в дев тулс слева, Property value то что справа в той же таблице)
        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty); //проверить что поле пустое

        $("").shouldBe(checked); // проверка если чек бокс включен
        $("").shouldNotBe(checked); // проверка если чек бокс не включен
    }
//проверка кондишинов
    void collections_examples {

     //коллекции
        $$("div"); // does nothing!
        $$x("//div"); // by XPath
// selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));
        $$("div").first().click();
        elements("div").first().click();
// $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0) $ ( cssSelector: "div", index: 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); // finds first
// assertions проверки
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same
        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(itemWithText("Gamma")); // only one text
        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(size:2));
    }
//коллекции
    void file_operation_examples() throws FileNotFoundException { //работа с файлами

        File filed = $("a. fileLink").download(): // only for <0 hrefs".."s Links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));
        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile ( …file: file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();

        //Видео инструкции про загрузки на ютубе по названию Selenide 5.14.0 - Обзор релиза
    }
//работа с файлами


import org.junit.jupiter.api.*;

public class JunitOptions {

    //В JUnit есть возможность выполнять какой то общий код перед каждым тестом при помощи аннотации @BeforeEach

    int result;

    @BeforeAll
    //эта аннотация выполняет НАСТРОЙКИ которые в ней заданы ПЕРЕД ВСЕМИ тестами один раз, например настройка определенного размера браузера
    static void beforeAll() { //перед BeforeAll ВСЕГДА пишется static
        System.out.println("\n###   beforeAll\n");

    }

    @BeforeEach
        //эта аннотация выполняет условие которое в ней задано ПЕРЕД каждым тестом
    void beforeEach() {
        System.out.println("###   beforeEach");
        result = getResult();
    }

    @AfterEach
        //та аннотация выполняет условие которые в ней задано ПОСЛЕ каждого теста
    void afterEach() {
        System.out.println("###   afterEach");
        result = 0;
    }

    @AfterAll
    //эта аннотация выполняет НАСТРОЙКИ которые в ней заданы ПОСЛЕ ВСЕХ тестов  один раз, например очистка куки
    static void afterAll() { //перед afterAll ВСЕГДА пишется static
        System.out.println("###   afterAll\n");
    }

    @Test
    void firstTest() {
        System.out.println("###   firstTest"); //выводим на экран результат
        Assertions.assertTrue(result > 2); //с помощью assert можно что-то сравнивать
    }

    @Test
    void secondTest() {
        System.out.println("###   secondTest"); //выводим на экран результат
        Assertions.assertTrue(result > 2); //с помощью assert можно что-то сравнивать
    }


    private int getResult() { //создаем метод который будет возвращать нам это условие
        return 3;
    }
}

//это минимальный ЖЦ автотеста



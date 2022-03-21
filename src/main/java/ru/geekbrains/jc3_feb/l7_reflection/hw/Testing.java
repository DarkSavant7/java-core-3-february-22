package ru.geekbrains.jc3_feb.l7_reflection.hw;

public class Testing {
    public static  void method1() {
        System.out.println("met1");
    }

    @BeforeSuite
    public static void start() {
        System.out.println("start");
    }

    @Test(priority = 4)
    public static  void method2() {
        System.out.println("met2");
    }

    @Test(priority = 3)
    public static  void method3() {
        System.out.println("met3");
    }

    @Test(priority = 2)
    public static  void method4() {
        System.out.println("met4");
    }

    @AfterSuite
    public static void shutdown() {
        System.out.println("shutdown");
    }

    @Test(priority = 10)
    public static  void method5() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
    }

//    @AfterSuite
    public static void shutdown1() {
        System.out.println("shutdown");
    }
}

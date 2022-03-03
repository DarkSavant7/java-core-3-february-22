package ru.geekbrains.jc3_feb.l6_log_test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;



    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @AfterEach
    void dispose() {
        System.out.println("Dispose");
    }

    @BeforeAll
    static void initAll() {
        System.out.println("Init All");
    }

    @AfterAll
    static void disposeAll() {
        System.out.println("Dispose All");
    }

    @Test
    void add() {
        int a = 10;
        int b = 10;
        int result = 20;
        Assertions.assertEquals(result, calculator.add(a, b));
    }

//    @CsvSource({
//            "1,2,3",
//            "4,5,9",
//            "9,1,10"
//    })
//    @CsvFileSource(files = {"test_files/t1.csv", "test_files/t2.csv"})
    @MethodSource("generateData")
    @ParameterizedTest
    void massAddTest(int a, int b, int res) {
        Assertions.assertEquals(res, calculator.add(a, b));
    }

    private static Stream<Arguments> generateData() {
        int limit = 100_000;
        List<Arguments> args = new LinkedList<>();
        for (int i = 0; i < limit; i++) {
            int a = (int) (Math.random() * limit);
            int b = (int) (Math.random() * limit);
            int res = a + b;
            args.add(Arguments.arguments(a, b, res));
        }
        return args.stream();
    }

    @Test
    @Disabled
    void sub() {
        int a = 10;
        int b = 10;
        int result = 0;
        Assertions.assertEquals(result, calculator.sub(a, b));
        Assertions.assertEquals(14, calculator.sub(21, 7));
        Assertions.assertEquals(2, calculator.sub(4, 2));
        Assertions.assertEquals(3, calculator.sub(5, 2));
        Assertions.assertEquals(1, calculator.sub(3, 2));
    }

    @Test
    void mul() {
        Assertions.fail();
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.MINUTES)
    void div() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.div(10, 0));
    }
}
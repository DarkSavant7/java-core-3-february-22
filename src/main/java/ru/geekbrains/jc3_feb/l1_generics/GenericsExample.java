package ru.geekbrains.jc3_feb.l1_generics;

import java.util.Collections;
import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {
//        simpleBoxExample();
//        genericBoxExample();
//        numbersExample();
//        comparingFloats();
        var ints1 = List.of(1, 2, 44);
        var ints2 = List.of(231, 2, 44);
        System.out.println((Integer) getfirstObject(ints1) + (Integer) getfirstObject(ints2));
        System.out.println(getfirstObjectGen(ints1) + getfirstObjectGen(ints2));

//        Collections.copy();

    }

    private static <T> T getfirstObjectGen(List<T> list) {
        return list.get(0);
    }

    private static Object getfirstObject(List list) {
        return list.get(0);
    }

    private static void numbersExample() {
        var boxI = new BoxWithNumbers<>(2313, 123, 21312, 12321);
        var boxD = new BoxWithNumbers<>(2313.0, 123.0, 21312.0, 12321.0);
//        var boxS = new BoxWithNumbers<String>("1000.0");
        System.out.println(boxI.avg());
        System.out.println(boxD.equalsByAvg(boxI));
//        boxI.setNumbers(new Float[] {1f, 2f});
    }

    private static void comparingFloats() {
        var a = 0.7;
        var b = 0.0;
        for (int i = 0; i < 70; i++) {
            b += 0.01;
        }

        System.out.println(a == b);
        System.out.println("A = " + a + " B = " + b);
    }

    private static void genericBoxExample() {
        var boxInt1 = new GenericBox<>(100500);
        var boxInt2 = new GenericBox<>(1);
        var boxString1 = new GenericBox<>("Hello ");
        var boxString2 = new GenericBox<>("world!");
//        GenericBox<Integer> box = new GenericBox<>(21);
//        GenericBox box = new GenericBox(21); Raw use

        //many code strings
//        boxInt1.setObj("hello");
//        boxInt1.setObj(2);
//        boxString1.setObj(324);
        //many code strings
        var sum = 0;
        sum = boxInt1.getObj() + boxInt2.getObj();

        System.out.println(sum);
    }

    private static void simpleBoxExample() {
        var boxInt1 = new Box(100500);
        var boxInt2 = new Box(1);
        var boxString1 = new Box("Hello ");
        var boxString2 = new Box("world!");

        //many code strings
        boxInt1.setObj("hello");
        //many code strings
        var sum = 0;
        if (boxInt1.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (Integer) boxInt1.getObj() + (Integer) boxInt2.getObj();
        }

        System.out.println(sum);
    }
}

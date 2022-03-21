package ru.geekbrains.jc3_feb.l7_reflection.hw;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTester {
    public static void start(Class<?> c) throws InvocationTargetException, IllegalAccessException {
        Method before = null;
        Method after = null;
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method o : methods) {
            if (o.isAnnotationPresent(Test.class)) {
                int prio = o.getAnnotation(Test.class).priority();
                if (prio < 1 || prio > 10) throw new RuntimeException("Priority exception!");
                list.add(o);
            } else if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (before != null) throw new RuntimeException("BeforeSuite exception");
                before = o;
            } else if (o.isAnnotationPresent(AfterSuite.class)) {
                if (after != null) throw new RuntimeException("AfterSuite exception");
                after = o;
            }
        }

        list.sort((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());
        if (before != null) list.add(0, before);
        if (after != null) list.add(after);

        for (Method o : list) {
            o.invoke(null);
        }
    }
}

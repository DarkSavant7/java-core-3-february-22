package ru.geekbrains.jc3_feb.l7_reflection;

import ru.geekbrains.jc3_feb.l3_io.Cat;

import java.lang.reflect.*;

public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        Class<Cat> catClass = Cat.class;
//        Class catClass = Class.forName("ru.geekbrains.jc3_feb.l7_reflection.ReflectionExample$Cat");
        Cat cat = new Cat("Murzik", "red", 2);
//        ru.geekbrains.jc3_feb.l3_io.Cat  cat = new ru.geekbrains.jc3_feb.l3_io.Cat ("Murzik", "red");
        Class catClass = cat.getClass();

//        Class - классы
//        Field - поля
//        Constructor - конструкторы
//        Method - методов

//        System.out.println(catClass.getName());
//        System.out.println(catClass.getSimpleName());
//        int modifiers = catClass.getModifiers();
//
//        System.out.println(modifiers);
//        System.out.println(Modifier.toString(modifiers));
//        System.out.println(Modifier.isAbstract(modifiers));
//        System.out.println(Modifier.isPrivate(modifiers));
//        System.out.println(Modifier.isPublic(modifiers));
//        System.out.println(Modifier.isStatic(modifiers));
//        System.out.println(Modifier.isFinal(modifiers));

//        Field[] fields = catClass.getFields();
        Field[] fields = catClass.getDeclaredFields();
//
//        for (Field field : fields) {
//            System.out.println(field);
//        }

//        Field field = catClass.getDeclaredField("name");
//        Field fieldStatic = catClass.getDeclaredField("type");
//        System.out.println(field.get(cat));
//        System.out.println(fieldStatic.get(null)); //для статики
//
//        field.setAccessible(true);
//        field.set(cat, "Brb");
//        System.out.println(field.get(cat));

//        var someField = catClass.getDeclaredField("priv");
//        someField.setAccessible(true);
//        someField.set(cat, "BBBB");
//        System.out.println(someField.get(cat));

//        var someField = catClass.getDeclaredField("type");
//        someField.setAccessible(true);
//        someField.set(null, "BBBB");
//        System.out.println(someField.get(null));

//        Constructor[] constructors = catClass.getConstructors();
//        Constructor[] constructors = catClass.getDeclaredConstructors();
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
//        Constructor<Cat> constructor = catClass.getDeclaredConstructor(String.class, String.class, int.class);
//
//        Cat reflector = constructor.newInstance("REFLECTOR", "BLACK", 999);
////        catClass.newInstance();
//        System.out.println(reflector);

//        Method[] methods = catClass.getDeclaredMethods();
//        var methRun = catClass.getDeclaredMethod("run", int.class);
//        methRun.setAccessible(true);
//        methRun.invoke(cat, 999);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println(field.getAnnotation(MyAnnotation.class).data());
            }
        }
    }

    @MyAnnotation()
    public static final class Cat {
        @MyAnnotation(data = "jksnvfdjdf")
        public final String some = "AAAA";
        static final String type = "CAT";
        public final String name;
        public String color;
        private int privateField;
        final int age = 1;
        private Bowl b;

        public Cat() {
//           age = 1;
            name = "Nameless";
        }

        //        @MyAnnotation(data = "jksnvfdjdf")
        public Cat(String name, String color, int age) {
            this.name = name;
            this.color = color;
//            this.age = age;
        }

        void voice() {
            System.out.println(name + " mew");
        }

        private void run(int distance) {
            System.out.println(name + " running for " + distance);
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class Bowl {
        int food;
    }
}

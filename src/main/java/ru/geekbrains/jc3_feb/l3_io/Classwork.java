package ru.geekbrains.jc3_feb.l3_io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Classwork {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        filesExample();
//        simpleFileFileWriteExample();
//        simpleReadExample();
//        bufferingExample();
//        readExample();
//        sequenseExample();
//        rafExample();

//        serializationExample();
//        externalizableExample();

        try (var fis = new FileInputStream("test/1/2.txt")) {
            try (var zos = new ZipOutputStream(new FileOutputStream("test/zipped.zip"))) {
                var buf = new byte[512];
                zos.putNextEntry(new ZipEntry("test/1/2.txt"));
                int x;
                while ((x = fis.read(buf)) > 0) {
                    zos.write(buf, 0, x);
                }

            }
        }

    }

    private static void externalizableExample() throws IOException, ClassNotFoundException {
        var cat = new CatEx("Barsik", "purple");
//        try (var oos = new ObjectOutputStream(new FileOutputStream("test/barsik"))) {
//            oos.writeObject(cat);
//        }

        try (var ois = new ObjectInputStream(new FileInputStream("test/barsik"))) {
            var deserializedCat = (CatEx) ois.readObject();
            System.out.println(cat);
            System.out.println(deserializedCat);
            System.out.println(cat == deserializedCat);
            System.out.println(cat.equals(deserializedCat));
        }
    }

    private static void serializationExample() throws IOException, ClassNotFoundException {
        Cat cat = new Cat("Murzik", "purple");
//        try (var oos = new ObjectOutputStream(new FileOutputStream("test/murzik"))) {
//            oos.writeObject(cat);
//        }

        try (var ois = new ObjectInputStream(new FileInputStream("test/murzik"))) {
            var deserializedCat = (Cat) ois.readObject();
            System.out.println(cat);
            System.out.println(deserializedCat);
            System.out.println(cat == deserializedCat);
            System.out.println(cat.equals(deserializedCat));
        }
    }

    private static void rafExample() throws IOException {
        try (var raf = new RandomAccessFile("test/1/1.txt", "r")) {
            raf.seek(52);
            int x;
            while ((x = raf.read()) > -1) {
                System.out.println((char) x);
            }
        }
    }

    private static void sequenseExample() throws IOException {
        var streams = new ArrayList<InputStream>();
        streams.add(new FileInputStream("test/1/1.txt"));
        streams.add(new FileInputStream("test/1/2.txt"));
        streams.add(new FileInputStream("test/1/3.txt"));
        var sis = new SequenceInputStream(Collections.enumeration(streams));

        int x;
        while ((x = sis.read()) != -1) {
            System.out.print((char) x);
        }
    }

    private static void readExample() throws IOException {
        //        try (var br = new BufferedReader(new FileReader("test/ex3.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
        try (var br = new BufferedReader(new FileReader("test/ex3.txt"))) {
//           var list = List.of("jkfdv");
//           list.stream();


            br.lines()
                    .distinct()
                    .filter(s -> s.contains("s"))
//                   .map(s -> s.length())
                    .flatMap(s -> Stream.of(s.getBytes(StandardCharsets.UTF_8)))
                    .forEach(System.out::println);


        }
    }

    private static void bufferingExample() throws IOException {
        //        var startTime = System.currentTimeMillis();
//        try (var fis = new FileInputStream("test/ex3.txt")) {
//            int x;
//            while ((x = fis.read()) > -1) {
//            }
//        }
//        System.out.println(System.currentTimeMillis() - startTime);

        var startTime = System.currentTimeMillis();
//        var buf = new byte[8];
//        var buf = new byte[512];
//        try (var fis = new FileInputStream("test/ex3.txt")) {
//            int x;
//            while ((x = fis.read(buf)) > -1) {
//            }
//        }
//        System.out.println(System.currentTimeMillis() - startTime);

//        var startTime = System.currentTimeMillis();
        try (var bis = new BufferedInputStream(new FileInputStream("test/ex3.txt"))) {
            int x;
            while ((x = bis.read()) > -1) {
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void simpleReadExample() throws IOException {
        try (var fis = new FileInputStream("test/ex2.txt")) {
            int x;
            while ((x = fis.read()) > -1) {
                System.out.print((char) x);
            }
        }
    }

    private static void simpleFileFileWriteExample() throws IOException {
        var s = "Hello world!";
        var file = new File("test/ex1.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (var fos = new FileOutputStream("test/ex1.txt")) {
            fos.write(s.getBytes(StandardCharsets.UTF_8));
//            for (int i = 0; i < 200; i++) {
//                fos.write(i);
//            }
        }
    }

    private static void filesExample() {
        //        File file = new File("test");
//
//        System.out.println(file.exists());
//        System.out.println(file.isFile());
//        System.out.println(file.isDirectory());
//
        var file2 = new File("test/1.txt");
//        if (!file2.exists()) {
//            file2.createNewFile();
//        }
//        System.out.println(file2.delete());
//        var file3 = new File("test/1");
//        System.out.println(file3.mkdir());
//
//        var file4 = new File("test/1/2/3/4/5/6/7/8/9/10");
//        System.out.println(file4.mkdirs());
//
//        var file5 = new File("test/1/5423");
//        file5.renameTo(new File("test/1/2/3/4/5/6/7/8/9/10/5423"));

        var file6 = new File("test");
//        var fileNames = file6.list();
//        var fileNames = file6.list(((dir, name) -> name.endsWith("1")));
//        for (String fileName : fileNames) {
//            System.out.println(fileName);
//        }

//        var files = file6.listFiles();
//        for (File file : files) {
//            System.out.println(file.getName());
//        }
//
//        System.out.println(file2.getParent());
//        recursiveFileWalkAndPrint(file6);
        recursiveFileWalkAndPrint(new File("src"));
    }

    private static void recursiveFileWalkAndPrint(File file) {
        if (file.isFile()) {
            System.out.println("File -->> " + file.getPath());
        } else {
            System.out.println("Catalog -->> " + file.getPath());
            var files = file.listFiles();
            for (File innerFile : files) {
                recursiveFileWalkAndPrint(innerFile);
            }
        }
    }
}

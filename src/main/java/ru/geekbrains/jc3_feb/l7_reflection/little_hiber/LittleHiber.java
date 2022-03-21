package ru.geekbrains.jc3_feb.l7_reflection.little_hiber;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LittleHiber {
    private static Connection connection;

    public static <T> void insertObject(T o) {
        if (!o.getClass().isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Class not annotated with @Table");
        }

        Field[] fields = o.getClass().getDeclaredFields();

        try {
            connect();
            // insert into cats (name, color) values ('ddd', 'dssdf');;

            var sb = new StringBuilder("insert into ");
            sb.append(o.getClass().getAnnotation(Table.class).name());
            sb.append(" (");
            int questionsCount = 0;
            var values = new LinkedList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    if (field.getAnnotation(Id.class).autoincrement()) {
                        continue;
                    }
                }

                if (field.isAnnotationPresent(Column.class)) {
                    var colName = field.getAnnotation(Column.class).name().isBlank() ?
                            field.getName() : field.getAnnotation(Column.class).name();
                    sb.append(colName);
                    sb.append(", ");
                    questionsCount++;
                    values.add(field.get(o));
                }
            }

            sb.setLength(sb.length() - 2);
            sb.append(") values (");

            for (int i = 0; i < questionsCount; i++) {
                sb.append(i + 1 == questionsCount ? "?);" : "?, ");
            }

            try (var stmt = connection.prepareStatement(sb.toString())) {
                var num = 1;
                for (Object value : values) {
                    stmt.setObject(num++, value);
                }
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static <T> void createTable(Class<T> c) {
        var typeMap = getTypeMap();

        if (!c.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Class not annotated with @Table");
        }

        Field[] fields = c.getDeclaredFields();
        var hasId = false;

        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                if (hasId) {
                    throw new RuntimeException("Only one primary key needed");
                }
                hasId = true;
            }
        }

        try {
            connect();
            try (var smt = connection.createStatement()) {
                // create table cats (id integer primary key autoincrement, name text, color text);

                var sb = new StringBuilder("create table ");
                sb.append(c.getAnnotation(Table.class).name());
                sb.append(" (");
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class)) {
                        var colName = field.getAnnotation(Column.class).name().isBlank() ?
                                field.getName() : field.getAnnotation(Column.class).name();
                        sb.append(colName);
                        sb.append(" ");
                        sb.append(typeMap.get(field.getType()));

                        if (field.isAnnotationPresent(Id.class)) {
                            sb.append(" primary key ");
                            if (field.getAnnotation(Id.class).autoincrement()) {
                                sb.append(" autoincrement ");
                            }
                        }
                        sb.append(", ");
                    }
                }
                sb.setLength(sb.length() - 2);
                sb.append(");");
                smt.execute(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static Map<Class, String> getTypeMap() {
        Map<Class, String> typeMap = new HashMap<>();
        typeMap.put(int.class, "integer");
        typeMap.put(String.class, "text");
        return typeMap;
    }

    private static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db/reflect.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

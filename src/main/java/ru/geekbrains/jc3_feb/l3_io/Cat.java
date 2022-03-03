package ru.geekbrains.jc3_feb.l3_io;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class Cat extends Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    private transient String name;

    @Setter
    private String color;

    public Cat() {
        super("Cat");
        System.out.println("Cat born");
    }

    public Cat(String name, String color) {
        this();
        this.name = name;
        this.color = color;
    }

}

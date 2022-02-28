package ru.geekbrains.jc3_feb.l5_multithreading.hw;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

package ru.javawebinar.basejava.model;

import java.util.ArrayList;

public class BulletedLineSection extends AbstractSection {

    private final ArrayList<String> list;

    public BulletedLineSection(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

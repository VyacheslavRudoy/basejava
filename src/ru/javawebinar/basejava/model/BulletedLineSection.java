package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class BulletedLineSection extends AbstractSection {

    private final List<String> list;

    public BulletedLineSection(List<String> list) {
        Objects.requireNonNull(list, "list must not be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulletedLineSection that = (BulletedLineSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}

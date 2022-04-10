package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateResume(Resume r, Object index) {
        storage.set((int) index, r);
    }

    protected void saveResume(Resume r, Object index) {
        storage.add(r);
    }

    protected Resume getResume(Object index) {
        return storage.get((int) index);
    }

    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    public int size() {
        return storage.size();
    }

    protected Object searchKey(String uuid) {
        Resume searchKey = new Resume((String) uuid);
        return storage.indexOf(searchKey);
    }

    protected boolean isExist(Object uuid) {
        return (int) searchKey((String) uuid) != -1;
    }
}


package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateResume(Resume r, int key) {
        storage.set(key, r);
    }

    protected void saveResume(Resume r, int key) {
        storage.add(r);
    }

    protected Resume getResume(String uuid, int key) {
        return storage.get(key);
    }

    protected void deleteResume(String uuid, int key) {
        storage.remove(key);
    }

    public Resume[] getAll() {
        List<Resume> copy = new ArrayList<>(storage);
        return copy.toArray(new Resume[storage.size()]);
    }

    public int size() {
        return storage.size();
    }

    protected int searchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }
}


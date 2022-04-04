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

    public Resume[] getAll() {
        List<Resume> copy = new ArrayList<>(storage);
        return copy.toArray(new Resume[storage.size()]);
    }

    public int size() {
        return storage.size();
    }

    protected Object searchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    protected boolean availability(String uuid) {
        if ((int) searchKey(uuid) != -1) {
            return true;
        }
        return false;
    }
}


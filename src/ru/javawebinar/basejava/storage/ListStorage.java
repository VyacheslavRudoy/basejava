package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateStorage(Resume r, int index) {
        storage.set(index, r);
    }

    protected void saveStorage(Resume r, int index) {
        storage.add(r);
    }

    protected Resume getStorage(String uuid, int index) {
        return storage.get(index);
    }

    protected void deleteStorage(String uuid, int index) {
        storage.remove(index);
    }

    public Resume[] getAll() {
        List<Resume> copy = new ArrayList<>(storage);
        return copy.toArray(new Resume[storage.size()]);
    }

    public int size() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        Resume searchIndex = new Resume(uuid);
        return storage.indexOf(searchIndex);
    }
}


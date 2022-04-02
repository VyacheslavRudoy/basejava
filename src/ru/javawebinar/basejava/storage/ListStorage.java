package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateStorage(Resume r) {
        storage.set(getIndex(r.getUuid()), r);
    }

    protected void saveStorage(Resume r) {
        storage.add(r);
    }

    protected Resume getStorage(String uuid) {
        return storage.get(getIndex(uuid));
    }

    protected void deleteStorage(String uuid) {
        storage.remove(getIndex(uuid));
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

    protected boolean availability(String uuid) {
        if (getIndex(uuid) > -1) {
            return true;
        }
        return false;
    }
}


package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateResume(Resume r) {
        storage.set(searchKey(r.getUuid()), r);
    }

    protected void saveResume(Resume r) {
        storage.add(r);
    }

    protected Resume getResume(String uuid) {
        return storage.get(searchKey(uuid));
    }

    protected void deleteResume(String uuid) {
        storage.remove(searchKey(uuid));
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


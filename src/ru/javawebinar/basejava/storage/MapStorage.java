package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new TreeMap<>();

    protected void updateResume(Resume r, Object key) {
        storage.put((String) key, r);
    }

    protected void saveResume(Resume r, Object key) {
        storage.put((String) key, r);
    }

    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    protected void deleteResume(Object key) {
        storage.remove(key);
    }

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    public int size() {
        return storage.size();
    }

    protected Object searchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }
}


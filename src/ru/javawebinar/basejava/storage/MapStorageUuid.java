package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorageUuid extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

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

    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }

    protected Object searchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }
}


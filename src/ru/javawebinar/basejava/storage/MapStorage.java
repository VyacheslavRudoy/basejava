package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new TreeMap<>();

    protected void updateStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    protected void saveStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    protected Resume getStorage(String uuid) {
        return storage.get(uuid);
    }

    protected void deleteStorage(String uuid) {
        storage.remove(uuid);
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

    protected boolean availability(String uuid) {
        if (storage.containsKey(uuid)) {
            return true;
        }
        return false;
    }
}

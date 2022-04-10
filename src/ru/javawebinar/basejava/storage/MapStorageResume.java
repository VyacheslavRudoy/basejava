package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void deleteResume(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Object searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}

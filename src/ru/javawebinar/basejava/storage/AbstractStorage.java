package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateResume(Resume r, Object key);

    protected abstract void saveResume(Resume r, Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object searchKey(String uuid);

    protected abstract boolean isExist(String uuid);

    public void update(Resume r) {
        Object key = existKey(r.getUuid());
        updateResume(r, key);
    }

    public void save(Resume r) {
        Object key = notExist(r.getUuid());
        saveResume(r, key);
    }

    public Resume get(String uuid) {
        Object key = existKey(uuid);
        return getResume(key);
    }

    public void delete(String uuid) {
        Object key = existKey(uuid);
        deleteResume(key);
    }

    private Object existKey(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey(uuid);
    }

    private Object notExist(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey(uuid);
    }
}
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

    protected abstract boolean availability(String uuid);

    public void update(Resume r) {
        Object key = searchKey(r.getUuid());
        notExist(r.getUuid());
        updateResume(r, key);
    }

    public void save(Resume r) {
        Object key = searchKey(r.getUuid());
        existStorage(r.getUuid());
        saveResume(r, key);
    }

    public Resume get(String uuid) {
        Object key = searchKey(uuid);
        notExist(uuid);
        return getResume(key);
    }

    public void delete(String uuid) {
        Object key = searchKey(uuid);
        notExist(uuid);
        deleteResume(key);
    }

    protected void notExist(String uuid) {
        if (!availability(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected void existStorage(String uuid) {
        if (availability(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }
}
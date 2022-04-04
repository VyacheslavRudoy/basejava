package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateResume(Resume r, int key);

    protected abstract void saveResume(Resume r, int key);

    protected abstract Resume getResume(String uuid, int key);

    protected abstract void deleteResume(String uuid, int key);

    public void update(Resume r) {
        int key = searchKey(r.getUuid());
        notExist(r.getUuid(), key);
        updateResume(r, key);
    }

    public void save(Resume r) {
        int key = searchKey(r.getUuid());
        existStorage(r.getUuid(), key);
        saveResume(r, key);
    }

    public Resume get(String uuid) {
        int key = searchKey(uuid);
        notExist(uuid, key);
        return getResume(uuid, key);
    }

    public void delete(String uuid) {
        int key = searchKey(uuid);
        notExist(uuid, key);
        deleteResume(uuid, key);
    }

    protected void notExist(String uuid, int key) {
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected void existStorage(String uuid, int key) {
        if (key > -1) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract int searchKey(String uuid);
}


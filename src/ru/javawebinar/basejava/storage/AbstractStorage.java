package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    public void update(Resume r) {
        notExist(r.getUuid());
        updateResume(r);
    }

    public void save(Resume r) {
        existStorage(r.getUuid());
        saveResume(r);
    }

    public Resume get(String uuid) {
        notExist(uuid);
        return getResume(uuid);
    }

    public void delete(String uuid) {
        notExist(uuid);
        deleteResume(uuid);
    }

    protected void notExist(String uuid) {
        if (searchKey(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected void existStorage(String uuid) {
        if (searchKey(uuid) > -1) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract int searchKey(String uuid);
}


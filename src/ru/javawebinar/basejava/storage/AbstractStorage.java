package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateStorage(Resume r);

    protected abstract void saveStorage(Resume r);

    protected abstract Resume getStorage(String uuid);

    protected abstract void deleteStorage(String uuid);

    public void update(Resume r) {
        if (getIndex(r.getUuid()) < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateStorage(r);
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) > -1) {
            throw new ExistStorageException(r.getUuid());
        }
        saveStorage(r);
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getStorage(uuid);
    }

    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteStorage(uuid);
    }

    protected abstract int getIndex(String uuid);
}


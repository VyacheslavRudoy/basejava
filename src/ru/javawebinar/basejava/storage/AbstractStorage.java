package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateStorage(Resume r, int index);

    protected abstract void saveStorage(Resume r, int index);

    protected abstract Resume getStorage(String uuid, int index);

    protected abstract void deleteStorage(String uuid, int index);

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateStorage(r, index);
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        }
        saveStorage(r, index);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getStorage(uuid, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteStorage(uuid, index);
    }

    protected abstract int getIndex(String uuid);
}


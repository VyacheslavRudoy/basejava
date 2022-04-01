package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateStorage(Resume r, int index);

    protected abstract void clearStorage();

    protected abstract void saveStorage(int index, Resume r);

    protected abstract Resume getStorage(int index);

    protected abstract void deleteStorage(int index);

    protected abstract Resume[] getAllStorage();

    protected abstract int sizeStorage();

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateStorage(r, index);
        }
    }

    public void clear() {
        clearStorage();
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        saveStorage(index, r);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getStorage(index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteStorage(index);
        }
    }

    public Resume[] getAll() {
        return getAllStorage();
    }

    public int size() {
        return sizeStorage();
    }

    protected abstract int getIndex(String uuid);
}


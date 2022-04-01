package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void saveStorage(int index, Resume r) {
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveToArray(r, index);
            size++;
        }
    }

    protected abstract void saveToArray(Resume r, int index);


    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateStorage(Resume r, int index) {
        storage[index] = r;
    }

    protected Resume[] getAllStorage() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int sizeStorage() {
        return size;
    }

    protected Resume getStorage(int index) {
        return storage[index];
    }

    protected void deleteStorage(int index) {
        fillDeletedElement(index);
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
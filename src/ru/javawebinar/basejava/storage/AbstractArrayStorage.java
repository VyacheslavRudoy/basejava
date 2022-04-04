package ru.javawebinar.basejava.storage;

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

    protected void saveResume(Resume r, int key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveToArray(r, key);
        size++;
    }

    protected abstract void saveToArray(Resume r, int key);


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(Resume r, int key) {
        storage[key] = r;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected Resume getResume(String uuid, int key) {
        return storage[key];
    }

    protected void deleteResume(String uuid, int key) {
        fillDeletedElement(key);
        size--;
    }

    protected abstract void fillDeletedElement(int key);

    protected abstract int searchKey(String uuid);
}
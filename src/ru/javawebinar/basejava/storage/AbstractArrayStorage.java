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

    protected void saveStorage(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveToArray(r, getIndex(r.getUuid()));
        size++;
    }

    protected abstract void saveToArray(Resume r, int index);


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateStorage(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected Resume getStorage(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected void deleteStorage(String uuid) {
        fillDeletedElement(getIndex(uuid));
        size--;
    }

    protected boolean availability(String uuid) {
        if (getIndex(uuid) > -1) {
            return true;
        }
        return false;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
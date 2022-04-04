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

    protected void saveResume(Resume r, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveToArray(r, (int) index);
        size++;
    }

    protected abstract void saveToArray(Resume r, int index);


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(Resume r, Object index) {
        storage[(int) index] = r;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    protected void deleteResume(Object index) {
        fillDeletedElement((int) index);
        size--;
    }

    protected boolean availability(String uuid) {
        if ((int) searchKey(uuid) > -1) {
            return true;
        }
        return false;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract Object searchKey(String uuid);
}
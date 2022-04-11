package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

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

    public List<Resume> getAll() {
        Resume[] copyArray = Arrays.copyOf(storage, size);
        return Arrays.asList(copyArray);
    }

    public int size() {
        return size;
    }

    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    protected void deleteResume(Object index) {
        fillDeletedElement((int) index);
        storage[size - 1] = null;
        size--;
    }

    protected boolean isExist(Object index) {
        return (int) index > -1;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract Object searchKey(String uuid);
}
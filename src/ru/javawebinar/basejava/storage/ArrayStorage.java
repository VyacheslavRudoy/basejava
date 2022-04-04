package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveToArray(Resume r, int key) {
        storage[size] = r;
    }

    @Override
    protected void fillDeletedElement(int key) {
        storage[key] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected int searchKey(String uuid) {
        int key = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                key = i;
            }
        }
        return key;
    }
}
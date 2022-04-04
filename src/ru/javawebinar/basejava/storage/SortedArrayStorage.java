package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveToArray(Resume r, int key) {
        int newIndex = -key - 1;
        int remainingElements = size - newIndex;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, remainingElements);
        storage[newIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int key) {
        int remainingElements = size - (key + 1);
        System.arraycopy(storage, key + 1, storage, key, remainingElements);
    }

    @Override
    protected int searchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
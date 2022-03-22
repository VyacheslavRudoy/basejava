package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveToArray(Resume r, int index) {
        int newIndex = -index - 1;
        int remainingElements = size - newIndex;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, remainingElements);
        storage[newIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int remainingElements = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, remainingElements);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
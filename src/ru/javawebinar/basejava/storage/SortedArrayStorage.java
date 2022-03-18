package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveRealization(Resume r, int index) {
        int newIndex = -index - 1;
        int remainingElements = size - newIndex;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, remainingElements);
        storage[newIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
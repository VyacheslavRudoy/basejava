package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            System.out.println("Резюме " + r.getUuid() + " найдено в списке имеющихся");
        } else if (index < 0 && checkCapacity()) {
            int newIndex = -index - 1;
            int remainingElements = size - newIndex;
            System.arraycopy(storage, newIndex, storage, newIndex + 1, remainingElements);
            storage[newIndex] = r;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
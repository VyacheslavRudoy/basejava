package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int index = Arrays.binarySearch(storage, 0, size, searchKey);
        if (index > -1) {
            System.out.println("Резюме " + uuid + " найдено в списке имеющихся");
        } else {
            System.out.println("Резюме " + uuid + " ранее не добавлялось");
        }
        return index;
    }
}
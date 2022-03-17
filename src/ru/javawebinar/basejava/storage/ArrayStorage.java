package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            System.out.println("Резюме " + r.getUuid() + " найдено в списке имеющихся");
        } else if (index < 0 && checkCapacity()) {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
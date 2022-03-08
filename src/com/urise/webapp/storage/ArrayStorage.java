package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (findIndex(r.getUuid()) != -1 && checkCapacity()) {
            storage[size] = r;
            size++;
        }
    }

    private boolean checkCapacity() {
        if (size == storage.length) {
            System.out.println("Место хранения переполнено");
            return true;
        }
        return false;
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) != -1) {
            return storage[findIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (findIndex(uuid) != -1) {
            storage[findIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] copyStorage = new Resume[storage.length];
        System.arraycopy(storage, 0, copyStorage, 0, storage.length);
        return copyStorage;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        if (findIndex(resume.getUuid()) != -1) {
            storage[findIndex(resume.getUuid())] = resume;
        }
    }

    private int findIndex(String uuid) {
        boolean available = false;
        int index = 0;
        for ( index = 0; index < size; index++) {
            if (storage[index].getUuid() == uuid) {
                System.out.println("Резюме " + uuid + " найдено в списке имеющихся");
                available = true;
                return index;
            }
        }
        if (!available) {
            System.out.println("Резюме " + uuid + " ранее не добавлялось");
        }
        return -1;
    }
}
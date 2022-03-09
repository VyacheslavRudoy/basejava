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
        int index = findIndex(r.getUuid());
        if (index != -1 && checkCapacity()) {
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
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
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
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                System.out.println("Резюме " + uuid + " найдено в списке имеющихся");
                return i;
            }
        }
        System.out.println("Резюме " + uuid + " ранее не добавлялось");
        return -1;
    }
}
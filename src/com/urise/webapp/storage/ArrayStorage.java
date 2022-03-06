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
        if (!availabilityResume(r.getUuid()) && notOverflow()) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (availabilityResume(uuid)) {
            return storage[indexSearch(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (availabilityResume(uuid)) {
            storage[indexSearch(uuid)] = storage[size - 1];
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
        if (availabilityResume(resume.getUuid())) {
            storage[indexSearch(resume.getUuid())] = resume;
        }
    }

    private boolean availabilityResume(String uuid) {
        boolean available = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                System.out.println("Резюме " + uuid + " найдено в списке имеющихся");
                available = true;
            }
        }
        if (available == false) {
            System.out.println("Резюме " + uuid + " ранее не добавлялось");
        }
        return available;
    }

    private boolean notOverflow() {
        if (size == storage.length) {
            System.out.println("Место хранения переполнено");
            return true;
        } else {
            return false;
        }
    }

    private int indexSearch(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                index = i;
            }
        }
        return index;
    }
}
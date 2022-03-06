package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (absenceResume(r.uuid) && notOverflow()) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        if (availabilityResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid == uuid) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (availabilityResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid == uuid) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] copyStorage = new Resume[storage.length];
        System.arraycopy(storage, 0, copyStorage, 0, storage.length);
        return copyStorage;
    }

    int size() {
        return size;
    }

    void update(Resume resume) {
        if (availabilityResume(resume.uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid == resume.uuid) {
                    storage[i] = resume;
                }
            }
        }
    }

    boolean availabilityResume(String uuid) {
        boolean available = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                available = true;
            }
        }
        if (available == false) {
            System.out.println("Резюме " + uuid + " не найдено");
        }
        return available;
    }

    boolean absenceResume(String uuid) {
        boolean absence = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                absence = false;
                System.out.println("Резюме " + uuid + " уже имеется");
            }
        }
        return absence;
    }

    boolean notOverflow() {
        if (size == storage.length) {
            System.out.println("Место хранения переполнено");
            return true;
        } else {
            return false;
        }
    }
}
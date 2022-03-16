package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.out.println("Резюме " + r.getUuid() + " найдено в списке имеющихся");
        }
        else if (index < 0 && checkCapacity()) {
            storage[size] = r;
            size++;
        }
    }

    private boolean checkCapacity() {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Место хранения переполнено");
            return false;
        }
        return true;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + resume.getUuid() + " ранее не добавлялось");
        } else {
            storage[index] = resume;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " ранее не добавлялось");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        int remainingElements = size - (index + 1);
        System.arraycopy(storage, 0, storage, index, remainingElements);
        System.arraycopy(storage, index + 1, storage, index, remainingElements);
        size --;
    }

    protected abstract int getIndex(String uuid);
}
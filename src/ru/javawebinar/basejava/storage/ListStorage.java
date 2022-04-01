package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    protected void clearStorage() {
        storage.removeAll(storage);
    }

    protected void updateStorage(Resume r, int index) {
        storage.set(index, r);
    }

    protected void saveStorage(int index, Resume r) {
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r);
        }
    }

    protected Resume getStorage(int index) {
        return storage.get(index);
    }

    protected void deleteStorage(int index) {
        storage.remove(index);
    }

    protected Resume[] getAllStorage() {
        List<Resume> copy = new ArrayList<>(storage);
        return copy.toArray(new Resume[storage.size()]);
    }

    protected int sizeStorage() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        int index = -1;
        for (Resume r : storage) {
            if (r.getUuid() == uuid) {
                index = storage.indexOf(r);
            }
        }
        return index;
    }
}


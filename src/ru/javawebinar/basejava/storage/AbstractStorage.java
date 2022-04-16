package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract void updateResume(Resume r, SK key);

    protected abstract void saveResume(Resume r, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract SK searchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getAll();

    public void update(Resume r) {
        SK key = receiveKeyIfExist(r.getUuid());
        updateResume(r, key);
    }

    public void save(Resume r) {
        SK key = receiveKeyIfNotExist(r.getUuid());
        saveResume(r, key);
    }

    public Resume get(String uuid) {
        SK key = receiveKeyIfExist(uuid);
        return getResume(key);
    }

    public void delete(String uuid) {
        SK key = receiveKeyIfExist(uuid);
        deleteResume(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> sortedList = getAll();
        sortedList.sort(Resume::compareTo);
        return sortedList;
    }

    private SK receiveKeyIfExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK receiveKeyIfNotExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
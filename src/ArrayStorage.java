/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (Resume resume : storage) {
            resume = null;
        }
    }

    void save(Resume r) {
        for (Resume resume : storage) {
            if (resume.uuid == null) {
                resume = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : getAll()) {
            if (resume.uuid == uuid) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid == uuid) {
                resume = null;
                break;
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
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }
}
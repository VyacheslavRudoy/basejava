package ru.javawebinar.basejava.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Резюме " + uuid + " найдено в списке имеющихся");
                return i;
            }
        }
        System.out.println("Резюме " + uuid + " ранее не добавлялось");
        return -1;
    }
}
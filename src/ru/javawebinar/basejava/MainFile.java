package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainFile.getAllFiles(dir);
    }


    public static void getAllFiles(File directory) {
        String count = " -";
        if (!directory.exists()) {
            System.out.println("Такого объекта файловой системы не существует");
        } else if (directory.isFile()) {
            System.out.println(count + directory.getName());

        } else {
            System.out.println(directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File dirOrFile : files) {
                    getAllFiles(dirOrFile);
                }
            }
        }
    }
}



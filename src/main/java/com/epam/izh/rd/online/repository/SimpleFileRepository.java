package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        //String absoluteFilePath = "C:/java-data-handling-template/src/main/resources/"+path;
            long filesThisDir=0;
            File f = new File(path);
            File[] files = f.listFiles();
            for (File item : files) {
                if (item.isFile()) {
                    filesThisDir++;
                }
                if (item.isDirectory()) {
                    filesThisDir += countFilesInDirectory(item.getAbsolutePath());
                }
            }
            return filesThisDir;

    }


    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
       File file =  new File (path);
       File[] files = file.listFiles();
       long DirsThisDir = 1;

       for(File item: files)
       {
           if (item.isDirectory()) {
               DirsThisDir += countDirsInDirectory(item.getAbsolutePath());
           }
       }
       return DirsThisDir;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) throws IOException {
        File fileFrom = new File(from);
        File fileTo = new File(to);
        Path pathFrom = Paths.get(fileFrom.getParent());
        Path pathTo = Paths.get(fileTo.getParent());

        if (!Files.exists(pathTo))  {
            Files.createDirectories(pathTo);
        }
        for(File item: pathFrom.toFile().listFiles()){
            if(item.getAbsolutePath().endsWith(".txt")) {
                Files.copy(fileFrom.toPath(), fileTo.toPath());
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) throws IOException {
        File file = new File(path+"/"+name);
        Path dir = Paths.get(file.getParent());
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
        if (!file.exists()) {
            file.createNewFile();
            return true;
        }
        return file.exists();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) throws IOException {
        String absoluteFilePath = "C:/java-data-handling-template/src/main/resources/" + fileName;
        String content = new String();
        FileReader fileReader = new FileReader(absoluteFilePath);
        BufferedReader buffReader = new BufferedReader(fileReader);
        try {
            while (buffReader.ready()) {
                content = buffReader.readLine();
            }
        }catch (IOException e){System.out.println(e.getMessage());}
        buffReader.close();
        return content;
    }
}

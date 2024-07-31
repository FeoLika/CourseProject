package ru.feolika.Tasks.w1d4;

public class Starter {
    public static void main(String[] args) {
        String[] words = WordGenerator.generateThousandWords();
        FileBuilder fileBuilder = new FileBuilder();
        String path = "src/ru/feolika/Tasks/w1d4/";
        try {
            fileBuilder.getFiles(path, 3, 10, words, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

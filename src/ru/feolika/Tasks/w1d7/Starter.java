package ru.feolika.Tasks.w1d7;

public class Starter {
    public static void main(String[] args) {
        String[] resources = {
                "https://www.gutenberg.org/files/2701/2701-0.txt",
                "https://www.gutenberg.org/files/11/11-0.txt",
                "https://www.gutenberg.org/files/84/84-0.txt",
                "https://www.gutenberg.org/files/84/84-0.txt",
                "https://www.gutenberg.org/files/1342/1342-0.txt",
                "https://www.gutenberg.org/files/1342/1342-0.txt",
                "https://www.gutenberg.org/files/1661/1661-0.txt"
        };
        String[] words = {"sister", "god"};
        String res = "src/ru/feolika/Tasks/w1d7/result.txt";

        ResourceManager resourceReader = new ResourceManager(resources, words, res);
        try {
            resourceReader.getOccurrences(resources, words, res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

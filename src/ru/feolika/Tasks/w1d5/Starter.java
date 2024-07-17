package ru.feolika.Tasks.w1d5;

public class Starter {
    public static void main(String[] args) {
        String path = "src/ru/feolika/Tasks/w1d5/";
        Serializer serializer = new Serializer();
        serializer.serialize(new Person(), path + "PersonSerialize.txt");
        Person person = (Person) serializer.deSerialize(path + "PersonSerialize.txt");
        System.out.println(person);
    }
}

package ru.feolika.Tasks.w2d1;

public class StartClient {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer("localhost", 5000);
    }
}

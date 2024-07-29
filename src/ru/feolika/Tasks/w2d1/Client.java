package ru.feolika.Tasks.w2d1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс для общения с сервером
 */
public class Client {
    /**
     * Устанавливает соединение с сервером по указанному хосту и порту, и
     * отправляет и получает сообщения от сервера. Клиент предлагает пользователю
     * ввести свое имя, и затем входит в цикл, где читает сообщения от сервера
     * и с ввода пользователя. Клиент отправляет каждое сообщение пользователя
     * серверу, и выводит любые сообщения, полученные от сервера. Если пользователь
     * вводит "quit", клиент закрывает соединение и возвращается.
     *
     * @param host имя хоста или IP-адрес сервера
     * @param port порт сервера
     */
    public void connectToServer(String host, int port) {
        try {
            Socket serverSocket = new Socket(host, port);
            PrintWriter socketWriter = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your name: ");
            while (!serverSocket.isClosed()) {
                if (bufferedReader.ready()) {
                    String message = bufferedReader.readLine();
                    socketWriter.println(message);
                    if (message.equals("quit")) {
                        System.out.println("Disconnecting...");
                        bufferedReader.close();
                        socketReader.close();
                        socketWriter.close();
                        serverSocket.close();
                        return;
                    }
                }
                if (socketReader.ready()) {
                    String message = socketReader.readLine();
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

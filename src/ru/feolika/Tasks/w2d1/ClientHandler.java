package ru.feolika.Tasks.w2d1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс обработчика клиента
 */
public class ClientHandler implements Runnable {
    /**
     * Сокет клиента
     */
    private Socket clientSocket;
    /**
     * Поток для записи в сокет
     */
    private PrintWriter socketWriter;
    /**
     * Имя клиента
     */
    private String name;

    /**
     * Конструктор
     *
     * @param clientSocket сокет клиента
     */
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            socketWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Возвращает имя клиента
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Запускает обработчик клиента в отдельном потоке. Этот метод читает строки из входного потока клиента
     * и обрабатывает их соответствующим образом. Если строка равна "quit", сокет клиента закрывается, клиент удаляется
     * из сервера, и сообщение выводится в консоль.
     */
    @Override
    public void run() {
        try {
            Scanner socketReader = new Scanner(clientSocket.getInputStream());
            boolean hasName = false;
            while (true) {
                String line = socketReader.nextLine();
                if (line.equals("quit")) {
                    clientSocket.close();
                    Server.leaveClient(this);
                    System.out.println(name + " left the chat.");
                    Server.notifyClients(name + " left the chat.");
                    break;
                }
                if (!hasName) {
                    name = line;
                    System.out.println(name + " joined the chat");
                    Server.notifyClients(name + " joined the chat");
                    hasName = true;
                } else {
                    System.out.println(name + ": " + line);
                    Server.notifyClients(name + ": " + line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отправляет сообщение всем подключённым клиентам.
     *
     * @param message сообщение для отправки
     */
    public void sendMessage(String message) {
        try {
            socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            socketWriter.println(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

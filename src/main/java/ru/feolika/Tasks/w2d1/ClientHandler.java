package ru.feolika.Tasks.w2d1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс обработчика клиента
 */
public class ClientHandler implements Runnable {

    /**
     * Логгер
     */
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
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
            logger.error(e.getMessage());
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
                    logger.info(name + " left the chat.");
                    Server.notifyClients(name + " left the chat.");
                    break;
                }
                if (!hasName) {
                    name = line;
                    logger.info(name + " joined the chat");
                    Server.notifyClients(name + " joined the chat");
                    hasName = true;
                } else {
                    logger.info(name + ": " + line);
                    Server.notifyClients(name + ": " + line);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
    }
}

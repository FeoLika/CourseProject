package ru.feolika.Tasks.w2d1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервер
 */
public class Server {
    /**
     * Защита от многопоточного доступа
     */
    private static final Object LOCK = new Object();
    /**
     * Список сокетов клиентов
     */
    private static List<ClientHandler> clients = new ArrayList<>();

    /**
     * Запускает локальный сервер на указанном порту
     *
     * @param port порт сервера
     */
    public Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket newClient = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(newClient);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаляет клиента из списка подключенных клиентов.
     *
     * @param clientHandler клиент, которого нужно удалить
     */
    public static void leaveClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    /**
     * Отправляет сообщение всем подключённым клиентам.
     *
     * @param message сообщение для отправки
     */
    public static void notifyClients(String message) {
        synchronized (LOCK) {
            for (ClientHandler clientHandler : clients) {
                clientHandler.sendMessage(message);
            }
        }
    }
}

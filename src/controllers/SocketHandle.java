package controllers;

import java.io.*;
import java.net.Socket;
import model.Message;
import system.Config;

public class SocketHandle implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socketOfClient;

    @Override
    public void run() {
        try {
            socketOfClient = new Socket("26.118.21.242", Config.PORT);
            System.out.println("Kết nối thành công");

            oos = new ObjectOutputStream(socketOfClient.getOutputStream());
            ois = new ObjectInputStream(socketOfClient.getInputStream());

            Message message;
            while (true) {
                message = (Message) ois.readObject();
                if (message == null) {
                    break;
                }
                // Đăng nhập thành công
                if (message.getType().equals("LOGIN_SUCCESS")) {
                    System.out.println("Đăng nhập thành công");
                    // Client.closeAllViews();
                    // Client.openView(Client.View.HOMEPAGE);
                     // nhận về
                    System.out.println(message.getObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            // Đóng tài nguyên
            try {
                if (ois != null) ois.close();
                if (oos != null) oos.close();
                if (socketOfClient != null) socketOfClient.close();
            } catch (IOException e) {
                System.err.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public void write(Message message) throws IOException {
        oos.writeObject(message);
        oos.flush();
    }
}

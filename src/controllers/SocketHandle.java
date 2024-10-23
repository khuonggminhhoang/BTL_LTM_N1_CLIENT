package controllers;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;
import model.Message;
import system.Config;
import model.Users;

public class SocketHandle implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socketOfClient;
    private Users currUser;
    @Override
    public void run() {
        try {
            socketOfClient = new Socket("26.118.21.242", Config.PORT);
            System.out.println("Ket noi thanh cong");

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
                    this.currUser = (Users) message.getObject();
                    System.out.println("Dang nhap thanh cong");
                    Client.closeAllViews();
                    Client.openView(Client.View.HOMEPAGE);
                    System.out.println(message.getObject());
                }
                // Đăng kí thành công
                if (message.getType().equals("REGISTER_SUCCESS")){
                    System.out.println("Dang ki thanh cong");
                    Client.closeAllViews();
                    Client.openView(Client.View.LOGIN);
                    System.out.println(message.getObject());
                }

                // Sai thông tin tài khoản
                if (message.getType().equals("WRONG_USER")){
                    System.out.println("Sai thong tin dang nhap");
                    Client.closeAllViews();
                    Client.openView(Client.View.LOGIN);
                    JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không chính xác!", "Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
                }

                //Xử lý trùng tên 
                if (message.getType().equals("REGISTER_FAIL")){
                    Client.closeAllViews();
                    Client.openView(Client.View.REGISTER);
                    JOptionPane.showMessageDialog(Client.registerFrm, "Tên tài khoản đã được người khác sử dụng");
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

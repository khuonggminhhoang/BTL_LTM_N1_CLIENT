package controllers;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;
import model.Message;
import model.Users;
import system.Config;

public class SocketHandle implements Runnable {
    private Users currUser;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socketOfClient;
<<<<<<< HEAD
    
=======
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66
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
                    System.out.println(this.currUser.getAvatar());
                    System.out.println(this.currUser.getNumberOfGame());
                    System.out.println("Dang nhap thanh cong");
                    Client.closeAllViews();
                    Client.openView(Client.View.HOMEPAGE,currUser);
                }
                // Đăng kí thành công
                if (message.getType().equals("REGISTER_SUCCESS")){
                    System.out.println("Dang ki thanh cong");
                    Client.closeAllViews();
                    Client.openView(Client.View.LOGIN);
                    System.out.println(message.getObject());
                }

                // Sai thông tin tài khoản
                if (message.getType().equals("LOGIN_FAIL")){
                    System.out.println("Sai thong tin dang nhap");
                    // Client.closeAllViews();
                    // Client.openView(Client.View.LOGIN);
                    Client.loginFrm.showError("Tài khoản hoặc mật khẩu không chính xác");
                }

                //Xử lý trùng tên 
                if (message.getType().equals("REGISTER_FAIL")){
                    // Client.closeAllViews();
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

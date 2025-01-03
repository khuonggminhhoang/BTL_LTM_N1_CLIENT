package controllers;

import java.io.*;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;
import model.Message;
import model.Users;
import system.Config;

public class SocketHandle implements Runnable {
    // private Users currUser;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socketOfClient;
    
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
                    Client.user = (Users) message.getObject();
                    System.out.println("Dang nhap thanh cong");
                    Client.closeAllViews();
                    Client.openView(Client.View.HOMEPAGE);
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
                    Client.loginFrm.showError("Tài khoản hoặc mật khẩu không chính xác");
                }

                //Xử lý trùng tên 
                if (message.getType().equals("REGISTER_FAIL")){
                    JOptionPane.showMessageDialog(Client.registerFrm, "Tên tài khoản đã được người khác sử dụng");
                }

                // chat world
                if (message.getType().equals("WORLD_CHAT_RESPONSE")) {
                    String mess = message.getObject() + "";
                    System.out.println(mess);
                    Client.homepageFrm.addMessage(mess);
                }

                // Lấy về danh sách người chơi get all user request
                if(message.getType().equals("GET_ALL_USER_SUCCESS")){
                    System.out.println(message.getType());
                    System.out.println(message.getObject());
                    Client.lst=(List<Users>)message.getObject();
                    Client.openView(Client.View.PLAYER);
                }


                // lấy về bảng xếp hạng view rank request
                if( message.getType().equals("VIEW_RANK_SUCCESS")){
                    System.out.println(message.getType());
                    System.out.println(message.getObject());
                    Client.lst=(List<Users>) message.getObject();
                    Client.openView(Client.View.RANK);
                }

                

            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Loi: " + e.getMessage());
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
package controllers;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import model.Message;
import model.Questions;
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
            socketOfClient = new Socket("localhost", Config.PORT);
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

                if (message.getType().equals("GET_ROOMS_SUCCESS")) {
                    HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) message.getObject();
                    Client.roomListFrm.setUserQuantity(map);
                }

                if (message.getType().equals("QUESTION")) {
                    Questions firstQuestion = (Questions) message.getObject();
                    System.out.println(firstQuestion.getImgPath());
                    Client.gameFrm.setCurrentQuestion(firstQuestion);
                }
                
                if (message.getType().equals("JOIN_ROOM_SUCCESS")) {
                    System.out.println("Join room success");
                }
                
                if (message.getType().equals("GET_ROOM_REQUEST")) {
                    Client.waitingRoomFrm.setAmountPlayer((int) message.getObject());
                }
                
                if (message.getType().equals("ANSWER_CORRECT")) {
                    Questions currentQuestion = (Questions) message.getObject();
                    Client.gameFrm.setCurrentQuestion(currentQuestion);
                    Client.gameFrm.setPoint1();
                }
                
                if (message.getType().equals("ANSWER_TEMP_RESPONSE")) {
                    String otherAnswer = (String) message.getObject();
                    Client.gameFrm.setAnswer(otherAnswer);
                }
                
                if (message.getType().equals("OTHER_ANSWER_CORRECT")) {
                    Questions currentQuestion = (Questions) message.getObject();
                    Client.gameFrm.setCurrentQuestion(currentQuestion);
                    Client.gameFrm.setPoint2();
                }
                
                if (message.getType().equals("GAME_OVER")) {
                    Client.gameFrm.setPoint1();
                }
                
                if (message.getType().equals("ANSWER_INCORRECT")) {
                    Client.gameFrm.showError("Đáp án không chính xác");

                }

                // if (message.getType().equals("OTHER_WIN_GAME")) {
                //     Client.gameFrm.setPoint2();
                // }

                if(message.getType().equals("OTHER_USER")) {
                    Users otherUser = (Users)message.getObject();
                    Client.gameFrm.setOtherUser(otherUser);
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
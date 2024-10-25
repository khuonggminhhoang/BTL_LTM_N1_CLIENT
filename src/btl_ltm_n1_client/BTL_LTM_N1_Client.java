/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_ltm_n1_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

=======
import java.util.Scanner;
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66
import model.Message;
import system.Config;
import java.util.Scanner;

/**
 *
 * @author minhk
 */
public class BTL_LTM_N1_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
<<<<<<< HEAD
            Socket serverSocket = new Socket("localhost", Config.PORT);
=======
            Socket serverSocket = new Socket("26.118.21.242", Config.PORT); 
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66

            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());

<<<<<<< HEAD
            // test đăng nhập
            Users user = new Users("phamvananh", "123478");
            Message loginMessage = new Message("LOGIN_REQUEST", user);
            oos.writeObject(loginMessage);

            loginMessage = (Message) ois.readObject();
            System.out.println(loginMessage.getType());

=======
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66
            // // type: GET_ROOMS_REQUEST | object: null
            // // test room
            // Message sendMessage = new Message("GET_ROOMS_REQUEST", null);
            // oos.writeObject(sendMessage);

            // Message receiveMessage = (Message) ois.readObject();
<<<<<<< HEAD
            // HashMap<Integer, Integer> mapRoom =(HashMap<Integer, Integer>)
            // receiveMessage.getObject();
            // System.out.println(receiveMessage.getType());
            // for(int key : mapRoom.keySet()) {
            // int idRoom = key;
            // int qty = mapRoom.get(key);
            // System.out.println(idRoom + " => " + qty);
            // }

            // type: JOIN_ROOM_REQUEST | object: idRoom
            // test join room
            Message sendMessage1 = new Message("JOIN_ROOM_REQUEST", "108");
            oos.writeObject(sendMessage1);

            Message receiveMessage1 = (Message) ois.readObject();
            System.out.println(receiveMessage1.getType());

            Scanner scn = new Scanner(System.in);
            while (true) {
                String mess = scn.nextLine();
                // dùng như này để gửi đáp án về giao diện người chơi đối thủ 
=======
            // HashMap<Integer, Integer> mapRoom =(HashMap<Integer, Integer>) receiveMessage.getObject();
            // System.out.println(receiveMessage.getType());
            // for(int key : mapRoom.keySet()) {
            //     int idRoom = key;
            //     int qty = mapRoom.get(key);
            //     System.out.println(idRoom + " => " + qty);
            // }
            
            // type: JOIN_ROOM_REQUEST | object: idRoom
            // test join room
            Message sendMessage = new Message("JOIN_ROOM_REQUEST", "108");
            oos.writeObject(sendMessage);

            Message receiveMessage = (Message) ois.readObject();
            System.out.println(receiveMessage.getType());
            
            Scanner scn = new Scanner(System.in);
            while (true) {
                String mess = scn.nextLine();

>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66
                Message message = new Message("ANSWER_TEMP_REQUEST", mess);
                oos.writeObject(message);

                Message received = (Message) ois.readObject();
                System.out.println(received.getType());
<<<<<<< HEAD

                System.out.println(received.getObject());
            }
=======
                System.out.println(received.getObject());
            }

            
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
    }

}
=======
   }
    
}
>>>>>>> 0742d6631f639888f4881b4d8ae7947ca8e1fb66

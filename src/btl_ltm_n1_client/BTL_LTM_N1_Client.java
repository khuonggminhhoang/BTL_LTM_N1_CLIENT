/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_ltm_n1_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import model.Message;
import model.Users;
import system.Config;

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
            Socket serverSocket = new Socket("localhost", Config.PORT); 

            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());

            Users user = new Users("hoangminhkhuong", "123456");
            HashMap<Integer, Users> map = new HashMap<>();
            map.put(102, user);
            Message sendMessage = new Message("JOIN_ROOM_REQUEST", map);
            oos.writeObject(sendMessage);  // gửi đi
            System.out.println("Da gui goi tin: " + sendMessage);
            
            // nhận về
            Message reciveMessage = (Message) ois.readObject();
            System.out.println(reciveMessage.getType());
            System.out.println(reciveMessage.getObject());
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

   }
    
}

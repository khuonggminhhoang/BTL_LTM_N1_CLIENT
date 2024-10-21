/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_ltm_n1_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
            Socket serverSocket = new Socket("26.118.21.242", Config.PORT); 

            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());

            Users user = new Users("Thoabinh", "12345678");
            Message sendMessage = new Message("CHANGE_PASSWORD_REQUEST", user);
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

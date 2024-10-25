/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_ltm_n1_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import model.Message;
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

            // // type: GET_ROOMS_REQUEST | object: null
            // // test room
            // Message sendMessage = new Message("GET_ROOMS_REQUEST", null);
            // oos.writeObject(sendMessage);

            // Message receiveMessage = (Message) ois.readObject();
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

                Message message = new Message("ANSWER_TEMP_REQUEST", mess);
                oos.writeObject(message);

                Message received = (Message) ois.readObject();
                System.out.println(received.getType());
                System.out.println(received.getObject());
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

   }
    
}
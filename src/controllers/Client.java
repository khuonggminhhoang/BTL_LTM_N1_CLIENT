/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import model.Users;
import views.*;


/**
 *
 * @author Admin
 */

public class Client{
    public static Users user;
    public static LoginFrm loginFrm;
    public static RegisterFrm registerFrm;
    public static SocketHandle socketHandle;
    
    public Client(){
        
    }
    
    public static void openView(View viewName){
        if(viewName!=null){
            switch(viewName){
                case LOGIN:
                    loginFrm=new LoginFrm();
                    loginFrm.setVisible(true);
                    break;
                case REGISTER:
                    registerFrm=new RegisterFrm();
                    registerFrm.setVisible(true);
            }
        }
    }
   
    
    public static void closeView(View viewName){
        if(viewName!=null){
            switch(viewName){
                    case LOGIN:
                        loginFrm.dispose();
                        break;
                    case REGISTER:
                        registerFrm.dispose();
                        break;
            }
        }
    }

        
    public static void closeAllViews(){
        if(loginFrm!=null) loginFrm.dispose();
        if(registerFrm!=null) registerFrm.dispose();
    }
    
    
    public static void main(String []args){
        new Client().initView();
    }
    
    public void initView() {
        loginFrm = new LoginFrm();
        loginFrm.setVisible(true);
        socketHandle = new SocketHandle();
        new Thread(socketHandle).start(); // Chạy SocketHandle trong một thread riêng
    }
    
    
    
    public enum View {
        LOGIN,
        REGISTER,
        HOMEPAGE,
        ROOM_LIST,
        FRIEND_LIST,
        FIND_ROOM,
        WAITING_ROOM,
        GAME_CLIENT,
        CREATE_ROOM_PASSWORD,
        JOIN_ROOM_PASSWORD,
        COMPETITOR_INFO,
        RANK,
        GAME_NOTICE,
        FRIEND_REQUEST,
        GAME_AI,
        ROOM_NAME_FRM
    }
}
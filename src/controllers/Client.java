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
    public static HomepageFrm homepageFrm;
    public static SocketHandle socketHandle;
    public static WaitingRoomFrm waitingRoomFrm;
    public static GameFrm gameFrm;
    public static PlayerFrm playerFrm;
    public static RoomListFrm roomListFrm;
    
    public Client(){}
    
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
                    break;

                case HOMEPAGE: 
                    homepageFrm = new HomepageFrm();
                    homepageFrm.setVisible(true);
                    break;
            
                case ROOM_LIST:
                    roomListFrm=new RoomListFrm();
                    roomListFrm.setVisible(true);
                    break;
                case WAITING_ROOM:
                    waitingRoomFrm=new WaitingRoomFrm();
                    waitingRoomFrm.setVisible(true);
                    break;
                case GAME_FRM:
                    gameFrm=new GameFrm();
                    gameFrm.setVisible(true);
                    break;
                case PLAYER:
                    playerFrm=new PlayerFrm();
                    playerFrm.setVisible(true);
                    break;
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
                    case HOMEPAGE:
                        homepageFrm.dispose();
                        break;
            }
        }
    }

        
    public static void closeAllViews(){
        if(loginFrm!=null) loginFrm.dispose();
        if(registerFrm!=null) registerFrm.dispose();
        if(homepageFrm!=null) homepageFrm.dispose();
        if(roomListFrm!=null) roomListFrm.dispose();
        if(waitingRoomFrm!=null) waitingRoomFrm.dispose();
        if(gameFrm!=null) gameFrm.dispose();
        if(playerFrm!=null) playerFrm.dispose();
    }
    
    
    public static void main(String []args){
        new Client().initView();
    }
    
    public void initView() {
        loginFrm = new LoginFrm();
        loginFrm.setVisible(true);
        // registerFrm =new RegisterFrm();
        // registerFrm.setVisible(true);
        // homepageFrm=new HomepageFrm();
        // homepageFrm.setVisible(true);
        // roomListFrm=new RoomListFrm();
        // roomListFrm.setVisible(true);
        // waitingRoomFrm=new WaitingRoomFrm();
        // waitingRoomFrm.setVisible(true);
        // gameFrm=new GameFrm();
        // gameFrm.setVisible(true);
        // playerFrm=new PlayerFrm();
        // playerFrm.setVisible(true);
        socketHandle = new SocketHandle();
        new Thread(socketHandle).start(); // Chạy SocketHandle trong một thread riêng
    }
    
    
    public enum View {
        LOGIN,
        REGISTER,
        HOMEPAGE,
        ROOM_LIST,
        WAITING_ROOM,
        GAME_FRM,
        PLAYER,
        RANK,
        FRIEND_REQUEST,
    }
}
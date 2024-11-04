/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import java.awt.Image;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.ImageIcon;
import model.Histories;
import model.Users;
import views.*;


/**
 *
 * @author Admin
 */

public class Client{
    public static Users user;
    public static List<Users> lst;
    public static List<Histories> hisLst;
    public static LoginFrm loginFrm;
    public static RegisterFrm registerFrm;
    public static HomepageFrm homepageFrm;
    public static SocketHandle socketHandle;
    public static WaitingRoomFrm waitingRoomFrm;
    public static GameFrm gameFrm;
    public static RankFrm rankFrm;
    public static PlayerFrm playerFrm;
    public static RoomListFrm roomListFrm;
    public static ResultFrm resultFrm;
    public static HistoryFrm historyFrm;

    public static LocalDateTime timeStart;
    public static LocalDateTime timeEnd;

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
                case RANK:
                    rankFrm=new RankFrm();
                    rankFrm.setVisible(true);
                    break;
                case RESULT_FRM:
                    resultFrm = new ResultFrm();
                    resultFrm.setVisible(true);
                    break;
                case HISTORY_FRM:
                    historyFrm=new HistoryFrm();
                    historyFrm.setVisible(true);
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
                case WAITING_ROOM:
                    waitingRoomFrm.dispose();
                    break;
                case HISTORY_FRM:
                    historyFrm.dispose();
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
        if(resultFrm!= null) resultFrm.dispose();
        if(rankFrm!= null) rankFrm.dispose();
        if(historyFrm!=null) historyFrm.dispose();
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

    public static ImageIcon resizeImage(String pathImg) {
        // Tạo ImageIcon và lấy kích thước ảnh gốc
        ImageIcon originalIcon = new ImageIcon(pathImg);
        
        // Resize ảnh khớp với kích thước ảnh gốc
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        return resizedIcon;
    }

    public static ImageIcon resizeImage(int width, int height, String pathImg) {
        // Tạo ImageIcon và lấy kích thước ảnh gốc
        ImageIcon originalIcon = new ImageIcon(pathImg);
        
        // Resize ảnh khớp với kích thước ảnh gốc
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        return resizedIcon;
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
        RESULT_FRM,
        HISTORY_FRM
    }
} 
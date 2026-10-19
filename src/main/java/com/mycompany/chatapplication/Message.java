/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

/**
 *
 * @author musho
 */
public class Message {
    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;
    
    public Message(String messageID,String recipientCell,String message){
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.message = message;
    }
    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
    
}

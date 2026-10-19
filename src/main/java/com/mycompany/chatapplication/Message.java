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
   
    public String checkRecipientCell() {
        if (recipientCell.length() <= 10
                && recipientCell.startsWith("+")) {
            return "Valid recipient number";
        } else {
            return "Invalid recipient number";
        }
    }
    public String createMessageHash(int messageNumber){
        String[]words = message.trim().split("\\s+");
        
        String firstWord =words[0];
        String lastWord =words [words.length - 1];
        
        messageHash = messageID.substring(0,2)
                +":"+messageNumber
                +":"+firstWord +lastWord;
        
        return messageHash.toUpperCase();
        
    }
    
    public String SentMessage() {
        java.util.Scanner input = new java.util.Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");

        String choice = input.nextLine();

        if (choice.equals("1")) {
            return "Message successfully sent";
        } else if (choice.equals("2")) {
            return "Press D to delete the message";
        } else if (choice.equals("3")) {
            return "Message successfully stored";
        } else {
            return "Invalid option";
        }
    }

}

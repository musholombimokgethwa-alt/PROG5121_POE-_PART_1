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
    private static int totalMessages = 0;

    
    public Message(String messageID,String recipientCell,String message){
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.message = message;
        totalMessages++;
    }
    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
   
   
    public String checkRecipientCell() {
        if (recipientCell.matches("^\\+27\\d{9}$")) {
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
    
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipientCell
                + "\nMessage: " + message;
    }
    
    public int returnTotalMessagess() {
        return totalMessages;
    }
   
    
    public String storeMessage() {
        try {
            java.io.File file = new java.io.File("messages.json");
            java.util.Scanner reader = new java.util.Scanner(file);
            String oldMessages = "";

            while (reader.hasNextLine()) {
                oldMessages += reader.nextLine() + "\n";
            }
            reader.close();

            String newMessage = """
                                
                                ----------------------------------
                                          MESSAGE DETAILS
                                ----------------------------------
                                Message ID: """ + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipientCell
            + "\nMessage: " + message
            + "\n----------------------------------";

            if (oldMessages.trim().equals("")
                    || oldMessages.trim().equals("[]")) {
                oldMessages = "[\n" + newMessage + "\n]";
            } else {
                oldMessages = oldMessages.trim();
                oldMessages = oldMessages.substring(
                        0, oldMessages.lastIndexOf("]"));

                if (oldMessages.trim().endsWith("}")) {
                    oldMessages += ",\n";
                }

                oldMessages += newMessage + "\n]";
            }

            java.io.FileWriter writer =
                    new java.io.FileWriter(file);

            writer.write(oldMessages);
            writer.close();

            return "Message successfully stored.";

        } catch (java.io.IOException e) {
            return "Error storing message.";
        }
    }


}


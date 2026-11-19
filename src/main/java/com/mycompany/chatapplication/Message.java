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
    private boolean sent = false;
    private String sender;
    
    public Message(String messageID, String recipientCell, String message) {
    this.messageID = messageID;
    this.recipientCell = recipientCell;
    this.message = message;
}
    
    public Message(String messageID,String recipientCell,String message,String sender){
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.message = message;
        this.sender = sender;
    }
    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
    public boolean checkMessageLength() {
        return message.length() < 250;
}
    
   
   
    public String checkRecipientCell() {
        if (recipientCell.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured";
        } else {
            return "Invalid phone number.Please try again.";
        }
    }

    public String createMessageHash(int messageNumber) {
        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        messageHash = messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord + lastWord;

        messageHash = messageHash.toUpperCase();

        return messageHash;
    }
    
    public String SentMessage() {
        java.util.Scanner input = new java.util.Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");

        String choice = input.nextLine();

        if (choice.equals("1")) {
            
            if(!sent){
                totalMessages++;
                sent = true;
            }
            return "Message successfully sent.";
        } else if (choice.equals("2")) {
            return "Press 0 to delete the message.";
        } else if (choice.equals("3")) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }
    
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\"sender\": \"" + sender + "\",\n"
                + "\nRecipient: " + recipientCell
                + "\nMessage: " + message;
    }
    
    public int returnTotalMessagess() {
        return totalMessages;
    }
   
    
    public String storeMessage() {
            try {
                java.io.File file = new java.io.File("messages.json");
                String oldMessages = "";

                if (file.exists()) {
                    java.util.Scanner reader = new java.util.Scanner(file);

                    while (reader.hasNextLine()) {
                        oldMessages += reader.nextLine() + "\n";
                    }

                    reader.close();
                }

                String newMessage = "  {\n"
                        + "    \"messageID\": \"" + messageID + "\",\n"
                        + "    \"messageHash\": \"" + messageHash + "\",\n"
                        + "    \"sender\": \"" + sender + "\",\n"
                        + "    \"recipient\": \"" + recipientCell + "\",\n"
                        + "    \"message\": \"" + message + "\"\n"
                        + "  }";

                if (oldMessages.trim().isEmpty()
                        || oldMessages.trim().equals("[]")) {
                    oldMessages = "[\n" + newMessage + "\n]";
                } else if (oldMessages.trim().endsWith("]")) {
                    oldMessages = oldMessages.trim();
                    oldMessages = oldMessages.substring(
                            0, oldMessages.lastIndexOf("]"));

                    if (!oldMessages.trim().equals("[")) {
                        oldMessages += ",\n";
                    }

                    oldMessages += newMessage + "\n]";
                } else {
                    return "Error: messages.json is not a valid JSON array.";
                }

                java.io.FileWriter writer = new java.io.FileWriter(file);
                writer.write(oldMessages);
                writer.close();

                return "Message successfully stored.";

            } catch (java.io.IOException e) {
                return "Error storing message.";
            }
            }
  
            public String getMessageID() {
                return messageID;
            }

            public String getRecipientCell() {
                return recipientCell;
            }

            public String getMessage() {
                return message;
            }

            public String getMessageHash() {
                return messageHash;
            }
            public String getSender() {
                return sender;
}
}

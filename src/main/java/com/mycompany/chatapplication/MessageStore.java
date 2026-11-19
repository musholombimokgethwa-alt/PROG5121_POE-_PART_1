/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * @author musho
 */
public class MessageStore {
    public static final int MAX_MESSAGES = 50;
    
    private String[] sentMessages = new String[MAX_MESSAGES];
    private String[] disregardedMessages = new String[MAX_MESSAGES];
    private String[] storedMessages = new String[MAX_MESSAGES];
    private String[] messageHashes = new String[MAX_MESSAGES];
    private String[] messageIDs = new String[MAX_MESSAGES];
    private String[] messageContents = new String[MAX_MESSAGES];
    
    private String[] storedSenders = new String[MAX_MESSAGES];
    private String[] storedRecipients = new String[MAX_MESSAGES];
    
    private int sentCount = 0;
    private int disregardedCount = 0;
    private int storedCount = 0;
    private int messageCount = 0;
   
    
        public void recordMessage(Message message, String status) {

            boolean validStatus = false;

            if (status.equals("Sent") && sentCount < MAX_MESSAGES) {
                sentMessages[sentCount] = message.getMessage();
                sentCount++;
                validStatus = true;

            } else if (status.equals("Disregarded")
                    && disregardedCount < MAX_MESSAGES) {
                disregardedMessages[disregardedCount] = message.getMessage();
                disregardedCount++;
                validStatus = true;

            } else if (status.equals("Stored")
                    && storedCount < MAX_MESSAGES) {
                storedMessages[storedCount] = message.getMessage();
                storedSenders[storedCount] = message.getSender();
                storedRecipients[storedCount] = message.getRecipientCell();

                storedCount++;
                validStatus = true;
        }

            if (validStatus && messageCount < MAX_MESSAGES) {
                messageHashes[messageCount] = message.getMessageHash();
                messageIDs[messageCount] = message.getMessageID();
                messageContents[messageCount] = message.getMessage();

                messageCount++;
            }
        }
        
        public String loadStoredFromJson(String fileName) {

            Gson gson = new Gson();
            int loaded = 0;

            try {
                String jsonText = Files.readString(Paths.get(fileName));

                JsonArray jsonMessages = JsonParser.parseString(jsonText)
                        .getAsJsonArray();

                for (int i = 0; i < jsonMessages.size(); i++) {

                    JsonObject jsonMessage = jsonMessages.get(i)
                            .getAsJsonObject();

                    if (storedCount < MAX_MESSAGES
                            && messageCount < MAX_MESSAGES) {

                        String messageID = jsonMessage.get("messageID").getAsString();
                        String messageHash = jsonMessage.get("messageHash").getAsString();
                        String recipient = jsonMessage.get("recipient").getAsString();
                        storedRecipients[storedCount] = recipient;
                        
                        String sender = jsonMessage.has("sender")
                                ? jsonMessage.get("sender").getAsString()
                                : "Unknown";

                        storedSenders[storedCount] = sender;
                        String content = jsonMessage.get("message").getAsString();

                        storedMessages[storedCount] = content;
                        storedCount++;

                        messageIDs[messageCount] = messageID;
                        messageHashes[messageCount] = messageHash;
                        messageContents[messageCount] = content;

                        messageCount++;
                        loaded++;
                    }
                }

                return loaded + " stored message(s) loaded.";

            } catch (IOException e) {
                return "No stored messages found.";
            }
        }
        
        public void displayStoredSendersAndRecipients() {

            if (storedCount == 0) {
                System.out.println("No stored messages found.");
                return;
            }

            for (int i = 0; i < storedCount; i++) {
                System.out.println("Sender: " + storedSenders[i]);
                System.out.println("Recipient: " + storedRecipients[i]);
                System.out.println("-------------------------");
            }
        }
        
        
}

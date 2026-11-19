/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

import com.google.gson.Gson;

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
                List<String> lines = Files.readAllLines(Paths.get(fileName));

                for (int i = 0; i < lines.size(); i++) {

                    String line = lines.get(i).trim();

                    if (line.startsWith("{") && storedCount < MAX_MESSAGES) {

                        Message message = gson.fromJson(line, Message.class);

                        storedMessages[storedCount] = message.getMessage();
                        storedCount++;

                        messageHashes[messageCount] = message.getMessageHash();
                        messageIDs[messageCount] = message.getMessageID();
                        messageContents[messageCount] = message.getMessage();

                        messageCount++;
                        loaded++;
                    }
                }

                return loaded + " stored message(s) loaded from " + fileName;

            } catch (IOException e) {
                return "No stored messages found.";
            }
        }

        
}

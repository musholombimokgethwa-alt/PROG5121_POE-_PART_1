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
    
    private String[] storedMessageIDs = new String[MAX_MESSAGES];
    private String[] storedMessageHashes = new String[MAX_MESSAGES];
    private String[] messageRecipients = new String[MAX_MESSAGES];
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
                storedMessageIDs[storedCount] = message.getMessageID();
                storedMessageHashes[storedCount] = message.getMessageHash();
                storedCount++;
                validStatus = true;
        }

            if (validStatus && messageCount < MAX_MESSAGES) {
                messageHashes[messageCount] = message.getMessageHash();
                messageIDs[messageCount] = message.getMessageID();
                messageContents[messageCount] = message.getMessage();
                messageRecipients[messageCount] = message.getRecipientCell();
                messageCount++;
            }
        }
        // Getter methods used to access sent messages during testing
        public int getSentCount() {
                return sentCount;
            }

            public String getSentMessage(int index) {
                if (index >= 0 && index < sentCount) {
                    return sentMessages[index];
                }

                return null;
            }
            // Getter method used to check the number of stored messages
                public int getStoredCount() {
                    return storedCount;
                }
           // Loads stored messages from the JSON file into the message arrays 
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
                        storedMessageIDs[storedCount] = messageID;
                        storedMessageHashes[storedCount] = messageHash;
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
                        messageRecipients[messageCount] = recipient;
                        messageCount++;
                        loaded++;
                    }
                }

                return loaded + " stored message(s) loaded.";

            } catch (IOException e) {
                return "No stored messages found.";
            }
        }
        // Displays the sender and recipient of each stored message
        public String displayStoredSendersAndRecipients() {

            if (storedCount == 0) {
                return "No stored messages found.";
            }

            String result = "";

            for (int i = 0; i < storedCount; i++) {
                result = result + "Sender: " + storedSenders[i]
                        + " | Recipient: " + storedRecipients[i] + "\n";
            }

            return result;
        }
        // Finds and returns the longest stored message
        public String longestStoredMessage() {

            if (storedCount == 0) {
                return "No stored messages found.";
            }

            String longest = storedMessages[0];

            for (int i = 1; i < storedCount; i++) {

                if (storedMessages[i].length() > longest.length()) {
                    longest = storedMessages[i];
                }
            }

            return longest;
        }
        // Searches for a stored message using its message ID
        public String searchByMessageID(String id) {

            for (int i = 0; i < storedCount; i++) {

                if (storedMessageIDs[i].equals(id)) {
                    return "Recipient: " + storedRecipients[i]
                            + "\nMessage: " + storedMessages[i];
                }
            }

            return "Message " + id + " not found.";
        }
        // Finds and displays all stored messages for a specific recipient
        public String searchByRecipient(String recipient) {

            String result = "";

            for (int i = 0; i < storedCount; i++) {

                if (storedRecipients[i].equals(recipient)) {
                    result = result + storedMessages[i] + "\n";
                }
            }

            if (result.equals("")) {
                return "No messages found for recipient " + recipient + ".";
            }

            return result;
        }
        // Deletes a stored message using its unique message hash
        public String deleteByMessageHash(String hash) {

            int deleteIndex = -1;

            for (int i = 0; i < storedCount; i++) {
                if (storedMessageHashes[i].equals(hash)) {
                    deleteIndex = i;
                    break;
                }
            }

            if (deleteIndex == -1) {
                return "Message hash " + hash + " not found.";
            }

            String deleted = storedMessages[deleteIndex];

            for (int i = deleteIndex; i < storedCount - 1; i++) {
                storedMessages[i] = storedMessages[i + 1];
                storedSenders[i] = storedSenders[i + 1];
                storedRecipients[i] = storedRecipients[i + 1];
                storedMessageIDs[i] = storedMessageIDs[i + 1];
                storedMessageHashes[i] = storedMessageHashes[i + 1];
            }

            storedMessages[storedCount - 1] = null;
            storedSenders[storedCount - 1] = null;
            storedRecipients[storedCount - 1] = null;
            storedMessageIDs[storedCount - 1] = null;
            storedMessageHashes[storedCount - 1] = null;

            storedCount--;

            return "Message \"" + deleted + "\" deleted.";
        }
        // Generates a report containing all stored messages
        public String reportStoredMessages() {

            if (storedCount == 0) {
                return "No stored messages found.";
            }

            String result = "STORED MESSAGES REPORT\n";

            for (int i = 0; i < storedCount; i++) {

                result = result + "----------\n"
                        + "Message ID: " + storedMessageIDs[i] + "\n"
                        + "Message Hash: " + storedMessageHashes[i] + "\n"
                        + "Sender: " + storedSenders[i] + "\n"
                        + "Recipient: " + storedRecipients[i] + "\n"
                        + "Message: " + storedMessages[i] + "\n";
            }

            result = result + "----------\nTotal stored messages: " + storedCount;

            return result;
        }
        // Displays a report of all sent messages
            public String reportSentMessages() {

                if (sentCount == 0) {
                    return "No sent messages found.";
                }

                String result = "SENT MESSAGES REPORT\n";

                for (int i = 0; i < sentCount; i++) {

                    result = result + "----------\n"
                            + "Message Hash: " + messageHashes[i] + "\n"
                            + "Recipient: " + messageRecipients[i] + "\n"
                            + "Message: " + sentMessages[i] + "\n";
                }

                result = result + "----------\nTotal sent messages: " + sentCount;

                return result;
            }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author musho
 */
public class MessageStoreTest {
    // Test 1: Sent Messages array is populated correctly
    @Test
    public void testSentMessagesArray() {

        MessageStore store = new MessageStore();

        Message message1 = new Message(
                "1234567890",
                "+27834557896",
                "Did you get the cake?"
        );

        Message message2 = new Message(
                "1234567891",
                "0838884567",
                "It is dinner time !"
        );

        store.recordMessage(message1, "Sent");
        store.recordMessage(message2, "Sent");

        assertEquals(2, store.getSentCount());
        assertEquals("Did you get the cake?", store.getSentMessage(0));
        assertEquals("It is dinner time !", store.getSentMessage(1));
    }

    // Test 2: Display the longest message using messages 1–4
    @Test
    public void testLongestMessage() {

        MessageStore store = new MessageStore();

        store.recordMessage(new Message("1234567890",
                "+27834557896", "Did you get the cake?"), "Sent");

        store.recordMessage(new Message("1234567891",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."),
                "Stored");

        store.recordMessage(new Message("1234567892",
                "+27834484567", "Yohoooo, I am at your gate."), "Disregarded");

        store.recordMessage(new Message("1234567893",
                "0838884567", "It is dinner time !"), "Sent");

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                store.longestStoredMessage()
        );
    }

    // Test 3: Search for a message and return its recipient and content
    @Test
    public void testSearchByMessageID() {

        MessageStore store = new MessageStore();

        Message message = new Message(
                "1234567893",
                "0838884567",
                "It is dinner time !"
        );

        store.recordMessage(message, "Stored");

        assertEquals(
                "Recipient: 0838884567\nMessage: It is dinner time !",
                store.searchByMessageID("1234567893")
        );
    }

    // Test 4: Search sent and stored messages by recipient
    @Test
    public void testSearchByRecipient() {

        MessageStore store = new MessageStore();

        store.recordMessage(new Message("1234567891",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."),
                "Stored");

        store.recordMessage(new Message("1234567894",
                "+27838884567",
                "Ok, I am leaving without you."), "Stored");

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.\n"
                + "Ok, I am leaving without you.\n",
                store.searchByRecipient("+27838884567")
        );
    }

    // Test 5: Delete a message using its message hash
    @Test
    public void testDeleteByMessageHash() {

        MessageStore store = new MessageStore();

        Message message = new Message(
                "1234567891",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."
        );

        message.createMessageHash(1);
        store.recordMessage(message, "Stored");

        assertEquals(
                "Message \"Where are you? You are late! I have asked you to be on time.\" deleted.",
                store.deleteByMessageHash(message.getMessageHash())
        );

        assertEquals(0, store.getStoredCount());
    }

    // Test 6: Display the report of sent messages
    @Test
    public void testDisplayReport() {

        MessageStore store = new MessageStore();

        Message message = new Message(
                "1234567890",
                "+27834557896",
                "Did you get the cake?"
        );

        message.createMessageHash(1);
        store.recordMessage(message, "Sent");

        String expected = "SENT MESSAGES REPORT\n"
                + "----------\n"
                + "Message Hash: " + message.getMessageHash() + "\n"
                + "Recipient: +27834557896\n"
                + "Message: Did you get the cake?\n"
                + "----------\nTotal sent messages: 1";

        assertEquals(expected, store.reportSentMessages());
    }
}



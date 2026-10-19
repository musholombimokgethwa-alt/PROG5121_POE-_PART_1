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
public class MessageTest {
    
    
    @Test
    public void testCheckMessageID() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals("Valid recipient number", msg.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipientCell() {
        Message msg = new Message("1234567890", "0718693002", "Hello");
        assertEquals("Invalid recipient number", msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:1:HITONIGHT?",
                msg.createMessageHash(1));
}

    @Test
    public void testSendMessage() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals("Message successfully sent", "Message successfully sent");
    }

    @Test
    public void testDisregardMessage() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals("Press D to delete the message", "Press D to delete the message");
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertNotNull(msg.storeMessage());
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertTrue(msg.returnTotalMessagess() > 0);
    }

    @Test
    public void testMessageLength() {
        String message = "Hello";
        assertTrue(message.length() <= 250);
    }
    @Test
public void testMessageLengthFailure() {
    String message = "A".repeat(251);

    assertTrue(message.length() > 250);
}
    
}

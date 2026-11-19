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
    public void testInvalidMessageID() {
        Message msg = new Message("12345678901", "+27718693002", "Hello");
        assertFalse(msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured",
                msg.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipientCell() {
        Message msg = new Message("1234567890", "0718693002", "Hello");
        assertEquals("Invalid phone number.Please try again.",
                msg.checkRecipientCell());
    }

   @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT",
                msg.createMessageHash(0));
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals("Message successfully stored.", msg.storeMessage());
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertEquals(0, msg.returnTotalMessagess());
    }

    @Test
    public void testMessageLength() {
        Message msg = new Message("1234567890", "+27718693002", "Hello");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message("1234567890", "+27718693002",
                "A".repeat(250));
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testHashLoop() {

        String[] messages = {
            "Hello there",
            "Good morning",
            "See you later"
        };

        for (int i = 0; i < messages.length; i++) {

            Message msg = new Message(
                    "0012345678",
                    "+27718693002",
                    messages[i]
            );

            String hash = msg.createMessageHash(i + 1);

            assertNotNull(hash);
            assertTrue(hash.contains(":"));
            assertEquals(hash, hash.toUpperCase());
        }
    }
 
    }


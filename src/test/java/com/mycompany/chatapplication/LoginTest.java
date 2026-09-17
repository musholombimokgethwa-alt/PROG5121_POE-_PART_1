/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 *
 * @author musho
 */
public class LoginTest {
    
    public LoginTest() {
    }
 @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.phoneNumber = "+27838968975";

        assertEquals(
                "Username successfully captured."
                + "Password successfully captured."
                + "Cell phone number successfully added",
                user.registerUser()
        );
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login();

        user.username = "kyle !!!!!!!";
        user.password = "Ch&&sec@ke99!";
        user.phoneNumber = "+27838968975";

        assertEquals(
                "Username is not correctly formatted;please ensure that your "
                + "username contains an underscore and is no more than five "
                + "characters in length",
                user.registerUser()
        );
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.phoneNumber = "+27838968975";

        assertEquals(
                "Username successfully captured."
                + "Password successfully captured."
                + "Cell phone number successfully added",
                user.registerUser()
        );
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "password";
        user.phoneNumber = "+27838968975";

        assertEquals(
                "Password is not correctly formatted please ensure that the password "
                + "contains at least eight characters,a capital letter, a number, "
                + "and a special character",
                user.registerUser()
        );
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.phoneNumber = "+27838968975";

        assertEquals(
                "Username successfully captured."
                + "Password successfully captured."
                + "Cell phone number successfully added",
                user.registerUser()
        );
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.phoneNumber = "08966553";

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain the international"
                + "code",
                user.registerUser()
        );
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.loginUsername = "kyl_1";
        user.loginPassword = "Ch&&sec@ke99!";

        assertTrue(user.loginUser());
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login();

        user.username = "kyl_1";
        user.password = "Ch&&sec@ke99!";
        user.loginUsername = "wrong";
        user.loginPassword = "wrong";

        assertFalse(user.loginUser());
    }

    @Test
    public void testUsernameCorrect() {
        Login user = new Login();

        user.username = "kyl_1";

        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrect() {
        Login user = new Login();

        user.username = "kyle !!!!!!!";

        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordCorrect() {
        Login user = new Login();

        user.password = "Ch&&sec@ke99!";

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordIncorrect() {
        Login user = new Login();

        user.password = "password";

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrect() {
        Login user = new Login();

        user.phoneNumber = "+27838968975";

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrect() {
        Login user = new Login();

        user.phoneNumber = "08966553";

        assertFalse(user.checkCellPhoneNumber());
    }
}
   
    


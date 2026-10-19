/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication;
import java.util.Scanner;
/**
 *
 * @author musho
 */
public class ChatApplication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
                Login user = new Login();
        
                System.out.println("==Welcome to chat app ==");
                System.out.println();
                System.out.println("== Register ==");
                
                System.out.println("Enter your name :");
                user.name = input.nextLine();
                
                System.out.println("Enter your surname :");
                user.surname = input.nextLine();
                
                System.out.println("Enter your username :");
                user.username = input.nextLine();
                
                System.out.println("Create your password :");
                user.password = input.nextLine();
                
                System.out.println("Enter your south african cellphone number: (NB:start with +27)");
                user.phoneNumber = input.nextLine();
                
                System.out.println();
                System.out.println(user.registerUser());
                
                System.out.println();
                System.out.println("== Login ==");
                
                System.out.println("Enter your username :");
                user.loginUsername = input.nextLine();
                
                System.out.println("Enter your password :");
                user.loginPassword = input.nextLine();
                
                boolean loginSuccess = user.loginUser();
                
                System.out.println(user.returnLoginStatus(loginSuccess));
                
                 if (loginSuccess) {
                System.out.println("==Welcome to ChatApp!==");

                int choice = 0;

                while (choice != 3) {
                    System.out.println();
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.println("===Choose an option:===");

                    choice = input.nextInt();
                    input.nextLine();


            if (choice == 1) {
                System.out.println("How many messages do you want to send?");
                int numberOfMessages = input.nextInt();
                input.nextLine();

                
                for (int i = 0; i < numberOfMessages; i++) {
                    System.out.println();
                    System.out.println("==================================");
                    System.out.println("          MESSAGE " + (i + 1));
                    System.out.println("==================================");

                    System.out.println("Enter recipient cellphone number:");

                    String recipient = input.nextLine();

                    System.out.println("Enter your message (less than 250 characters):");
                    String text = input.nextLine();

                    if (text.length() >= 250) {
                        System.out.println("Message must be less than 250 characters.");
                        i--;
                        continue;
                    }

                    String messageID = String.format("%010d",
                            (long) (Math.random() * 10000000000L));

                    Message newMessage =
                            new Message(messageID, recipient, text);

                    System.out.println(newMessage.checkRecipientCell());

                    newMessage.createMessageHash(i + 1);

                    String result = newMessage.SentMessage();
                    System.out.println(result);

                    if (result.equals("Message successfully stored")) {
                        System.out.println(newMessage.storeMessage());
                    } else if (result.equals("Message successfully sent")) {
                        System.out.println(newMessage.printMessages());
                    } else if (result.equals("Press D to delete the message")) {
                        System.out.println("Message disregarded.");
                    }
                }
            }
            
            else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}
}




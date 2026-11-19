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
                MessageStore messageStore = new MessageStore();
        
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
                     messageStore.loadStoredFromJson("messages.json");
                    System.out.println("==Welcome to QuickChat!==");

                int choice = 0;

                while (choice != 3) {
                    System.out.println();
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.println("4. Stored Messages");
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
            
                String recipient;
                do {
                    System.out.println("Enter recipient cellphone number (+27):");
                    recipient = input.nextLine();

                    Message checkNumber = new Message("00", recipient, "");

                    System.out.println(checkNumber.checkRecipientCell());

                } while (!recipient.matches("^\\+27\\d{9}$"));
                    System.out.println("Enter your message (less than 250 characters):");
                    String text = input.nextLine();

                    if (text.length() >= 250) {
                        System.out.println("Message must be less than 250 characters.");
                        i--;
                        continue;
                    }

                    String messageID = "";
                    for (int j = 0;j< 10;j++){
                        messageID+=(int)(Math.random()*10);
                    }
                            

                    Message newMessage =
                        new Message(messageID, recipient, text,
                                user.name + " " + user.surname);
                    

                    newMessage.createMessageHash(i + 1);

                    String result = newMessage.SentMessage();
                    System.out.println(result);

                    if (result.equals("Message successfully stored.")) {
                        System.out.println(newMessage.storeMessage());
                        messageStore.recordMessage(newMessage, "Stored");
                    } else if (result.equals("Message successfully sent.")) {
                        System.out.println(newMessage.printMessages());
                        messageStore.recordMessage(newMessage, "Sent");
                    } else if (result.equals("Press 0 to delete the message.")) {
                        System.out.println("Message disregarded.");
                        messageStore.recordMessage(newMessage, "Disregarded"); 
                    }
                }
            }
            
            else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Goodbye!");
            } else if (choice == 4) {
                    int storedChoice = 0;
                        while (storedChoice != 7) {

                            System.out.println();
                            System.out.println("=== STORED MESSAGES ===");
                            System.out.println("1. Display senders and recipients");
                            System.out.println("2. Display longest stored message");
                            System.out.println("3. Search message by ID");
                            System.out.println("4. Search messages by recipient");
                            System.out.println("5. Delete message using hash");
                            System.out.println("6. Display all stored messages");
                            System.out.println("7. Back to main menu");

                            System.out.println("Choose an option:");
                            storedChoice = input.nextInt();
                            input.nextLine();
                            
                            if (storedChoice == 1) {
                                    System.out.println(messageStore.displayStoredSendersAndRecipients());
                                }
                            else if (storedChoice == 2) {
                                    System.out.println(messageStore.longestStoredMessage());
                                
                            } else if (storedChoice == 3) {
                                System.out.println("Enter message ID to search:");
                                String id = input.nextLine();
                                System.out.println(messageStore.searchByMessageID(id));

                            } else if (storedChoice == 4) {
                                System.out.println("Enter recipient cellphone number:");
                                String recipient = input.nextLine();
                                System.out.println(messageStore.searchByRecipient(recipient));

                            } else if (storedChoice == 5) {
                                System.out.println("Enter message hash to delete:");
                                String hash = input.nextLine();
                                System.out.println(messageStore.deleteByMessageHash(hash));

                            } else if (storedChoice == 6) {
                                System.out.println(messageStore.reportStoredMessages());

                            } else if (storedChoice == 7) {
                                System.out.println("Returning to main menu.");

                            } else {
                                System.out.println("Invalid option. Try again.");
                            }
                        }  
             }else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}
}




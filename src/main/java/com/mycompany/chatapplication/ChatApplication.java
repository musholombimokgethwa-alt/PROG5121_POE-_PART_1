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
                
                System.out.println("Enter your surname :");
                user.loginPassword = input.nextLine();
                
                boolean loginSuccess = user.loginUser();
                
                System.out.println(user.returnLoginStatus(loginSuccess));
                
               
    }
}

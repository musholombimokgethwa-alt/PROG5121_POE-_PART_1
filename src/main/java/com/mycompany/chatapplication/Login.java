/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

/**
 *
 * @author musho
 */
public class Login {
        String username;
        String password;
        String phoneNumber;
        String name;
        String surname;
        
        public boolean checkUserName(){
            return username.contains("_")&& username.length()<=5;
            
        }
}

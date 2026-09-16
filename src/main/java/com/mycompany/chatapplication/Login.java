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
        public boolean checkPasswordComplexity(){
            return password.length() >=8 &&
                   password.matches(".*[A-Z].*")&&
                   password.matches(".*[0-9].*")&&
                   password.matches(".*[^a-zA-Z0-9].*");
        
        }
        public boolean checkCellPhoneNumber(){
            return phoneNumber.matches("^\\+27\\d{9}$");
        
        }
}

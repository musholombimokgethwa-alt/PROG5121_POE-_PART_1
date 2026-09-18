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
    public String username;
    public String password;
    public String phoneNumber;
    public String name;
    public String surname;
    public String loginUsername;
    public String loginPassword;
        
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
            // Source: Afshin Ghazi (2015), "Java regex phone number", Stack Overflow.
            // https://stackoverflow.com/questions/33477950/java-regex-phone-number
        }
        public String registerUser(){
            if(!checkUserName()){
                return "Username is not correctly formatted; please ensure that your "
                        + "username contains an underscore and is no more than five "
                        + "characters in length";
            
            }
            if (!checkPasswordComplexity()){
                return "Password is not correctly formatted please ensure that the password "
                        + "contains at least eight characters,a capital letter, a number, "
                        + "and a special character";
            }
            if (!checkCellPhoneNumber()){
                return"Cell phone number is incorrectly formatted or does not contain the international"
                        + "code";
            }
          
            return "Username successfully captured. "
                    +"Password successfully captured. "
                    +"Cell phone number successfully added";

        }
       public boolean loginUser(){
           return loginUsername.equals(username)&&
                   loginPassword.equals(password);
       }
       public String returnLoginStatus(boolean loginSuccess){
           if(loginSuccess){
               return "Welcome "+name +", "+surname
                       +" it is great to see you again.";
           }else{
               return "Username or password incorrect,please try again";
           }
       
       
       }
}

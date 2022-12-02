/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.conf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author lth7p
 */
public class RegexCheck {
    public static int checkLogin(String user,String pwd){
        String regExpn =
            "^(?=.*[0-9])(?=.*[a-z]).{1,}$";
        String regExpn1 =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        if(user.matches(regExpn)&&pwd.matches(regExpn1))
            return 1;
        return -1;
    }
}

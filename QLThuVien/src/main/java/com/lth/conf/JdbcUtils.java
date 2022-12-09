/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.conf;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lth7p
 */
public class JdbcUtils {
    private static Connection conn;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//truyen driver
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/qlthuvien", "root", "letienhung8602");
    }
}

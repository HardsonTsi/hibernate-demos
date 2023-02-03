package com.hardtech.hibernatedemos;


import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    @SneakyThrows
    public static void main(String[] args) {

        //Db url
        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernatedemos?useSSL=false";

        //Db credentials
        String user = "root";
        String password = "";

        //Db connection
        System.out.println("Connecting to database: " + jdbcUrl);
        Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
        System.out.println("Connection successfu!!!");

    }

}

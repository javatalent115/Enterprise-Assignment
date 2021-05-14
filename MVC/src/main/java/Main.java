package main.java;

import main.java.service.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        /**
         * Setup database if not exist
         */
//        Handler.setupDatabase();

        /**
         * Comment below line if u want to setup database. After finishing setting up, comment in above line and comment out below line
         */
        SpringApplication.run(Main.class,args);
    }
}

package assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        /**
         * Comment below line if u want to setup database. After finishing setting up, comment in above line and comment out below line
         */
        SpringApplication.run(Main.class,args);
    }
}

// **********************************************************************************************
// Program Name: HelloThere.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/2/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given 
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//      This program will prompt the user to enter their name. 
//      Then, it will display a greeting to the user with their first name only.
// **********************************************************************************************

import java.util.*;

public class HelloThere {
    public static void main(String[] args) {

        /* prompt user for their name */
        System.out.println("What is your name?");

        Scanner sc = new Scanner(System.in);
        String name = sc.next();

        /* display greeting to user */
        System.out.println("Hello " + name + "!");

    }//end main
}//end HelloThere
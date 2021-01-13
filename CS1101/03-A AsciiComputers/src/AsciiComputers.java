// **********************************************************************************************
// Program Name: AsciiComputers.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/15/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will print several Ascii objects, including happy and tired boys and girls.
// **********************************************************************************************

import java.util.*;

public class AsciiComputers {

    // prints top of tired PC (from leading newline to space after "Tired")
    public static void displayTiredTop() {
        System.out.println("");
        System.out.print("     Tired ");
    }

    // prints top of smiley PC (from leading newline to space after "Smiley")
    public static void displaySmileyTop() {
        System.out.println("");
        System.out.print("     Smiley ");
    }

    // prints header of girl (from "Girl" to line before eyes)
    public static void displayGirlHeader() {
        System.out.println("Girl");
        System.out.println("     ----------");
        System.out.println("  _   _");
        System.out.println(" | \\_/ |_________");
        System.out.println(" |_/-\\_|_______  |");
        System.out.println(" | |           | |");
    }

    // prints header of boy (from "Boy" to the line before eyes)
    public static void displayBoyHeader() {
        System.out.println("Boy");
        System.out.println("     ----------");
        System.out.println("  _______________ ");
        System.out.println(" |  ___________  |");
        System.out.println(" | |           | |");
    }

    // prints eyes to mouth of tired PC
    public static void displayTiredPC() {
        System.out.println(" | |   X   X   | |");
        System.out.println(" | |     -     | |");
        System.out.println(" | |    ___    | |");
    }

    // prints eyes to mouth of smiley PC
    public static void displaySmileyPC() {
        System.out.println(" | |   0   0   | |");
        System.out.println(" | |     -     | |");
        System.out.println(" | |   \\___/   | |");
    }

    // prints chin to bowtie of boy
    public static void displayBoyFooter() {
        System.out.println(" | |___     ___| |");
        System.out.println(" |_____|\\_/|_____|");
        System.out.println("   _|__|/ \\|_|_");
    }

    // prints chin and neck of girl
    public static void displayGirlFooter() {
        System.out.println(" | |___________| |");
        System.out.println(" |_______________|");
        System.out.println("   _|________|_");
    }

    // prints keyboard at the bottom
    public static void displayKeyboard() {
        System.out.println("   / ********** \\");
        System.out.println(" /  ************  \\");
        System.out.println("--------------------");
    }

    // combines 5 other methods to print the entire tired boy
    public static void displayTiredBoy() {
        displayTiredTop();
        displayBoyHeader();
        displayTiredPC();
        displayBoyFooter();
        displayKeyboard();
    }

    // prints the entire tired girl
    public static void displayTiredGirl() {
        displayTiredTop();
        displayGirlHeader();
        displayTiredPC();
        displayGirlFooter();
        displayKeyboard();
    }

    // prints the entire smiley boy
    public static void displaySmileyBoy() {
        displaySmileyTop();
        displayBoyHeader();
        displaySmileyPC();
        displayBoyFooter();
        displayKeyboard();
    }

    // prints the entire smiley boy
    public static void displaySmileyGirl() {
        displaySmileyTop();
        displayGirlHeader();
        displaySmileyPC();
        displayGirlFooter();
        displayKeyboard();
    }

    public static void main(String args[]) {
        displaySmileyGirl(); // prints smiley girl PC
        displaySmileyBoy(); // prints smiley boy PC
        displayTiredGirl(); // prints tired girl PC
        displayTiredBoy(); // prints tired boy PC
    }
}
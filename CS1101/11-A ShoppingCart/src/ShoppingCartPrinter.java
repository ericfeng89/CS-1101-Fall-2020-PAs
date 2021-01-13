// **********************************************************************************************
// Program Name: Parking.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 11/4/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will construct a shopping cart of two types of items, keeping
//                      track of price and quantity. Total cost is calculated at the end.
//************************************************************************************************

import java.util.Scanner;

public class ShoppingCartPrinter {

    /**
     * The main method will call several methods from
     * the ItemToPurchase class. It will use a scanner
     * to get the name, price, and quantity of two items,
     * then calculate (and print) the total cost at the end.
     * @param args
     * @return void
     */
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        ItemToPurchase item = new ItemToPurchase();

        System.out.println("Item 1");
        System.out.println("Enter the item name:");
        item.setName(scnr.nextLine());

        System.out.println("Enter the item price:");
        item.setPrice(scnr.nextInt());

        System.out.println("Enter the item quantity:");
        item.setQuantity(scnr.nextInt());

        String name1 = item.getName();
        int price1 = item.getPrice();
        int quantity1 = item.getQuantity();

        scnr.nextLine();

        System.out.println("\nItem 2");
        System.out.println("Enter the item name:");
        item.setName(scnr.nextLine());

        System.out.println("Enter the item price:");
        item.setPrice(scnr.nextInt());

        System.out.println("Enter the item quantity:");
        item.setQuantity(scnr.nextInt());

        String name2 = item.getName();
        int price2 = item.getPrice();
        int quantity2 = item.getQuantity();

        int total1 = quantity1 * price1;
        int total2 = quantity2 * price2;

        System.out.println("\nTOTAL COST");
        System.out.println(name1 + " " + quantity1 + " @ $"
                + price1 + " = $" + total1);
        System.out.println(name2 + " " + quantity2 + " @ $"
                + price2 + " = $" + total2);
        System.out.println("\nTotal: $" + (total1 + total2));
    }
}

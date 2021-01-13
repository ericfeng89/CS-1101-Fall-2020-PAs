// **********************************************************************************************
// File Name: PlaylistClient.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 11/22/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program allows the user to create a playlist, and to add, remove, and
//                      display songs in a playlist. This file is a client program that runs and
//                      tests the base/derived class hierarchy.

public class PlaylistClient {
    public static void main(String[] args) {
        PlaylistDerived myPlaylist2 = new PlaylistDerived();

        String result2 = myPlaylist2.toString();

        //test creating the empty playlist
        //*********************************************************
        System.out.println("***** Testing creating new playlist *****");
        PlaylistDerived myPlaylist = new PlaylistDerived();
        System.out.println(myPlaylist);
        System.out.println();

        //test adding various songs
        //*********************************************************
        System.out.println("***** Testing adding 6 songs ***** ");
        myPlaylist.addSong("All By Myself", "Eric Carmen", 0);
        myPlaylist.addSong("All By Myself", "Nadha Skolar", 0);
        myPlaylist.addSong("Java Paradisolation", "NaDHA Skolar", 0);
        myPlaylist.addSong("You Drive Me Crazy", "The Skolar Family", 0);
        myPlaylist.addSong("Be My Zoom Bae", "NAdha Skolar", 1);
        myPlaylist.addSong("Sad Sitch", "Stellar Skolar", 0);
        System.out.println(myPlaylist.toString());

        //test adding duplicate songs
        //*********************************************************
        System.out.println("***** Testing adding duplicate songs ***** ");

        //not a duplicate but close -- should be able to add this
        if (myPlaylist.addSong("Be My Zoom Bae", "nadha SKOLAR", 0) != -1) {
            System.out.println("SUCCESS: Near duplicate song Be My Zoom Bae (genre 0) successfully added");
        } else {
            System.out.println("TEST FAIL: Close to duplicate song Be My Zoom Bae (genre 0) was not added");
        }

        //now it's a duplicate song --should not add
        if (myPlaylist.addSong("Be My Zoom Bae", "Nadha Skolar", 1) == -1) {
            System.out.println("SUCCESS: Duplicate song Be My Zoom Bae (genre 0) not added");
        }
        else {
            System.out.println("TEST FAIL: Duplicate song Be My Zoom Bae (genre 0) was added");
        }

        //duplicate song
        if (myPlaylist.addSong("Java Paradisolation", "Nadha SkolAR", 0) == -1) {
            System.out.println("SUCCESS: Duplicate song Java Paradisolation (genre 0) not added");
        }
        else {
            System.out.println("TEST FAIL: Duplicate song Java Paradisolation (genre 0) was added");
        }

        //visually verify playlist
        System.out.println("");
        System.out.println(myPlaylist);

        //test removing songs
        //*********************************************************
        System.out.println("***** Testing removing existing and non-existing song ***** ");

        //existing song - should remove
        if (myPlaylist.removeSong("Java Paradisolation", "Nadha Skolar", 0) == -1) {
            System.out.println("TEST FAIL: Existing song Java Paradisolation (genre 0) could not be removed.");
        }else {
            System.out.println("SUCCESS: Existing song Java Paradisolation (genre 0) successfully removed");
        }

        //existing song - should remove
        if (myPlaylist.removeSong("All By Myself", "Eric Carmen", 0) == -1) {
            System.out.println("TEST FAIL: Existing song All By Myself (genre 0) was not removed");
        }
        else {
            System.out.println("SUCCESS: Existing song All By Myself (genre 0) successfully removed");
        }

        //not existing song - should not be able to remove
        if (myPlaylist.removeSong("All By Myself", "Eric Carmen", 1) != -1) {
            System.out.println("TEST FAIL: Non-existing song All By Myself (wrong genre) was removed");
        }
        else {
            System.out.println("SUCCESS: Non-existing All By Myself (genre 1) could not be removed.");
        }

        //visually verify playlist
        System.out.println("");
        System.out.println(myPlaylist.toString());

        //test custom playlist - artist
        //*********************************************************
        System.out.println("Testing custom playlist for non-existing artist: Scholar");
        System.out.println(myPlaylist.customPlayList("Scholar"));
        System.out.println();

        System.out.println("***** Testing custom playlist for existing artist: Nadha Skolar ***** ");
        System.out.println(myPlaylist.customPlayList("Nadha Skolar"));

        System.out.println("Testing custom playlist for existing artist: Skolar");
        System.out.println(myPlaylist.customPlayList("Skolar"));

        //test overloaded custom playlist - genre
        //*********************************************************
        System.out.println("***** Testing custom playlist for non-existing genre 9 ***** ");
        System.out.println(myPlaylist.customPlayList(9));
        System.out.println();

        System.out.println("***** Testing custom playlist for genre 0 ***** ");
        System.out.println(myPlaylist.customPlayList(0));

        //test overflowing the playlist with too many songs -- should throw exception
        //*********************************************************
        System.out.println("***** Testing overflowing the playlist ***** ");
        System.out.println(myPlaylist.toString());
        System.out.println("Testing overflowing by adding several songs to demonstrate throws exception");
        for (int i=1; i<=10; i++) {
            String songTitle = "AddMe" + i;
            myPlaylist.addSong(songTitle, "Nadha Skolar", 1);
        }
    }
}
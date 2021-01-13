// **********************************************************************************************
// File Name: SongBase.java
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
//                      display songs in a playlist. This class provides the base class definition
//                      for a song. Client program that runs and tests the base/derived class
//                      hierarchy.
//************************************************************************************************

public class SongBase {
    /**
     * Title, artist, and genre of the song.
     */
    private String title;
    private String artist;
    private int genre;

    /**
     * Default constructor that initializes the values
     * of title, artist, and genre.
     */
    public SongBase() {
        this.title = "";
        this.artist = "";
        this.genre = -1;
    }

    /**
     * Overloaded constructor
     * @param title : title of song
     * @param artist : name of artist
     * @param genre : genre (represented by integer)
     */
    public SongBase(String title, String artist, int genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    /**
     * Sets title to parameter.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets artist to parameter.
     * @param artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Sets genre to parameter.
     * @param genre
     */
    public void setGenre(int genre) {
        this.genre = genre;
    }

    /**
     * Gets title.
     * @return String : the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets artist.
     * @return String : the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets genre.
     * @return int : numerical representation of genre.
     */
    public int getGenre() {
        return genre;
    }

    /**
     * Gets the name of the genre using the
     * genre number
     * @return String : name of the genre
     */
    public String getGenreName() {
        if (genre == 0) {
            return "Quarantine";
        }
        else if (genre == 1) {
            return "Dance";
        }
        else if (genre == 2) {
            return "Romance";
        }

        // return empty string if number doesn't match any
        else {
            return "";
        }
    }

    /**
     * Using the title, artist, and genre name,
     * creates a 3-row formatted string (representing
     * one song).
     * @return String : the formatted song
     */
    public String toString() {
        return "Title: " + title + "\nArtist: " + artist +
                "\nGenre: " + getGenreName();
    }

    /**
     * Compares the titles, authors, and genres of
     * two SongBase objects. Returns true if all three
     * match, case insensitive.
     * @param other : a SongBase object
     * @return boolean
     */
    public boolean equals(SongBase other) {
        return other.getTitle().toLowerCase().equals(title.toLowerCase()) && other.getArtist()
                .toLowerCase().equals(artist.toLowerCase()) && other.getGenre() == genre;
    }
}


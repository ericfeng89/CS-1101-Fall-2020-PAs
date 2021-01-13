// **********************************************************************************************
// File Name: PlaylistDerived.java
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
//                      display songs in a playlist. This file extends the base class definition
//                      to add functionality for a playlist of several songs.

public class PlaylistDerived {
    // playlist maximum size
    private final int MAXSIZE = 8;

    // array of SongBase objects
    private SongBase[] playlist = new SongBase[MAXSIZE];

    // count keeps track of # of songs in playlist
    private int count;

    /**
     * Default constructor
     */
    public PlaylistDerived() {
        this.count = 0;
    }

    /**
     * Gets the # of songs in playlist
     * @return number of songs
     */
    public int getCount() {
        return count;
    }

    /**
     * Adds a new song to the playlist at the next available
     * location in the playlist array and then increments the
     * instance variable count by 1. Returns 0 if song is
     * successfully added to the playlist and returns a -1
     * if the song is a duplicate of a song that already exists.
     * @param songTitle : title of the song
     * @param songArtist : name of the artist
     * @param songGenre : genre of the song
     * @return either a 0 (if successfully added) or -1 (duplicate).
     */
    public int addSong(String songTitle, String songArtist, int songGenre) {
        SongBase newSong = new SongBase(songTitle, songArtist, songGenre);
        int index = 0;

        // keep iterating until next empty slot found
        while (playlist[index] != null) {
            index++;

            // throw exception if no empty slots left
            if (index >= MAXSIZE) {
                throw new ArrayIndexOutOfBoundsException("Cannot add: " + songTitle +
                        " Maximum " + "playlist size is " + MAXSIZE);
            }
        }

        // if duplicate, return -1
        if (findSong(newSong) == -1) {
            return -1;
        }
        else {

            // add new song to playlist, then increment count
            playlist[index] = newSong;
            count++;
            return 0;
        }
    }

    /**
     *  Removes a specific song from the playlist and then
     *  decrements the count of songs in the playlist by 1.
     *  Returns a 0 if the song is successfully deleted and
     *  returns a -1 if the song is not found in the playlist.
     * @param songTitle : title of the song
     * @param songArtist : name of the artist
     * @param songGenre : genre of the song
     * @return either a 0 (if successfully removed) or -1 (not found).
     */
    public int removeSong(String songTitle, String songArtist, int songGenre) {
        SongBase songToRemove = new SongBase(songTitle, songArtist, songGenre);

        // if song not found...
        if (findSong(songToRemove) == 0) {
            return -1;
        }
        else {
            for (int i = 0; i < MAXSIZE; ++i) {
                if (songToRemove.equals(playlist[i])) {
                    moveSongsUp(i);
                    break;
                }
            }

            // decrement count
            count--;
            return 0;
        }
    }

    /**
     * This helper method helps the removeSongs()
     * method by moving all the songs up one position
     * in the playlist based on the position of the
     * song that is to be "deleted".
     * @param position : the index of the deleted song
     */
    public void moveSongsUp(int position) {
        for (int i = position; i < MAXSIZE - 1; ++i) {
            playlist[i] = playlist[i+1];
        }

        // last spot should always be null
        playlist[MAXSIZE-1] = null;
    }

    /**
     * Searches through the playlist to see if a song
     * exists using the SongBase .equals() method.
     * @param songToFind : the song
     * @return -1 if the song already exists, 0 if not
     */
    public int findSong(SongBase songToFind) {
        for (int i = 0; i < MAXSIZE; ++i) {

            // if song already exists, return -1
            if (playlist[i] != null && songToFind.equals(playlist[i])) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * This method searches the playlist for all songs
     * containing this artist's name (ignore case) in
     * the artist field.
     * @param artist : the name of the artist
     * @return printable string of the list of songs
     */
    public String customPlayList(String artist) {
        String headingText = "";
        String sameArtistSongs = "";
        int cnt = 0;

        for (int i = 0; i < MAXSIZE; ++i) {
            if (playlist[i] != null) {

                // if the keyword matches, append matching songs to string list
                if (playlist[i].getArtist().toLowerCase().contains(artist.toLowerCase())) {
                    cnt++;
                    sameArtistSongs += "\n" + cnt + ". " + playlist[i].toString() + "\n";
                }
            }
        }

        if (cnt == 0) {
            return "No songs by this artist found";
        }

        // Heading text
        headingText = "Artist Search: " + artist + " (" + cnt + " songs )\n";

        return headingText + sameArtistSongs;
    }

    /**
     * Overloaded method that accepts an int
     * as a parameter rather than a string.
     * @param genre : genre to search for
     * @return printable string of the list of songs
     */
    public String customPlayList(int genre) {
        String headingText = "";
        String sameGenreSongs = "";
        int cnt = 0;

        for (int i = 0; i < MAXSIZE; ++i) {
            if (playlist[i] != null) {

                // if the genre numbers match, append to string list
                if (playlist[i].getGenre() == genre) {
                    cnt++;
                    sameGenreSongs += "\n" + cnt + ". " + playlist[i].toString() + "\n";
                }
            }
        }

        if (cnt == 0) {
            return "No songs in this genre found";
        }

        // create object with only genre known, used to access getGenreName()
        SongBase genreOnly = new SongBase("", "", genre);

        headingText = "Genre Search: " + genreOnly.getGenreName() + " ("
                + cnt + " songs )\n";

        return headingText + sameGenreSongs;
    }

    /**
     * Overrides the default toString() method for
     * printing an object. It returns a string with
     * the appropriate information for a complete
     * playlist by repeatedly calling the base class
     * toString() method.
     * @return printable numbered playlist
     */
    public String toString() {
        String headingText = "";
        String playlistText = "";
        int cnt = 0;

        for (int i = 0; i < MAXSIZE; ++i) {
            if (playlist[i] != null) {
                cnt++;
                playlistText += "\n" + (i+1) + ". " + playlist[i].toString() + "\n";
            }
        }
        if (cnt == 0) {
            headingText = "Playlist is empty";
        }
        else {
            // print custom heading if not empty
            headingText = "My Playlist (count of songs = " + cnt + ")\n";
        }
        return headingText + playlistText;
    }
}

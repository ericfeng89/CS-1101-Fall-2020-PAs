// **********************************************************************************************
// Program Name: WhoAmI.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/29/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will read in a CSV file and print out some statistics about
//                      the students. Data from students with invalid entries will be ignored.
//************************************************************************************************

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentsFile {
    public static final String FILENAME = "Students.csv";
    public static final String F1 = "Freshman.txt";
    public static final String F2 = "Sophomore.txt";
    public static final String F3 = "Junior.txt";
    public static final String F4 = "Senior.txt";
    public static final String FBAD = "Bad.txt";
    public static final double EPSILON = 0.0001;

    public static void main(String [] args) throws FileNotFoundException, IOException {
        Scanner scnr = new Scanner(System.in);

        // create file object
        File f = getInputFile(scnr);
        printHeader(f);

        String fullRows[] = new String[getNumRows(f)];

        int validRowCnt = countValidRows(f, fullRows);

        String cleanRows[] = new String[validRowCnt];
        cleanRows = makeCleanRows(f, fullRows, validRowCnt);
        String cleanRowsString = Arrays.toString(cleanRows).replaceAll("\\[|\\]|\\s", "");

        int numRejected = fullRows.length - validRowCnt;
        printAnalyzedRejected(fullRows.length, numRejected);

        // second scanner for reading file
        Scanner input = new Scanner(cleanRowsString);
        input.useDelimiter("[, @]");

        String fullNameColumn[] = new String[validRowCnt];
        String uidColumn[] = new String[validRowCnt];
        double gpaColumn[] = new double[validRowCnt];
        int yearColumn[] = new int[validRowCnt];
        char class1[] = new char[validRowCnt];
        char class2[] = new char[validRowCnt];
        char class3[] = new char[validRowCnt];

        for (int i = 0; i < validRowCnt; ++i) {
            fullNameColumn[i] = input.next();

            input.next();

            uidColumn[i] = input.next();
            gpaColumn[i] = input.nextDouble();
            yearColumn[i] = input.nextInt();
            class1[i] = input.next().charAt(0);
            class2[i] = input.next().charAt(0);
            class3[i] = input.next().charAt(0);
        }

        double firstGPA = calcClassGPA(validRowCnt, class1, gpaColumn);
        double secondGPA = calcClassGPA(validRowCnt, class2, gpaColumn);
        double thirdGPA = calcClassGPA(validRowCnt, class3, gpaColumn);

        printAverageGPA(firstGPA, secondGPA, thirdGPA);

        String allNoFr[] = new String[validRowCnt];
        String allNoSo[] = new String[validRowCnt];
        String allNoJr[] = new String[validRowCnt];
        String allNoSr[] = new String[validRowCnt];

        int frCount = getAllNoCountByGrade(validRowCnt, class1, class2, class3, yearColumn, 1);
        int soCount = getAllNoCountByGrade(validRowCnt, class1, class2, class3, yearColumn, 2);
        int jrCount = getAllNoCountByGrade(validRowCnt, class1, class2, class3, yearColumn, 3);
        int srCount = getAllNoCountByGrade(validRowCnt, class1, class2, class3, yearColumn, 4);

        allNoFr = getNames(validRowCnt, class1, class2, class3, yearColumn, 1, fullNameColumn);
        allNoSo = getNames(validRowCnt, class1, class2, class3, yearColumn, 2, fullNameColumn);
        allNoJr = getNames(validRowCnt, class1, class2, class3, yearColumn, 3, fullNameColumn);
        allNoSr = getNames(validRowCnt, class1, class2, class3, yearColumn, 4, fullNameColumn);

        printAllNoTableHeader(frCount, soCount, jrCount, srCount);
        printNames(validRowCnt, allNoFr, allNoSo, allNoJr, allNoSr);

        String invalidRows[] = new String[numRejected];
        invalidRows = makeInvalidRows(f, fullRows, numRejected);

        String pseudoFile = Arrays.toString(invalidRows).replaceAll("\\[|\\]|", "");
        pseudoFile = pseudoFile.replaceAll("\\s", "\n");

        Scanner nameDetector = new Scanner(pseudoFile);
        nameDetector.useDelimiter("[. @]");

        writeBadFile(nameDetector, numRejected);

        writeAllNoFile(nameDetector, frCount, F1, allNoFr);
        writeAllNoFile(nameDetector, soCount, F2, allNoSo);
        writeAllNoFile(nameDetector, jrCount, F3, allNoJr);
        writeAllNoFile(nameDetector, srCount, F4, allNoSr);
    }

    /**
     * Gets the path of the file from the user and appends
     * it to the file name. Keep prompting the user until
     * the file under this path exists, and return the
     * file object.
     * @param object: scanner object
     * @return object: the file object containing data
     */
    public static File getInputFile(Scanner console) {
        String path;
        String pathAndFile;

        // prompt user
        System.out.print("Enter path for file \"" + FILENAME + "\" (e.g. allFiles/)? ");
        path = console.nextLine();

        // combine path with file name
        pathAndFile = path + FILENAME;

        // create file object
        File f = new File(pathAndFile);

        // keep prompting until file exists under inputted path
        while (!f.exists()) {
            System.out.println("File does not exist. Please try again.");
            System.out.print("Enter path for file \"" + FILENAME + "\"? ");

            path = console.nextLine();
            pathAndFile = path + FILENAME;

            f = new File(pathAndFile);
        }
        System.out.println(FILENAME + " successfully found.");
        return f;
    }

    /**
     * Prints the heading of the printed table;
     * states whether file can be read and the
     * length of the file.
     * @param object : file object containing data
     * @return void
     */
    public static void printHeader(File f) {
        System.out.println("Can be read: " + f.canRead());
        System.out.println("Length: " + f.length());
        System.out.println("\nStudent file Statistics");
        System.out.print("---------------------------------------------");
        System.out.println("-------------------------------------------");
    }

    /**
     * Checks the given row to see if it is valid.
     * Calls several functions that verify each
     * piece of data one by one, and if any is invalid,
     * then the entire line is invalid
     * @param string : the row of data
     * @return boolean : whether or not row is valid
     */
    public static boolean isValidRow(String currentRow) {
        Scanner lineSplitter = new Scanner(currentRow);

        lineSplitter.useDelimiter("[,]");

        if (!isValidEmail(lineSplitter)) {
            return false;
        }
        else if (!isValidUID(lineSplitter)) {
            return false;
        }
        else if (!isValidGPA(lineSplitter)) {
            return false;
        }
        else if (!isValidYear(lineSplitter)) {
            return false;
        }
        else if (!isValidYN(lineSplitter)) {
            return false;
        }
        else if (!isValidYN(lineSplitter)) {
            return false;
        }
        else if (!isValidYN(lineSplitter)) {
            return false;
        }
        return true;
    }

    /**
     * Checks email to see if it is valid. Makes sure the
     * email is not numerical, having only alphabetical
     * characters in it. Makes sure it ends in "vanderbilt.edu".
     * @param object : scanner object
     * @return boolean : whether or not email is valid
     */
    public static boolean isValidEmail(Scanner scnr) {
        String email;
        String first;
        String last;
        String ending;

        if (scnr.hasNext() && !scnr.hasNextDouble()) {
            email = scnr.next();

            Scanner nameScanner = new Scanner(email);
            nameScanner.useDelimiter("[. @]");

            if (nameScanner.hasNext()) {
                first = nameScanner.next();

                if (!isAlpha(first)) {
                    return false;
                }

                if (nameScanner.hasNext()) {
                    last = nameScanner.next();

                    if (!isAlpha(last)) {
                        return false;
                    }
                    if (nameScanner.hasNext() && nameScanner.next().equals("vanderbilt")) {
                        if (nameScanner.next().equals("edu")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks UID to see if it is valid.
     * It needs to be a text (non-numeric) value.
     * @param object : scanner object
     * @return boolean : whether or not the UID is valid
     */
    public static boolean isValidUID(Scanner scnr) {
        if (scnr.hasNext() && !scnr.hasNextDouble()) {
            scnr.next();

            return true;
        }
        return false;
    }

    /**
     * Checks the GPA to see if it is valid.
     * Must be a double between 0 and 4.
     * Tolerance of .0001 to account for inexact
     * floating-point numners.
     * @param object : scanner object
     * @return boolean : whether or not GPA is valid
     */
    public static boolean isValidGPA(Scanner scnr) {
        double gpa;

        if (scnr.hasNextDouble()) {
            gpa = scnr.nextDouble();

            if (gpa >= (0 - EPSILON) && gpa < (4 + EPSILON)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the grade level to see if it is valid.
     * Must be an integer between 1 and 4.
     * @param object : scanner object
     * @return boolean : whether or not the grade level is valid.
     */
    public static boolean isValidYear(Scanner scnr) {
        int year;

        if (scnr.hasNextInt()) {
            year = scnr.nextInt();

            if (year >= 1 && year <= 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * For yes/no questions, determines if the data
     * is valid. Must be a character Y or N, case
     * insensitive.
     * @param object : scanner object
     * @return boolean : whether or not it's a Y or N
     */
    public static boolean isValidYN(Scanner scnr) {
        char yesOrNo;

        if (scnr.hasNext() && !scnr.hasNextDouble()) {
            yesOrNo = Character.toUpperCase(scnr.next().charAt(0));

            if (yesOrNo == 'Y' || yesOrNo == 'N') {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the number of rows of a file.
     * @param object : file object
     * @return int : the number of rows
     */
    public static int getNumRows(File f) throws FileNotFoundException {
        Scanner rowReader = new Scanner(f);
        int numRows = 0;

        while (rowReader.hasNextLine()) {
            rowReader.nextLine();
            numRows++;
        }
        return numRows;
    }

    /**
     * Determines if every single character in
     * a string is an alphabetical character.
     * @param string : a piece of text
     * @return boolean : whether all characters are alphabetical
     */
    public static boolean isAlpha(String text) {
        for (int i = 0; i < text.length(); ++i) {
            if ((text.charAt(i) < 'a' || text.charAt(i) > 'z') &&
                    (text.charAt(i) < 'A' || text.charAt(i) > 'Z')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Counts the amount of valid rows in the data.
     * Checks all rows in the list, using isValidRow()
     * function to determine which rows are invalid.
     * Tallies up and returns number of valid rows.
     * @param object : file object (containing the text)
     * @param string array : the list to be checked
     * @return int : the number of valid rows
     */
    public static int countValidRows(File f, String [] list) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        int validRowCount = 0;

        for (int i = 0; i < list.length; ++i) {
            list[i] = sc.nextLine();

            if (isValidRow(list[i])) {
                validRowCount++;
            }
        }
        return validRowCount;
    }

    /**
     * Takes in a list with a mix of valid
     * and invalid rows and removes the invalid ones,
     * returning a new list with solely valid rows. Calls
     * isValidRow() to help determine invalid rows.
     * @param object : file object
     * @param string array : mixed/unrefined list
     * @param int : # of rows that the refined list will have
     * @return string array : the refined list
     */
    public static String [] makeCleanRows(File f, String [] dirtyList, int rowCount)
            throws FileNotFoundException {
        int validIndexCount = 0;

        Scanner input = new Scanner(f);

        String cleanRows[] = new String[rowCount];

        for (int i = 0; i < dirtyList.length; ++i) {
            dirtyList[i] = input.nextLine();

            if (isValidRow(dirtyList[i])) {
                cleanRows[validIndexCount] = dirtyList[i];
                validIndexCount++;
            }
        }
        return cleanRows;
    }

    /**
     * Does opposite of makeCleanRows():
     * Takes in a list with a mix of valid
     * and invalid rows and removes VALID ones,
     * returning a new list with solely INVALID rows.
     * Calls isValidRow() to help determine invalid rows.
     * @param object : file object
     * @param string array : mixed/unrefined list
     * @param int : # of rows that the invalid list will have
     * @return string array : the invalid list
     */
    public static String [] makeInvalidRows(File f, String [] mixedList,
                                            int numRejected) throws FileNotFoundException {
        int invalidIndexCount = 0;

        Scanner input = new Scanner(f);

        String invalidRows[] = new String[numRejected];

        for (int i = 0; i < mixedList.length; ++i) {
            mixedList[i] = input.nextLine();

            if (!isValidRow(mixedList[i])) {
                invalidRows[invalidIndexCount] = mixedList[i];
                invalidIndexCount++;
            }
        }
        return invalidRows;
    }

    /**
     * Prints the middle part of the table,
     * including the amount of records analyzed
     * and rejected.
     * @param int : length of the unrefined data list (# of records analyzed)
     * @param int : number of records rejected
     * @return void
     */
    public static void printAnalyzedRejected(int arrayLength, int numRejected) {
        System.out.print("Total number of records analyzed: [");
        System.out.printf("%3d", arrayLength);
        System.out.print("]    rejected: [");
        System.out.printf("%3d", numRejected);
        System.out.println("]\n");
    }

    /**
     * Prints the average GPA of the students
     * taking each CS class. If the GPA cannot
     * be calculated, prints a dash.
     * @param double : the average GPA for cs1101.
     * @param double : the average GPA for cs1104.
     * @param double : the average GPA for cs2212.
     * @return void
     */
    public static void printAverageGPA(double firstGPA, double secondGPA, double thirdGPA) {
        System.out.println("Average GPA per class:  cs1101    cs1104    cs2212");
        System.out.print("                          ");
        if (firstGPA == 0) {
            System.out.print("   -      ");
        }
        else {
            System.out.printf("%3.2f      ", firstGPA);
        }
        if (secondGPA == 0) {
            System.out.print("   -      ");
        }
        else {
            System.out.printf("%3.2f      ", secondGPA);
        }
        if (thirdGPA == 0) {
            System.out.println("   -      ");
        }
        else {
            System.out.printf("%3.2f\n", thirdGPA);
        }
    }

    /**
     * Calculates the average GPA of the students taking
     * a given class. If the student is taking the class,
     * take their GPA and add it to the total. Then divide
     * by the total amount of people taking the class to
     * get the average GPA.
     * @param int : the amount of rows in the student list
     * @param char array : array of Y's and N's (did student take the class?)
     * @param double array : list of GPAs of all students
     * @return double : the average GPA of students taking the class
     */
    public static double calcClassGPA(int rowCount, char [] classList, double [] gpaColumn) {
        double avgGPA = 0;
        double cumGPA = 0;
        int totalStudents = 0;

        for (int i = 0; i < rowCount; ++i) {
            if (classList[i] == 'Y') {
                cumGPA += gpaColumn[i];

                totalStudents++;
            }
        }
        avgGPA = cumGPA / totalStudents;

        return avgGPA;
    }

    /**
     * Prints the header of the table with all of the
     * students taking none of the CS classes.
     * @param int : amount of freshmen taking none of the classes
     * @param int : amount of sophomores taking none of the classes
     * @param int : amount of juniors taking none of the classes
     * @param int : amount of juniors taking none of the classes
     * @return void
     */
    public static void printAllNoTableHeader(int frCount, int soCount,
                                             int jrCount, int srCount) {
        System.out.println("\nStudents not registered in CS1101 nor CS1104 nor CS2212");
        System.out.print("---------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.print("Freshman [");
        System.out.printf("%2d", frCount);
        System.out.print("]            Sophomore [");
        System.out.printf("%2d", soCount);
        System.out.print("]           Junior [");
        System.out.printf("%2d", jrCount);
        System.out.print("]              Senior [");
        System.out.printf("%1d", srCount);
        System.out.println("]");
        System.out.print("---------------------------------------------");
        System.out.println("-------------------------------------------");
    }

    /**
     * Gets the amount of people in a given grade
     * who did not take any of the three CS classes.
     * @param int : amount of rows in the list of students
     * @param char array : Y's and N's for the first class.
     * @param char array : Y's and N's for the second class.
     * @param char array : Y's and N's for the third class.
     * @param int array : list of grade levels of all the students.
     * @param int : the given grade level that we want our data for.
     * @return int : number of students in the particular grade
     * who did not take any of the three classes.
     */
    public static int getAllNoCountByGrade(int rowCount, char [] class1, char [] class2,
                                           char [] class3, int [] yearColumn, int grade) {
        int count = 0;

        for (int i = 0; i < rowCount; ++i) {
            if (class1[i] == 'N' && class2[i] == 'N' && class3[i] == 'N') {
                if (yearColumn[i] == grade) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Gets the names of the students of a given
     * grade level, who did not take any of the
     * three classes. Returns the list of students.
     * @param int : amount of rows in the list of students
     * @param char array : Y's and N's for the first class.
     * @param char array : Y's and N's for the second class.
     * @param char array : Y's and N's for the third class.
     * @param int array : list of grade levels of all the students.
     * @param int : the given grade level that we want our data for.
     * @param string array : the list of full names (first.last).
     * @return string array : the list of the names for that grade.
     */
    public static String [] getNames(int rowCount, char [] class1, char [] class2,
                                     char [] class3, int [] yearColumn, int grade, String [] fullNameColumn) {
        int count = 0;
        String [] studentList = new String[rowCount];

        for (int i = 0; i < rowCount; ++i) {
            studentList[i] = "";
        }

        for (int j = 0; j < rowCount; ++j) {
            if (class1[j] == 'N' && class2[j] == 'N' && class3[j] == 'N') {
                if (yearColumn[j] == grade) {
                    studentList[count] = fullNameColumn[j];

                    count++;
                }
            }
        }
        return studentList;
    }

    /**
     * Prints formatted list of students who did not
     * take any of the three classes in columns
     * organized by grade.
     * @param int : amount of rows in the list of students
     * @param string array : list of freshmen who didn't take any class
     * @param string array : list of sophomores who didn't take any class
     * @param string array : list of juniors who didn't take any class
     * @param string array : list of seniors who didn't take any class
     * @return void
     */
    public static void printNames(int rowCount, String [] fr, String [] so,
                                  String [] jr, String [] sr) {
        for (int i = 0; i < rowCount; ++i) {
            if (fr[i].equals("") && so[i].equals("") && jr[i].equals("")
                    && sr[i].equals("")) {

                return;
            }
            System.out.printf("%-25s", fr[i]);
            System.out.printf("%-25s", so[i]);
            System.out.printf("%-25s", jr[i]);
            System.out.printf("%-25s\n", sr[i]);
        }
    }

    /**
     * Writes a list of all the names, first and last,
     * of the students with invalid data to the file
     * Bad.txt
     * @param object : scanner object
     * @param int : length of the list of invalid student data,
     * which determines how many times we should loop through
     * @return void
     */
    public static void writeBadFile(Scanner scnr, int numIterations)
            throws FileNotFoundException, IOException {
        FileOutputStream fileStream = new FileOutputStream(FBAD);
        PrintWriter outFS = new PrintWriter(fileStream);
        for (int i = 0; i < numIterations; ++i) {
            outFS.print(scnr.next() + " ");
            outFS.println(scnr.next());
            scnr.nextLine();
        }
        fileStream.close();
    }

    /**
     * Writes a list of emails to a given file.
     * @param object : scanner object
     * @param int : length of the list of invalid student data,
     * which determines how many times we should loop through
     * @param string : name of the file
     * @param string array : list of names for a given grade
     * @return void
     */
    public static void writeAllNoFile(Scanner scnr, int numIterations, String fileName,
                                      String [] gradeList) throws FileNotFoundException, IOException {
        FileOutputStream fileStream = new FileOutputStream(fileName);
        PrintWriter outFS = new PrintWriter(fileStream);
        for (int i = 0; i < numIterations; ++i) {
            outFS.println(gradeList[i] + "@vanderbilt.edu");
        }
        fileStream.close();
    }
}
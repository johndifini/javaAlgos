import java.util.*;
import java.io.*;


/**
 * Naming Convention - ExocticEngineer was too long :-)
 * 
 * @see https://www.careercup.com/question?id=5713445122998272
 */
public class Stripper {
public static void removeDupes(String filename, int chunkSize) throws IOException {
                //@todo Validate user input. Filename can't be null; Chunk size has to be >=1


                // Note - BufferedReader is NOT loading the entire file into mem
BufferedReader reader = new BufferedReader(new FileReader(filename));
Set<String> uniqueLines = new HashSet<String>(chunkSize);
List<Set<String>> chunks = new ArrayList<Set<String>>();
String line;
while((line = reader.readLine()) != null) {
uniqueLines.add(line);
if(uniqueLines.size() == chunkSize) {
        // Save a copy of the lines using copy constructor
chunks.add(new HashSet<String>(uniqueLines));
                uniqueLines.clear();
}
}
reader.close();


// If size of uniqueLines is not equal to chunkSize, we haven't saved it yet
if(uniqueLines.size() != chunkSize) {
chunks.add(new HashSet<String>(uniqueLines));
        uniqueLines.clear();
}


// @todo init list size x chunkSize
Set<String> consolidated = new HashSet<String>(chunkSize);
for(Set<String> buff : chunks) {
        consolidated.addAll(buff);
}


for(String congo : consolidated) {
        System.out.println("<"+congo+">");
}
}


public static void main(String[] args) throws IOException {
        // How many lines do we want to hold in memory?
        // 2^13 = 8,192 seems like a good nbr for non-test cases
final int chunkSize = 2;
final String filename = "Stripper.txt";
BufferedWriter writer;
int testCaseNbr = 1;


        // Test Case - Simple one
        System.out.println("Test Case Nbr<"+testCaseNbr+">");
writer = new BufferedWriter(new FileWriter(filename));
writer.write("This is my line.\n");
writer.write("There are many like it,\n");
writer.write("but this one is mine.\n");
writer.write("\n");
writer.write("This is my line.\n"); // DUPE
writer.close();
removeDupes(filename, chunkSize);
testCaseNbr++;
System.out.println("\n\n\n");


        // Test Case - Tofor:  Just 1 line with no newline char,
        // and chunkSize is greater than nbr of lines
        System.out.println("Test Case Nbr<"+testCaseNbr+">");
writer = new BufferedWriter(new FileWriter(filename));
writer.write("This is my line with no newline character.");
writer.close();
removeDupes(filename, chunkSize);
testCaseNbr++;
System.out.println("\n\n\n");


// Test Case - Multiple dupes
        System.out.println("Test Case Nbr<"+testCaseNbr+">");
writer = new BufferedWriter(new FileWriter(filename));
writer.write("This is my line.\n");
writer.write("\n");
writer.write("There are many like it,\n");
writer.write("There are many like it,\n");
writer.write("\n");
writer.write("but this one is mine.\n");
writer.close();
removeDupes(filename, chunkSize);
testCaseNbr++;
System.out.println("\n\n\n");


// Test Case - Empty File
        System.out.println("Test Case Nbr<"+testCaseNbr+">");
writer = new BufferedWriter(new FileWriter(filename));
writer.close();
removeDupes(filename, chunkSize);
testCaseNbr++;
System.out.println("\n\n\n");


// Test Case - Nbr of lines = Chunk Size
        System.out.println("Test Case Nbr<"+testCaseNbr+">");
writer = new BufferedWriter(new FileWriter(filename));
writer.write("This is my line.\n");
writer.write("There are many like it,\n");
writer.close();
removeDupes(filename, chunkSize);
testCaseNbr++;
System.out.println("\n\n\n");


}
}
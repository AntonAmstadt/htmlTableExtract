package htmlTableExtract;

import java.io.File;
import java.lang.StringBuilder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		
		double total = 0; // used to verify percentages add up to ~100%
		
		File inputFile = new File("input.txt");
		try (Scanner in = new Scanner(inputFile); PrintWriter out = new PrintWriter("outputBigrams.txt")){
		in.useDelimiter(""); // read file one character at a time
		LinkedList<Character> fileChars = new LinkedList<>();//
		while(fileChars.size() < 9) {
			if(in.hasNext()) {
				fileChars.add(in.next().charAt(0));
			}
			else {
				break;
			}
		}
		//these indicator characters are the 9 characters that immediately precede the info we care about in the file
		char[] indicator = {'d',' ','t','i','t','l','e','=','\"'};
		
		while(in.hasNext()) {
			int counter = 0; // counts number of consecutive matches with indicator characters
			//if counter gets to 9, we have a match with indicator
			while(counter < 9) {
				if(indicator[counter] == fileChars.pop()) { //removes first element of fileChars
					counter++; // the next first element of fileChars must match the next element of indicator for it to be a match
				}
				else {
					counter = 0;
				}
				if(in.hasNext()) {
					fileChars.add(in.next().charAt(0));
				}
				else {
					counter = 100; //end the loop because file ended
				}
				
			
			}
			if(fileChars.size() == 9) { //prevents nonsense at end of file because the previous loop stops with  fileChars.size() = 8 at end of file
				//at this point, we have a linked list that contains a bigram and its frequency
				//elements 0 and 1 are the bigram, elements 4-8 are the frequency
				
				//uncomment the following section to output the total percentage of all the bigram frequencies
				//start uncomment line below
//				StringBuilder build = new StringBuilder();
//				for (int i = 4; i < 9; i++) {
//					build.append(fileChars.get(i));
//				}
//				String number = build.toString();
//				try {
//					total += Double.parseDouble(number);
//				} catch(NumberFormatException e) {
//					System.out.println("number format exception");
//				}
				//end uncomment line above; must uncomment output statement below to see output
				
				out.print(fileChars.get(0));
				out.print(fileChars.get(1));
				out.print(" "); // space between bigram and frequency
				out.print(fileChars.get(4));
				out.print(fileChars.get(5));
				out.print(fileChars.get(6));
				out.print(fileChars.get(7));
				out.println(fileChars.get(8));
			}
			
		}
		//uncomment line below for output of percentages
		//System.out.println(total);
		}catch (FileNotFoundException e) {
			System.out.println("file not found");
		}	
	}
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class dictionary {

	public static void main(String[] args) {
		//array filled with the alphabet
		char[] alphabet = new char[26]; // new array

		for(char ch = 'a'; ch <= 'z'; ++ch)// fills alphabet array with the alphabet
		{
			alphabet[ch-'a']=ch;
		} 
		//array list is used here to make sure that we can use any size of file with any number of words
		List<String> dict= new ArrayList<String>();
		try {
			File text = new File("PersonOfTheCentury.txt");
			Scanner reader = new Scanner(text);    
			//while loop to put everything in the text file in the array list
			while(reader.hasNext()) {
				String data= reader.next();
				dict.add(data);
			}
			//closing reader
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}




		//sorting the arraylist in alphabetical order:
		Collections.sort(dict, String.CASE_INSENSITIVE_ORDER);

		//enhanced for loop to give i all values of the alphabet this will print the subHeaders before every letter
		for(char c: alphabet) {
			//change the characters to string to use them in the  start's with statement
			String s=String.valueOf(c);
			System.out.println("-------"+s.toUpperCase()+"----------");
			// enhanced for loop to check all words in the text
			for(String x: removeDuplicates(dict)) 
				if(x.startsWith(s))
					System.out.println(x.toUpperCase());
		}


		
		

		// Creating the new file and typing in the SubDictionary
		String fileName = "SubDictionary.txt";
		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			for(char c: alphabet) {
				//change the characters to string to use them in the  start's with statement
				String s=String.valueOf(c);
				bufferedWriter.newLine();
				bufferedWriter.write("-------"+s.toUpperCase()+"----------");
				bufferedWriter.newLine();
				// enhanced for loop to check all words in the text
				for(String x: removeDuplicates(dict)) 
				{if(x.startsWith(s))
				{bufferedWriter.newLine();
				bufferedWriter.write(x.toUpperCase());
				}
				}
			}
			// Always close files.
			bufferedWriter.close();
		}
		catch(Exception ex) {
			System.out.println(
					"Error writing to file '"
							+ fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}//---------------------------------------End of main class----------------------------------------------




	//method to check if the word contains special characters then remove them
	public static String specialChar(String word) {
		String noCharWord = word.replaceAll("[?:,;!SM.']","");//s and m must be capital because we capitalize every word in the text 
		return numbers(noCharWord);			//use recursion to call the method number to check for any numbers
	}




	//method to check if the word contains any numbers
	// then we used recursion to implement the oneLetter method
	public static String numbers(String word) {
		if(word.contains("1")||word.contains("2")||word.contains("3")||
				word.contains("4")||word.contains("5")||word.contains("6")||
				word.contains("7")||word.contains("8")||word.contains("9"))
		{
			word = "";
			return oneLetter(word);
		}
		else
			return oneLetter(word);
	}





	//method to check if the word is made from a single character but is not a or I
	public static String oneLetter(String word) {
		if (word.length()==1)
		{if(word.equalsIgnoreCase("a"))
			return word;
		if(word.equalsIgnoreCase("i"))
			return word;
		else 
		{
			word="";
			return word;
		}
		}
		else 
			return word;
	}





	//method to check for repetition, we do that by creating a new array with no repetition:
	//this method will also remove empty spaces created in the method numbers and special char
	public static <T> ArrayList<String> removeDuplicates(List<String> dict) 
	{ 
		// Create a new ArrayList 
		ArrayList<String> newList = new ArrayList<String>(); 

		// Traverse through the first list 
		for (String element : dict) { 
			// If this element is not present in newList 
			// then add it 
			if (!newList.contains(element)) { 
				newList.add(specialChar(element)); 
			} 
		} 
		// return the new list 
		return newList; 
	} 
}


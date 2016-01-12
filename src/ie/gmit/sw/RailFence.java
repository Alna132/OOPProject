package ie.gmit.sw;

import java.util.Scanner;

/* Basic implementation of the Rail Fence Cypher using a 2D char array 
 * Note that there are more efficient ways to encrypt and decrypt, but the following implementation illustrates the steps
 * involved in each process and shows how the zig-zagging works. Feel free to change / adapt. 
 */

public class RailFence {
	
	//***** Encrypt a String called cypherText using an integer key ***** 
	public String encrypt(String cypherText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] matrix = new char[key][cypherText.length()]; //The array is filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int row = 0; //Used to keep track of rows
		boolean down = true; //Used to zigzag
		for (int i = 0; i < cypherText.length(); i++){ //Loop over the plaintext
			matrix[row][i] = cypherText.charAt(i); //Add the next character in the plaintext to the array
			
			if (down){ //If we are moving down the array
				row++;
				if (row == matrix.length){ //Reached the bottom
					row = matrix.length - 2; //Move to the row above
					down = false; //Switch to moving up
				}// End of if
			}// End of for
			else{ //We are moving up the array
				row--;
				
				if (row == -1){ //Reached the top
					row = 1; //Move to the first row
					down = true; //Switch to moving down
				}// End of if
			}// End of else
		}// End of for
		
		//printMatrix(matrix); //Output the matrix (debug)
		
		//Extract the cypher text
		StringBuffer sb = new StringBuffer(); //A string buffer allows a string to be built efficiently
		for (row = 0; row < matrix.length; row++){ //Loop over each row in the matrix
			for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
				if (matrix[row][col] > '0') sb.append(matrix[row][col]); //Extract the character at the row/col position if it's not 0.
			}// End of for
		}// End of for
		
		return sb.toString(); //Convert the StringBuffer into a String and return it
	}// End of encrypt
	
	//***** Decrypt a String cypherText using an integer key ***** 
	public String decrypt(String cypherText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] matrix = new char[key][cypherText.length()]; //The array is filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int targetRow = 0;
		int index = 0;
		do{
			int row = 0; //Used to keep track of rows		
			boolean down = true; //Used to zigzag
			for (int i = 0; i < cypherText.length(); i++){ //Loop over the plaintext
				if (row == targetRow){
					matrix[row][i] = cypherText.charAt(index); //Add the next character in the plaintext to the array
					index++;
				}// End of if
				
				if (down){ //If we are moving down the array
					row++;
					if (row == matrix.length){ //Reached the bottom
						row = matrix.length - 2; //Move to the row above
						down = false; //Switch to moving up
					} // End of if
				}// End of if
				else{ //We are moving up the array
					row--;
					
					if (row == -1){ //Reached the top
						row = 1; //Move to the first row
						down = true; //Switch to moving down
					}// End of if
				}// End of else
			}// End of for

			targetRow++;
		}while (targetRow < matrix.length);
		
		//printMatrix(matrix); //Output the matrix (debug)
		
		//Extract the cypher text
		StringBuffer sb = new StringBuffer(); //A string buffer allows a string to be built efficiently
		int row = 0;
		boolean down = true; //Used to zigzag
		for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
			sb.append(matrix[row][col]); //Extract the character at the row/col position if it's not 0.
			
			if (down){ //If we are moving down the array
				row++;
				if (row == matrix.length){ //Reached the bottom
					row = matrix.length - 2; //Move to the row above
					down = false; //Switch to moving up
				}// End of if 
			}// End of if
			else{ //We are moving up the array
				row--;
				
				if (row == -1){ //Reached the top
					row = 1; //Move to the first row
					down = true; //Switch to moving down
				}// End of if
			}// End of else

		}// End of for
		
		return sb.toString(); //Convert the StringBuffer into a String and return it
	}// End of decrypt
	
	//***** Output the 2D array in CSV format ***** 
	private void printMatrix(char[][] matrix){
		for (int row = 0; row < matrix.length; row++){ //Loop over each row in the matrix
			for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
				System.out.print(matrix[row][col]); //Output the value at row/column index
				if (col < matrix[row].length - 1) System.out.print(",");
			}// End of for
			System.out.println();
		}// End of for
	}// End of printMatrix
		
	public static void main(String[] args) throws Exception{
		Scanner console = new Scanner(System.in);
		int choice;
		
		System.out.println("~~ MENU ~~");
		System.out.println("1. Use preset message");
		System.out.println("2. Enter your own message");
		System.out.print("\nEnter Choice: ");
		choice = console.nextInt();
		
		if(choice == 1)
		{
			System.out.println("\nThe message to be decrypted: TTFOHATGRNREEANOETYRCIMHHAKT");
			String secretMessage = new RailFence().decrypt("TTFOHATGRNREEANOETYRCIMHHAKT", 5);
			System.out.println("\nThe decrypted message reads: " + secretMessage);
		}// End of if
		else if(choice == 2)
		{
			int rows;
			String message = "";
			System.out.println("\nEnter the message you wish to have decrypted. ");
			message = console.next();
			System.out.println("\nEnter the number of rows in the cypher. ");
			rows = console.nextInt();
			
			String secretMessage = new RailFence().decrypt(message, rows);
			System.out.println("\nThe message to be decrypted: " + message);
			System.out.println("\nThe decrypted message reads: " + secretMessage);
		}
		else
		{
			System.exit(1);
		}
	
	}// End of main
}// End of RailFence
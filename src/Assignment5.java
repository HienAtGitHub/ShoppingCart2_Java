import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Assignment5 {

	public static void main(String[] args) 
	{
		try
		{
			System.out.println("Hello! What is your name? ");
			Scanner keyboard = new Scanner(System.in);
			while (!keyboard.hasNext("[A-Za-z]+"))
			{
				System.out.println("Name should be alphabetical characters only. Please re-enter your name: ");
				keyboard.next();
			}
			String usersName = keyboard.next();
			exitProgram(usersName);
			System.out.println("Welcome, " +  usersName + "! How much do you have in your bank account?");
			System.out.println("Please note: The amount of your bank account should be no less than $59.00");
			while (!keyboard.hasNextDouble())
			{
				System.out.println("The amount should be a number only. Please re-enter the amount: ");
				keyboard.next();
			}
			double bankAccount = keyboard.nextDouble();
			if (bankAccount < 0 || bankAccount < 59.00)
			{
				throw new InvalidBankAccountException();
			}
				
			System.out.println("Thank you! Please enter 7 items in your shopping"
					+ " list along with their quantities and priorities:");
			System.out.println("(Note: You can enter \"exit\" anytime to quit the program.)");
			
			Random priceGenerator = new Random();
			ShoppingList cart = new ShoppingList();

			String fileName = "shoppingList.txt";
			Scanner inputStream = null;
			PrintWriter outputStream = null;
			try
			{
				inputStream = new Scanner(new File(fileName));
				outputStream = new PrintWriter(fileName);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Error opening the file " + fileName);
				System.exit(0);
			}
			while (inputStream.hasNextLine())
			{
				String line = inputStream.nextLine();
			}
			inputStream.close();
			
			for (int i = 0; i < 7; i++)
			{
				System.out.println("What is the item's name? ");
				while (!keyboard.hasNext("[A-Za-z]+"))
				{
					System.out.println("Name should be alphabetical characters only. Please re-enter the item name:");
					keyboard.next();
				}
				String name = keyboard.next();
				exitProgram(name);
				outputStream.println(" " + name);
				
				if (cart.size() > 0 && cart.duplicateName(name))
				{
					i = i - 1;
					System.out.println("This item is already in your list. Please enter"
							+ " a different one.");
					continue;
				}
				System.out.println("How many do you want? ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Input should be an integer only. How many do you want?");
					keyboard.next();
				}
				int quantity = keyboard.nextInt();
				outputStream.println(" " + quantity);
				System.out.println("What is its priority?");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Input should be an integer only.Please re-enter its priority:");
					keyboard.next();
				}
				int priority = keyboard.nextInt();
				outputStream.println(" " + priority);
				
				double price = priceGenerator.nextDouble() * 24;
				outputStream.println(" " + price);
				ItemToBuy itemToBuysInfo = new ItemToBuy(name, quantity, priority, price);
				cart.add(itemToBuysInfo);				
			}
			outputStream.close();
			cart.bubbleSort();
			System.out.println();
			cart.goShopping();
			System.out.println("This is the confirmation of your shopping list in the "
					+ "order of their priorities:");
			System.out.printf("%-10s %-10s %-10s %-10s\n", "Quantity", "Name", "Priority", "Price");
			cart.confirmation();
			cart.result();
			System.out.println();
			System.out.println("This is the amount of your bank account before shopping: $" + bankAccount);
			double finalBankAccount = bankAccount - 59.00;
			System.out.println("And here is what left in your bank account after shopping: $" + finalBankAccount);
		}
		
		catch (InvalidBankAccountException e)
		{
			System.out.println(e.getMessage());
		}
	}
	private static void exitProgram(String e)
	{
		if (e.equalsIgnoreCase("exit"))
		{
			System.out.println("Thank you for not breaking the program. See you later!");
			System.exit(0);
		}
	}
	
}

package org.homework;

import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * @author Xuqiang Lai
 * @version 2.Nov.2020
 */
public class MyLibrary {
	public static void main(String[] args) {
		MyLibrary myLibrary = new MyLibrary();

		myLibrary.runMainMenu();

	}

	private BorrowerDatabase BorrowerDB;
	private BookDatabase BookDB;
	private Validation V;
	private String input;


	public MyLibrary() {
		BorrowerDB = new BorrowerDatabase();
		BookDB = new BookDatabase();
		V = new Validation();
		loadData();
		input = "";
	}

	private ArrayList<String> readFile(String fileName) {
		ArrayList<String> database = new ArrayList<>();
		try {
			FileReader file = new FileReader(fileName);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				database.add(line);
			}
		} catch (Exception e) {
			System.out.println("File not Found!");
		}
		return database;
	}

	private void borrowBook(int id) {
		Scanner sc = new Scanner(System.in);
		if (hasTwoBooks(id)) {
			System.out.println("You have reached the borrow limit. You have to return a book first.");
			//可以借书
		} else {
			//成年人
			if (V.isAdult(getBorrowerById(id).getAge())) {
				BookDB.displayAllBooks();
				System.out.println("\n");
				System.out.println("Please enter then book number that you wish to borrow: ");
				input = sc.nextLine();
				while (!V.isNumber(input)) {
					System.out.println("Error, Book number must be a number!");
					input = sc.nextLine();
				}
				int bookId = Integer.parseInt(input);
				BorrowerDB.getBorrowers().get(id - 1).getBooklist()[0] = BookDB.getBooks().get(bookId - 1);
				System.out.println("<" + BorrowerDB.getBorrowers().get(id - 1).getBooklist()[0].getTitle() + "> was successfully added to your booklist!");
				//未成年人
			} else {
				ArrayList<Book> noneAdultBooks = BookDB.displayNonAdultBooks();
				System.out.println("\n");
				System.out.println("Please enter then book number that you wish to borrow: ");
				input = sc.nextLine();
				while (!V.isNumber(input)) {
					System.out.println("Error, Book number must be a number!");
					input = sc.nextLine();
				}
				int bookId = Integer.parseInt(input);
				//01 || 10 || 00
				//借书
				Book book = borrowBook(getBorrowerById(id), getBookById(bookId, noneAdultBooks));
				System.out.println("<" + book.getTitle() + "> was successfully added to your booklist!");
			}
		}
	}

	/**
	 * Display the borrower menu to the user.
	 */
	private void displayBorrowerMenu() {
		System.out.println("------------------------------");
		System.out.println("-       Borrower Menu        -");
		System.out.println("------------------------------");
		System.out.println("(1). 📚Borrow a Book");
		System.out.println("(2). 🔖Return a Book");
		System.out.println("(3). 🎒List Borrowed Books");
		System.out.println("(4). 🔙Return to Main Menu");
		System.out.println("Choose an option:");
	}

	private void displayHelp() {
		System.out.println("\n");
		System.out.println("------------------------------------------------------------------");
		System.out.println("!!!!!!!!!!        YOU DON'T NEED ANY INSTRUCTION        !!!!!!!!!!");
		System.out.println("YOU ARE SMART ENOUGH TO FIND OUT HOW THIS PROGRAM WORK BY YOURSELF");
		System.out.println("------------------------------------------------------------------");
		System.out.println("\n");
	}

	/**
	 * Display the main menu to the user.
	 */
	private void displayMainMenu() {
		System.out.println("------------------------------");
		System.out.println("-         Main Menu          -");
		System.out.println("------------------------------");
		System.out.println("(1). 🆕Register New Borrower");
		System.out.println("(2). 🏬Manage Borrower");
		System.out.println("(3). 🎒List All Borrowers");
		System.out.println("(4). ℹDisplay Help");
		System.out.println("(5). 💨Exit Library");
		System.out.println("Choose an option:");
	}

	private void listAllBorrowers() {
		BorrowerDB.displayAllBorrowers();
	}

	private void listBorrowedBooks(int id) {
		BorrowerDB.getBorrowers().get(id - 1).displayBooks();
	}

	private void loadData() {
		ArrayList<String> allBorrowers = readFile("D:\\JavaSoft\\Workspase\\leetCode\\org\\homework\\Borrowers.txt");
		BorrowerDB.loadBorrowers(allBorrowers);
		ArrayList<String> allBooks = readFile("D:\\JavaSoft\\Workspase\\leetCode\\org\\homework\\books.txt");
		BookDB.loadBooks(allBooks);
	}

	private void manageBorrowers() {
		System.out.println("All Borrowers:");
		System.out.println("\n");
		listAllBorrowers();
		int id = 0;
		System.out.println("Please enter the borrower's Id that you wish to take action on: ");
		Scanner sc = new Scanner(System.in);
		try {
			input = sc.nextLine();
			id = Integer.parseInt(input);
			if (BorrowerDB.getAllIds(BorrowerDB.getBorrowers()).contains(id)) {
				System.out.println("Current Borrower: " + BorrowerDB.getBorrowers().get(id - 1).getName() + ", ID: " + id);
				runBorrowerMenu(id);
			} else {
				System.out.println("Error, borrower not found!");
			}
		} catch (Exception e) {
			System.out.println("Error, Borrower's id must be a number.");
		}
	}

	private void readBooks() {
		ArrayList<String> allBooks = readFile("books.txt");
		BookDB.loadBooks(allBooks);
	}

	private void readBorrowers() {
		ArrayList<String> allBorrowers = readFile("borrowers.txt");
		BorrowerDB.loadBorrowers(allBorrowers);
	}

	private void register() {
		if (BorrowerDB.getBorrowers().size() > 99) {
			System.out.println("The Library is currently full!");
			System.out.println("The system will automatically return to the main menu!");
			runMainMenu();
		} else {
			Scanner sc = new Scanner(System.in);
			String name = "";
			System.out.println("Please enter your name: ");
			name = sc.nextLine().trim();
			while (BorrowerDB.findBorrower(name) >= 0 || name.isEmpty() || V.isAlphabetic(name) == false) {
				if (BorrowerDB.findBorrower(name) >= 0) {
					System.out.println("Error, borrower's name must be unique!");
				}

				if (name.isEmpty()) {
					System.out.println("Error, borrower's name not be blank!");
				}

				if (V.isAlphabetic(name) == false) {
					System.out.println("Error, borrower's name must be alphabetic!");
				}
				System.out.println("Please input your name again: ");
				name = sc.nextLine().trim();
			}
			int id = 1;
			ArrayList<Integer> allIds = BorrowerDB.getAllIds(BorrowerDB.getBorrowers());
			for (int i = 1; i < 100; i++) {
				if (allIds.contains(id)) {
					id += 1;
				} else {
					break;
				}
			}
			int age = 0;
			String input = "";
			System.out.println("Please enter your age: ");
			input = sc.nextLine();
			while (!V.isNumber(input) || Integer.parseInt(input) < 5 || Integer.parseInt(input) > 110) {
				System.out.println("Age must be a number and between 5 to 110(no space allowed).");
				input = sc.nextLine();
			}
			age = Integer.parseInt(input);
			Book[] booklist = new Book[2];
			System.out.println("\n");
			System.out.println("Hello " + name + ", your Id is " + id + ".");
			System.out.println("\n");
			BorrowerDB.addBorrower(name, id, age, booklist);
		}
	}

	private void runBorrowerMenu(int id) {
		Scanner sc = new Scanner(System.in);
		displayBorrowerMenu();
		String option = sc.nextLine();
		while (!option.equals("4")) {
			switch (option) {
				case "1":
					borrowBook(id);
					break;
				case "2":
					returnBook(id);
					break;
				case "3":
					listBorrowedBooks(id);
					break;
				default:
					System.out.println("Error, Please select the choice between 1 to 4.");
			}
			displayBorrowerMenu();
			option = sc.nextLine();
		}
	}

	public void runMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("==============================");
		System.out.println("=  Welcome to the MyLibrary  =");
		System.out.println("==============================");
		displayMainMenu();
		String option = sc.nextLine();
		while (!option.equals("5")) {
			switch (option) {
				case "1":
					register();
					break;
				case "2":
					manageBorrowers();
					break;
				case "3":
					listAllBorrowers();
					break;
				case "4":
					displayHelp();
					break;
				default:
					System.out.println("Error, Please select the choice between 1 to 5.");
			}
			displayMainMenu();
			option = sc.nextLine();
		}
		System.out.println("==============================");
		System.out.println("Thank you for using My Library");
		System.out.println("==============================");
	}

	private void returnBook(int id) {
		Scanner sc = new Scanner(System.in);
		if (BorrowerDB.getBorrowers().get(id - 1).getBooklist()[0] == null && BorrowerDB.getBorrowers().get(id - 1).getBooklist()[1] == null) {
			System.out.println("Sorry, you dont have any book in your booklist.");
		} else {
			BorrowerDB.getBorrowers().get(id - 1).displayBooks();
			System.out.println("Please enter the book number you wish to return: ");
			input = sc.nextLine();
			int returnId = Integer.parseInt(input);
			if (returnId < 1 || returnId > 2) {
				System.out.println("Error, please enter the number between 1 to 2.");
			} else {
				if (BorrowerDB.getBorrowers().get(id - 1).getBooklist()[returnId - 1] == null) {
					System.out.println("Error, there is no book is this section.");
				} else {
					BorrowerDB.getBorrowers().get(id - 1).getBooklist()[returnId - 1] = null;
					System.out.println("<" + BorrowerDB.getBorrowers().get(id - 1).getBooklist()[returnId - 1].getTitle() + "> returned");
				}
			}
		}
	}

	public Boolean hasTwoBooks(int id) {
		return getBorrowerById(id).getBooklist()[0] != null && getBorrowerById(id).getBooklist()[1] != null;
	}

	public Borrower getBorrowerById(int id) {
		ArrayList<Borrower> allBorrower = BorrowerDB.getBorrowers();
		for (int i = 0; i < allBorrower.size(); i++) {
			Borrower cur = allBorrower.get(i);
			if (cur.getId() == id) {
				return cur;
			}
		}return null;


	}

	/**
	 * 借书
	 * @param borrower  Name: David Smith, ID: 1, Age: 16
	 * @param book  My Best Friend, Author: Joe Nobody, Rating: General
	 */
	public Book borrowBook(Borrower borrower, Book book) {
		Book[] booklist = borrower.getBooklist();
		if (booklist[0] == null) {
			booklist[0] = book;
		}else {
			booklist[1] = book;
		}
		return book;
	}

	public Book getBookById(int id,ArrayList<Book> noneAdultBooks) {
		return noneAdultBooks.get(id-1);
	}


}

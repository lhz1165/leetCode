package org.homework;

import java.util.*;

/**
 * A simple board game.
 * To collect each players' information.
 *
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */
public class BookDatabase {
	private ArrayList<Book> books;

	private ArrayList<Book> NonAdultBooks;




	/**
	 * Constructor for objects of class BookDatabase
	 */
	public BookDatabase() {
		books = new ArrayList<>();
	}

	public void displayAllBooks() {
		for (int i = 0; i < books.size(); i++) {
			System.out.println("NO: " + (i + 1));
			books.get(i).displayBooks();
		}
	}

	public void loadBooks(ArrayList<String> allBooks) {
		for (String line : allBooks) {
			String[] data = line.split(",");
			Book b = new Book();
			b.setTitle(data[0]);
			b.setAuthor(data[1]);
			b.setRating(data[2]);
			books.add(b);
		}
	}

	/**
	 * 初始化未成年人列表
	 */
	public ArrayList<Book> getNonAdultBooks(ArrayList<Book> books) {
		NonAdultBooks = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (book.getRating().equals("General")) {
				NonAdultBooks.add(book);
			}
		}
		return NonAdultBooks;
	}

	/**
	 * 控制台打印未成年人列表
	 */
	public ArrayList<Book> displayNonAdultBooks() {
		//初始化未成年人列表
		getNonAdultBooks(books);

		for (int i = 0; i < NonAdultBooks.size(); i++) {
			System.out.println("NO: " + (i + 1));
			NonAdultBooks.get(i).displayBooks();
		}
		return NonAdultBooks;
	}

	public void setBook(ArrayList<Book> newBooks) {
		books = newBooks;
	}

	public ArrayList<Book> getNonAdultBooks() {
		return NonAdultBooks;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}
}

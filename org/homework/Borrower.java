package org.homework;

import java.util.*;

/** 
 * A simple board game.
 * To collect each players' information.
 * 
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */
public class Borrower
{
    private String name;
    private int id;
    private int age;
    private Book[] booklist;

    public Borrower()
    {
        name = null;
        id = 0;
        age = 0;
        booklist = new Book[2];
    }

    public Borrower(String borrowerName, int borrowerId, int borrowerAge, Book[] borrowerBooklist)
    {
        name = borrowerName;
        id = borrowerId;
        age = borrowerAge;
        booklist = borrowerBooklist;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setId(int newId)
    {
        id = newId;
    }

    public void setAge(int newAge)
    {
        age = newAge;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public int getAge()
    {
        return age;
    }

    public Book[] getBooklist()
    {
        return booklist;
    }

    public void setBooklist(Book[] newBooklist)
    {
        booklist = newBooklist;
    }

    public void displayBorrower()
    {
        System.out.println("Name: " + name + ", ID: " + id + ", Age: " + age);
    }

    public void displayBooks()
    {
        if (booklist[0] != null)
        {
            System.out.println("1. Titel: " + booklist[0].getTitle() + ", Author: " + booklist[0].getAuthor() + ", Rating: " + booklist[0].getRating());
        }
        else
        {
            System.out.println("1. EMPTY");
        }

        if (booklist[1] != null)
        {
            System.out.println("2. Titel: " + booklist[1].getTitle() + ", Author: " + booklist[1].getAuthor() + ", Rating: " + booklist[1].getRating());
        }
        else
        {
            System.out.println("2. EMPTY");
        }
    }

    public void addBook(String title, String author, String rating)
    {
        Book b = new Book(title, author, rating);
        if (booklist[0] == null)
            booklist[0] = b;
        else
            booklist[1] = b;
    }

    public void returnBook(String title, String author, String rating)
    {
        Book b = new Book(title, author, rating);
        if (booklist[0] == null)
            booklist[0] = null;
        else
            booklist[1] = b;
    }
}

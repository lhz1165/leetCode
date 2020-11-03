package org.homework;

import java.util.*;

/**
 * A simple board game.
 * To collect each players' information.
 *
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */
public class BorrowerDatabase
{
    private ArrayList<Borrower> borrowers;
    private ArrayList<Integer> ids;

    public BorrowerDatabase()
    {
        borrowers = new ArrayList<>();
    }

    public ArrayList<Borrower> getBorrowers()
    {
        return borrowers;
    }

    public ArrayList<Borrower> getBookList()
    {
        return borrowers;
    }

    public void setBorrower(ArrayList<Borrower> newBorrowers)
    {
        borrowers = newBorrowers;
    }

    public void addBorrower(String name, int id, int age, Book[] booklist)
    {
        Borrower b = new Borrower(name, id, age, booklist);
        borrowers.add(b);
    }

    public ArrayList<Integer> getAllIds(ArrayList<Borrower> borrowers)
    {
        ids = new ArrayList<>();
        for (int i = 0; i < borrowers.size(); i++)
        {
            ids.add(borrowers.get(i).getId());
        }
        return ids;
    }

    public void loadBorrowers(ArrayList<String> allBorrowers)
    {
        for(String line : allBorrowers)
        {
            String [] data = line.split(",");
            Borrower b = new Borrower();
            b.setName(data[0]);
            b.setId(Integer.parseInt(data[1]));
            b.setAge(Integer.parseInt(data[2]));
            if (data.length > 3)
            {
                b.addBook(data[3], data[4], data[5]);
            }

            if (data.length >6)
            {
                b.addBook(data[6], data[7], data[8]);
            }
            borrowers.add(b);
        }
    }

    public int findBorrower(String name)
    {
        int index = -1;
        for (Borrower b : borrowers)
        {
            if (b.getName().equals(name))
            {
                index = borrowers.indexOf(b);
                break;
            }
        }
        return index;
    }

    public void displayAllBorrowers()
    {
        borrowers.sort(new IdComparator());
        for (int i = 0; i < borrowers.size(); i++)
        {
            borrowers.get(i).displayBorrower();
            borrowers.get(i).displayBooks();
            System.out.println("\n");
        }
    }
}
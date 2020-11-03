package org.homework;

/**
 * A simple board game.
 * To collect each players' information.
 * 
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */

/**
 *
 * @author Xuqiang Lai
 * @version 2.Nov.2020
 */
public class Book
{
    private String title;
    private String author;
    private String rating;

    /**
     * Constructor for objects of class Book
     */
    public Book()
    {
        title = null;
        author = null;
        rating = null;
    }

    public Book(String newtitle, String newauthor, String newrating)
    {
        title = newtitle;
        author = newauthor;
        rating = newrating;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getRating()
    {
        return rating;
    }

    public void setTitle(String newtitle)
    {
        title = newtitle;
    }

    public void setAuthor(String newauthor)
    {
        author = newauthor;
    }

    public void setRating(String newrating)
    {
        rating = newrating;
    }

    public void displayBooks()
    {
        System.out.println("Title: " + title + ", Author: " + author + ", Rating: " + rating);
    }
}

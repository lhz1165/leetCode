package org.homework;
import java.util.Comparator;

public class IdComparator implements Comparator<Borrower>
{
    public int compare(Borrower b1, Borrower b2)
    {
        return b1.getId() - b2.getId();
    }
}


package org.homework;

/**
 * A validation class to check user input.
 * 
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */

/**
 * A validation class to check user input.
 *
 * @author Xuqiang Lai
 * @version 29.Oct.2020
 */
public class Validation
{
    /**
     * Check the input of the age, is it greater than 18.
     */
    public boolean isAdult(int age)
    {
        if (age > 18)
        {
            return true;
        }
        return false;
    }

    /**
     * Check the input of the name, is it only contain letters and space.
     */
    public boolean isAlphabetic(String name)
    {
        for (int i = 0; i < name.length(); i++)
        {
            char c = name.charAt(i);
            if (c == ' ' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')
            {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Check the input, is it numeric.
     */
    public boolean isNumber(String input)
    {
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (!Character.isDigit(c))
            {
                return false;
            }
        }
        return true;
    }
}

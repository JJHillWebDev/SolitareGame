import java.util.*;

/**
   Simulates a solitaire game in which you have a list of random numbers 
   between 10 and 99. Any pair of numbers whose first or second digit 
   match will be removed. You win by removing all of the numbers.
   @author Jeremy Hill
   @version 12.0.2
*/

public class Solitaire 
{
    //create in Integer ArrayList to hold the "cards"
    private ArrayList<Integer> numList;


    /**
     *  Main method creates a Solitaire object and calls the startGame method.
     */
    public static void main(String[] args) 
    {
        //creates an instance of the Solitaire simulation
        Solitaire game = new Solitaire();
        //starts the simulation
        game.startGame();
    }


    /**
     *  The startGame method runs the Solitaire simulation. It uses a do while
     *  loop to continuingly traverse the list, checking for pairs, until 
     *  there are either no more pairs or the list becomes empty.
     */
    public void startGame() 
    {
        boolean repeat;                //whether the do-while should continue

        System.out.print("The list is originally: ");
        displayList();

        do 
        {
            repeat = false;
            while (checkAndRemovePairs()) 
            {
                System.out.println("");
                System.out.print("The list is now: ");
                displayList();
            }
            System.out.println("No more pairs to remove.");
        } 
        while (repeat);

        if (numList.isEmpty()) 
        {
            System.out.println("\n*** Congradulations You Won!!! ***");
        } 
        else 
        {
            System.out.println("\n*** Better luck next time! ***");
        }
    }


    /**
     *  Constructor calls the initializeList method.
     */
    public Solitaire() 
    {
        numList = new ArrayList<>();
        initializeList();
    }


    /**
     *  Initializes the list with 40 random numbers between 10-99.
     */
    private void initializeList() 
    {
        //create new random number generator
        Random generator = new Random();
        //create a listIterator to add values to numList
        ListIterator<Integer> iter = numList.listIterator();

        for (int i = 0; i < 40; i++) 
        {
            int randomNumber = generator.nextInt((99 - 10) + 1) + 10;
            iter.add(randomNumber);
        }
    }


    /** 
     *  Checks to see if the two numbers can be removed. 
     *  @param one The first integer value.
     *  @param y The second integer value.
     *  @return True if x and y match in the first or second digit.
     */
    private boolean removablePair(Integer first, Integer second) 
    {
        //convert the numbers to Strings for comparison
        String firstNum = String.valueOf(first);
        String secondNum = String.valueOf(second);

        boolean isRemoved = false;     //whether the pair should be removed

        if (firstNum.charAt(0) == secondNum.charAt(0) || 
           (firstNum.charAt(1) == secondNum.charAt(1))) 
        {
            isRemoved = true;
        } 
       
        //print the pair that is going to be removed
        if(isRemoved) 
        {
            System.out.println("   Removed: " + first + " " + second);
            return true;
        }
        return false;
    }


   /** 
    *  Traverses the list and removes any pairs of values that are removable.
    *  @return True if any pairs were removed.
    */
    private boolean checkAndRemovePairs()
    {
        boolean removedPair = false;   //whether the pair is to be removed

        ListIterator<Integer> listIt = numList.listIterator();
      
        Integer first = null;       //holds the first number for comparison
        Integer second = null;      //holds the second number for comparison

        //check to see if list hasNext
        if (listIt.hasNext()) 
        {
            first = listIt.next();
        }

        while (listIt.hasNext()) {

            second = listIt.next();

            //checks to see if the pair should be removed
            if (removablePair(first, second)) 
            {
                listIt.remove();

                if (listIt.hasPrevious()) 
                {
                    listIt.previous();
                    listIt.remove();
                    removedPair = true;
                }

                if (listIt.hasNext()) 
                {
                    first = listIt.next();
                    continue;
                }
            }
            first = second;
        }
        return removedPair;
    }


   /** 
    * Traverses the list with a listIterator and prints the values within it.
    */
    private void displayList() 
    {
        //create a listIterator to print the values in numList
        ListIterator<Integer> display = numList.listIterator();
      
        //checks is the list is empty
        if(numList.isEmpty()) 
        {
            System.out.println("The list is empty.");
        }

        //prints out the list if not empty 
        if(!numList.isEmpty())
        {
            System.out.print("[");
            
            while (display.hasNext()) 
            {
                System.out.print(display.next() + " ");
            }
            System.out.println("]");
            System.out.println("");
        }
    }


}
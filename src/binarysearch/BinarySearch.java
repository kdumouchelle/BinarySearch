package binarysearch;

import java.util.Scanner;


public class BinarySearch 
{
    static Scanner scan = new Scanner(System.in);
    static private int[] list;
    static private int[] list2;
    static private int temp;
    static private int leftIndex;
    static private int rightIndex;
    static private int midIndex;    
    
    public static void main(String[] args)
    {
        BinarySearch binarySearch = new BinarySearch();
        
        createAndFillListFromUserInput();
        bubbleSortOnList();
        printList(); 
        
        list2 = createCopyOfList();

        int numberOfSearches = 6;
        for (int trialNumber = 0; trialNumber < numberOfSearches; trialNumber++)
        {
            int searchTarget = promptUserForSearchTarget();
            binarySearch.iterativeSearch(searchTarget);
            System.out.println("");
            System.out.println("");
            System.out.println("Recursively...");
            System.out.println("");
            binarySearch.recursiveSearch(searchTarget, leftIndex, rightIndex);
            System.out.println("");
        }    
    }//main
    
    public static void createAndFillListFromUserInput()
    {
        int numberOfElements = readNumberOfElementsFromUser();
        list = new int[numberOfElements];
       readAndAssignListFromUser();   
    }  
    static int readNumberOfElementsFromUser()
    {
        System.out.println("How many elements are in your list?");
        int numberOfElements = scan.nextInt();
        return numberOfElements;
    }
   static void readAndAssignListFromUser()
    {
       System.out.println("Enter the numbers in list:");
        for (int i = 0; i < list.length; i++)
        {
            list[i] = scan.nextInt();
        }  
    }

   public static void bubbleSortOnList()
   {
        for (int numSorted = 0; numSorted < list.length; numSorted++)
        {
            for (int swapIndex = 1; swapIndex < (list.length - numSorted); swapIndex++)
            {
                int precedingIndex = swapIndex - 1;
                swapDataIfPrecedingIsGreater(precedingIndex, swapIndex);
            }        
        }
   }
   static void swapDataIfPrecedingIsGreater(int precedingIndex, int swapIndex)
   {
       if(list[precedingIndex] > list[swapIndex])
        {
           temp = list[precedingIndex];
           list[precedingIndex] = list[swapIndex];
           list[swapIndex] = temp;
        }
   }
   
   public static void printList()
   {
       for(int i = 0; i < list.length; i++)
       {
           System.out.print(list[i] + " ");
       }
       System.out.println(""); 
   }
   
   public static int[] createCopyOfList()
   {
       int[] listCopy = new int[list.length];
       for(int i = 0; i < list.length; i++)
       {    
           listCopy[i] = list[i];
       }
       return listCopy;
   }
   
   public static int promptUserForSearchTarget()
   {
       System.out.println("Please enter the number you are searching for");
       int searchTarget = scan.nextInt();
       return searchTarget;
   }
   
//iterative binary search     
    public int iterativeSearch(int searchTarget)
    {
        leftIndex = 0;
        rightIndex = list.length - 1;
        
        while(leftIndex <= rightIndex) //while the pool of search targets is non-zero
        {
            midIndex = (leftIndex + rightIndex) / 2; //integer division 
            
            if (searchTargetIsFoundAtMidpoint(searchTarget))
            {
                return 1; //denotes success
            }
            
            else adjustRightOrLeftIndexDependingOnComparisonWithMidpoint(searchTarget);        
        }
        
        printFailureMessage(searchTarget);
        return -1; //denotes failure
    }
   
    public int recursiveSearch(int searchTarget, int leftIndex, int rightIndex)
    {
        if(leftIndex > rightIndex) 
        {
            System.out.println("");
            printFailureMessage(searchTarget);
            return -1; //denotes failure
        }
        else
        {
            midIndex = (leftIndex + rightIndex) / 2;

            if (searchTargetIsFoundAtMidpoint(searchTarget))
            {
                return 1; //denotes success
            }
            else
            {
                return decideAppropriateRecursiveHalf(searchTarget, 
                        leftIndex, rightIndex);
            }
        }
    }
    public int decideAppropriateRecursiveHalf(int searchTarget, 
            int leftIndex, int rightIndex)
    {
        if(searchTarget < list[midIndex])
        {
            return recursiveSearch(searchTarget, leftIndex, midIndex - 1);
        }
        else
        {
            return recursiveSearch(searchTarget, midIndex + 1, rightIndex);
        }
    }
    
    public boolean searchTargetIsFoundAtMidpoint(int searchTarget)
    {
        if (list[midIndex] == searchTarget)
        {
            System.out.println("");
            System.out.print(searchTarget 
                    + " is in the list of numbers! Found at list index of "
                    + midIndex + ".   ");
            return true;
        }
        return false;
    }
    public void adjustRightOrLeftIndexDependingOnComparisonWithMidpoint(int searchTarget)
    {
        if (searchTarget < list[midIndex])
        {
            rightIndex = midIndex - 1;
        }
        else
        {
            leftIndex = midIndex + 1; 
        }
    }
    public void printFailureMessage(int searchTarget)
    {
        System.out.println("We could not find search target "
                + searchTarget + " in the list. Sorry!");
    }
    
}

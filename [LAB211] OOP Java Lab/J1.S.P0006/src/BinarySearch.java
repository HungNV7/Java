
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class BinarySearch {
 
    public void displayAll(int[] arr)
    {
         System.out.print("[");
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
            if (i<arr.length-1){
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }
    
    public void sortAsc(int[] arr)
    {
        for (int i=0; i<arr.length-1; i++)
        {
            for (int j=i+1; j<arr.length; j++)
                if (arr[i]>arr[j])
                {
                    int tmp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=tmp;
                }
        }
    }
    public int search(int[] arr, int searchValue, int first, int last)
    {
        if (first>last)
             return -1;
        int middle=(first+last)/2;
        if (arr[middle]==searchValue){
            return middle;
        }
        else if (arr[middle]>searchValue)
            return search(arr, searchValue, first, middle-1);
        else return search(arr, searchValue, middle+1, last);
    }
    
    public static void main(String[] args) {
        Scanner s;
        int size, search;
        while(true){
            try {
                s=new Scanner(System.in);
                System.out.println("Enter number of array:");
                size=s.nextInt();
                if (size<=0){
                    System.out.println("input is not valid");
                    continue;
                }
                else break;
            } catch (Exception e) {
                System.out.println("input is not valid");
            }
        }
      
        while(true)
        {
            try {
                s=new Scanner(System.in);
                System.out.println("Enter search value:");
                search=s.nextInt();
                if (search<=0 || search>=size){
                    System.out.println("input is not valid");
                    continue;
                }
                else break;
            } catch (Exception e) {
                 System.out.println("input is not valid");
            } 
        }
        
          //create a random array
        int[] arr=new int[size];
        for (int i=0; i<size; i++){
            arr[i]=new Random().nextInt(size);
        }
        
        BinarySearch searcher=new BinarySearch();    
        System.out.print("Sorted array:");
        searcher.sortAsc(arr);
        searcher.displayAll(arr);
        
        int index=searcher.search(arr, search, 0, size-1);
        if (index<0)    System.out.println(search+" is not exist in array");
        else{
            System.out.println("Found "+search+" at index: "+index);
        }
       
    }
}

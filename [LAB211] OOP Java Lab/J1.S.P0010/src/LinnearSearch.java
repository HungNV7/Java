
import java.util.Arrays;
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
public class LinnearSearch {
    
    public void display(int[] arr)
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
    
    public void linearSearch(int[] arr, int search)
    {
        int count=0;
        for (int i=0; i<arr.length; i++){
            if(arr[i]==search){
                count++;
                System.out.println("Found "+search+" at index: "+i);
            }
        }
        if (count==0){
              System.out.println(search+" is not in array!");
        }
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
        
        LinnearSearch searcher=new LinnearSearch();
        System.out.print("The array:");
        searcher.display(arr);
        
        searcher.linearSearch(arr, search);
       
    }
}

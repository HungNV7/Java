
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
public class Calculation {
    public int menu()
    {
        System.out.println("========= Equation Program ========");
        System.out.println("1. Calculate Superlative Equation");
        System.out.println("2. Calculate Quadratic Equation");
        System.out.println("3. Exit");
    
        return Validation.checkInput();
    }
    
    public boolean isOdd(double n)
    {
        return n%2==1;
    }
    
    public boolean isEven(double n)
    {
        return n%2==0;
    }
    
    public boolean isPerfectSquare(double n)
    {
        return n!=0 &&((int)Math.sqrt(n)*(int)Math.sqrt(n))==n;
    }
    
    public void calculateEquatation()
    {
        System.out.println("----- Calculate Equation -----");
        
        System.out.print("Enter A:");
        double a=Validation.checkNumber();
        
        System.out.print("Enter B:");
        double b=Validation.checkNumber();
        
        if(a==0)
            if (b==0)
                 System.out.println("Solution: infinitely many solutions");
            else
                System.out.println("Solution: No solution");
        else 
            System.out.println("Solution: x = "+(-b/a)*1.0);
        
        System.out.print("Number is Odd: ");
        if (isOdd(a))
            System.out.print(a+" ");
        if (isOdd(b))
            System.out.print(b);
        System.out.println();
        
        System.out.print("Number is even:");
        if (isEven(a))
            System.out.print(a+" ");
        if (isEven(b))
            System.out.print(b);
        System.out.println();
        
        System.out.print("Number is Perfect Square:");
        if (isPerfectSquare(a))
            System.out.print(a+" ");
        if (isPerfectSquare(b))
            System.out.print(b);
        System.out.println();
        
    }
    
    public void calculateQuadraticEquation()
    {
        
        System.out.println("----- Calculate Quadratic Equation -----");
        
        System.out.print("Enter A:");
        double a=Validation.checkNumber();
        
        System.out.print("Enter B:");
        double b=Validation.checkNumber();
        
        System.out.print("Enter C:");
        double c=Validation.checkNumber();
        
        
        if (a==0)
            if (b==0)
                if (c==0)
                    System.out.println("Solution: infinitely many solutions");
                else
                    System.out.println("Solution: No solution");
            else
                System.out.println("Solution: x1 = x2 = "+(-c/b)*1.0);
        else{
            double delta=b*b-4*a*c;
            if (delta < 0)
                System.out.println("Solution: No solution");
            else if(delta==0)
                System.out.println("Solution: x1 = x2 = "+(-b/(2*a))*1.0);
            else{
                double x1=(-b+Math.sqrt(delta))*1.0/(2*a);
                double x2=(-b-Math.sqrt(delta))*1.0/(2*a);
                System.out.println("Solution: x1 = "+x1+" and x2 = "+x2);
            }
        }
        
        System.out.print("Number is Odd: ");
        if (isOdd(a))
            System.out.print(a+" ");
        if (isOdd(b))
            System.out.print(b+" ");
        if (isOdd(c))
            System.out.print(c);
        System.out.println();
        
        System.out.print("Number is even:");
        if (isEven(a))
            System.out.print(a+" ");
        if (isEven(b))
            System.out.print(b+" ");
        if (isEven(c))
            System.out.print(c);
        System.out.println();
        
        System.out.print("Number is Perfect Square:");
        if (isPerfectSquare(a))
            System.out.print(a+" ");
        if (isPerfectSquare(b))
            System.out.print(b+" ");
        if (isPerfectSquare(c))
            System.out.print(c);
        System.out.println();
        
    }
    public static void main(String[] args) {
        Calculation tmp=new Calculation();
        tmp.calculateEquatation();
    }
            
    
}

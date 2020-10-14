/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Matrix {
    public int menu()
    {
        System.out.println("========= Calculator Program ===========");
        System.out.println("1. Addition Matrix");
        System.out.println("2. Subtraction Matrix");
        System.out.println("3. Multiplication Matrix");
        System.out.println("4. Quit");
        return Validation.checkChoice();
    }
    
    public int[][] createMatrix( int n)
    {
        System.out.print("Enter Row Matrix "+n+":");
        int row=Validation.checkRowColumn();
        
        System.out.print("Enter Column Matrix"+n+":");
        int column=Validation.checkRowColumn();
        
        
        int[][] matrix=new int[row][column];
        
        for (int i=0; i<row; i++){
             for (int j=0; j<column; j++){
                 System.out.print("Enter Matrix"+n+"["+(i+1)+"]["+(j+1)+"]:");
                 matrix[i][j]=Validation.checkValue();
             }   
        }
        return matrix;
    }
    
    public void displayMatrix(int[][] matrix)
    {
        int row=matrix.length;
        int column=matrix[0].length;
        
        for (int i=0; i<row; i++){
             for (int j=0; j<column; j++){
                 System.out.print("["+matrix[i][j]+"]");
             }   
             System.out.println();
        }
    }
    
    public void addition()
    {
        System.out.println("------------- Addition -------------");
        int[][] matrix1=createMatrix(1);
        int[][] matrix2=createMatrix(2);
        
        int row1=matrix1.length;
        int column1=matrix1[0].length;
        
        int row2=matrix2.length;
        int column2=matrix2[0].length;
        
        if (row1!= row2 || column1!=column2){
              System.out.println("Can't do this task");
            return;
        }
        int[][] result=new int[row1][column1];
        for (int i=0; i<row1; i++){
             for (int j=0; j<column1; j++){
                 result[i][j]=matrix1[i][j]+matrix2[i][j];
             }   
        }
         System.out.println("---------- Result ---------");
         
        displayMatrix(matrix1);
        System.out.println("+");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }
    
    public void substraction()
    {
        System.out.println("----------- Subtraction --------------");
        int[][] matrix1=createMatrix(1);
        int[][] matrix2=createMatrix(2);
        
        int row1=matrix1.length;
        int column1=matrix1[0].length;
        
        int row2=matrix2.length;
        int column2=matrix2[0].length;
        
        if (row1!= row2 || column1!=column2){
            System.out.println("Can't do this task");
            return;
        }
         int[][] result=new int[row1][column1];
        for (int i=0; i<row1; i++){
             for (int j=0; j<column1; j++){
                 result[i][j]=matrix1[i][j]-matrix2[i][j];
             }   
        }
        System.out.println("---------- Result ---------");
        displayMatrix(matrix1);
        System.out.println("-");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }
    
    public void multiplication(){
        System.out.println("------------ Multiplication -------------");
        int[][] matrix1=createMatrix(1);
        int[][] matrix2=createMatrix(2);
        
        int row1=matrix1.length;
        int column1=matrix1[0].length;
        
        int row2=matrix2.length;
        int column2=matrix2[0].length;
        
        if (column1!=row2){
            System.out.println("Can't do this task");
            return;
        }
            
        int[][] result=new int[row1][column2];
        for (int i=0; i<row1; i++){
             for (int j=0; j<column2; j++){
                 result[i][j]=0;
             }   
        }
        
          for (int i=0; i<row1; i++){
             for (int j=0; j<column2; j++){
                 for (int k=0; k<column1; k++){
                     result[i][j]+=matrix1[i][k]*matrix2[k][j];
                 }
             }   
        }
        
           System.out.println("---------- Result ---------");
           
        displayMatrix(matrix1);
        System.out.println("*");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }
}

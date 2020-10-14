
import java.util.Scanner;

public class NumberSystem {
    public int menu()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Base number:");
        System.out.println("1. Binary");
        System.out.println("2. Decimal");
        System.out.println("3. Hexadecimal");
        int choice=Validate.checkBaseNumber();
        return choice;
    }
    
    public void convertFromBinary(String binary)
    {
        int choice=menu();
        switch(choice){
            case 1: System.out.println(binary+"(BIN) = "+binary+" (BIN)"); break;
            case 2: System.out.println(binary+"(BIN) = "+binaryToDecimal(binary)+" (DEC)"); break;
            case 3: System.out.println(binary+"(BIN) = "+binaryToHexa(binary)+" (HEX)"); break;
        }
    }
    
    public void convertFromDecimal(String decimal)
    {
        int choice=menu();
        switch(choice){
            case 1: System.out.println(decimal+"(DEC) = "+decimalToBinary(decimal)+" (BIN)"); break;
            case 2: System.out.println(decimal+"(DEC) = "+decimal+" (DEC)"); break;
            case 3: System.out.println(decimal+"(DEC) = "+decimalToHexa(decimal)+" (HEX)"); break;
        }
    }
    
    public void convertFromHexa(String hexa)
    {
        int choice=menu();
        switch(choice){
            case 1: System.out.println(hexa+"(HEX) = "+hexaToBinary(hexa)+" (BIN)"); break;
            case 2: System.out.println(hexa+"(HEX) = "+hexaToDecimal(hexa)+" (DEC)"); break;
            case 3: System.out.println(hexa+"(HEX) = "+hexa+" (HEX)"); break;
        }
    }
    
    public String decimalToBinary(String decimal)
    {
        return Integer.toBinaryString(Integer.parseInt(decimal));
    }
    
    public String decimalToHexa(String decimal)
    {
        return Integer.toHexString(Integer.parseInt(decimal));
    }
    
    public String binaryToDecimal(String binary)
    {
        int decimal=Integer.parseInt(binary, 2);
        return Integer.toString(decimal);
    }
    
    public String binaryToHexa(String binary)
    {
        String decimal=binaryToDecimal(binary);
        return decimalToHexa(decimal);
    }
    
    public String hexaToDecimal(String hexa)
    {
        int decimal=Integer.parseInt(hexa, 16);
        return Integer.toString(decimal);
    }
    
    public String hexaToBinary(String hexa)
    {
        String decimal=hexaToDecimal(hexa);
        return decimalToBinary(decimal);
    }
    
    public static void main(String[] args) {
        NumberSystem tmp=new NumberSystem();
        System.out.println(tmp.decimalToBinary("535"));
        System.out.println(tmp.binaryToHexa("1000010111"));
      
    }
}

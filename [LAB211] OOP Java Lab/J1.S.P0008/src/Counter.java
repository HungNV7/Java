
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Counter {
    private Map<Character, Integer> countLetter;
    private Map<String, Integer> countWord;

    public Counter() {
        countLetter=new HashMap<>();
        countWord=new HashMap<>();
    }
    
    public void countWord(String str)
    {
        StringTokenizer tokenizer=new StringTokenizer(str);
        while(tokenizer.hasMoreTokens()){
            String token=tokenizer.nextToken();
            if (!countWord.containsKey(token))
                countWord.put(token, 1);
            else{
                countWord.put(token, (int)countWord.get(token)+1);
            }
        }
    }
    
    public void countLetter(String str)
    {
        for (int i=0; i<str.length(); i++)
        {
            char c=str.charAt(i);
            if (Character.isSpaceChar(c)) continue;
            if (!countLetter.containsKey(c)){
                countLetter.put(c, 1);
            }
            else{
                countLetter.put(c, (int)countLetter.get(c)+1);
            }   
        }
    }
    
    public void display()
    {
        System.out.println(countWord);
        System.out.println(countLetter);
    }
    
    public String input()
    {
        String content;
        Scanner s=new Scanner(System.in);
        while(true){
            System.out.println("Enter your content:");
            content=s.nextLine();
            if (!"".equals(content)) break;
        }
        return content;
    }
    
    public static void main(String[] args) {
        Counter counter=new Counter();
        String content=counter.input();
        counter.countLetter(content);
        counter.countWord(content);
        counter.display();
    }
    
}

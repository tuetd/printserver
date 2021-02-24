package com.pruvn.rms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordUtil {
	private static final String charset = "!@0123456789abcdefghijklmnopqrstuvwxyz";
	 
    public static String getRandomString(int length) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
 
            System.out.println(PasswordUtil.getRandomString(10));
 
            try {
                // if you generate more than 1 time, you must
                // put the process to sleep for awhile
                // otherwise it will return the same random string
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        for (int i = 0; i < 1; i++)
            System.out.println("String: "+passwordGenerator(10));
    }
    
    public static String passwordGenerator(int length) {
        List<Character> chars = new ArrayList<Character>();
        Random rand = new Random();
        // min number of digits
        for (int i = 0; i < 1; i++) chars.add((char) ('0' + rand.nextInt(10)));
        // min number of lower case
        for (int i = 0; i < 2; i++) chars.add((char) ('a' + rand.nextInt(26)));
        // min number of upper case
        for (int i = 0; i < 1; i++) chars.add((char) ('A' + rand.nextInt(26)));
        // min number of symbols
        String symbols = "!@";
        for (int i = 0; i < 1; i++) chars.add(symbols.charAt(rand.nextInt(symbols.length())));
        // fill in the rest
        while (chars.size() < length) chars.add((char) ('!' + rand.nextInt(93)));
        // appear in a random order
        Collections.shuffle(chars);
        // turn into a String
        char[] arr = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) arr[i] = chars.get(i);
        return new String(arr);
    }
}

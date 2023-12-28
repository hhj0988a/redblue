package com.example.demo.checkWords;

import java.util.HashMap;

public class CheckWords{
    public static void main(String line){
        HashMap<String,Integer> hMap = new HashMap();
        String lowerCased = line.toLowerCase();
        String[] words = lowerCased.split(" ");

        for(String w:words){
            if(!hMap.containsKey((w))){
                hMap.put(w, 1);
            }else {
                int num= hMap.get(w);
                hMap.put(w, num+1);
            }
        }
        
        hMap.forEach((k,v)->System.out.println(k+" "+v));
    }
}
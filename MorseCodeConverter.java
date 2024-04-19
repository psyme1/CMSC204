package com.example.project5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
    private static MorseCodeTree tree = null;
    public MorseCodeConverter(){
        tree = new MorseCodeTree();
        tree.buildTree();
    }
    public static String printTree(){
        if(tree == null){
            tree = new MorseCodeTree();
            tree.buildTree();
        }
        StringBuilder result = new StringBuilder();
        ArrayList<String> list = tree.toArrayList();
        for(int i = 0; i < list.size(); i++){
            result.append(list.get(i));
            if(i + 1 != list.size()){
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String convertToEnglish(String str){
        if(tree == null){
            tree = new MorseCodeTree();
            tree.buildTree();
        }
        StringBuilder output = new StringBuilder();
        for(String s : str.split(" ")){
            boolean a = false;
            if(s.endsWith("/")){
                s = s.replace('/', ' ');
                a = true;
            }try {
                output.append(tree.fetch(s));
            }catch(Exception ex){
                System.out.println(s + " caused an error");
            }
            if(a) output.append(" ");
        }
        return output.toString();
    }

    public static String convertToEnglish(File file){
        try {
            StringBuilder result = new StringBuilder();
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String morse = scan.nextLine();
                result.append(convertToEnglish(morse));
            }
            return result.toString();
        } catch (FileNotFoundException ignored) {

        }
        return "";
    }

}

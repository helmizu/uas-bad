package Num2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Num2 {
    public static void linkedList() {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for (int i = 1; i <= 5; i++) {
            ll.add(i);
        }
        System.out.println(ll);
    }
    
    public static void arrayList() {
        ArrayList<Integer> al = new ArrayList<Integer>();   
        for (int i = 1; i <= 5; i++) {
            al.add(i);
        }
        System.out.println(al);
    }
    
    public static void main(String args[]) {
        linkedList();
        arrayList();
    }
}

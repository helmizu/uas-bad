package Num1;

public class Num1 {
  public static void main(String args[]) {
    String str = "Saya berjalan kaki dari depan rumah saya ke pasar dekat rumah saya sekitar 15km";
    String name = "Helmi";
    String output = str.replaceAll("(?i)\\bsaya\\b", name);

    System.out.println(output);
  }
}
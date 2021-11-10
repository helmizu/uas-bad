package Num4;

public class Num4 {
    public static void main(String args[]) {
        Dosen ds = new Dosen(1, "Mirza", "Malang", "0001");
        Mahasiswa mhs = new Mahasiswa(2, "Helmi", "Blitar", 201900001);
        
        System.out.println("---- INFORMASI DOSEN ----");
        System.out.println(ds.toString());
        
        System.out.println("---- INFORMASI MAHASISWA ----");
        System.out.println(mhs.toString());
    }
}
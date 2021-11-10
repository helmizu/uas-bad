package Num4;

public class Mahasiswa extends Member {
    public int nim;
    
    public Mahasiswa(int kd_member, String nama, String alamat, int nim) {
        super(kd_member, nama, alamat);
        this.nim = nim;
    }
    
    @Override
    public String toString() {
        return (super.toString() + "\n"
                + "NIM\t\t: " + nim);
    }
}
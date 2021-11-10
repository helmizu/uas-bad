package Num4;

public class Member {
    public int kd_member;
    public String nama;
    public String alamat;
    
    public Member(int kd_member, String nama, String alamat) {
        this.kd_member = kd_member;
        this.nama = nama;
        this.alamat = alamat;
    }
    
    public String toString() {
        return ("KD Member\t: " + kd_member + "\n"
                + "Nama\t\t: " + nama + "\n"
                + "Alamat\t\t: " + alamat);
    }
}
package Num4;

public class Dosen extends Member {
    public String kd_dosen;
    
    public Dosen(int kd_member, String nama, String alamat, String kd_dosen) {
        super(kd_member, nama, alamat);
        this.kd_dosen = kd_dosen;
    }
    
    @Override
    public String toString() {
        return (super.toString() + "\n"
                + "KD Dosen\t: " + kd_dosen);
    }
}
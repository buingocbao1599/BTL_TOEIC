package Model;

import android.widget.ImageView;

public class TuVung {
    private String tiengAnh;
    private String tiengViet;
    private String mota;
    private int imgmota;
    private boolean dahoc;
    private int phatam;

    public String getTiengAnh() {
        return tiengAnh;
    }

    public int getPhatam() {
        return phatam;
    }

    public TuVung(String tiengAnh, String tiengViet, String mota, int imgmota, int phatam, boolean dahoc) {
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
        this.mota = mota;
        this.imgmota = imgmota;
        this.dahoc = dahoc;
        this.phatam = phatam;
    }

    public void setPhatam(int phatam) {
        this.phatam = phatam;
    }

    public TuVung() {
    }

    public boolean isDahoc() {
        return dahoc;
    }

    public void setDahoc(boolean dahoc) {
        this.dahoc = dahoc;
    }



    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getTiengViet() {
        return tiengViet;
    }

    public void setTiengViet(String tiengViet) {
        this.tiengViet = tiengViet;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getImgmota() {
        return imgmota;
    }

    public void setImgmota(int imgmota) {
        this.imgmota = imgmota;
    }
}

package vn.edu.iuh.kieu.chitieucanhan.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Money implements Serializable {

    private long id;

    private double tongtien;

    private List<KhoanThu> khoanThuList = new ArrayList<>();

    private List<KhoanChi> khoanChiList = new ArrayList<>();

    public Money(long id, double tongtien) {
        this.id = id;
        this.tongtien = tongtien;
    }

    public Money(double tongtien) {
        this.id = 0;
        this.tongtien = tongtien;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public List<KhoanThu> getKhoanThuList() {
        return khoanThuList;
    }

    public void setKhoanThuList(List<KhoanThu> khoanThuList) {
        this.khoanThuList = khoanThuList;
    }

    public List<KhoanChi> getKhoanChiList() {
        return khoanChiList;
    }

    public void setKhoanChiList(List<KhoanChi> khoanChiList) {
        this.khoanChiList = khoanChiList;
    }

    ///////////////////////////////////////////////////

    public void updateTongTien(List<KhoanThu> khoanThuList, List<KhoanChi> khoanChiList) {
        // check null
        if (khoanThuList == null) {
            khoanThuList = new ArrayList<>();
        }

        if (khoanChiList == null) {
            khoanChiList = new ArrayList<>();
        }

        // cap nhat khoan thu
        for (KhoanThu khoanThu : khoanThuList) {
            this.tongtien += khoanThu.getSotien();
        }

        // cap nhat khoan chi
        for (KhoanChi khoanChi : khoanChiList) {
            this.tongtien -= khoanChi.getSotien();
        }
    }
}

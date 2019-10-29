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
    // KhoanChi

    public void addKhoanThu(KhoanThu khoanThu) {

        // check null
        if (khoanThu == null) {
            throw new AssertionError();
        }

        // init list
        if (this.khoanThuList == null) {
            this.khoanThuList = new ArrayList<>();
        }

        // check contain khoanthu
        if (this.khoanThuList.contains(khoanThu)) {
            throw new AssertionError();
        }

        // add khoanthu if not contain
        this.khoanThuList.add(khoanThu);

        // set tongtien trong tui
        this.tongtien = this.khoanThuList.stream().mapToDouble(o -> o.getSotien()).sum();
    }

    public boolean updateKhoanThu(KhoanThu khoanThu) {
        if (khoanThu == null) {
            throw new AssertionError();
        }

        if (this.khoanThuList == null) {
            throw new AssertionError();
        }

        int index = this.khoanThuList.indexOf(khoanThu);

        if (index != -1) {
            this.khoanThuList.set(this.khoanThuList.indexOf(khoanThu), khoanThu);
            // set tongtien trong tui
            this.tongtien = this.khoanThuList.stream().mapToDouble(o -> o.getSotien()).sum();
            return true;
        }

        return false;
    }

    public boolean removeKhoanThu(KhoanThu khoanThu) {
        if (this.khoanThuList == null) {
            throw new AssertionError();
        }

        if (!this.khoanThuList.contains(khoanThu)) {
            return false;
        }

        boolean kq = this.khoanThuList.remove(khoanThu);

        if (kq) {
            // set tongtien trong tui
            this.tongtien = this.khoanThuList.stream().mapToDouble(o -> o.getSotien()).sum();
        }

        return kq;
    }

    public boolean removeKhoanThu (long khoanthuId) {
        if (this.khoanThuList == null) {
            throw new AssertionError();
        }

        KhoanThu khoanThu = new KhoanThu(khoanthuId);
        if (!this.khoanThuList.contains(khoanThu)) {
            return false;
        }

        boolean kq = this.khoanThuList.remove(khoanThu);
        if (kq) {
            // set tongtien trong tui
            this.tongtien = this.khoanThuList.stream()
                    .filter(o -> o.getSotien() > 0)
                    .mapToDouble(o -> o.getSotien())
                    .sum();
        }

        return kq;
    }

    ///////////////////////////////////////////////////
    // KhoanChi

    public void addKhoanChi(KhoanChi khoanChi) {

        // check null
        if (khoanChi == null) {
            throw new AssertionError();
        }

        // init list
        if (this.khoanChiList == null) {
            this.khoanChiList = new ArrayList<>();
        }

        // check contain khoanchi
        if (this.khoanChiList.contains(khoanChi)) {
            throw new AssertionError();
        }

        // add khoanchi if not contain
        this.khoanChiList.add(khoanChi);

        // update tong tien trong tui
        this.tongtien = this.khoanChiList.stream()
                .filter(o -> o.getId() == khoanChi.getId())
                .mapToDouble(o -> this.tongtien -= o.getSotien())
                .sum();
    }

    public boolean updateKhoanChi(KhoanChi khoanChi) {
        if (khoanChi == null) {
            throw new AssertionError();
        }

        if (this.khoanChiList == null) {
            throw new AssertionError();
        }

        int index = this.khoanChiList.indexOf(khoanChi);

        if (index != -1) {
            this.khoanChiList.set(this.khoanChiList.indexOf(khoanChi), khoanChi);

            // update tong tien trong tui
            this.tongtien = this.khoanChiList.stream()
                    .filter(o -> o.getId() == khoanChi.getId())
                    .mapToDouble(o -> this.tongtien -= o.getSotien())
                    .sum();

            return true;
        }

        return false;
    }

    public boolean removeKhoanChi(KhoanChi khoanChi) {
        if (this.khoanChiList == null) {
            throw new AssertionError();
        }

        if (!this.khoanChiList.contains(khoanChi)) {
            return false;
        }

        boolean kq = this.khoanChiList.remove(khoanChi);

        if (kq) {
            // set tongtien trong tui
            this.tongtien = this.khoanThuList.stream()
                    .filter(o -> o.getSotien() > 0)
                    .mapToDouble(o -> o.getSotien())
                    .sum();
        }

        return kq;
    }

    public boolean removeKhoanChi(long khoanChiId) {
        if (this.khoanChiList == null) {
            throw new AssertionError();
        }

        KhoanChi khoanChi = new KhoanChi(khoanChiId);
        if (!this.khoanChiList.contains(khoanChi)) {
            return false;
        }

        boolean kq = this.khoanChiList.remove(khoanChi);

        if (kq) {
            // set tongtien trong tui
            this.tongtien = this.khoanThuList.stream()
                    .filter(o -> o.getSotien() > 0)
                    .mapToDouble(o -> o.getSotien())
                    .sum();
        }

        return kq;
    }
}

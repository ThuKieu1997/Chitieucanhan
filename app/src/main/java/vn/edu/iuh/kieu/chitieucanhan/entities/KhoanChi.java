package vn.edu.iuh.kieu.chitieucanhan.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class KhoanChi implements Serializable {

    private long id;

    private String title;

    private double sotien;

    private LocalDateTime time;

    public KhoanChi(String title, double sotien, LocalDateTime time) {
        this.id = 0;
        this.title = title;
        this.sotien = sotien;
        this.time = time;
    }

    public KhoanChi(long id) {
        this.id = id;
        this.title = "";
        this.sotien = 0;
        this.time = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhoanChi khoanChi = (KhoanChi) o;
        return id == khoanChi.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

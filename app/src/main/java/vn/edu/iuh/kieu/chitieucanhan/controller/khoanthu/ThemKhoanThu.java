package vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu;

import vn.edu.iuh.kieu.chitieucanhan.controller.Validator;

public interface ThemKhoanThu extends Validator {

    void setContent(String content);

    void setSotien(String sotien);

    void setNgay(String ngay);

    boolean result();
}

package vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi;

import vn.edu.iuh.kieu.chitieucanhan.controller.Validator;

public interface ThemKhoanChi extends Validator {
    void setContent(String content);

    void setSotien(String sotien);

    void setNgay(String ngay);

    boolean result();
}

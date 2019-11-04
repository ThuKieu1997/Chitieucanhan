package vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.Validator;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;

public interface FindAllKhoanChi extends Validator {
    List<KhoanChi> getKhoanChiList();

    double getTongTien();

    double getTongKhoanChi();
}

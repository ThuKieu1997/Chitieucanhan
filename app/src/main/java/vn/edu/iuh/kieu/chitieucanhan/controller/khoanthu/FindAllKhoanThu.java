package vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.Validator;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public interface FindAllKhoanThu extends Validator {

    List<KhoanThu> getKhoanThuList();

    double getTongTien();

    double getTongKhoanThu();
}

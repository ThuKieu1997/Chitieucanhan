package vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public class FindAllKhoanThuImpl extends BaseController implements FindAllKhoanThu {

    private KhoanThuDAO khoanThuDAO;

    private List<KhoanThu> khoanThuList = new ArrayList<>();

    private String tongKhoanThu = "0";

    public FindAllKhoanThuImpl(Context context) {
        this.khoanThuDAO = new KhoanThuDAO(context);
    }

    @Override
    public void validate() throws Exception {
    }

    @Override
    public void execute() throws Exception {
        validate();
        this.khoanThuList = khoanThuDAO.getAll();

        double tongthu = 0;
        for (KhoanThu khoanThu : this.khoanThuList) {
            tongthu = khoanThu.getSotien();
        }

        this.tongKhoanThu = String.valueOf(tongthu);
    }

    @Override
    public List<KhoanThu> getKhoanThuList() {
        return khoanThuList;
    }

    @Override
    public String getTongKhoanThu() {
        return this.tongKhoanThu;
    }

    @Override
    public String toString() {
        return FindAllKhoanThuImpl.class.getSimpleName();
    }
}

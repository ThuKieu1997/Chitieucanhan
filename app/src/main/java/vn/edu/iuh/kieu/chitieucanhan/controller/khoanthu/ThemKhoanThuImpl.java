package vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu;

import android.content.Context;

import vn.edu.iuh.kieu.chitieucanhan.BaseContext;
import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public class ThemKhoanThuImpl extends BaseController implements ThemKhoanThu {

    private String content = "";

    private String sotien = "";

    private String ngay = "";

    private boolean result = false;

    private KhoanThuDAO khoanThuDAO;

    public ThemKhoanThuImpl(Context context) {
        this.khoanThuDAO = new KhoanThuDAO(context);
    }

    @Override
    public void setContent(String content) {
        this.content = content.trim();
    }

    @Override
    public void setSotien(String sotien) {
        this.sotien = sotien.trim();
    }

    @Override
    public void setNgay(String ngay) {
        this.ngay = ngay.trim();
    }

    @Override
    public void validate() throws Exception {

        if (!isValidDate(this.ngay)) {
            throw new Exception("Ngày đã nhập không hợp lệ !");
        }

        if (!isValidNumeric(this.sotien)) {
            throw new Exception("Số tiền không hợp lệ !");
        }

        if (this.content.length() > 255) {
            throw new Exception("Nội dung không hợp lệ !");
        }
    }

    @Override
    public void execute() throws Exception {
        validate();

        KhoanThu khoanThu = new KhoanThu(this.content, Double.parseDouble(this.sotien), ngay);

        this.result = khoanThuDAO.insert(khoanThu, BaseContext.TABLE_KHOANTHU);
    }

    @Override
    public boolean result() {
        return this.result;
    }

    @Override
    public String toString() {
        return ThemKhoanThuImpl.class.getSimpleName() + "[content=" + this.content + ", sotien=" + this.sotien + ", ngay=" + this.ngay + "]";
    }
}

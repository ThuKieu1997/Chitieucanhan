package vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi;

import android.content.Context;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.BaseContext;
import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu.ThemKhoanThuImpl;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanChiDao;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.dao.MoneyDao;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.entities.Money;

import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_MONEY;

public class ThemKhoanChiImpl extends BaseController implements ThemKhoanChi {

    private String content = "";

    private String sotien = "";

    private String ngay = "";

    private boolean result = false;

    private KhoanThuDAO khoanThuDAO;

    private KhoanChiDao khoanChiDao;

    private MoneyDao moneyDao;

    public ThemKhoanChiImpl(Context context) {
        this.khoanThuDAO = new KhoanThuDAO(context);
        this.khoanChiDao = new KhoanChiDao(context);
        this.moneyDao = new MoneyDao(context);
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
        // validate
        validate();

        // insert khoan thu
        KhoanChi khoanChi = new KhoanChi(this.content, Double.parseDouble(this.sotien), ngay);
        this.result = khoanChiDao.insert(khoanChi, BaseContext.TABLE_KHOANCHI);

        // get all khoan thu
        List<KhoanThu> khoanThuList = khoanThuDAO.getAll();

        // get all khoan chi
        List<KhoanChi> khoanChiList = khoanChiDao.getAll();

        // update tong tien
        List<Money> moneyList = moneyDao.getAll();
        if (moneyList.size() > 0) {
            Money money = moneyDao.getAll().get(0);
            money.updateTongTien(khoanThuList, khoanChiList);
            moneyDao.update(money, TABLE_MONEY, "id=?", new String[] {String.valueOf(money.getId())});
        }
    }

    @Override
    public boolean result() {
        return this.result;
    }

    @Override
    public String toString() {
        return ThemKhoanChiImpl.class.getSimpleName() + "[content=" + this.content + ", sotien=" + this.sotien + ", ngay=" + this.ngay + "]";
    }
}

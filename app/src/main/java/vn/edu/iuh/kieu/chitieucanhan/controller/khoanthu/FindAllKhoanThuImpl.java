package vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.dao.MoneyDao;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.entities.Money;

public class FindAllKhoanThuImpl extends BaseController implements FindAllKhoanThu {

    private KhoanThuDAO khoanThuDAO;

    private MoneyDao moneyDao;

    private List<KhoanThu> khoanThuList = new ArrayList<>();

    private double tongKhoanThu = 0;

    private double tongTien = 0;

    public FindAllKhoanThuImpl(Context context) {
        this.khoanThuDAO = new KhoanThuDAO(context);
        this.moneyDao = new MoneyDao(context);
    }

    @Override
    public void validate() throws Exception {
    }

    @Override
    public void execute() throws Exception {
        validate();

        // get all khoan thu
        this.khoanThuList = khoanThuDAO.getAll();

        // tinh tong khoan thu
        for (KhoanThu khoanThu : this.khoanThuList) {
            this.tongKhoanThu += khoanThu.getSotien();
        }

        // get tong tien tra ve
        List<Money> moneyList = this.moneyDao.getAll();
        if (moneyList.size() > 0) {
            Money money = this.moneyDao.getAll().get(0);
            this.tongTien = money.getTongtien();
        }
    }

    @Override
    public List<KhoanThu> getKhoanThuList() {
        return khoanThuList;
    }

    @Override
    public double getTongTien() {
        return this.tongTien;
    }

    @Override
    public double getTongKhoanThu() {
        return this.tongKhoanThu;
    }

    @Override
    public String toString() {
        return FindAllKhoanThuImpl.class.getSimpleName();
    }
}

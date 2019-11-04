package vn.edu.iuh.kieu.chitieucanhan.controller.thongke;

import android.content.Context;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanChiDao;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.dao.MoneyDao;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.entities.Money;

public class ThongKeImpl extends BaseController implements ThongKe {

    private KhoanThuDAO khoanThuDAO;

    private KhoanChiDao khoanChiDao;

    private MoneyDao moneyDao;

    private double tongKhoanThu = 0;

    private double tongKhoanChi = 0;

    private double tongTien = 0;

    public ThongKeImpl(Context context) {
        this.khoanThuDAO = new KhoanThuDAO(context);
        this.khoanChiDao = new KhoanChiDao(context);
        this.moneyDao = new MoneyDao(context);
    }

    @Override
    public void validate() throws Exception {
    }

    @Override
    public void execute() throws Exception {
        validate();

        // get all khoan thu
        List<KhoanThu> khoanThuList = khoanThuDAO.getAll();

        // tinh tong khoan thu
        for (KhoanThu khoanThu : khoanThuList) {
            this.tongKhoanThu += khoanThu.getSotien();
        }

        // get all khoan chi
        List<KhoanChi> khoanChiList = khoanChiDao.getAll();

        // tinh tong khoan chi
        for (KhoanChi khoanChi : khoanChiList) {
            this.tongKhoanChi += khoanChi.getSotien();
        }

        // get tong tien tra ve
        List<Money> moneyList = this.moneyDao.getAll();
        if (moneyList.size() > 0) {
            Money money = this.moneyDao.getAll().get(0);
            this.tongTien = money.getTongtien();
        }
    }

    @Override
    public double getTongThu() {
        return this.tongKhoanThu;
    }

    @Override
    public double getTongChi() {
        return this.tongKhoanChi;
    }

    @Override
    public double getTongTien() {
        return this.tongTien;
    }

    @Override
    public String toString() {
        return ThongKeImpl.class.getSimpleName();
    }
}

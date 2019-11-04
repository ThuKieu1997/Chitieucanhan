package vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanChiDao;
import vn.edu.iuh.kieu.chitieucanhan.dao.MoneyDao;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.entities.Money;

public class FindAllKhoanChiImpl extends BaseController implements FindAllKhoanChi {

    private KhoanChiDao khoanChiDao;

    private MoneyDao moneyDao;

    private List<KhoanChi> khoanChiList = new ArrayList<>();

    private double tongKhoanChi = 0;

    private double tongTien = 0;

    public FindAllKhoanChiImpl(Context context) {
        this.khoanChiDao = new KhoanChiDao(context);
        this.moneyDao = new MoneyDao(context);
    }

    @Override
    public void validate() throws Exception {
    }

    @Override
    public void execute() throws Exception {
        validate();

        // get all khoan chi
        this.khoanChiList = khoanChiDao.getAll();

        // tinh tong khoan thu
        for (KhoanChi khoanChi : this.khoanChiList) {
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
    public List<KhoanChi> getKhoanChiList() {
        return khoanChiList;
    }

    @Override
    public double getTongTien() {
        return tongTien;
    }

    @Override
    public double getTongKhoanChi() {
        return tongKhoanChi;
    }

    @Override
    public String toString() {
        return FindAllKhoanChiImpl.class.getSimpleName();
    }
}

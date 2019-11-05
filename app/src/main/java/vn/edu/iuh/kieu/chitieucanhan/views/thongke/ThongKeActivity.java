package vn.edu.iuh.kieu.chitieucanhan.views.thongke;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.controller.thongke.ThongKe;
import vn.edu.iuh.kieu.chitieucanhan.controller.thongke.ThongKeImpl;

public class ThongKeActivity extends AppCompatActivity {

    private TextView tvTongThu;
    private TextView tvTongChi;
    private TextView tvTongConLai;

    private PieChart pieChart;
    private List<PieEntry> entries;
    private ArrayList<Integer> colorList;

    private static int[] rootColor = ColorTemplate.MATERIAL_COLORS;

    static int[] append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    static {
        int[] joyful = ColorTemplate.JOYFUL_COLORS;
        int[] colorful = ColorTemplate.COLORFUL_COLORS;
        int length = joyful.length < colorful.length ? colorful.length : joyful.length;
        for (int i = 0; i < length; i++) {
            if (i < joyful.length)
                rootColor = append(rootColor, joyful[i]);
            if (i < colorful.length)
                rootColor = append(rootColor, colorful[i]);
        }
    }

    private ThongKe thongKe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        tvTongThu = findViewById(R.id.tvTongThu);
        tvTongChi = findViewById(R.id.tvTongChi);
        tvTongConLai = findViewById(R.id.tvTongConLai);

        pieChart = findViewById(R.id.chart_total);
        pieChart.getLegend().setEnabled(false);
        entries = new ArrayList<>();
        colorList = new ArrayList<>();

        this.thongKe = new ThongKeImpl(this);
        try {
            this.thongKe.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double tongThu = this.thongKe.getTongThu();
        double tongChi = this.thongKe.getTongChi();
        double tongTien = this.thongKe.getTongTien();
        double conLai = tongTien - tongChi;

        tvTongThu.setText(BaseController.formatNumber("#,###", tongThu) + " VND");
        tvTongChi.setText(BaseController.formatNumber("#,###", tongChi) + " VND");
        tvTongConLai.setText(BaseController.formatNumber("#,###", conLai) + " VND");

        Double tongThuPercent =  tongThu / (tongTien / 100);
        Double tongChiPercent = tongChi / (tongTien / 100);

        // add tong thu
        entries.add(new PieEntry(tongThuPercent.floatValue(), "Thu"));
        colorList.add(ColorTemplate.COLORFUL_COLORS[0]);

        // add tong chi
        entries.add(new PieEntry(tongChiPercent.floatValue(), "Chi"));
        colorList.add(ColorTemplate.COLORFUL_COLORS[1]);

        buildPieChart(conLai);
    }

    private void buildPieChart(double tongTien) {
        addTotalPoint(tongTien);

        PieDataSet set = new PieDataSet(entries, "");
        set.setSliceSpace(2f);

        set.setColors(colorList);
        PieData data = new PieData(set);
        pieChart.setData(data);

        pieChart.getDescription().setEnabled(false); // remove Description
        pieChart.getLegend().setEnabled(false); // remove chu thich

        pieChart.invalidate(); // refresh
        pieChart.animateX(2000);
    }

    private void addTotalPoint(double tongTien) {
        pieChart.setCenterText(BaseController.formatNumber("#,###", tongTien) + " VND");
        pieChart.setCenterTextSize(12f);
        pieChart.setCenterTextColor(Color.BLUE);
    }
}

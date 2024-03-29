package vn.edu.iuh.kieu.chitieucanhan.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.views.khoanchi.KhoanChiActivity;
import vn.edu.iuh.kieu.chitieucanhan.views.khoanthu.KhoanThuActivity;
import vn.edu.iuh.kieu.chitieucanhan.views.thongke.ThongKeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnKhoanThu;

    private Button btnKhoanChi;

    private Button btnThongKe;

    private Button btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContents();
        addEvents();
    }

    private void addContents() {
        btnKhoanChi = findViewById(R.id.main_btn_khoanchi);
        btnKhoanThu = findViewById(R.id.main_btn_khoanthu);
        btnThongKe = findViewById(R.id.main_btn_thongke);
        btnThoat = findViewById(R.id.main_btn_thoat);
    }

    private void addEvents() {
        btnKhoanChi.setOnClickListener(this);
        btnKhoanThu.setOnClickListener(this);
        btnThongKe.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnKhoanChi)) {
            Intent intent = new Intent(this, KhoanChiActivity.class);
            startActivity(intent);
        } else if (v.equals(btnKhoanThu)) {
            Intent intent = new Intent(this, KhoanThuActivity.class);
            startActivity(intent);
        } else if (v.equals(btnThongKe)) {
            Intent intent = new Intent(this, ThongKeActivity.class);
            startActivity(intent);
        } else if (v.equals(btnThoat)) {
            confirmExit();
        }
    }

    @Override
    public void onBackPressed() {
        confirmExit();
    }

    private void confirmExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát ứng dụng ?");
        builder.setMessage("Bạn muốn thoát ứng dụng ?");
        builder.setCancelable(false);
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Yes", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });

        builder.create().show();
    }
}

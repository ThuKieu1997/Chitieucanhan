package vn.edu.iuh.kieu.chitieucanhan.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.views.khoanthu.KhoanThuActivity;

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

        } else if (v.equals(btnKhoanThu)) {
            Intent intent = new Intent(this, KhoanThuActivity.class);
            startActivity(intent);
        } else if (v.equals(btnThongKe)) {

        } else if (v.equals(btnThoat)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thoát ứng dụng ?");
            builder.setMessage("Bạn muốn thoát ứng dụng ?");
            builder.setCancelable(false);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            builder.create().show();
        }
    }
}

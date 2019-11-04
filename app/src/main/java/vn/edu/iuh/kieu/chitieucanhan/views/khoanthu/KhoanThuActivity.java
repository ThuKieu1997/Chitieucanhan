package vn.edu.iuh.kieu.chitieucanhan.views.khoanthu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu.FindAllKhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu.FindAllKhoanThuImpl;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.views.khoanthu.adapter.KhoanThuAdapter;

public class KhoanThuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ADD_KHOANTHU = 100;

    private TextView tvKhoanthu;

    private FloatingActionButton btnAdd;

    private RecyclerView recyclerViewKhoanThu;

    private List<KhoanThu> khoanThuList;

    private KhoanThuAdapter adapter;

    private FindAllKhoanThu findAllKhoanThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_thu);

        addControls();
        addEvents();

        getKhoanThuList();
    }

    private void getKhoanThuList() {
        this.findAllKhoanThu = new FindAllKhoanThuImpl(KhoanThuActivity.this);
        try {
            this.findAllKhoanThu.execute();

            // init list khoan thu
            this.khoanThuList.clear();
            this.khoanThuList.addAll(this.findAllKhoanThu.getKhoanThuList());

            Log.i("HTC", "getKhoanThuList: " + this.khoanThuList);

            this.adapter.notifyDataSetChanged();

            // set tong tien
            this.tvKhoanthu.setText(BaseController.formatNumber("#,###", this.findAllKhoanThu.getTongKhoanThu()));

        } catch (Exception e) {
            e.printStackTrace();

            AlertDialog.Builder builder = new AlertDialog.Builder(KhoanThuActivity.this);
            builder.setTitle("Lỗi hệ thống !");
            builder.setMessage("Đã xảy ra lỗi khi khởi tạo danh sách khoản thu, quay lại sau !");
            builder.setCancelable(false);
            builder.setPositiveButton("Exit", (dialog, which) -> {
                dialog.dismiss();
                finish();
            });
            builder.create().show();
        }
    }

    private void addControls() {
        tvKhoanthu = findViewById(R.id.khoanthu_tv_khoanthu);
        btnAdd = findViewById(R.id.fab);
        this.recyclerViewKhoanThu = findViewById(R.id.khoanthu_lvKhoanthu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerViewKhoanThu.setLayoutManager(layoutManager);
        this.khoanThuList = new ArrayList<>();
        this.adapter = new KhoanThuAdapter(this, khoanThuList);
        this.recyclerViewKhoanThu.setAdapter(adapter);
    }

    private void addEvents() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_KHOANTHU && resultCode == RESULT_OK) {
            getKhoanThuList();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnAdd)) {
            Intent intent = new Intent(this, AddKhoanThuActivity.class);
            startActivityForResult(intent, REQUEST_ADD_KHOANTHU);
        }
    }
}

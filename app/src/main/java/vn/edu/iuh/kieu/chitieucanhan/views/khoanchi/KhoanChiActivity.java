package vn.edu.iuh.kieu.chitieucanhan.views.khoanchi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.controller.BaseController;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi.FindAllKhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi.FindAllKhoanChiImpl;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.views.khoanchi.adapter.KhoanChiAdapter;

public class KhoanChiActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ADD_KHOANCHI = 200;

    private TextView tvKhoanchi;

    private FloatingActionButton btnAdd;

    private RecyclerView recyclerViewKhoanChi;

    private List<KhoanChi> khoanChiList;

    private KhoanChiAdapter adapter;

    private FindAllKhoanChi findAllKhoanChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_chi);

        addControls();
        addEvents();

        getKhoanChiList();
    }

    private void getKhoanChiList() {
        this.findAllKhoanChi = new FindAllKhoanChiImpl(this);
        try {
            this.findAllKhoanChi.execute();

            // init list khoan thu
            this.khoanChiList.clear();
            this.khoanChiList.addAll(this.findAllKhoanChi.getKhoanChiList());

            this.adapter.notifyDataSetChanged();

            // set tong tien
            this.tvKhoanchi.setText(BaseController.formatNumber("#,###", this.findAllKhoanChi.getTongKhoanChi()));

        } catch (Exception e) {
            e.printStackTrace();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Lỗi hệ thống !");
            builder.setMessage("Đã xảy ra lỗi khi khởi tạo danh sách khoản chi, quay lại sau !");
            builder.setCancelable(false);
            builder.setPositiveButton("Exit", (dialog, which) -> {
                dialog.dismiss();
                finish();
            });
            builder.create().show();
        }
    }

    private void addControls() {
        tvKhoanchi = findViewById(R.id.khoanchi_tv_khoanchi);
        btnAdd = findViewById(R.id.fab);
        this.recyclerViewKhoanChi = findViewById(R.id.khoanchi_lvKhoanchi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerViewKhoanChi.setLayoutManager(layoutManager);
        this.khoanChiList = new ArrayList<>();
        this.adapter = new KhoanChiAdapter(this, khoanChiList);
        this.recyclerViewKhoanChi.setAdapter(adapter);
    }

    private void addEvents() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_KHOANCHI && resultCode == RESULT_OK) {
            getKhoanChiList();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnAdd)) {
            Intent intent = new Intent(this, AddKhoanChiActivity.class);
            startActivityForResult(intent, REQUEST_ADD_KHOANCHI);
        }
    }
}

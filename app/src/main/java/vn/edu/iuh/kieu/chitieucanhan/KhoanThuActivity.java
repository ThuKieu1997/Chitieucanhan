package vn.edu.iuh.kieu.chitieucanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class KhoanThuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvKhoanthu;

    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_thu);

        addControls();
        addEvents();
    }

    private void addControls() {
        tvKhoanthu = findViewById(R.id.khoanthu_tv_khoanthu);
        btnAdd = findViewById(R.id.fab);
    }

    private void addEvents() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnAdd)) {
            Intent intent = new Intent(this, AddKhoanThuActivity.class);
            startActivity(intent);
        }
    }
}

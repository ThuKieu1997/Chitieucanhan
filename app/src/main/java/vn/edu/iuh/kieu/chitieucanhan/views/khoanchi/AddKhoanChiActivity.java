package vn.edu.iuh.kieu.chitieucanhan.views.khoanchi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi.ThemKhoanChi;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanchi.ThemKhoanChiImpl;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu.ThemKhoanThu;
import vn.edu.iuh.kieu.chitieucanhan.controller.khoanthu.ThemKhoanThuImpl;

public class AddKhoanChiActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText edtNoiDung;

    private EditText edtSoTien;

    private EditText edtThoigian;

    private ImageButton btnSeleectDate;

    private Button btnAdd;

    private ThemKhoanChi themKhoanChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khoan_chi);

        addContents();
        addEvents();

        this.themKhoanChi = new ThemKhoanChiImpl(this);
    }

    private void addContents() {
        edtNoiDung = findViewById(R.id.add_khoanchi_edt_noidung);
        edtSoTien = findViewById(R.id.add_khoanchi_edt_sotien);
        edtThoigian = findViewById(R.id.add_khoanchi_edt_ngay);
        btnSeleectDate = findViewById(R.id.add_khoanchi_btn_selectDate);
        btnAdd = findViewById(R.id.add_khoanchi_add);
    }

    private void addEvents() {
        btnSeleectDate.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnSeleectDate)) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(AddKhoanChiActivity.this,
                            this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
        } else if (v.equals(btnAdd)) {
            this.themKhoanChi.setContent(edtNoiDung.getText().toString());
            this.themKhoanChi.setNgay(edtThoigian.getText().toString());
            this.themKhoanChi.setSotien(edtSoTien.getText().toString());
            try {
                // execute insert
                this.themKhoanChi.execute();
            } catch (Exception e) {
                e.printStackTrace();
                // neu xay ra loi validate
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            // get result insert
            boolean result = this.themKhoanChi.result();
            if (result) {
                Toast.makeText(this, "Thêm khoản chi thành công !", Toast.LENGTH_SHORT).show();

                Intent intent = getIntent();
                intent.putExtra("RESULT_ADD", true);

                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // xu ly them 0 truoc ngay
        String days = "";
        if (dayOfMonth < 10) {
            days += "0" + dayOfMonth;
        } else {
            days = String.valueOf(dayOfMonth);
        }

        // xu ly them 0 truoc thang
        String months = "";
        if ((month + 1) < 10) {
            months += "0" + (month + 1);
        } else {
            months = String.valueOf(month);
        }

        // format date is dd/MM/yyyy
        edtThoigian.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(days).append("/").append(months).append("/").append(year));
    }
}

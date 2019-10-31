package vn.edu.iuh.kieu.chitieucanhan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.TimeZone;

import vn.edu.iuh.kieu.chitieucanhan.dao.KhoanThuDAO;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public class AddKhoanThuActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText edtNoiDung;

    private EditText edtSoTien;

    private EditText edtThoigian;

    private ImageButton btnSeleectDate;

    private Button btnAdd;

    private KhoanThuDAO khoanThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khoan_thu);
        addContents();
        addEvents();

        khoanThuDAO = new KhoanThuDAO(this);
    }

    private void addContents() {
        edtNoiDung = findViewById(R.id.add_khoanthu_edt_noidung);
        edtSoTien = findViewById(R.id.add_khoanthu_edt_sotien);
        edtThoigian = findViewById(R.id.add_khoanthu_edt_ngay);
        btnSeleectDate = findViewById(R.id.add_khoanthu_btn_selectDate);
        btnAdd = findViewById(R.id.add_khoanthu_add);
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
                    new DatePickerDialog(this,
                            this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
        } else if (v.equals(btnAdd)) {
            KhoanThu khoanThu = new KhoanThu(
                    edtNoiDung.getText().toString(),
                    Double.parseDouble(edtSoTien.getText().toString()),
                    edtThoigian.getText().toString());
            khoanThuDAO.insert(khoanThu, MoneySqliteOpenHelper.TABLE_KHOANTHU);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        edtThoigian.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(dayOfMonth).append("/").append(month + 1).append("/").append(year));
    }
}

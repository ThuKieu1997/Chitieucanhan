package vn.edu.iuh.kieu.chitieucanhan.views.khoanthu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public class KhoanThuAdapter extends ArrayAdapter<KhoanThu> {

    private Context context;
    private int resource;
    private List<KhoanThu> khoanThuList;

    public KhoanThuAdapter(Context context, int resource, List<KhoanThu> khoanThuList) {
        super(context, resource, khoanThuList);
        this.context = context;
        this.resource = resource;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(this.resource, parent, false);

        KhoanThu khoanThu = this.khoanThuList.get(position);

        TextView tvContent = view.findViewById(R.id.item_khoanthu_tv_content);
        TextView tvDate = view.findViewById(R.id.item_khoanthu_tv_date);
        TextView tvSotien = view.findViewById(R.id.item_khoanthu_tv_sotien);

        tvContent.setText(khoanThu.getTitle());
        tvDate.setText(khoanThu.getTime());
        tvSotien.setText("+" + String.valueOf(khoanThu.getSotien()));

        return view;
    }
}

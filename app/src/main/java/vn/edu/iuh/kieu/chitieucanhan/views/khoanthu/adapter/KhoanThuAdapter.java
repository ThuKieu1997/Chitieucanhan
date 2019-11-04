package vn.edu.iuh.kieu.chitieucanhan.views.khoanthu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.KhoanThuViewHolder> {

    private Context context;
    private List<KhoanThu> khoanThuList;

    public KhoanThuAdapter(Context context, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.khoanThuList = khoanThuList;
    }

    @NonNull
    @Override
    public KhoanThuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khoan_thu, null);
        return new KhoanThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuViewHolder holder, int i) {
        KhoanThu khoanThu = this.khoanThuList.get(i);

        holder.tvContent.setText(khoanThu.getTitle());
        holder.tvDate.setText(khoanThu.getTime());
        holder.tvSotien.setText("+" + khoanThu.getSotien());
    }

    @Override
    public int getItemCount() {
        return khoanThuList.size();
    }


    class KhoanThuViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        TextView tvDate;
        TextView tvSotien;

        public KhoanThuViewHolder(@NonNull View view) {
            super(view);
            tvContent = view.findViewById(R.id.item_khoanthu_tv_content);
            tvDate = view.findViewById(R.id.item_khoanthu_tv_date);
            tvSotien = view.findViewById(R.id.item_khoanthu_tv_sotien);
        }
    }
}

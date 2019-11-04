package vn.edu.iuh.kieu.chitieucanhan.views.khoanchi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.R;
import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.KhoanChiViewHolder> {

    private Context context;
    private List<KhoanChi> khoanChiList;

    public KhoanChiAdapter(Context context, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.khoanChiList = khoanChiList;
    }

    @NonNull
    @Override
    public KhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khoan_chi, null);
        return new KhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiViewHolder holder, int i) {
        KhoanChi khoanChi = this.khoanChiList.get(i);

        holder.tvContent.setText(khoanChi.getTitle());
        holder.tvDate.setText(khoanChi.getTime());
        holder.tvSotien.setText("-" + khoanChi.getSotien());
    }

    @Override
    public int getItemCount() {
        return khoanChiList.size();
    }

    class KhoanChiViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        TextView tvDate;
        TextView tvSotien;

        public KhoanChiViewHolder(@NonNull View view) {
            super(view);
            tvContent = view.findViewById(R.id.item_khoanchi_tv_content);
            tvDate = view.findViewById(R.id.item_khoanchi_tv_date);
            tvSotien = view.findViewById(R.id.item_khoanchi_tv_sotien);
        }
    }
}

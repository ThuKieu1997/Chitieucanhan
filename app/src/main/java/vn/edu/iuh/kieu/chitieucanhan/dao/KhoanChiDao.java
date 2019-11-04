package vn.edu.iuh.kieu.chitieucanhan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanChi;

import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_KHOANCHI;

public class KhoanChiDao extends BaseDAO<KhoanChi> {

    public KhoanChiDao(Context context) {
        super(context);
    }

    @Override
    public List<KhoanChi> getAll() {
        db = openHelper.getReadableDatabase();
        List<KhoanChi> khoanChiList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_KHOANCHI,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double sotien = cursor.getDouble(cursor.getColumnIndex("sotien"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                KhoanChi khoanThu = new KhoanChi(id, title, sotien, time);
                khoanChiList.add(khoanThu);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return khoanChiList;
    }

    @Override
    public KhoanChi getOne(int id) {
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_KHOANCHI,
                null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        KhoanChi khoanChi = null;
        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double sotien = cursor.getDouble(cursor.getColumnIndex("sotien"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                khoanChi = new KhoanChi(id, title, sotien, time);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return khoanChi;
    }

    @Override
    ContentValues createContentValueInsert(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", khoanChi.getTitle());
        contentValues.put("sotien", khoanChi.getSotien());
        contentValues.put("time", khoanChi.getTime());
        return contentValues;
    }

    @Override
    ContentValues createContentValueUpdate(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", khoanChi.getTitle());
        contentValues.put("sotien", khoanChi.getSotien());
        contentValues.put("time", khoanChi.getTime());
        return contentValues;
    }
}

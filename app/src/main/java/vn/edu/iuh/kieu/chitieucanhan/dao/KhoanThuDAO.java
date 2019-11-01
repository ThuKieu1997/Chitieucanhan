package vn.edu.iuh.kieu.chitieucanhan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.entities.KhoanThu;

import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_KHOANTHU;

public class KhoanThuDAO extends BaseDAO<KhoanThu> {

    public KhoanThuDAO(Context context) {
        super(context);
    }

    @Override
    public List<KhoanThu> getAll() {
        db = openHelper.getReadableDatabase();
        List<KhoanThu> khoanThuList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_KHOANTHU,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double sotien = cursor.getDouble(cursor.getColumnIndex("sotien"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                KhoanThu khoanThu = new KhoanThu(id, title, sotien, time);
                khoanThuList.add(khoanThu);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return khoanThuList;
    }

    @Override
    public KhoanThu getOne(int id) {
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_KHOANTHU,
                null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        KhoanThu khoanThu = null;
        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double sotien = cursor.getDouble(cursor.getColumnIndex("sotien"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                khoanThu = new KhoanThu(id, title, sotien, time);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return khoanThu;
    }

    @Override
    ContentValues createContentValueInsert(KhoanThu khoanThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", khoanThu.getTitle());
        contentValues.put("sotien", khoanThu.getSotien());
        contentValues.put("time", khoanThu.getTime());
        return contentValues;
    }

    @Override
    ContentValues createContentValueUpdate(KhoanThu khoanThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", khoanThu.getTitle());
        contentValues.put("sotien", khoanThu.getSotien());
        contentValues.put("time", khoanThu.getTime());
        return contentValues;
    }
}

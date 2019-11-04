package vn.edu.iuh.kieu.chitieucanhan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.entities.Money;

import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_MONEY;

public class MoneyDao extends BaseDAO<Money> {

    public MoneyDao(Context context) {
        super(context);
    }

    @Override
    public List<Money> getAll() {
        db = openHelper.getReadableDatabase();
        List<Money> moneyList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_MONEY,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Money money = new Money(
                        cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getDouble(cursor.getColumnIndex("tongtien")));
                moneyList.add(money);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return moneyList;
    }

    @Override
    public Money getOne(int id) {
        Money money = null;
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MONEY,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                money = new Money(
                        cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getDouble(cursor.getColumnIndex("tongtien")));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return money;
    }

    @Override
    ContentValues createContentValueInsert(Money money) {
        ContentValues values = new ContentValues();
        values.put("tongtien", money.getTongtien());
        return values;
    }

    @Override
    ContentValues createContentValueUpdate(Money money) {
        ContentValues values = new ContentValues();
        values.put("tongtien", money.getTongtien());
        return values;
    }
}

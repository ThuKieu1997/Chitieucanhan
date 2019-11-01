package vn.edu.iuh.kieu.chitieucanhan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_KHOANCHI;
import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_KHOANTHU;
import static vn.edu.iuh.kieu.chitieucanhan.BaseContext.TABLE_MONEY;

public class MoneySqliteOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "money.db";

    public MoneySqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // MONEY
        db.execSQL("CREATE TABLE " + TABLE_MONEY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, tongtien double);");

        // KHOAN THU
        db.execSQL("CREATE TABLE " + TABLE_KHOANTHU + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), sotien DOUBLE, time datetime);");

        // KHOAN CHI
        db.execSQL("CREATE TABLE " + TABLE_KHOANCHI + " (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), sotien DOUBLE, time datetime);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_KHOANTHU);
        db.execSQL("drop table " + TABLE_KHOANCHI);
        db.execSQL("drop table " + TABLE_MONEY);
        onCreate(db);
    }
}

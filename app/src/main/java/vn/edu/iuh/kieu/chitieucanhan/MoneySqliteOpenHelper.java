package vn.edu.iuh.kieu.chitieucanhan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoneySqliteOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "money.db";

    public static final String TABLE_KHOANTHU = "KHOANTHU";
    public static final String TABLE_KHOANCHI = "KHOANCHI";
    public static final String TABLE_MONEY = "MONEY";

    public MoneySqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // MONEY
        db.execSQL("CREATE TABLE " + TABLE_MONEY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, tongtien double);");

        // KHOAN THU
        db.execSQL("CREATE TABLE " + TABLE_KHOANTHU + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), sotien DOUBLE, time datetime, moneyid INTEGER NOT NULL CONSTRAINT pk_money_khoanthu REFERENCES " + TABLE_MONEY +"(id));");

        // KHOAN CHI
        db.execSQL("CREATE TABLE " + TABLE_KHOANCHI + " (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), sotien DOUBLE, time datetime, moneyid INTEGER NOT NULL CONSTRAINT pk_money_khoanthu REFERENCES " + TABLE_MONEY + "(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_KHOANTHU);
        db.execSQL("drop table " + TABLE_KHOANCHI);
        db.execSQL("drop table " + TABLE_MONEY);
        onCreate(db);
    }
}

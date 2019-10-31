package vn.edu.iuh.kieu.chitieucanhan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import vn.edu.iuh.kieu.chitieucanhan.MoneySqliteOpenHelper;

public abstract class BaseDAO<T> {

    protected MoneySqliteOpenHelper openHelper;

    protected SQLiteDatabase db;

    public BaseDAO(Context context) {
        this.openHelper = new MoneySqliteOpenHelper(context);
    }

    /**
     * Function get all Object a Table from Sqlite.
     * @return
     */
    abstract List<T> getAll();

    /**
     * Function get a Object from Sqlite with ID primary key.
     * @param id primary key.
     * @return Object.
     */
    abstract T getOne(int id);

    /**
     * Function insert a Object into Sqlite.
     *
     * @param t Object
     * @param table Table.
     * @return result insert.
     */
    public boolean insert(T t, String table) {
        this.db = openHelper.getWritableDatabase();
        ContentValues contentValues = createContentValueInsert(t);
        long rows = db.insert(table, null, contentValues);
        db.close();

        if (rows > 0) {
            // insert success
            return true;
        }
        return false;
    }

    /**
     * Function update a Object into Sqlite.
     *
     * @param t Object.
     * @param table Table.
     * @param where where condition. @Example: ID = ?.
     * @param whereParams where params.
     * @return result of update.
     */
    public boolean update(T t, String table, String where, String[] whereParams) {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = createContentValueUpdate(t);
        long rows = db.update(table, contentValues, where, whereParams);

        db.close();

        if (rows > 0) {
            // insert success
                return true;
        }
        return false;
    }

    /**
     * Function create {@link ContentValues} of create.
     *
     * @param t Object.
     * @return ContentValues.
     */
    abstract ContentValues createContentValueInsert(T t);

    /**
     * Function create {@link ContentValues} of create.
     *
     * @param t Object.
     * @return ContentValues.
     */
    abstract ContentValues createContentValueUpdate(T t);
}

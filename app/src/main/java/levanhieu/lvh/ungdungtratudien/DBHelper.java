package levanhieu.lvh.ungdungtratudien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class DBHelper  {
    Context context;
    String dbName = "UngdungTuDien.db";

    public DBHelper(Context context) {
        this.context = context;
    }

    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }
    public void createTable() {
        SQLiteDatabase db = openDB();
        String sqlVocabularies = "CREATE TABLE IF NOT EXISTS tblVocabularies (" +
                " IdVocabulary INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " Word TEXT," +
                " Mean INTEGER," +
                " IdTopic INTEGER," +
                "  IsFavourite INTERGER, " +
                " IsHistory INTEGER );";
        String sqlTopics = "CREATE TABLE IF NOT EXISTS tblTopics (" +
                " IdTopics INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT," +
                " Image INTEGER );";

        String sql0 = "UPDATE tblVocabularies SET IsHistory = 0";
        String sql1 = "UPDATE tblVocabularies SET IsFavourite = 0";


        db.execSQL(sqlVocabularies);
        db.execSQL(sqlTopics);
        db.execSQL(sql0);
        db.execSQL(sql1);
        closeDB(db);
    }
    public ArrayList<Vocabulary> getALLVocabulary() {
        SQLiteDatabase db = openDB();
        ArrayList<Vocabulary> arr = new ArrayList<>();
        String sql = "select * from tblVocabularies";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int IdVocabulary = csr.getInt(0);
                    String word = csr.getString(1);
                    String mean = csr.getString(2);
                    int IdTopic = csr.getInt(3);
                    arr.add(new Vocabulary( word, mean, IdTopic, IdVocabulary));
                    Log.d("aw", String.valueOf(word));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Vocabulary> getVocabularyHis() {
        SQLiteDatabase db = openDB();
        ArrayList<Vocabulary> arr = new ArrayList<>();
        String sql = "select * from tblVocabularies where IsHistory = 1";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int IdVocabulary = csr.getInt(0);
                    String word = csr.getString(1);
                    String mean = csr.getString(2);
                    int IdTopic = csr.getInt(3);
                    arr.add(new Vocabulary( word, mean, IdTopic, IdVocabulary));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
    public  void  setVocabularyHis(int idVocabulary){
        SQLiteDatabase db = openDB();
        String sql = "UPDATE tblVocabularies SET IsHistory = 1 WHERE IdVocabulary = " + idVocabulary;
        db.execSQL(sql);
        closeDB(db);
    }
    public  void  deleteVocabularyHis(int idVocabulary){
        SQLiteDatabase db = openDB();
        String sql = "UPDATE tblVocabularies SET IsHistory = 0 WHERE IdVocabulary = " + idVocabulary;
        db.execSQL(sql);
        closeDB(db);
    }
    public  void  setVocabularyFavourite(int idFavourite){
        SQLiteDatabase db = openDB();
        String sql = "UPDATE tblVocabularies SET IsFavourite = 1 WHERE IdVocabulary = " + idFavourite;
        db.execSQL(sql);
        closeDB(db);
    }

    public ArrayList<Topics> getALLTopics(){
        SQLiteDatabase db = openDB();
        ArrayList<Topics> arr = new ArrayList<>();

        String sql = "select * from tblTopics";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    int image = csr.getInt(2);
                    arr.add(new Topics( image, name, id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Vocabulary> getTopicsDetail(int idCategories){
        SQLiteDatabase db = openDB();

        ArrayList<Vocabulary> arr = new ArrayList<>();
        String sql = "select * from tblFurniture where tblFurniture.CategoriesID ="+idCategories;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int IdVocabulary = csr.getInt(0);
                    String word = csr.getString(1);
                    String mean = csr.getString(2);
                    int IdTopic = csr.getInt(3);
                    arr.add(new Vocabulary( word, mean, IdTopic, IdVocabulary));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public  ArrayList<Vocabulary>  getVocabularyDetail(int idFurniture){
        SQLiteDatabase db = openDB();

        ArrayList<Vocabulary> arr = new ArrayList<>();
        String sql = "select * from tblFurniture where tblFurniture.ID ="+idFurniture;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int IdVocabulary = csr.getInt(0);
                    String word = csr.getString(1);
                    String mean = csr.getString(2);
                    int IdTopic = csr.getInt(3);
                    arr.add(new Vocabulary( word, mean, IdTopic, IdVocabulary));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public void insertFurniture(@NonNull Vocabulary vocabulary){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Word", vocabulary.word);
        contentValues.put("Mean", vocabulary.mean);
        contentValues.put("IdTopic", vocabulary.IdTopic);
        contentValues.put("IsFavourite",0);
        contentValues.put("IsHistory",0);
        SQLiteDatabase db = openDB();
        db.insert("tblVocabularies",null, contentValues);
        closeDB(db);
    }

    public void insertCategories(@NonNull Topics topics){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", topics.name);
        contentValues.put("Image", topics.image);
        SQLiteDatabase db = openDB();
        db.insert("tblTopics",null, contentValues);
        closeDB(db);
    }

    public int countFurniture(){
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query("tblVocabularies",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            return cursor.getCount();
        }
        return 0;
    }
}
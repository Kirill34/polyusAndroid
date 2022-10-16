package dataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocaleDataBase extends SQLiteOpenHelper {

    public LocaleDataBase(Context context) {
        super(context, "localCFDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User ("
                + "id text primary key,"
                + "password text,"
                + "phone text,"
                + "name text,"
                + "description text,"
                + "registration_date text,"
                + "mail text,"
                + "photo text,"
                + "isActive text"
                + new String(");"));

        db.execSQL("create table Events("
                + "id text primary key,"
                + "name text,"
                + "firstCoordinate text,"
                + "secondCoordinate text,"
                + "description text,"
                + "startTime text,"
                + "endTime text,"
                + "reit text,"
                + "picture text"
                + new String(");"));

        db.execSQL("create table Tags("
                + "id integer primary key autoincrement,"
                + "name text"
                + new String(");"));

        db.execSQL("create table EventTag("
                + "id integer primary key autoincrement,"
                + "tagId integer,"
                + "eventId text"
                + new String(");"));

    }

    public void addEventTag(int tagId, String eventId){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put("tagId", tagId);
        cv.put("eventId", eventId);

        db.insert("EventTag", null, cv);
    }

    public void addTag(String tag){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put("name", tag);

        db.insert("Tags", null, cv);
    }

    public void addUser(String name, String id, String password, String phone, String description, String registration_date
            , String mail, String photo, String isActive){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put("id", id);
        cv.put("password", password);
        cv.put("phone", phone);
        cv.put("name", name);
        cv.put("description", description);
        cv.put("registration_date", registration_date);
        cv.put("mail", mail);
        cv.put("photo", photo);
        cv.put("isActive", isActive);

        db.insert("User", null, cv);
    }

    public void addEvent(String id, String name, String firstCoordinate,
                         String secondCoordinate,
                         String description,
                         String startTime,
                         String endTime,
                         String reit,
                         String picture){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put("id", id);
        cv.put("name", name);
        cv.put("firstCoordinate", firstCoordinate);
        cv.put("secondCoordinate", secondCoordinate);
        cv.put("description", description);
        cv.put("startTime", startTime);
        cv.put("endTime", endTime);
        cv.put("reit", reit);
        cv.put("picture", picture);

        db.insert("Events", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

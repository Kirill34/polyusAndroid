package APILayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

import dataLayer.LocaleDataBase;
import model.User;
import model.mapPoint;
import ru.cityflow24.cityflow24.R;

public class DBAPI {
    private LocaleDataBase LDB;
    public DBAPI(Context context){
        LDB = new LocaleDataBase(context);
    }

    public void setUser(User u){
        SQLiteDatabase db = LDB.getWritableDatabase();
        db.delete("User", null, null);
      //  LDB.addUser(u.getName(),""+u.getId(),u.getPassword(),u.getPhone(),u.getDescription(),""+u.getRegistration_date(),u.getMail(),u.getPhoto(),""+u.getActive());
    }

    public User getUser(){
            User u = new User();
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("User", null, null, null, null, null, null);

        if(c.moveToFirst()){

            // определяем номера столбцов по имени в выборке
            int contentColName = c.getColumnIndex("name");
            int contentColDescription = c.getColumnIndex("description");
            int contentColId = c.getColumnIndex("id");
            int contentColPassword = c.getColumnIndex("password");
            int contentColPhone = c.getColumnIndex("phone");
            int contentColRegistration_date = c.getColumnIndex("registration_date");
            int contentColMail = c.getColumnIndex("mail");
            int contentColPhoto = c.getColumnIndex("photo");
            int contentColisActive = c.getColumnIndex("isActive");

            u.setActive(Boolean.parseBoolean(c.getString(contentColisActive)));
            u.setId(Long.parseLong(c.getString(contentColId)));
            u.setName(c.getString(contentColName));
            u.setPassword(c.getString(contentColPassword));
        }

        c.close();
        return u;
        }

    public void init() {
        Time t = new Time(System.currentTimeMillis());
        LDB.addEvent("1","Я", ""+ new Float(48.71800), ""+ new Float(44.50183),
                "\n" +
                        "описание", ""+t, ""+t, "5", ""+ R.drawable.event1);

        LDB.addEventTag(1,"1");

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.YEAR, 1);

        LDB.addEvent("2","Цель (база №23)", ""+ new Float(48.71940), ""+ new Float(44.51188),
                "описание", ""+t, ""+t, "4", ""+R.drawable.event3);
        LDB.addEventTag(2,"2");
    }

    public ArrayList<mapPoint> getAllMapPoints(){

        ArrayList<mapPoint> ret = new ArrayList<>();
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Events", null, null, null, null, null, null);

        if(c.moveToFirst()){
            // определяем номера столбцов по имени в выборке
            int contentColName = c.getColumnIndex("name");
            int contentColDescription = c.getColumnIndex("description");
            int contentColFirstCoordinate = c.getColumnIndex("firstCoordinate");
            int contentColSecondCoordinate = c.getColumnIndex("secondCoordinate");
            int contentColStartTime = c.getColumnIndex("startTime");
            int contentColEndTime = c.getColumnIndex("endTime");
            int contentColReit = c.getColumnIndex("reit");
            int contentColPicture = c.getColumnIndex("picture");

            do {
                ret.add(new mapPoint(c.getString(contentColName), c.getString(contentColDescription),
                        new Pair<Float, Float>(Float.parseFloat(c.getString(contentColFirstCoordinate)), Float.parseFloat(c.getString(contentColSecondCoordinate))),
                        getTagsByEventName(c.getString(contentColName)), c.getString(contentColStartTime), c.getString(contentColEndTime), Float.parseFloat(c.getString(contentColReit)), Integer.parseInt(c.getString(contentColPicture))));
            }while(c.moveToNext());
        }

        c.close();
        return ret;
    }

    public ArrayList<mapPoint> getMapPointByTags(ArrayList<String> tags){

        //Сопоставить тегам (каждому) ID событий, в котором такой тег есть
        ArrayList<String> eventIDs = findEventsByTags(tags);

        ArrayList<mapPoint> ret = new ArrayList<mapPoint>();

        for(int i = 0; i < eventIDs.size(); i++){
            ret.add(mapPointByID(eventIDs.get(i)));
        }

        return ret;

    }

    public ArrayList<mapPoint> getMapPointByName(String name){
        ArrayList<mapPoint> ret = new ArrayList<mapPoint>();
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Events", null, "name LIKE " + name, null, null, null, null);

        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int contentColName = c.getColumnIndex("name");
            int contentColDescription = c.getColumnIndex("description");
            int contentColFirstCoordinate = c.getColumnIndex("firstCoordinate");
            int contentColSecondCoordinate = c.getColumnIndex("secondCoordinate");
            int contentColStartTime = c.getColumnIndex("startTime");
            int contentColEndTime = c.getColumnIndex("endTime");
            int contentColReit = c.getColumnIndex("reit");
            int contentColPicture = c.getColumnIndex("picture");

            do {
                ret.add(new mapPoint(c.getString(contentColName), c.getString(contentColDescription),
                        new Pair<Float, Float>(Float.parseFloat(c.getString(contentColFirstCoordinate)),Float.parseFloat(c.getString(contentColSecondCoordinate))),
                        getTagsByEventName(c.getString(contentColName)),c.getString(contentColStartTime),c.getString(contentColEndTime), Float.parseFloat(c.getString(contentColReit)), Integer.parseInt(c.getString(contentColPicture))));
            } while (c.moveToNext());
        }
        c.close();
        return ret;
    }

    public void deleteAllEvents(){
        SQLiteDatabase db = LDB.getWritableDatabase();

        db.delete("Events", null, null);
        db.delete("EventTag", null, null);
    }

    public mapPoint mapPointByID(String ID){
        mapPoint ret = null;
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Events", null, "id = " + ID, null, null, null, null);

        if(c.moveToFirst()){
            // определяем номера столбцов по имени в выборке
            int contentColName = c.getColumnIndex("name");
            int contentColDescription = c.getColumnIndex("description");
            int contentColFirstCoordinate = c.getColumnIndex("firstCoordinate");
            int contentColSecondCoordinate = c.getColumnIndex("secondCoordinate");
            int contentColStartTime = c.getColumnIndex("startTime");
            int contentColEndTime = c.getColumnIndex("endTime");
            int contentColReit = c.getColumnIndex("reit");
            int contentColPicture = c.getColumnIndex("picture");

            ret = new mapPoint(c.getString(contentColName), c.getString(contentColDescription),
                    new Pair<Float, Float>(Float.parseFloat(c.getString(contentColFirstCoordinate)),Float.parseFloat(c.getString(contentColSecondCoordinate))),
                    getTagsByEventName(c.getString(contentColName)),c.getString(contentColStartTime),c.getString(contentColEndTime), Float.parseFloat(c.getString(contentColReit)), Integer.parseInt(c.getString(contentColPicture)));
        }

        c.close();
        return ret;
    }

    public ArrayList<String> findEventsByTags(ArrayList<String> tags){
        //Получить ID каждого тега
        ArrayList<Integer> tmp = convertTagNamesToIDs(tags);
        if(tmp.size() == 0){
            return null;
        }
        HashSet<String> ret = eventIDsByTagID(tmp.get(0));
        for(int i = 1; i < tmp.size(); i++){
            ret.retainAll(eventIDsByTagID(tmp.get(i)));
        }

        ArrayList<String> ret1 = new ArrayList<>();
        Iterator iter = ret.iterator();
        while(iter.hasNext()){
            ret1.add(iter.next().toString());
        }

        return ret1;
    }

    /**
     * По одному тегу получаем все события, которые его включают
     * @param tagID
     * @return
     */
    public HashSet<String> eventIDsByTagID(int tagID){
        HashSet<String> ret = new HashSet<>();
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("EventTag", null, "tagId = " + tagID, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int contentColIndex = c.getColumnIndex("eventId");

            do
                ret.add(c.getString(contentColIndex));
            while(c.moveToNext());
        }

        c.close();
        return ret;
    }

    public ArrayList<Integer> convertTagNamesToIDs(ArrayList<String> tags){

        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < tags.size(); i++){
            int curID = getIdByTag(tags.get(i));
            if(curID != -1){
                tmp.add(curID);
            }
        }

        return tmp;
    }

    private ArrayList<String> getTagsByEventName(String name) {
        ArrayList<String> ret = new ArrayList<String>();
        String tmp = "";
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Events", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int contentColIndex = c.getColumnIndex("id");
            int contentColName = c.getColumnIndex("name");
            String str = "";
            do {
                str = c.getString(contentColName);
                tmp = c.getString(contentColIndex);
            }while (!str.equals(name) && c.moveToNext());
            // определяем номера столбцов по имени в выборке
            if(!str.equals(name)) {
                tmp = "";
            }
        }

        c.close();

        ArrayList<Integer> tmp1 = getTagIDsByEventID(tmp);

        for(int i = 0; i < tmp1.size(); i++){
            ret.add(getTagById(tmp1.get(i)));
        }

        return ret;
    }

    public String getTagById(int id){
        String ret = "";
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Tags", null, "id = " + id, null, null, null, null);

        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int contentColIndex = c.getColumnIndex("name");
            ret = c.getString(contentColIndex);
        }

        c.close();
        return ret;
    }

    public int getIdByTag(String tagName){
        int ret = -1;
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("Tags", null, "name = " + tagName, null, null, null, null);

        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int contentColIndex = c.getColumnIndex("name");
            ret = c.getInt(contentColIndex);
        }

        c.close();
        return ret;
    }

    public ArrayList<Integer> getTagIDsByEventID(String eID){
        SQLiteDatabase db = LDB.getWritableDatabase();
        Cursor c = db.query("EventTag", null, "eventId = " + eID, null, null, null, null);

        ArrayList<Integer> tmp1 = new ArrayList<>();
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int contentColIndex1 = c.getColumnIndex("tagId");

            do {
                tmp1.add(c.getInt(contentColIndex1));
            } while (c.moveToNext());
        }

        c.close();
        return tmp1;
    }

}

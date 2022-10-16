package model;

import android.util.Pair;

import java.util.ArrayList;

import ru.cityflow24.cityflow24.R;

public class mapPoint {
    private String name;
    private String description;
    private Pair<Float,Float> coordinate;
    private ArrayList<String> tags;
    private String startTime, endTime;
    private Float reit;
   // private int immageID = R.drawable.dance;

    public mapPoint(String name,
                    String description,
                    Pair<Float, Float> coordinate,
                    ArrayList<String> tags,
                    String startTime,
                    String endTime,
                    Float reit,
                    int immageID) {
        this.name = name;
        this.description = description;
        this.coordinate = coordinate;
        this.tags = tags;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reit = reit;
      //  this.immageID = immageID;
    }

    public Float getReit() {
        return reit;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Pair<Float, Float> getCoordinate() {
        return coordinate;
    }

    public String getDescription() {
        return description;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    //public int getImmageID() {
    //    return immageID;
   // }

   // public void setImmageID(int immageID) {
  //      this.immageID = immageID;
 //   }
}

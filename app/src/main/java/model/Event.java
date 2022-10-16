package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Event {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("authorId")
    @Expose
    private Long authorId;

    @SerializedName("dateFrom")
    @Expose
    private Date dateFrom;

    @SerializedName("dateTo")
    @Expose
    private Date dateTo;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("introduction")
    @Expose
    private String introduction;

    @SerializedName("locations")
    @Expose
    private List<Location> locations;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("paid")
    @Expose
    private Boolean paid;

    @SerializedName("photo")
    @Expose
    private String photo;

    public Event() {
    }

    public Event(Long id, Long authorId, Date dateFrom, Date dateTo, String description, String introduction, List<Location> locations, String name, Boolean paid, String photo) {
        this.id = id;
        this.authorId = authorId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
        this.introduction = introduction;
        this.locations = locations;
        this.name = name;
        this.paid = paid;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getDateFrom() {
        SimpleDateFormat sdp = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return sdp.format(dateFrom) ;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        SimpleDateFormat sdp = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return sdp.format(dateTo);
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", description='" + description + '\'' +
                ", introduction='" + introduction + '\'' +
                ", locations=" + locations +
                ", name='" + name + '\'' +
                ", paid=" + paid +
                ", photo='" + photo + '\'' +
                '}';
    }
}

package com.example.hisaki.netflix.enteties;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hisaki on 26.10.2016.
 */
@Entity
public class Movie implements Parcelable {
    @Id
    private long unit;
    private int show_id;
    private String show_title;
    private String release_year;
    private String rating;
    private String category;
    private String show_cast;
    private String director;
    private String summary;
    private String poster;
    private int mediatype;


    @Generated(hash = 1604036300)
    public Movie(long unit, int show_id, String show_title, String release_year,
            String rating, String category, String show_cast, String director,
            String summary, String poster, int mediatype) {
        this.unit = unit;
        this.show_id = show_id;
        this.show_title = show_title;
        this.release_year = release_year;
        this.rating = rating;
        this.category = category;
        this.show_cast = show_cast;
        this.director = director;
        this.summary = summary;
        this.poster = poster;
        this.mediatype = mediatype;
    }

    @Generated(hash = 1263461133)
    public Movie() {
    }


    /**
     *
     * @return
     *     The unit
     */
    public long getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     *     The unit
     */
    public void setUnit(long unit) {
        this.unit = unit;
    }

    /**
     *
     * @return
     *     The show_id
     */
    public int getshow_id() {
        return show_id;
    }

    /**
     *
     * @param show_id
     *     The show_id
     */
    public void setshow_id(int show_id) {
        this.show_id = show_id;
    }

    /**
     *
     * @return
     *     The show_title
     */
    public String getShowTitle() {
        return show_title;
    }

    /**
     *
     * @param show_title
     *     The show_title
     */
    public void setShowTitle(String show_title) {
        this.show_title = show_title;
    }

    /**
     *
     * @return
     *     The release_year
     */
    public String getReleaseYear() {
        return release_year;
    }

    /**
     *
     * @param release_year
     *     The release_year
     */
    public void setReleaseYear(String release_year) {
        this.release_year = release_year;
    }

    /**
     *
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     *     The show_cast
     */
    public String getShowCast() {
        return show_cast;
    }

    /**
     *
     * @param show_cast
     *     The show_cast
     */
    public void setShowCast(String show_cast) {
        this.show_cast = show_cast;
    }

    /**
     *
     * @return
     *     The director
     */
    public String getDirector() {
        return director;
    }

    /**
     *
     * @param director
     *     The director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     *
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     *     The poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     *
     * @param poster
     *     The poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     *
     * @return
     *     The mediatype
     */
    public int getMediatype() {
        return mediatype;
    }

    /**
     *
     * @param mediatype
     *     The mediatype
     */
    public void setMediatype(int mediatype) {
        this.mediatype = mediatype;
    }

    public int getShow_id() {
        return this.show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public String getShow_title() {
        return this.show_title;
    }

    public void setShow_title(String show_title) {
        this.show_title = show_title;
    }

    public String getRelease_year() {
        return this.release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getShow_cast() {
        return this.show_cast;
    }

    public void setShow_cast(String show_cast) {
        this.show_cast = show_cast;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(unit);
        parcel.writeString(show_title);
        parcel.writeString(poster);
    }
    private Movie(Parcel parcel) {
        show_title  = parcel.readString();
        poster      = parcel.readString();
        unit        = parcel.readLong();
    }
}

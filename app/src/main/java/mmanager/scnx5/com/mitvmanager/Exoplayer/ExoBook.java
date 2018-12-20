package mmanager.scnx5.com.mitvmanager.Exoplayer;



import android.graphics.Bitmap;

public class ExoBook {

    private String Title;
    private String Category ;
    private String Url ;
    private String Sypnopsis;
    private String Thumbnail ;
    private String Id;
    private Integer epg;
    private String progInfo1;
    private String progInfo2;
    private String premium;

    public ExoBook() {
    }

    public ExoBook(String title, String category, String url, String thumbnail,String sypnopsis,String id,String premium,Integer epgid,String progInfo1,String progInfo2) {
        Title = title;
        Category = category;
        Url = url;
        Thumbnail = thumbnail;
        Sypnopsis = sypnopsis;
        Id=id;
        epg=epgid;
        this.premium=premium;
        this.progInfo1=progInfo1;
        this.progInfo2=progInfo2;


    }


    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getUrl() {
        return Url;
    }

    public String getSysnopsis() {
        return Sypnopsis;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public String getId() {
        return Id;
    }

    public Integer getepgId() {
        return epg;
    }

    public String getProgInfo1() {
        return progInfo1;
    }

    public String getProgInfo2() {
        return progInfo2;
    }

    public String getpremium() {
        return premium;
    }



    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setSypnopsis(String sypnopsis) {
        Sypnopsis = sypnopsis;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setEpgId(Integer epgid) {
        epg = epgid;
    }

    public void setProgInfo1(String programInfo1) {
        progInfo1 = programInfo1;
    }

    public void setProgInfo2(String programInfo2) {
        progInfo2 = programInfo2;
    }

    public void setPremium(String prem) {
        premium = prem;
    }

}
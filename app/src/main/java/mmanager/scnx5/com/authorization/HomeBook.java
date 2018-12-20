package mmanager.scnx5.com.authorization;


public class HomeBook {

    private String Title;
    private String Category ;
    private String Url ;
    private String Sypnopsis;
    private String Thumbnail ;
    private String Id;
    private Integer epg;

    private String premium;

    public HomeBook() {
    }

    public HomeBook(String title, String category, String url, String thumbnail, String sypnopsis, String id, String premium, Integer epgid) {
        Title = title;
        Category = category;
        Url = url;
        Thumbnail = thumbnail;
        Sypnopsis = sypnopsis;
        Id=id;
        epg=epgid;
        this.premium=premium;


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



    public void setPremium(String prem) {
        premium = prem;
    }

}
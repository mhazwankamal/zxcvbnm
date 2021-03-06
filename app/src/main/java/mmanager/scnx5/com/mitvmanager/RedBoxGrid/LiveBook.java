package mmanager.scnx5.com.mitvmanager.RedBoxGrid;


public class LiveBook {

    private String Title;
    private String Category ;
    private String Url ;
    private String Sypnopsis;
    private String Thumbnail ;
    private String Id;
    private String Premium;

    public LiveBook() {
    }

    public LiveBook(String title, String category, String url, String thumbnail, String sypnopsis, String id,String premium) {
        Title = title;
        Category = category;
        Url = url;
        Thumbnail = thumbnail;
        Sypnopsis = sypnopsis;
        Id=id;
        Premium=premium;

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

    public String getPremium() {
        return Premium;
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

    public void setPremium(String premium) {
        Id = premium;
    }
}

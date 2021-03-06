package mmanager.scnx5.com.mitvmanager.VODGrid;


public class VODBook {


    private String Title;
    private String Category ;
    private String Url ;
    private String Sypnopsis;
    private String Thumbnail ;
    private String Backdrop ;
    private String Id;

    public VODBook() {
    }

    public VODBook(String id,String title, String category, String url, String thumbnail, String sypnopsis,String backdrop) {
        Title = title;
        Category = category;
        Url = url;
        Thumbnail = thumbnail;
        Sypnopsis = sypnopsis;
        Backdrop = backdrop;
        Id=id;
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

    public String getBackdrop() {
        return Backdrop;
    }

    public String getId() {        return Id;    }

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

    public void setId(String id) { Id=id;}

    public void setBackdrop(String backdrop) {
        Backdrop = backdrop;
    }
}

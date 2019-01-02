package mmanager.scnx5.com.authorization;


public class LiveEventBook {

    private String TitleType;
    private String BackgroundImage;
    private String Date;
    private String Time ;
    private String Title;
    private Integer id;



    public LiveEventBook() {
    }

    public LiveEventBook(String TitleTypes, String BackgroundImages, String Dates, String Times, String Titles, Integer ids) {
        TitleType = TitleTypes;
        BackgroundImage = BackgroundImages;
        Date = Dates;
        Time = Times;
        Title=Titles;
        id=ids;


    }


    public String getTitle() {
        return Title;
    }

    public String getBackgroundImage() {
        return BackgroundImage;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public Integer getId() {
        return id;
    }

    public String getTitles() {
        return Title;
    }





    public void setTitle(String title) {
        Title = title;
    }

    public void setBackgroundImage(String BackgroundImages) {
        BackgroundImage = BackgroundImages;
    }

    public void setDate(String Dates) {
        Date = Dates;
    }

    public void setTime(String Times) {
        Time = Times;
    }

    public void setId(String id) {
        id = id;
    }



}
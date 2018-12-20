package mmanager.scnx5.com.authorization;


import java.util.ArrayList;
import java.util.List;

public class AllBook {

    private String HeaderTitle;
    private List<HomeTrendBook> AllBooks;


    public AllBook() {
    }

    public AllBook(String headertitle, List<HomeTrendBook> allbooks) {
        HeaderTitle = headertitle;
        AllBooks = allbooks;

    }


    public String getHeaderTitle() {
        return HeaderTitle;
    }

    public List<HomeTrendBook> getAllBooks() {
        return AllBooks;
    }


    public void setHeaderTitle(String headertitle) {
        HeaderTitle = headertitle;
    }

    public void setAllBooks(List<HomeTrendBook> allbooks) {
        AllBooks = allbooks;
    }


}
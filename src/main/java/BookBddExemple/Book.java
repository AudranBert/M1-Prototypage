package BookBddExemple;

import java.sql.Date;

public class Book {
    private String BookId;
    private String Title;
    private String SubTitle;
    private int Pages;
    private Date Published;

    public Book(String bookId, String title, String subTitle, int pages,
                Date published, String description) {
        super();
        BookId = bookId;
        Title = title;
        SubTitle = subTitle;
        Pages = pages;
        Published = published;
        Description = description;
    }

    public String getBookId() {
        return BookId;
    }
    public void setBookId(String bookId) {
        BookId = bookId;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getSubTitle() {
        return SubTitle;
    }
    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }
    public int getPages() {
        return Pages;
    }
    public void setPages(int pages) {
        Pages = pages;
    }
    public Date getPublished() {
        return Published;
    }
    public void setPublished(Date published) {
        Published = published;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    private String Description ;

}
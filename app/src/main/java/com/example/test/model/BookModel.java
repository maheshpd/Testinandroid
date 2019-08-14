package com.example.test.model;

public class BookModel {
    String bookimgUrl,booktitle,bookdes,booktype;

    public BookModel(String bookimgUrl, String booktitle, String bookdes, String booktype) {
        this.bookimgUrl = bookimgUrl;
        this.booktitle = booktitle;
        this.bookdes = bookdes;
        this.booktype = booktype;
    }

    public String getBookimgUrl() {
        return bookimgUrl;
    }

    public void setBookimgUrl(String bookimgUrl) {
        this.bookimgUrl = bookimgUrl;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getBookdes() {
        return bookdes;
    }

    public void setBookdes(String bookdes) {
        this.bookdes = bookdes;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }
}

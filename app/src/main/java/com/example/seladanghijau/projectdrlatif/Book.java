package com.example.seladanghijau.projectdrlatif;

/**
 * Created by SeladangHijau on 20/4/2016.
 */
public class Book {
    private int id, pdfID;
    private String accessionno, author, title;

    // constructors
    public Book() {
        id = 0;
        pdfID = 0;
        accessionno = null;
        author = null;
        title = null;
    }

    public Book(int id, int pdfID, String accessionno, String author, String title) {
        this.id = id;
        this.pdfID = pdfID;
        this.accessionno = accessionno;
        this.author = author;
        this.title = title;
    }

    // getter and setter
    public void setId(int id) { this.id = id; }
    public void setPdfID(int pdfID) { this.pdfID = pdfID; }
    public void setAccessionno(String accessionno) { this.accessionno = accessionno;}
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }

    public int getId() { return id; }
    public int getPdfID() { return pdfID; }
    public String getAccessionno() { return accessionno; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
}

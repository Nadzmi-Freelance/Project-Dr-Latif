package com.example.seladanghijau.projectdrlatif;

/**
 * Created by Seladang Hijau on 3/6/2016.
 */
public class Book {
    private int id;
    private String accessionno, author, title, edition;

    // constructors
    public Book() {
        id = 0;
        accessionno = null;
        author = null;
        title = null;
        edition = null;
    }

    public Book(int id, String accessionno, String author, String title, String edition) {
        this.id = id;
        this.accessionno = accessionno;
        this.author = author;
        this.title = title;
        this.edition = edition;
    }

    // getter and setter
    public void setId(int id) { this.id = id; }
    public void setAccessionno(String accessionno) { this.accessionno = accessionno;}
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setEdition(String edition) { this.edition = edition; }

    public int getId() { return id; }
    public String getAccessionno() { return accessionno; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getEdition() { return edition; }
}

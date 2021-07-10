package com.example.gbpiet.ui.Ebook;

public class Edata {
    private String DocumentTitle, DocumentUrl , date, time;

    public Edata() {
    }

    public Edata(String documentTitle, String documentUrl,String date, String time) {
        DocumentTitle = documentTitle;
        DocumentUrl = documentUrl;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocumentTitle() {
        return DocumentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        DocumentTitle = documentTitle;
    }

    public String getDocumentUrl() {
        return DocumentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        DocumentUrl = documentUrl;
    }
}


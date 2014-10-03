package com.example.FeedHunter;

public class ArticleInfo {
    public ArticleInfo(){

    }
    public ArticleInfo(String titl, String text, String lin){
        this.Title = titl;
        this.Link = lin;
        this.Abstract = text;
    }
    public String Title;
    public String Abstract;
    public String Link;
}

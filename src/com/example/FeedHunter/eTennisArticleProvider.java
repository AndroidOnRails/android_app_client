package com.example.FeedHunter;


import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;



public class eTennisArticleProvider implements IArticleProvider {
    public String getName() {
        return "e-tennis.ME";
    }

    public ArrayList<ArticleInfo> getArticles() {
        if (!HttpHelpers.isInternetAvailable())
        {
            ArrayList<ArticleInfo> articles = new ArrayList<ArticleInfo>();
            ArticleInfo info = new ArticleInfo();
            info.Title = "No articles found.";
            info.Abstract = "Check your Internet connection.";
            articles.add(info);
            return articles;
        }

        String url = "http://sleepy-ocean-8631.herokuapp.com/articles.json";
        String rss = HttpHelpers.DownloadRss(url);

        ArrayList<ArticleInfo> articles = ParseToArticles(rss);


        //ArrayList<ArticleInfo> res = new ArrayList<ArticleInfo>();
        //res.add(new ArticleInfo("beatles",rss));


        return articles;
    }

    private static ArrayList<ArticleInfo> ParseToArticles(String rss)
    {
        ArrayList<ArticleInfo> articles = new ArrayList<ArticleInfo>();


        String author,
                text,
                url;
        int id;

        try {
            JSONArray jArray =  new JSONArray(rss);

            for (int i=0; i < jArray.length(); i++){
                JSONObject oneObject = jArray.getJSONObject(i);
                // Pulling items from the array
                id = oneObject.getInt("id");
                author = oneObject.getString("author");
                text = oneObject.getString("text");
                url = oneObject.getString("url");
                ArticleInfo obj = new ArticleInfo(author, text, url);
                articles.add(obj);
            }




        } catch (Exception e) {
            ArticleInfo ob = new ArticleInfo("idiot","idiot","idiot");
            articles.add(ob);
            return articles;
        }



//        Document doc = DomFromString(rss);
//        NodeList nodes = doc.getElementsByTagName("entry");
//        int size = Math.min(nodes.getLength(), 20);
//
//        // For each ENTRY node
//        for (int i=0; i<size; i++) {
//            Node rssEntry = nodes.item(i);
//            NodeList entryNodes = rssEntry.getChildNodes();
//            ArticleInfo a = new ArticleInfo();
//            Node title = entryNodes.item(1);
//            a.Title = title.getTextContent();
//            Node summary = entryNodes.item(2);
//            a.Abstract = summary.getTextContent();
//            articles.add(a);
//        }

        return articles;
    }

    private static Document DomFromString(String xml)
    {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);
        } catch (ParserConfigurationException e) {
            System.out.println("XML parse error: " + e.getMessage());
            return null;
        } catch (SAXException e) {
            System.out.println("Wrong XML file structure: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
            return null;
        }

        return doc;
    }
}

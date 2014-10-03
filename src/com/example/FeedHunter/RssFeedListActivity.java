package com.example.FeedHunter;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RssFeedListActivity extends ListActivity {

    private ListView list;
    final String LOG_TAG = "myLogs";
    private Button buttonCreate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // list = getListView();
        //list = (ListView) findViewById(android.R.id.list);

//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        ArrayAdapter<ArticleInfo> adapter = new ArrayAdapter<ArticleInfo>(this, android.R.layout.activity_list_item);
//        list.setAdapter(adapter);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rssfeedlist);

        list = getListView();
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        buttonCreate = (Button) findViewById(R.id.button2);
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            //            @Override
            public void onClick(View v) {
                method();
            }
        };
        buttonCreate.setOnClickListener(oclBtnOk);


        ArrayAdapter<ArticleInfo> adapter = new ArrayAdapter<ArticleInfo>(this, android.R.layout.simple_list_item_single_choice);
//        list.setListAdapter(adapter);
        setListAdapter(adapter);

        RssDownloadOperation op = new RssDownloadOperation(this);
        op.execute();


    }

    public void method() {

        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://sleepy-ocean-8631.herokuapp.com/articles.json");
//        httpPost.addHeader("author","text");

//        httpDelete.addHeader("Content-Type","application/json");

        //   StringEntity en = new StringEntity();


        // JSONObject bean = new JSONObject(" \"authenticity_token\":\"eMIiDdHbJk7f+gV1acQawBrYmAi0u1eDok77EGU9pMY=\", \"article\":{\"author\":\"bfbrbredc\",\"text\":\"rverbrebrebrebre\"}}");
        // bean.optJSONObject("");


        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("authenticity_token", "eMIiDdHbJk7f+gV1acQawBrYmAi0u1eDok77EGU9pMY="));
        params.add(new BasicNameValuePair("author", "pashka"));
        params.add(new BasicNameValuePair("text", "pashka"));
        try {

            JSONObject obj = new JSONObject();
            obj.put("authenticity_token", "eMIiDdHbJk7f+gV1acQawBrYmAi0u1eDok77EGU9pMY=");
            obj.put("author", "pashka");
            obj.put("text", "pashka");
            Toast.makeText(this, "You selected: " + obj, Toast.LENGTH_LONG).show();

            httpPost.setEntity(new StringEntity(obj.toString(), "UTF-8"));


            // httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String text = EntityUtils.toString(entity);
            Toast.makeText(this, "You selected: " + text, Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
        }

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ArticleInfo listItem = (ArticleInfo) getListAdapter().getItem(position);
//TODO: to delete the selested article


//
//
//            HttpClient client = new DefaultHttpClient();
//            HttpDelete httpdelete = new HttpDelete("http://sleepy-ocean-8631.herokuapp.com/articles/1");
//
//            httpdelete.getMethod();
//
//           // HttpResponse response = client.execute(new HttpGet(url));
//           // HttpEntity entity = response.getEntity();
//           // text = EntityUtils.toString(entity);
//
//
//        }
//        catch(Exception xception)
//        {
//        }


            // Set the item as checked to be highlighted
//        l.setItemChecked(position, true);
//        v.setBackgroundColor(Color.BLUE);

            // conversationAdapter.notifyDataSetChanged();


//        JSONObject bean = new JSONObject(" \"authenticity_token\":\"eMIiDdHbJk7f+gV1acQawBrYmAi0u1eDok77EGU9pMY=\", \"article\":{\"author\":\"bfbrbredc\",\"text\":\"rverbrebrebrebre\"}}");


            HttpClient client = new DefaultHttpClient();
            HttpDelete httpPost = new HttpDelete("http://sleepy-ocean-8631.herokuapp.com/articles/7.json");
//

            try {

                JSONObject obj = new JSONObject();
                obj.put("authenticity_token", "eMIiDdHbJk7f+gV1acQawBrYmAi0u1eDok77EGU9pMY=");
               obj.put("id", 7);
              //  obj.put("text", "pashka");
                Toast.makeText(this, "You selected: " + obj, Toast.LENGTH_LONG).show();

                httpPost.setEntity(new StringEntity(obj.toString(), "UTF-8"));


                // httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String text = EntityUtils.toString(entity);

                Toast.makeText(this, "You selected: ", Toast.LENGTH_LONG).show();


//        try{
//                   URL url;
//              url = new URL("http://sleepy-ocean-8631.herokuapp.com/articles/1.json");
//            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            httpCon.setDoOutput(true);
//            httpCon.setRequestProperty(
//                    "head", "no_content");
//            httpCon.setRequestMethod("DELETE");
//            httpCon.connect();
//        } catch (Exception ex) {
//        }


                String url = "http://sleepy-ocean-8631.herokuapp.com/articles/" + position + ".json";


//
            } catch (Exception ex) {
            }



    }
}
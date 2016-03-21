package com.example.seladanghijau.projectdrlatif;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seladang Hijau on 3/11/2016.
 */
public class HTTPHandler {
    private HttpClient client;
    private HttpPost post;
    private HttpResponse response;
    private HttpEntity entity;

    private int status;
    private String url, data;

    public HTTPHandler() throws Exception {
        client = new DefaultHttpClient();

        this.status = HttpURLConnection.HTTP_GONE;
        this.url = null;
        this.data = null;
    }

    public HttpResponse execute(HttpPost postData) throws Exception {
        HttpResponse httpResponse = client.execute(postData);

        return httpResponse;
    }

    public String result(String url) throws Exception {
        post = new HttpPost(url);
        response = execute(post);
        status = response.getStatusLine().getStatusCode();

        if(status == 200) {
            entity = response.getEntity();
            data = EntityUtils.toString(entity);
        } else data = null;

        return data;
    }

    public String result(String url, List<NameValuePair> dataList) throws Exception {
        post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(dataList));

        response = execute(post);
        status = response.getStatusLine().getStatusCode();

        if(status == 200) {
            entity = response.getEntity();
            data = EntityUtils.toString(entity);
        } else data = null;

        return data;
    }

    // getter and setter
    public void setUrl(String url) { this.url = url; }
    public void setData(String data) { this.data = data; }


    public int getStatus() { return status; }
    public String getUrl() { return url; }
    public String getData() { return data; }
}

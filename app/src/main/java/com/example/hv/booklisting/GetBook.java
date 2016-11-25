package com.example.hv.booklisting;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hv on 11/21/16.
 */
public class GetBook extends AsyncTask<String, String, String> {

    ArrayList<Book> bookList;
    private Context context;
    Book book;
    int itemsCount;

    public GetBook(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            bookList = new ArrayList<Book>();

            String response = sendGet(strings[0]);
            Log.d("result", response);

            JSONObject object = new JSONObject(response);
            JSONArray booksArray = object.getJSONArray("items");

            Log.d("items", booksArray.toString());
            itemsCount = booksArray.length();
            Log.d("itemsLength", "" + itemsCount);

            for (int i = 0; i <= booksArray.length() - 1; i++){

                JSONObject item = booksArray.getJSONObject(i);

                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

              book= new Book();

                if (volumeInfo.has("title")) {
                    String title = volumeInfo.getString("title");
                    book.setbName(title);
                }

                if (volumeInfo.has("authors")) {
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    String author = authors.getString(0);
                    book.setbAuthor(author);
                }

                if (volumeInfo.has("publisher") && volumeInfo.has("publishedDate")) {
                    String publisher = volumeInfo.getString("publisher");
                    String publishedDate = volumeInfo.getString("publishedDate");
                    String publishDetails = publisher + " " + publishedDate;
                    book.setbPublisher(publishDetails);
                }

                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String s) {

        // Setup the data source
        BooksAdapter adapter = new BooksAdapter(context, bookList);

        // get the ListView and attach the adapter
        MainActivity.booksListView.setAdapter(adapter);
        MainActivity.countTextView.setText("Number of Books: " + bookList.size());

    }

    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

}

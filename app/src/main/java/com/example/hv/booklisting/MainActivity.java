package com.example.hv.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView searchTextView;
    private Button searchButton;

    public static ListView booksListView;

    public static TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTextView = (TextView) findViewById(R.id.textView);

        searchButton = (Button) findViewById(R.id.button);

        booksListView = (ListView) findViewById(R.id.listView);

        countTextView = (TextView) findViewById(R.id.count);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!searchTextView.getText().toString().isEmpty()){

                    //for google books api
                    String url = "https://www.googleapis.com/books/v1/volumes?q=";

                    String newText = searchTextView.getText().toString().replace(" ", "+");

                    final String completeUrl = url + newText;

                    new GetBook(MainActivity.this).execute(completeUrl);

                }
            }
        });
    }


}

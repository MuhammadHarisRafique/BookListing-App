package com.example.hv.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hv on 11/21/16.
 */
public class BooksAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> items;

    public BooksAdapter(Context context, ArrayList<Book> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // inflate the layout for each list row
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_book, parent, false);

        //get current item to be displayed
        Book currentItem = (Book) getItem(position);

        //get the TextView for book name, book author and book publisher

        TextView bookName = (TextView) convertView.findViewById(R.id.bookname);

        TextView bookAuthor = (TextView) convertView.findViewById(R.id.bookauthor);

        TextView bookPublisher = (TextView) convertView.findViewById(R.id.bookpublisher);

        //sets the text for book name, book author and book publisher from the current item object
        bookName.setText(currentItem.getbName());
        bookAuthor.setText(currentItem.getbAuthor());
        bookPublisher.setText(currentItem.getbPublisher());

        //returns the view for the current row
        return convertView;
    }
}

package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.examen.data.RatingContract;
import com.example.examen.data.RatingDbHelper;

public class ListActivity extends AppCompatActivity {
    private SQLiteDatabase mDb;
    private RatingListAdaptater mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView ratingListRecyclerView;
        ratingListRecyclerView = findViewById(R.id.rating_list_view);
        ratingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RatingDbHelper dbHelper = new RatingDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllRating();
        mAdapter = new RatingListAdaptater(this, cursor);
        ratingListRecyclerView.setAdapter(mAdapter);
    }
    private Cursor getAllRating() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                RatingContract.RatingEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                RatingContract.RatingEntry.COLUMN_DATE_HEURE
        );
    }
}
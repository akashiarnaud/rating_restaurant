package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.examen.data.RatingContract;
import com.example.examen.data.RatingDbHelper;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class FormulaireActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private SQLiteDatabase mDb;

    private EditText mRestaurantName;
    private EditText mDateHeure;
    private RatingBar mDecorationRating;
    private RatingBar mFoodRating;
    private RatingBar mServiceRating;
    private EditText mComments;
    private Button mValider;
    private Button mReinitialiser;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        // get element from view
        mRestaurantName = findViewById(R.id.restaurant_name);
        mDateHeure = findViewById(R.id.date_heure);
        mDecorationRating = findViewById(R.id.decoration_rating);
        mFoodRating = findViewById(R.id.food_rating);
        mServiceRating = findViewById(R.id.service_rating);
        mComments = findViewById(R.id.comments);
        mValider = findViewById(R.id.valider);
        mReinitialiser = findViewById(R.id.reinitialiser);
        RatingDbHelper dbHelper = new RatingDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        mDateHeure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(
                        FormulaireActivity.this,
                        null,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
// If you're calling this from a support Fragment
                dpd.show();
            }
        });

        mValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurant_name = mRestaurantName.getText().toString();
                String date_heure = mRestaurantName.getText().toString();
                float decoration_rating = mDecorationRating.getRating();
                float food_rating = mFoodRating.getRating();
                float service_rating = mServiceRating.getRating();
                String comments = mComments.getText().toString();

                /* put here SQLITE INSERT FUNCTION and show popup*/
                addAdviceRating(restaurant_name,date_heure,decoration_rating,food_rating,service_rating,comments);
                new AlertDialog.Builder(FormulaireActivity.this)
                        .setTitle("Succès")
                        .setMessage("Votre avis a bien été enregistrer")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(FormulaireActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });

        mReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRestaurantName.setText("");
                mDateHeure.setText("");
                mDecorationRating.setRating(0);
                mFoodRating.setRating(0);
                mServiceRating.setRating(0);
                mComments.setText("");
            }
        });
    }
    public long addAdviceRating(String name,String dateHeure, float deco, float food, float service, String comments){
        ContentValues cv = new ContentValues();
        cv.put(RatingContract.RatingEntry.COLUMN_NAME, name);
        cv.put(RatingContract.RatingEntry.COLUMN_DATE_HEURE, dateHeure);
        cv.put(RatingContract.RatingEntry.COLUMN_RATING_DECO, deco);
        cv.put(RatingContract.RatingEntry.COLUMN_RATING_FOOD, food);
        cv.put(RatingContract.RatingEntry.COLUMN_RATING_SERVICE, service);
        cv.put(RatingContract.RatingEntry.COLUMN_DESCRIPTION, comments);
        System.out.println(name + " insertion reussi"+ deco);
        return mDb.insert(RatingContract.RatingEntry.TABLE_NAME, null,cv);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(month+1)+"/"+year;
        mDateHeure.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute+"m"+second;
        mDateHeure.setText(mDateHeure.getText().toString()+ " "+time);
    }
}
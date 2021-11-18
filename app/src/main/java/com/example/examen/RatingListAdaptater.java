package com.example.examen;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.data.RatingContract;

public class RatingListAdaptater extends RecyclerView.Adapter<RatingListAdaptater.RatingListViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    public RatingListAdaptater(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }
    @Override
    public RatingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.rating_list_item, parent, false);
        return new RatingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        String name = mCursor.getString(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_NAME));
        String dateHour = mCursor.getString(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_DATE_HEURE));
        String comments = mCursor.getString(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_DESCRIPTION));
        float decoRating = mCursor.getFloat(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_RATING_DECO));
        float foodRating = mCursor.getFloat(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_RATING_FOOD));
        float serviceRating = mCursor.getFloat(mCursor.getColumnIndexOrThrow(RatingContract.RatingEntry.COLUMN_RATING_SERVICE));
        holder.nameTextView.setText(name);
        holder.dateTextView.setText("date et heure "+dateHour);
        holder.commentsTextView.setText("commentaire : "+comments);
        holder.decoTextView.setText("note du decoration : "+String.valueOf(decoRating));
        holder.foodTextView.setText("note du repas : "+String.valueOf(foodRating));
        holder.serviceTextView.setText("note du service : " +String.valueOf(serviceRating));
    }

    @Override
    public int getItemCount() {
       return mCursor.getCount();
    }

    class RatingListViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView dateTextView;
        TextView commentsTextView;
        TextView decoTextView;
        TextView foodTextView;
        TextView serviceTextView;

        public RatingListViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.idName);
            dateTextView = itemView.findViewById(R.id.idDate);
            commentsTextView = itemView.findViewById(R.id.idComments);
            decoTextView = itemView.findViewById(R.id.idDeco);
            foodTextView = itemView.findViewById(R.id.idFood);
            serviceTextView = itemView.findViewById(R.id.idService);
        }

    }
}

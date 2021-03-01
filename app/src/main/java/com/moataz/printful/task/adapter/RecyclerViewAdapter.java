package com.moataz.printful.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.printful.task.activity.ContentActivity;
import com.moataz.printful.task.model.Data;
import com.moataz.printful.task.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Data> images;
    Context context;

    public RecyclerViewAdapter(Context context, List<Data> images){
        this.inflater = LayoutInflater.from(context);
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("imageURL",images.get(viewHolder.getAdapterPosition()).getImageURL());
                intent.putExtra("title",images.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("description",images.get(viewHolder.getAdapterPosition()).getDescription());

                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(images.get(position).getTitle());
        // using Picasso library to display the images
        Picasso.get()
                .load(images.get(position).getImageURL())
                .fit() // to resize the image to imageView
                .transform(new PicassoRoundedTransformation(20, 0)) // Add radius to the images
                .noFade()
                .into(holder.imageURL);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageURL;
        RelativeLayout view_container;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.imageonclick);
            title = itemView.findViewById(R.id.text_main);
            imageURL = itemView.findViewById(R.id.image_main);
        }
    }

    // Calculate the items and auto-fit it on the screen
    public static class Utility {
        public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
            return noOfColumns;
        }
    }
}

package com.moataz.printful.task.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.moataz.printful.task.R;
import com.moataz.printful.task.adapter.PicassoRoundedTransformation;
import com.squareup.picasso.Picasso;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //make the status bar white with black icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        // get the data
        String image_url = getIntent().getExtras().getString("imageURL") ;
        String title  = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");
        // findView
        ImageView img = findViewById(R.id.image_content);
        TextView textTitle = findViewById(R.id.title_content);
        TextView textDescription = findViewById(R.id.dic_content);
        // setting values to each view
        textTitle.setText(title);
        textDescription.setText(description);
        Picasso.get()
                .load(image_url)
                .noFade()
                .into(img);

        // back to last activity
        Button back_icon = findViewById(R.id.button_back);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button about = findViewById(R.id.button_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ContentActivity.this, "There is no thing here :D" , Toast.LENGTH_LONG).show();
            }
        });
    }
}
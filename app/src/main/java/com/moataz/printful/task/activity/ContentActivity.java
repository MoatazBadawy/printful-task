package com.moataz.printful.task.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.moataz.printful.task.R;
import com.moataz.printful.task.adapter.PicassoRoundedTransformation;
import com.squareup.picasso.Picasso;

public class ContentActivity extends AppCompatActivity {

    BottomSheetBehavior mBottomSheetBehavior;
    ConstraintLayout mBottomsheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //make the status bar white with black icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initializeDisplayContent();
        webView();
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
    }

    public void webView() {
        String infoURL = getIntent().getExtras().getString("infoURL");
        Button about = findViewById(R.id.button_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WebView mWebView = findViewById(R.id.web_view_info_display);
                mWebView.loadUrl(infoURL);
                // Enable Javascript
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                // To close WebView when go back
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (url.endsWith("#closeWebview")) {
                            mWebView.setVisibility(View.GONE);
                        }
                    }
                });

                // BottomSheetBehavior to make sheet bar
                mBottomsheet = findViewById(R.id.bottom_sheet);
                mBottomSheetBehavior = BottomSheetBehavior.from(mBottomsheet);
                if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }
}
package com.example.hisaki.netflix.adapters;

import android.os.AsyncTask;
import android.renderscript.Double2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hisaki.netflix.R;

/**
 * Created by hisaki on 26.10.2016.
 */

public class MovieHolder {
    TextView title;
    TextView director;
    TextView category;

    ImageView image;

    TextView rating;
    TextView release;

    public MovieHolder(View... views) {
        category    = (TextView) views[0];
        director    = (TextView) views[1];

        image       = (ImageView) views[2];

        rating      = (TextView) views[3];
        release     = (TextView) views[4];
        title    = (TextView) views[5];
    }
}

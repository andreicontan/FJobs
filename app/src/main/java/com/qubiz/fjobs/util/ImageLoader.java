package com.qubiz.fjobs.util;

import android.content.Context;
import android.widget.ImageView;

import com.qubiz.fjobs.R;
import com.squareup.picasso.Picasso;

/**
 * Created by cameliahotico on 8/30/17.
 */

public class ImageLoader {

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.job_image_placeholder)
                .error(R.drawable.job_image_placeholder)
                .into(imageView);
    }
}

package com.kt.mikky.motto.pick.effects.image.processor;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.kt.mikky.motto.pick.effects.R;
import com.kt.mikky.motto.pick.effects.view.ProgressWheel;

public class BitmapImage extends AsyncTask<Uri, Integer, Bitmap> {

	Uri mImageuri;
	Activity mContext;
	ProgressWheel progressWheel;

	public BitmapImage(Context context, Uri imageUri) {
		this.mImageuri = imageUri;
		this.mContext = (Activity) context;
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();		
		progressWheel = (ProgressWheel) mContext.findViewById(R.id.progressBarFour);
		progressWheel.setVisibility(View.VISIBLE);
		progressWheel.spin();
	}

	@Override
	protected Bitmap doInBackground(Uri... params) {

		InputStream imageStream = null;
		try {
			imageStream = mContext.getContentResolver().openInputStream(
					mImageuri);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Bitmap bmp = BitmapFactory.decodeStream(imageStream);
		Bitmap scaled = null;
		if (bmp != null) {
			int nh = (int) (bmp.getHeight() * (512.0 / bmp.getWidth()));
			scaled = Bitmap.createScaledBitmap(bmp, 512, nh, true);
			bmp.recycle();
		}
		
		return scaled;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		progressWheel.setVisibility(View.GONE);
		((ImageView) mContext.findViewById(R.id.image_to_crop))
				.setImageBitmap(result);
		
	}

}

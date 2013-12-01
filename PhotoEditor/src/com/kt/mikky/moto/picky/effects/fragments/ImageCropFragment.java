package com.kt.mikky.moto.picky.effects.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;

import com.kt.mikky.motto.pick.effects.R;
import com.kt.mikky.motto.pick.effects.image.processor.BitmapImage;

public class ImageCropFragment extends SherlockFragment{
	
	Context mContext;
	
	Uri imageUri;
	
	public ImageCropFragment(Uri selectedImage) {
		this.imageUri = selectedImage;
	}	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_main,
		        container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ImageView imageToCrop = (ImageView)view.findViewById(R.id.image_to_crop);
		if (imageUri != null) {
			imageToCrop.setImageBitmap(null);
			BitmapImage loadImage = new BitmapImage(mContext, imageUri);
			Uri[] uri = { imageUri };
			loadImage.execute(uri);

		}
	}

}

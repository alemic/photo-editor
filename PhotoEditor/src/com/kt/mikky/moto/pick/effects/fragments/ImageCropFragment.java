package com.kt.mikky.moto.pick.effects.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kt.mikky.motto.pick.effects.SatelliteMenu;
import com.kt.mikky.motto.pick.effects.SatelliteMenu.SateliteClickedListener;
import com.kt.mikky.motto.pick.effects.SatelliteMenuItem;

import com.actionbarsherlock.app.SherlockFragment;
import com.kt.mikky.motto.pick.effects.R;
import com.kt.mikky.motto.pick.effects.image.processor.BitmapImage;

public class ImageCropFragment extends SherlockFragment implements SateliteClickedListener {
	
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
		SatelliteMenu menu = (SatelliteMenu) view.findViewById(R.id.menu);
		List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_device_access_camera));
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_satellite_effects));
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_satellite_contrast));
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_satellite_brightness));
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_satellite_crop));
		items.add(new SatelliteMenuItem(4, R.drawable.ic_action_satellite_text));

		menu.addItems(items);        
	        
	       
		if (imageUri != null) {
			imageToCrop.setImageBitmap(null);
			BitmapImage loadImage = new BitmapImage(mContext, imageUri);
			Uri[] uri = { imageUri };
			loadImage.execute(uri);

		}
	}

	@Override
	public void eventOccured(int id) {
		
	}

}

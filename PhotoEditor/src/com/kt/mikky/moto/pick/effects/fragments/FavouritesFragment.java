package com.kt.mikky.moto.pick.effects.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kt.mikky.motto.pick.effects.R;

public class FavouritesFragment extends SherlockFragment {

	public static final String TAG = FavouritesFragment.class.getSimpleName()
			.toString();

	Context mContext;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.history_layout, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		TextView tv = (TextView)view.findViewById(R.id.text);
		tv.setText(TAG);
		super.onViewCreated(view, savedInstanceState);
	}

}

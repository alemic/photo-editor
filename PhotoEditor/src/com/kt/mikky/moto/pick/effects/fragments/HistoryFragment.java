package com.kt.mikky.moto.pick.effects.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kt.mikky.moto.pick.effects.adapter.Item;
import com.kt.mikky.moto.pick.effects.adapter.MenuGridAdapter;
import com.kt.mikky.moto.pick.effects.utils.Utils;
import com.kt.mikky.motto.pick.effects.MainActivity;
import com.kt.mikky.motto.pick.effects.R;

public class HistoryFragment extends SherlockFragment {

	public static final String TAG = HistoryFragment.class.getSimpleName()
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

package com.kt.mikky.moto.picky.effects.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kt.mikky.moto.pick.effects.adapter.Item;
import com.kt.mikky.moto.pick.effects.adapter.MenuGridAdapter;
import com.kt.mikky.motto.pick.effects.R;

public class MenuFragment extends SherlockFragment{
	
	Context mContext;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_grid_view,
		        container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		List<Object> griditems = new ArrayList<Object>();
		griditems.add(new Item("Pick From Album", R.drawable.ic_content_new_picture));
		griditems.add(new Item("Take Picture", R.drawable.ic_device_access_camera));
		griditems.add(new Item("History", R.drawable.ic_collections_go_to_today));
		griditems.add(new Item("My Favourite", R.drawable.ic_rating_favorite));
		griditems.add(new Item("Share This App", R.drawable.ic_social_share));
		griditems.add(new Item("About Us", R.drawable.ic_content_about));
		
		GridView menuGrid = (GridView) view.findViewById(R.id.gridview);
		menuGrid.setAdapter(new MenuGridAdapter(mContext, griditems));
		
		super.onViewCreated(view, savedInstanceState);
	}

}

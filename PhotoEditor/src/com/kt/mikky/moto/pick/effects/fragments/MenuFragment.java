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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kt.mikky.moto.pick.effects.adapter.Item;
import com.kt.mikky.moto.pick.effects.adapter.MenuGridAdapter;
import com.kt.mikky.moto.pick.effects.utils.Utils;
import com.kt.mikky.motto.pick.effects.MainActivity;
import com.kt.mikky.motto.pick.effects.R;

public class MenuFragment extends SherlockFragment implements
		OnItemClickListener {

	public static final String TAG = MenuFragment.class.getSimpleName()
			.toString();

	Context mContext;
	List<Object> griditems;
	
	FragmentManager fmanager;
	FragmentTransaction ft;
	
	Uri selectedImage;
	
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
		fmanager = ((MainActivity) mContext).getSupportFragmentManager();
		ft = fmanager.beginTransaction();
		
		if (selectedImage != null) {
			ft.replace(R.id.container_frame, new ImageCropFragment(selectedImage));
			ft.addToBackStack(null);
			ft.commit();
			selectedImage = null;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_grid_view, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		griditems = new ArrayList<Object>();
		Resources resources = mContext.getResources();

		TypedArray arrayImages = resources
				.obtainTypedArray(R.array.menu_grid_images);
		TypedArray arrayNames = resources
				.obtainTypedArray(R.array.menu_grid_names);

		for (int i = 0; i < arrayNames.length(); i++) {

			griditems.add(new Item(arrayNames.getResourceId(i, -1), arrayImages
					.getResourceId(i, -1)));
		}

		GridView menuGrid = (GridView) view.findViewById(R.id.gridview);
		menuGrid.setAdapter(new MenuGridAdapter(mContext, griditems));

		menuGrid.setOnItemClickListener(this);

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long arg3) {

		switch (view.getId()) {
		case R.string.menu_pick_from_album:
			
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("image/*");

			try {
				startActivityForResult(
						Intent.createChooser(intent, "Complete action using"),
						MainActivity.REQUEST_PICK_ALBUM);
			} catch (ActivityNotFoundException e) {
				Log.e("khader", "ActivityNotFoundException");
			}

			break;
		case R.string.menu_take_picture:
			
			if (Utils.isIntentAvailable(mContext, MediaStore.ACTION_IMAGE_CAPTURE)) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, MainActivity.REQUEST_PICK_CAMERA);
			} else {
				Log.d(TAG, "No Feature of Camera by default available");
			}

			break;
		case R.string.menu_history:
			
			
			ft.replace(R.id.container_frame, new HistoryFragment());
			ft.addToBackStack(null);
			ft.commit();

			break;
		case R.string.menu_my_favourite:
			
			ft.replace(R.id.container_frame, new FavouritesFragment());
			ft.addToBackStack(null);
			ft.commit();

			break;
		case R.string.menu_share_this_app:
			
			ft.replace(R.id.container_frame, new ShareAppFragment());
			ft.addToBackStack(null);
			ft.commit();

			break;
		case R.string.menu_about_us:
			
			ft.replace(R.id.container_frame, new AboutFragment());
			ft.addToBackStack(null);
			ft.commit();

			break;
		default:
			Log.d(TAG, "Un Supported With this menu item" + view);
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == MainActivity.REQUEST_PICK_ALBUM || requestCode == MainActivity.REQUEST_PICK_CAMERA)
				&& resultCode == Activity.RESULT_OK) {
			System.out.println("on activity result");
			selectedImage = data.getData();			

		}

		super.onActivityResult(requestCode, resultCode, data);
	}
}

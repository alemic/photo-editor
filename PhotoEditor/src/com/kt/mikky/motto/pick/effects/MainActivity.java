package com.kt.mikky.motto.pick.effects;

import java.util.ArrayList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.kt.mikky.moto.pick.effects.adapter.Category;
import com.kt.mikky.moto.pick.effects.adapter.Item;
import com.kt.mikky.moto.pick.effects.adapter.MenuAdapter;
import com.kt.mikky.moto.pick.effects.fragments.ImageCropFragment;
import com.kt.mikky.moto.pick.effects.fragments.MenuFragment;
import com.kt.mikky.moto.pick.effects.utils.Utils;
import com.kt.mikky.motto.pick.effects.image.processor.BitmapImage;

public class MainActivity extends SherlockFragmentActivity implements
		MenuAdapter.MenuListener {

	public static final String TAG = MainActivity.class.getSimpleName()
			.toString();
	
	public static final String MENU_FRAGMENT_TAG = MenuFragment.class.getSimpleName().toString();

	public static final int REQUEST_PICK_ALBUM = 1;

	public static final int REQUEST_PICK_CAMERA = 2;

	public static final int REQUEST_PICK_SAVE = 3;

	private MenuDrawer mDrawer;

	protected MenuAdapter mAdapter;
	protected ListView mList;

	private int mActivePosition = 0;

	ImageView imageToCrop;
	Uri selectedImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY,
				Position.START, MenuDrawer.MENU_DRAG_CONTENT);
		//mDrawer.setContentView(R.layout.activity_main);
		//imageToCrop = (ImageView) findViewById(R.id.image_to_crop);
		mDrawer.setSlideDrawable(R.drawable.ic_action_ic_drawer);
		mDrawer.setDrawerIndicatorEnabled(true);

		List<Object> items = new ArrayList<Object>();
		items.add(new Item("Brightness", R.drawable.ic_menu_access_brightness_medium));
		items.add(new Item("About", R.drawable.ic_action_about));
		
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listLayout = inflater.inflate(R.layout.menu_list_view, null);

		mList = (ListView) listLayout.findViewById(R.id.listview);

		mList.setBackgroundResource(R.color.md__transparent);
		mList.invalidate();

		mAdapter = new MenuAdapter(this, items);
		mAdapter.setListener(this);
		mAdapter.setActivePosition(mActivePosition);

		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(mItemClickListener);

		mDrawer.setMenuView(mList);
		
//		List<Object> griditems = new ArrayList<Object>();
//		griditems.add(new Item("Pick From Album", R.drawable.ic_content_new_picture));
//		griditems.add(new Item("Take Picture", R.drawable.ic_device_access_camera));
//		griditems.add(new Item("History", R.drawable.ic_collections_go_to_today));
//		griditems.add(new Item("My Favourite", R.drawable.ic_rating_favorite));
//		griditems.add(new Item("Share This App", R.drawable.ic_social_share));
//		griditems.add(new Item("About Us", R.drawable.ic_content_about));
//
//		GridView menuGrid = (GridView)
//		inflater.inflate(R.layout.menu_grid_view, null);
//		menuGrid.setAdapter(new MenuGridAdapter(this, griditems));
		mDrawer.setContentView(R.layout.frame_container_layout);

	}

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mActivePosition = position;
			mDrawer.setActiveView(view, position);
			mAdapter.setActivePosition(position);
			// onMenuItemClicked(position, (Item) mAdapter.getItem(position));
		}
	};

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawer.toggleMenu();
			break;
		case R.id.action_pick_album:

			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("image/*");

			try {
				startActivityForResult(
						Intent.createChooser(intent, "Complete action using"),
						REQUEST_PICK_ALBUM);
			} catch (ActivityNotFoundException e) {
				Log.e("khader", "ActivityNotFoundException");
			}

			break;

		case R.id.action_pick_save:

			break;

		case R.id.action_pick_camera:
			if (Utils.isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, REQUEST_PICK_CAMERA);
			} else {
				Log.d(TAG, "No Feature of Camera by default available");
			}

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {
		super.onStart();
		FragmentManager fmanager = getSupportFragmentManager();
		FragmentTransaction ft = fmanager.beginTransaction();
		
		if (selectedImage != null) {
			ft.replace(R.id.container_frame, new ImageCropFragment(selectedImage));
			ft.addToBackStack(null);
			ft.commit();
		}else {			
			ft.add(R.id.container_frame, new MenuFragment(),MENU_FRAGMENT_TAG);			
			ft.commit();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == REQUEST_PICK_ALBUM || requestCode == REQUEST_PICK_CAMERA)
				&& resultCode == RESULT_OK) {

			selectedImage = data.getData();			

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onActiveViewChanged(View v) {
		// TODO Auto-generated method stub

	}

}

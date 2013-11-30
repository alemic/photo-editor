package com.kt.mikky.motto.pick.effects.image.processor;

import android.graphics.Bitmap;
import android.net.Uri;

interface ImageProcessor {
	
	Bitmap createScaledBitmap(Uri uri);

}

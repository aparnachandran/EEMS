package com.ultimate.eems.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Fonts {
	
	private static Typeface tf;
	
	public static final int HELVETICA = 1;
	public static final int CHUNKFIVE = 2;
	public static final int COMIC_SANS =3;
	
	
	
	
	public static Typeface getTypeFace(Context mContext, int type) {

		switch(type) {
		
		case HELVETICA:
			tf = Typeface.createFromAsset(mContext.getAssets(), "HelveticaLTStd-Bold.otf");
			break;

		case CHUNKFIVE:
			tf = Typeface.createFromAsset(mContext.getAssets(), "Chunkfive.otf");
			break;
		case COMIC_SANS:
			tf = Typeface.createFromAsset(mContext.getAssets(), "Comic_Sans.ttf");
			break;
		
			
		default:
			tf = Typeface.DEFAULT;
			break;
		}

		return tf;
	}
}
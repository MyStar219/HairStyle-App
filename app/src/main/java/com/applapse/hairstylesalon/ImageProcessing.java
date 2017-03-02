package com.applapse.hairstylesalon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class ImageProcessing {


	public static Drawable rotate(Bitmap currentBitmap, float scaleX,
			float scaleY, float rotate) {

		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleY);
		matrix.postRotate(rotate);
		Bitmap resizedBitmap = Bitmap.createBitmap(currentBitmap, 0, 0,
				currentBitmap.getWidth(), currentBitmap.getHeight(), matrix,
				true);
		return new BitmapDrawable(resizedBitmap);
	}

	public static Bitmap contrast(Bitmap bmp, float value) {
		
		
		 ColorMatrix cm = new ColorMatrix(new float[]
		            {
				 		value, 0, 0, 0, 0,
		                0, value, 0, 0, 0,
		                0, 0, value, 0, 0,
		                0, 0, 0, 1, 0
		            });

		    Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

		    Canvas canvas = new Canvas(ret);

		    Paint paint = new Paint();
		    paint.setColorFilter(new ColorMatrixColorFilter(cm));
		    canvas.drawBitmap(bmp, 0, 0, paint);

		    return ret;
//		// image size
//		int width = src.getWidth();
//		int height = src.getHeight();
//		// create output bitmap
//
//		// create a mutable empty bitmap
//		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
//
//		// color information
//		int A, R, G, B;
//		int pixel;
//		// get contrast value
//		double contrast = Math.pow((100 + value) / 100, 2);
//
//		// scan through all pixels
//		for (int x = 0; x < width; ++x) {
//			for (int y = 0; y < height; ++y) {
//				// get pixel color
//				pixel = src.getPixel(x, y);
//				A = Color.alpha(pixel);
//				// apply filter contrast for every channel R, G, B
//				R = Color.red(pixel);
//				R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//				if (R < 0) {
//					R = 0;
//				} else if (R > 255) {
//					R = 255;
//				}
//
//				G = Color.green(pixel);
//				G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//				if (G < 0) {
//					G = 0;
//				} else if (G > 255) {
//					G = 255;
//				}
//
//				B = Color.blue(pixel);
//				B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//				if (B < 0) {
//					B = 0;
//				} else if (B > 255) {
//					B = 255;
//				}
//
//				// set new pixel color to output bitmap
//				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
//			}
//		}
//		return bmOut;
	}

	public static Bitmap brightness(Bitmap bmp, float brightness) {
		
		float contrast = 1;
		 ColorMatrix cm = new ColorMatrix(new float[]
		            {
		                contrast, 0, 0, 0, brightness,
		                0, contrast, 0, 0, brightness,
		                0, 0, contrast, 0, brightness,
		                0, 0, 0, 1, 0
		            });

		    Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

		    Canvas canvas = new Canvas(ret);

		    Paint paint = new Paint();
		    paint.setColorFilter(new ColorMatrixColorFilter(cm));
		    canvas.drawBitmap(bmp, 0, 0, paint);

		    return ret;
		
//		Bitmap dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
//				src.getConfig());
//
//		int width = src.getWidth();
//		int height = src.getHeight();
//
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				int pixelColor = src.getPixel(x, y);
//				int pixelAlpha = Color.alpha(pixelColor);
//
//				int pixelRed = Color.red(pixelColor) + brightnessValue;
//				int pixelGreen = Color.green(pixelColor) + brightnessValue;
//				int pixelBlue = Color.blue(pixelColor) + brightnessValue;
//
//				if (pixelRed > 255) {
//					pixelRed = 255;
//				} else if (pixelRed < 0) {
//					pixelRed = 0;
//				}
//
//				if (pixelGreen > 255) {
//					pixelGreen = 255;
//				} else if (pixelGreen < 0) {
//					pixelGreen = 0;
//				}
//
//				if (pixelBlue > 255) {
//					pixelBlue = 255;
//				} else if (pixelBlue < 0) {
//					pixelBlue = 0;
//				}
//
//				int newPixel = Color.argb(pixelAlpha, pixelRed, pixelGreen,
//						pixelBlue);
//
//				dest.setPixel(x, y, newPixel);
//
//			}
//		}
//		// return final image
//		return dest;
	}
	
	
	private  static Canvas canvas;
	private  static  Paint paint = new Paint();
	

	public static Bitmap rgbChange(Bitmap baseBitmap , float red, float green, float blue) {
		
		paint.setAntiAlias(true);
		Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
				baseBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		
		canvas = new Canvas(afterBitmap);
		
	    float[] srcMatrix = new float[]{
	    		red, 0, 0, 0, 0, 
                 0, green, 0, 0, 0,
                 0, 0, blue, 0, 0, 
                 0, 0, 0, 1, 0};
		// The definition of ColorMatrix, and specify the RGBA matrix
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.set(srcMatrix);
		// Set the Paint color
		paint.setColorFilter(new ColorMatrixColorFilter(srcMatrix));
		// By specifying the RGBA matrix Paint of the original picture of the
		// blank picture
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);
		return afterBitmap;

//		int width = src.getWidth();
//		int height = src.getHeight();
//
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				int pixelColor = src.getPixel(x, y);
//				int pixelAlpha = Color.alpha(pixelColor);
//
//				int pixelRed = Color.red(pixelColor) + red;
//				int pixelGreen = Color.green(pixelColor) + green;
//				int pixelBlue = Color.blue(pixelColor) + blue;
//
//				if (pixelRed > 255) {
//					pixelRed = 255;
//				} else if (pixelRed < 0) {
//					pixelRed = 0;
//				}
//
//				if (pixelGreen > 255) {
//					pixelGreen = 255;
//				} else if (pixelGreen < 0) {
//					pixelGreen = 0;
//				}
//
//				if (pixelBlue > 255) {
//					pixelBlue = 255;
//				} else if (pixelBlue < 0) {
//					pixelBlue = 0;
//				}
//
//				int newPixel = Color.argb(pixelAlpha, pixelRed, pixelGreen,
//						pixelBlue);
//
//				dest.setPixel(x, y, newPixel);
//
//			}
//		}
//		return dest;
	}
	
	

}

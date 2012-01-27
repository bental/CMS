package com.test.firstAnimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Sprite extends View{


	 private Bitmap img; // the image of Greg
	 private Bitmap imgNH;
	 private Bitmap img2; // the image of ball 
	 private Bitmap img3; // head
	 private Bitmap imgBlood; //blood
	 private static int gregCoordX = 410; // the x coordinate at the canvas for greg
	 private static int gregCoordY = MyAnimationView.gregYHeight; // the y coordinate at the canvas for greg
	 private static int pointCoordX = 10;
	 private static int pointCoordY = 3;
	 private static int gregSpeed = 2;
	 public static int ballSpeed = 25;
	 private static boolean goingRight = false;
	 private static boolean goingLeft = true;
	 public static boolean pointerGoingRight = false;
	 public static boolean pointerGoingLeft = true;	
	// private static int droppedTimes = 1;
	
	
	public Sprite(Context context, int drawable) {

		super(context);
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.greg);
		img2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
		img3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.greghead);
		imgNH = BitmapFactory.decodeResource(context.getResources(), R.drawable.gregnohead);
		imgBlood = BitmapFactory.decodeResource(context.getResources(), R.drawable.blood_spatter);
		}
	
	public static int getCount() {
		return gregSpeed;
	}
	
	void setX(int newValue) {
        gregCoordX = newValue;
    }
	
	public static int getX() {
		return gregCoordX;
	}
	
	public static int getY() {
		return gregCoordY;
	}
	
	public static int getBX() {
		return pointCoordX;
	}
	
	public static int getBY() {
		return pointCoordY;
	}
	
	public static void setBX(int x) {
		pointCoordX = x;
	}
	
	public static void setBY(int y) {
	    pointCoordY = y;
	}
		
	public Bitmap getBitmapGreg() {
		return img;
	}
	
	public Bitmap getBitmapBall() {
		return img2;
	}
	
	public Bitmap getBitmapHead() {
		return img3;
	}

	public Bitmap getBitmapNoHead() {
		return imgNH;
	}
	
	public Bitmap getBitmapBlood() {
		return imgBlood;
	}
	
	public static void dropBall()
	{
		moveBall(pointCoordX);
		lowerBall(pointCoordY);
    }
	
	private static void lowerBall(int pointY) 
	{
		pointCoordY = pointY + 5; 
	}

	public static void moveBall(int x) {
		 
	       // check the borders
			//if more than ten go left
			//if ten or less go right
			//if more than 250 go left
			if (x <= 0 && pointerGoingLeft)
			{
			pointCoordX = pointCoordX + ballSpeed;
			pointerGoingRight = true;
			pointerGoingLeft = false;
			}
			else if (x >= (MyAnimationView.screensWidth - MyAnimationView.ballWidth) && pointerGoingRight)
			{
				pointCoordX = pointCoordX - ballSpeed;
				pointerGoingLeft = true;
				pointerGoingRight = false;
			}
			else if (pointerGoingRight)
				pointCoordX = pointCoordX + ballSpeed;
			else
				pointCoordX = pointCoordX - ballSpeed;
	}
	
	public static void moveGreg(int x) {
		 
		if (x <= 0 && goingLeft)
		{
        gregCoordX = gregCoordX + gregSpeed;
		goingRight = true;
		goingLeft = false;
		}
		else if (x >= (MyAnimationView.screensWidth - MyAnimationView.imageW) && goingRight)
		{
	    gregCoordX = gregCoordX - gregSpeed;
		goingLeft = true;
		goingRight = false;
		}
		else if (goingRight)
		gregCoordX = gregCoordX + gregSpeed;
		else
	    gregCoordX = gregCoordX - gregSpeed;
}
}

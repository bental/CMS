package com.test.firstAnimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MyAnimationView extends View{

	private Sprite greg;
	private Sprite ball;
	private Sprite head;
	private Sprite blood;
	private int gregXCoOr;
	private int gregYCoOr;
	private int ballXCoOr;
	private int ballYCoOr;
	float touched_x, touched_y;
	public static boolean ballDropped;
	static boolean touched;
	static boolean startAnimation;
	static int imageW;
	int imageH;
	int ballHeight;
	static int ballWidth;
	static int screensHeight;
	static int screensWidth;
	static int gregYHeight;
	boolean headDropping;
	boolean doneAlready;
	int headFallen;
	String headDirection;
	int score;
	
	public MyAnimationView(Context context) {
		super(context);
       
//		Menu men = new Menu();//this may not work
//		
//		men.requestWindowFeature(Window.FEATURE_NO_TITLE);
//	        men.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
//	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//	        
		ballDropped = false;
		startAnimation = false;
		touched = false;
		headDropping = false;
		doneAlready = false;
		score = 0;
		greg = new Sprite(context,R.drawable.greg);
		ball = new Sprite(context, R.drawable.ball);
		imageW = greg.getBitmapGreg().getWidth();
		imageH = greg.getBitmapGreg().getHeight();
		ballHeight = ball.getBitmapBall().getHeight();
		ballWidth = ball.getBitmapBall().getWidth();
		gregYHeight = screensHeight - imageH;
	}	
	
	@Override 
	protected void onDraw(Canvas canvas) {

		 canvas.drawColor(0xFFFFFFFF);    								 //white background  	 
		 
		// TextView t = new TextView(getContext());
		 //t.setText(score);
		 
		 setScore(canvas);
		 getCoordinates();
	     Sprite.moveGreg(gregXCoOr); 										 //move ball left or right depending
		
		if (!startAnimation) 
		{
		    Sprite.moveBall(ballXCoOr);
		    didUserTouchGreg(isOverGreg());
		} 
		else
		{			
			if (ballIsInView()) //if ball is in view
			{
				Sprite.dropBall();
				if(!headDropping)
				{
					checkForCollision();
				}
			}
			else if (headFallen > 8 || headFallen == 0)
				resetAll(10, 3);
		}
		 moveHeadIfRequired(canvas);
		 invalidate();
		}

	private void setScore(Canvas canvas) {
		Paint p = new Paint();
		 p.setColor(Color.RED);
		 p.setTextSize(25);
		 Typeface tf = Typeface.create("Ariel", 1);
		 p.setTypeface(tf);
		 canvas.drawText(String.valueOf(score), 1, 250, p);
	}

	private void getCoordinates() {
		 ballXCoOr = Sprite.getBX();  
		 ballYCoOr = Sprite.getBY();
		 gregXCoOr = Sprite.getX();
	     gregYCoOr = gregYHeight - 30;
	}

	private void moveHeadIfRequired(Canvas canvas) {
		if(headDropping)
		{		
		setHeadDirection(canvas);
		}
		else
		{
		 canvas.drawBitmap(greg.getBitmapGreg(), gregXCoOr, gregYCoOr, null);
		 canvas.drawBitmap(ball.getBitmapBall(), ballXCoOr, ballYCoOr, null);
		}
	}

	private void setHeadDirection(Canvas canvas) {
		int x, y;
		
			if(headDirection == "left")
			{
				x =  gregXCoOr-(headFallen*2);
				y =  gregYCoOr-(headFallen*2);
			}
			else
			{
				x =  gregXCoOr+(headFallen*2);
				y =  gregYCoOr-(headFallen*2);
			}

		 canvas.drawBitmap(blood.getBitmapBlood(), gregXCoOr-28, gregYCoOr-25, null);
		 canvas.drawBitmap(head.getBitmapHead(), x, y, null);
		 canvas.drawBitmap(greg.getBitmapNoHead(), gregXCoOr, gregYCoOr, null);
		 headFallen++;
	}

	private void didUserTouchGreg(boolean overGreg) {
		if (touched && overGreg) 
			startAnimation = true;		
		else
			touched = false;
	}

	private boolean ballIsInView() {
		return ballYCoOr + ballHeight < screensHeight;
	}

	private void checkForCollision() {
		if (collision())
		{
			score++;
			dropHead();
			setBlood();
			setHeadDirection(Sprite.pointerGoingLeft);
		}
	}
	
		private void setBlood() 
	    {
			blood = new Sprite(getContext(),R.drawable.blood_spatter);
	    }
	    	
		private void setHeadDirection(boolean pointerGoingLeft) 
	    {
		if (pointerGoingLeft)
			headDirection = "left";
		else
			headDirection = "right";		
	    }

		private void resetAll(int x, int y) 
	    {
		startAnimation = false;
		touched = false;
		headDropping = false;
		doneAlready = false;
		greg = new Sprite(getContext(),R.drawable.greg);	
		headFallen = 0;

		Sprite.setBX(x);
		Sprite.setBY(y);
	    }
	    
		private void dropHead() 
		{
			headDropping = true;
			headFallen = 1;
	    	greg = new Sprite(getContext(),R.drawable.gregnohead);	    	
		}

		private boolean collision() 
		{
			head = new Sprite(getContext(),R.drawable.greghead);
			int headHeight = head.getBitmapHead().getHeight();
			int bottomOfHead = gregYCoOr + headHeight;
			int bottomOfBall = ballYCoOr + ballHeight;
			int rightOfGreg = gregXCoOr + imageW;
			int rightOfBall = ballXCoOr + ballWidth;
			boolean x = false;
			boolean y = false;
			
			//      ballycoor is top of ball
			if (bottomOfBall >= gregYCoOr && bottomOfBall <= bottomOfHead)
				y = true;
			else if (ballYCoOr >= gregYCoOr && ballYCoOr <= bottomOfHead)
				y = true;
			//        ballxcoor is left of ball 
			if (ballXCoOr <= rightOfGreg && (ballXCoOr >= gregXCoOr))
				x = true;
			else if (rightOfBall <= rightOfGreg && (rightOfBall >= gregXCoOr))
				x = true;
			
		return (x && y);
		}

		private boolean isOverGreg() 
		{
	    	
	    	if (!touched)
	    			return false;
	    	boolean inW = (touched_x >  gregXCoOr && touched_x <  (gregXCoOr + imageW));
	    	boolean inY = (touched_y >  gregYCoOr && touched_y <  (gregYCoOr + imageH));	    		
	    	
		return inW && inY;
		}

		@Override
	    public boolean onTouchEvent(MotionEvent event) 
		{			
	        touched_x = event.getX();
	        touched_y = event.getY();
	        int action = event.getAction();
	        
	        if (action == MotionEvent.ACTION_DOWN)
	        {
	        	touched = true;
	        }
	        return true; // processed
	    }
}

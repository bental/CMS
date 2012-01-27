package com.test.firstAnimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Menu extends Activity{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);
        
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
//                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.greg_front), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.greg_front2), 500);
        animation.setOneShot(false);

        ImageView imageAnim =  (ImageView) findViewById(R.id.imageView1);
        imageAnim.setBackgroundDrawable(animation);
        
        Button plyGame = (Button) findViewById(R.id.button1);
        plyGame.setOnClickListener((OnClickListener) this);
        
        Button highScore = (Button) findViewById(R.id.button2);
        highScore.setOnClickListener((OnClickListener) this);
        
        // start the animation!
        animation.start();
    }
    
    public void startGame()
    {
        getScreenResolution();     
        setContentView(new MyAnimationView(this)); 
    }
    
    public void highScores()
    {
       // getScreenResolution();      if needed
        setContentView(new highScores(this)); 
    }
    
    private void getScreenResolution() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        MyAnimationView.screensHeight = displaymetrics.heightPixels;
        MyAnimationView.screensWidth = displaymetrics.widthPixels;   	
	}
//	@Override
//	public void onPause()
//	{
//		
//	}
	
//	@Override
//	public void onResume()
//	{
//		//getScreenResolution();
//		Sprite.setBX(10);
//		Sprite.setBY(10);
//		MyAnimationView.ballDropped = false;
//		MyAnimationView.stopAnimation = false;
//		MyAnimationView.touched = false;
//
//		setContentView(new MyAnimationView(this));
//	}
}

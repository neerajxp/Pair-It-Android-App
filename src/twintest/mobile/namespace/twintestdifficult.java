package twintest.mobile.namespace;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

import twintest.mobile.namespace.twintest6.MyCounter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class twintestdifficult extends Activity {
	
	GameMainClass gm ;
	Dialog dialog ;
	Button btname;
	Button btnclicks;
	Button btnshowtimers;
	public MyCounter timer;
	AudioManager am ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.twintestdifficult);
	   
	   gm = new GameMainClass();
	   gm.QUIZNUMBERS=999;
	   //Generat a unique backgound which id differenct from previous wallpaer
	   TwinTestActivity.prevDrawable=TwinTestActivity.currDrawable;        
	   TwinTestActivity.currDrawable= TwinTestActivity.randraw.nextInt(8);
	   while  (TwinTestActivity.currDrawable.toString().equals(TwinTestActivity.prevDrawable) || TwinTestActivity.currDrawable==0)
	   {
	   	TwinTestActivity.currDrawable= TwinTestActivity.randraw.nextInt(8);        	
	   }        
	   if(!gm.CreateGame(this, twintestdifficult.this)==true)
	   {
	   	finish();
	   }
	   gm.noofclicks=0;
	   gm.starttime = System.currentTimeMillis();   	
	   
	   try
	   {
	     Resources res = this.getResources(); 		 
	     gm.SoundResouceId = res.getIdentifier("coinsound", "raw", this.getPackageName());
	     gm.afd = this.getApplicationContext().getResources().openRawResourceFd(gm.SoundResouceId);
	     gm.mp = new MediaPlayer();
	     gm.mpnames = new MediaPlayer();
	     
	     am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	     int soundresid;     
	     gm.soundlist.clear();
	     for(int i=0; i< TwinTestActivity.VegetablesArrayList.size();i++)
	     {
	    	 soundresid= res.getIdentifier(TwinTestActivity.VegetablesArrayList.get(i), "raw", this.getPackageName());
	    	 gm.soundlist.put(TwinTestActivity.VegetablesArrayList.get(i), soundresid);    	      
	     }
	     for(int i=0; i< TwinTestActivity.AnimalsArrayList.size();i++)
	     {
	    	 soundresid= res.getIdentifier(TwinTestActivity.AnimalsArrayList.get(i), "raw", this.getPackageName());
	    	 gm.soundlist.put(TwinTestActivity.AnimalsArrayList.get(i), soundresid);    	      
	     }
	     
	     //gm.volumelevel=am.getStreamVolume(AudioManager.STREAM_RING);
	     gm.volumelevel=am.getStreamVolume(AudioManager.STREAM_MUSIC);
	     gm.mp.setVolume(gm.volumelevel,gm.volumelevel);     
	     gm.mpnames.setVolume(gm.volumelevel,gm.volumelevel);
	   }
	   catch(Exception ex)
	   {
		   String s="error";
	   }
	   btname=(Button)findViewById(R.id.btnnames);
	    btnclicks=(Button)findViewById(R.id.btnclicks);
	    btnshowtimers=(Button)findViewById(R.id.btnshowtimer);
	    btnclicks.setVisibility(View.INVISIBLE);
	    btnshowtimers.setVisibility(View.INVISIBLE);
	    
	    if(TwinTestActivity.TIMERON.equalsIgnoreCase("ON"))
		{
	    	if(timer!=null)
	    	{
	    		timer.cancel();
	    	}    	
	    	timer=new MyCounter(gm.timerdifficult,1000);
	    	timer.start();	    		    	
	    	gm.timerticks=0;
	    	btnshowtimers.setVisibility(View.VISIBLE);
		}	
	    else
	    {
	    	btnclicks.setVisibility(View.VISIBLE);
	    	if(timer!=null)
	    	{
	    		timer.cancel();
	    	}
	    }
	}

public  class MyCounter extends CountDownTimer
{
	public long currtick;
	public MyCounter(long millisInFuture, long CountDownInteral)
	{
		super(millisInFuture, CountDownInteral);
	}
	@Override
	public void onFinish()
	{
		 // TextView txtcounter= (TextView)findViewById(R.id.txtcounter);
    	  //txtcounter.setText("finish");
		btnshowtimers.setText("0");
		timer.cancel();
		gm.endtime=System.currentTimeMillis();
	   	long totaltime =(gm.endtime - gm.starttime) / 1000;
	   	totaltime=totaltime-1;
	    gm.minutes=totaltime/60;
	    gm.seconds=totaltime%60;
	    
		if(!gm.gameover.equalsIgnoreCase("over"))
		{				
			results.gmresullt=gm;			
			Intent ent= new Intent(getApplicationContext(), results.class);
	    	startActivity(ent);	    	
		}
	}
	@Override
	public void onTick(long millisuntilfinished)
	{
		//TextView txtcounter= (TextView)findViewById(R.id.txtcounter);
		//txtcounter.setText("timer " +(millisuntilfinished/1000));			
			currtick=(millisuntilfinished/1000);				
			//btnclicks.setText(String.valueOf(currtick));
			btnshowtimers.setText(String.valueOf(currtick));
			gm.timerticks=(int) currtick;
	}
}

	public void ButtonClick(View view)
	{
		
		Button b1;    	
		
	   String currBtn;	    
	   int btnid= view.getId();	    	    
	   	try
	   	{	    				    		
		    	b1=(Button) findViewById(btnid);		    	
	   		     		    	
		    	if(TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Numbers"))
		    	{
		    		b1.setBackgroundResource(R.drawable.quiztextborder);
		    		b1.setText(b1.getTag().toString());
		    	}
		    	else if(TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Alphabets"))
		    	{		    		
		    		b1.setBackgroundResource(R.drawable.quiztextborder);
		    		b1.setText(b1.getTag().toString());		    		
		    	}		    	
		    	else if(TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Vegetables"))
		    	{		    		
		    		 Resources res = this.getResources(); 
		    		 String vegetable=b1.getTag().toString();
		    		 int resID = res.getIdentifier(vegetable, "drawable", this.getPackageName());		    		 
		    		 b1.setBackgroundResource(resID);		    		
		    	}
		    	else if(TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Animals"))
		    	{		    		
		    		 Resources res = this.getResources(); 
		    		 String animals=b1.getTag().toString();
		    		 int resID = res.getIdentifier(animals, "drawable", this.getPackageName());		    		 
		    		 b1.setBackgroundResource(resID);		    		
		    	}
		    			    			    		    
		    	if(gm.UserClicks.contains(btnid))
		    	{
		    		return;
		    	}
		    	gm.noofclicks=gm.noofclicks+1;//keep counting button clicks
		    	
//		    	show names on top	  		
		  		String tempdata=b1.getTag().toString();
		  		tempdata= tempdata.substring(0, 1).toUpperCase() +tempdata.substring(1, tempdata.length()).toLowerCase();
		  		tempdata=tempdata.replace("_", " ");	
		  		
		  		if(TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Numbers") || TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Alphabets"))
		    	{	
		  			btname.setText("");		  				  			
		    	}
		  		else
		  		{
		  			btname.setText(tempdata);
			  		btname.setTextColor(Color.parseColor("#ED1C24"));
		  		}		  				  		
		  		btnclicks.setText(String.valueOf(gm.noofclicks));   
		  		
		    	if(gm.UserClicks.size()==1)
		    	{
		    		//add selection in list
		    		//Check Answer
		    		Boolean res;
		    		gm.UserClicks.add(btnid);
		    		gm.CheckAnswer(this, twintestdifficult.this);
		    		
		    		if(gm.gameover.equalsIgnoreCase("over"))
		    		{
		    			//ShowDialog();
		    			if(TwinTestActivity.TIMERON.equalsIgnoreCase("ON"))
		    			{	    		    	
		    		    	if(timer!=null)
		    		    	{
		    		    		timer.cancel();
		    		    	}
		    			}	   
		    			results.gmresullt=gm;		    				
		    			Intent ent= new Intent(this, results.class);
			        	startActivity(ent);  	    	
		    		}
		    		if(gm.CorrectAnswerShowname.equals("correct"))
		    		{
		    			
		    		     //	show names on top	    	  		
		    	  		btname.setTextColor(Color.parseColor("#099335"));
		    		}
		    	}
		    	else
		    	{		 
		    		gm.CorrectAnswerShowname="incorrect";
		    		//Record Selection
		    		gm.UserClicks.add(btnid);		    		
		    	}
		    		
	   	}
	   	catch(Exception ex)
	   	{
	   		System.out.println(ex.toString());
	   	}  	
	}
 		
	public void RePlay(View view)
	{
		Intent ent= new Intent(this, twintestdifficult.class);
		startActivity(ent);		
	}
	@Override    
	public void onBackPressed() {
		if(timer!=null)
		{
			timer.cancel();
		}
		
		Intent ent= new Intent(this, TwinTestActivity.class);
		startActivity(ent);		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    switch (keyCode) {
	    case KeyEvent.KEYCODE_VOLUME_UP:
	        am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
	        //Raise the Volume Bar on the Screen
	        //volumeControl.setProgress( am.getStreamVolume(AudioManager.STREAM_MUSIC)+ AudioManager.ADJUST_RAISE);
	        return true;
	    case KeyEvent.KEYCODE_VOLUME_DOWN:
	        //Adjust the Volume
	        am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
	        //Lower the VOlume Bar on the Screen
	        //volumeControl.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC)+ AudioManager.ADJUST_LOWER);
	        return true;
	    case KeyEvent.KEYCODE_BACK:
	    	if(timer!=null)
			{
				timer.cancel();
			}
			
			Intent ent= new Intent(this, TwinTestActivity.class);
			startActivity(ent);	
	    	 return true;
	    default:
	        return false;
	    }
	}

	
	}

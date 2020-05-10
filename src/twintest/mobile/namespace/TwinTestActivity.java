package twintest.mobile.namespace;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
	
public class TwinTestActivity extends Activity {
	public static Integer QUIZROWS;
	public static Integer QUIZCOLS;
	public static Random randraw= new Random();
	public static Integer prevDrawable=1;
	public static Integer currDrawable=1;
	public static String PAIRTYPE="Numbers";
	public static String SOUNDON="ON";
	public static String LEVEL="";
	public static ArrayList<String> VegetablesArrayList = new ArrayList<String> ();
	public static ArrayList<String> AnimalsArrayList = new ArrayList<String> ();	
	public static ArrayList<String> AlphabetsArrayList = new ArrayList<String> ();
	public static String LEVEL_ACTIVE_BUTTON="";
	public static String ACTIVE_SOUND="";
	public static String TIMERON="OFF";
	public static String ACTIVE_TIMERON="";
	int soundresidresult;
	AssetFileDescriptor afd;
	AudioManager am;
	MediaPlayer mp;	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String> adpter;
        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        Resources res = this.getResources(); 
        mp= new MediaPlayer();
        soundresidresult= res.getIdentifier("click2", "raw", this.getPackageName());
    	afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
    	
        String[] mTestArray;
        mTestArray=getResources().getStringArray(R.array.vegetables);         
        VegetablesArrayList.clear();
        for(int i=0;i<mTestArray.length;i++ ){
        	VegetablesArrayList.add(mTestArray[i]);
        }
        mTestArray=null;
        mTestArray=getResources().getStringArray(R.array.animals);         
        AnimalsArrayList.clear();
        for(int i=0;i<mTestArray.length;i++ ){
        	AnimalsArrayList.add(mTestArray[i]);
        }
        AlphabetsArrayList.clear();
        String tem; 
        for(int i=65;i<91;i++)
        {
        	tem= Character.toString((char)i);
        	AlphabetsArrayList.add(tem);
        }
        
        //Check Previous selection
        
        Button btnsound= (Button)findViewById(R.id.btnsound);
        btnsound.setBackgroundResource(R.drawable.soundon);
        btnsound.setTag("soundon");
        SOUNDON="ON";  	    	  
        
        if(ACTIVE_SOUND.equals("soundon") ||ACTIVE_SOUND.equals(""))
		{    	
        	 SOUNDON="ON";  	    
        	 btnsound.setBackgroundResource(R.drawable.soundon);
		}        
        else
		{    	
        	SOUNDON="OFF";  	    
        	btnsound.setBackgroundResource(R.drawable.soundoff);
		}	
        
        //check timer staus
        Button btntimer= (Button)findViewById(R.id.btntimer);
        btntimer.setBackgroundResource(R.drawable.timeron);
        btntimer.setTag("timeroff");
        TIMERON="OFF";  	    	  
        if(ACTIVE_TIMERON.equals("timeroff") || ACTIVE_TIMERON.equals(""))
		{    	
        	 TIMERON="OFF";  
        	btntimer.setBackgroundResource(R.drawable.timeroff);
		}	
        
        /*
    	CheckBox soundon= (CheckBox )findViewById(R.id.soundon);
    	soundon.setChecked(true);
    	
    	if(TwinTestActivity.SOUNDON.equalsIgnoreCase("OFF"))
    	{    		
    		soundon.setChecked(false);    		
    	}*/
   		    		
    	Button btneasy= (Button)findViewById(R.id.btneasy );
    	Button btnmedium= (Button)findViewById(R.id.btnmedium );
    	Button btndifficult= (Button)findViewById(R.id.btndifficult );
    	
    	btneasy.setBackgroundResource(R.drawable.easyon);
    	btneasy.setTag("easyon");
    	btnmedium.setBackgroundResource(R.drawable.mediumoff);
    	btnmedium.setTag("mediumoff");
    	btndifficult.setBackgroundResource(R.drawable.difficultoff);
    	btndifficult.setTag("difficultoff"); 
    	
    	if(LEVEL_ACTIVE_BUTTON.equals("Easy"))
		{    	
    		btneasy.setBackgroundResource(R.drawable.easyon);
    		btnmedium.setBackgroundResource(R.drawable.mediumoff);
    		btndifficult.setBackgroundResource(R.drawable.difficultoff);
		}	
    	else if(LEVEL_ACTIVE_BUTTON.equals("Medium"))
		{    	
    		btneasy.setBackgroundResource(R.drawable.easyoff );
    		btnmedium.setBackgroundResource(R.drawable.mediumon);
    		btndifficult.setBackgroundResource(R.drawable.difficultoff);

		}
    	else if(LEVEL_ACTIVE_BUTTON.equals("Difficult"))
		{    	
    		btneasy.setBackgroundResource(R.drawable.easyoff );
    		btnmedium.setBackgroundResource(R.drawable.mediumoff);
    		btndifficult.setBackgroundResource(R.drawable.difficulton);
		}
    	    	
    }
    
   
    public void SetVariables()
    {
  
    	  if(ACTIVE_SOUND.equalsIgnoreCase("soundon") || ACTIVE_SOUND.equalsIgnoreCase(""))
          {
        	  SOUNDON="ON";        	  
          }
          else
          {
        	  SOUNDON="OFF";        	  
          }
          
          if(  ACTIVE_TIMERON.equalsIgnoreCase("timeron"))
          {
        	  TIMERON="ON";        	  
          }
          else
          {
        	  TIMERON="OFF";        	  
          }    
          
    	playSound();
    	
    	if(LEVEL_ACTIVE_BUTTON.equals("Easy") || LEVEL_ACTIVE_BUTTON=="")
    			{    				
    				LEVEL="Easy";
    				QUIZROWS=4;
    		    	QUIZCOLS=4;
    		    	Intent ent= new Intent(this, twintest6.class);    	     	
    		    	startActivity(ent);    
    			}
    	else if(LEVEL_ACTIVE_BUTTON.equals("Medium"))
				{    	
    				LEVEL="Medium";
    				QUIZROWS=6;
    	        	QUIZCOLS=4;
    	        	Intent ent= new Intent(this, twintestmedium.class);
    	        	startActivity(ent);    
				}
    	else if(LEVEL_ACTIVE_BUTTON.equals("Difficult"))
				{
    				LEVEL="Difficult";
    				QUIZROWS=7;
    		    	QUIZCOLS=4;
    		    	Intent ent= new Intent(this, twintestdifficult.class);
    		    	startActivity(ent);    
				}    	                             
    }
    
    public void setSound(View view1)
    {
    	Button b1;    	       	   	    
 	   int btnid= view1.getId();	    	    
 	   b1=(Button) findViewById(btnid);
 	   String tag1=b1.getTag().toString() ;
 	    	   	     	  
 	   //if(tag1.equals("soundon"))
 	  if(ACTIVE_SOUND.equals("soundon") || ACTIVE_SOUND.equals(""))
 	   {
	 	 	    Resources res = this.getResources();  	  
	 		   	Button btnsound= (Button)findViewById(R.id.btnsound );
	 		    int resIDeasy = res.getIdentifier("soundoff", "drawable", this.getPackageName());
	 		    btnsound.setTag("soundoff");
	 		   	btnsound.setBackgroundResource(resIDeasy); 	 		   	
	 		    ACTIVE_SOUND="soundoff";	 		   	 
 	   }
 	   else
 	   {
 		   Resources res = this.getResources();  	  
		   	Button btnsound= (Button)findViewById(R.id.btnsound );
		    int resIDeasy = res.getIdentifier("soundon", "drawable", this.getPackageName());
		    btnsound.setTag("soundon");
		   	btnsound.setBackgroundResource(resIDeasy); 	 		   	
		    ACTIVE_SOUND="soundon";		    
 	   } 	  
 	  playSound();
    }
    
    public void setTimer(View view1)
    {
    	Button b1;    	       	   	    
  	   int btnid= view1.getId();	    	    
  	   b1=(Button) findViewById(btnid);
  	   String tag1=b1.getTag().toString() ;
  	    	   	     	  
  	   //if(tag1.equals("timeron"))
  	   if(ACTIVE_TIMERON.equals("timeron"))  		 
  	   {
 	 	 	    Resources res = this.getResources();  	  
 	 		   	Button btntimer= (Button)findViewById(R.id.btntimer );
 	 		    int resIDeasy = res.getIdentifier("timeroff", "drawable", this.getPackageName());
 	 		    btntimer.setTag("timeroff");
 	 		    btntimer.setBackgroundResource(resIDeasy); 	 		   	
 	 		    ACTIVE_TIMERON="timeroff"; 	 		  
  	   }
  	   else
  	   {
  		   Resources res = this.getResources();  	  
 		   Button btntimer= (Button)findViewById(R.id.btntimer );
 		   int resIDeasy = res.getIdentifier("timeron", "drawable", this.getPackageName());
 		   btntimer.setTag("timeron");
 		   btntimer.setBackgroundResource(resIDeasy); 	 		   	
 		   ACTIVE_TIMERON="timeron";
  	   } 	
  		playSound();
    }
    
    
    public void setlevel(View view1)
    {
    		Button b1;    	       	   	    
    	   int btnid= view1.getId();	    	    
    	   b1=(Button) findViewById(btnid);
    	   String tag1=b1.getTag().toString() ;
    	   
    	   String seteasytag="";
    	   String setmediumtag="";
    	   String setdifficulttag="";
    	   
    	   if(tag1.equals("easyoff"))
    	   {
    		    seteasytag="easyon";
    		    LEVEL_ACTIVE_BUTTON="Easy";
        	    setmediumtag="mediumoff";
        	    setdifficulttag="difficultoff";
        	   
    	   }
    	   else if(tag1.equals("mediumoff"))
    	   {
	    		seteasytag="easyoff";
	       	    setmediumtag="mediumon";
	       	    LEVEL_ACTIVE_BUTTON="Medium";
	       	    setdifficulttag="difficultoff";
    	   }
    	   else if(tag1.equals("difficultoff"))
    	   {
    		   	seteasytag="easyoff";
	       	    setmediumtag="mediumoff";
	       	    setdifficulttag="difficulton";
	       	    LEVEL_ACTIVE_BUTTON="Difficult";
    	   }
    	   else if(tag1.equals("easyon"))
    	   {
    		   LEVEL_ACTIVE_BUTTON="Easy";
	   		   	seteasytag="easyon";
	       	    setmediumtag="mediumoff";
	       	    setdifficulttag="difficultoff";
    	   }
    	   else if(tag1.equals("mediumon"))
    	   {
    		   	seteasytag="easyoff";
	       	    setmediumtag="mediumon";
	       	    setdifficulttag="difficultoff";
	       	   LEVEL_ACTIVE_BUTTON="Medium";
    	   }
    	   else if(tag1.equals("difficulton"))
    	   {
	   		   	seteasytag="easyoff";
	       	    setmediumtag="mediumoff";
	       	    setdifficulttag="difficulton";
	       	    LEVEL_ACTIVE_BUTTON="Difficult";
    	   }
    	   
    	   ////set images
   		
    	     Resources res = this.getResources(); 
	  		 int resIDeasy = res.getIdentifier(seteasytag, "drawable", this.getPackageName());
	  		int resIDmedium = res.getIdentifier(setmediumtag, "drawable", this.getPackageName());
	  		int resIDdifficult = res.getIdentifier(setdifficulttag, "drawable", this.getPackageName());
	  		 	  		 
	  		 //b1.setBackgroundResource(resID);		    	
	  		 
		   	Button btneasy= (Button)findViewById(R.id.btneasy );
		   	Button btnmedium= (Button)findViewById(R.id.btnmedium );
		   	Button btndifficult= (Button)findViewById(R.id.btndifficult );
		   	
		   	btneasy.setTag(seteasytag);
		   	btnmedium.setTag(setmediumtag);
		   	btndifficult.setTag(setdifficulttag);
		   	
    		btneasy.setBackgroundResource(resIDeasy);        	
        	btnmedium.setBackgroundResource(resIDmedium);        	
        	btndifficult.setBackgroundResource(resIDdifficult);  
        	
        	playSound();
    }
    
    public void startNumbers(View view1)
    {
    	SetVariables();
    	PAIRTYPE="Numbers";  	    	
    }
    public void startVegetables(View view1)
    {    
    	PAIRTYPE="Vegetables";
    	SetVariables();
    
    }
    public void startAlphabets(View view1)
    {    	   
    	PAIRTYPE="Alphabets";
    	SetVariables();    	
    }
    public void startAnimals(View view1)
    {
    	PAIRTYPE="Animals";
    	SetVariables();    	
    }
    
    public void aboutme(View view1)
    {
    	Intent ent= new Intent(this, about.class);
    	startActivity(ent);    
    }
    public void startHelp(View view1)
    {
    	Intent ent= new Intent(this, help.class);
    	startActivity(ent);    
    }
    public void startRateit(View view1)
    {    	
    	playSound();
    	Intent ent= new Intent(Intent.ACTION_VIEW);
    	//ent.setData(Uri.parse("market://details?id="  + this.getPackageName()));
    	ent.setData(Uri.parse("https://play.google.com/store/apps/details?id="  + this.getPackageName()));
    	startActivity(ent);    
    }
    public void playSound()
    {
    	if(ACTIVE_SOUND.equalsIgnoreCase("soundon") || ACTIVE_SOUND.equalsIgnoreCase(""))    	
    	{	    		    		    	    	 		    	
	    	try
	    	{	    		 
	    		int volumelevel=am.getStreamVolume(AudioManager.STREAM_MUSIC);	    	     	    	         	    	     	    	    
		    	mp.reset();
		    	mp.setVolume(volumelevel,volumelevel);
		    	mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
		    	//mp.setAudioStreamType(AudioManager.STREAM_RING);
		    	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		    	mp.prepare();				 				 			 
		    	mp.start();
	    	}
	    	catch(Exception ex)
	    	{
	    		String s=ex.toString();
	    	}
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;        
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId())
        {
        case R.id.menu_about:
           // Toast.makeText(MenuSample.this, "You Clicked About", 3000).show();
        	Intent ent= new Intent(this, about.class);
        	startActivity(ent);    
            return true;
        case R.id.menu_help:
        	Intent ent1= new Intent(this, help.class);
        	startActivity(ent1);  
        	return true;
        case R.id.menu_disclaimer:
        	Intent ent3= new Intent(this, disclaimer.class);
        	startActivity(ent3);  
        	return true;
        	
            //Toast.makeText(MenuSample.this, "You Clicked Help", 3000).show();    
      /*  case R.id.menu_upgrade:
            //Toast.makeText(MenuSample.this, "You Clicked New", 3000).show();
            return true;*/
        default:
            return super.onOptionsItemSelected(item);
        }        
    }
    
    @Override    
    public void onBackPressed() {
    	//System.exit(0);
    	
    	SOUNDON="ON";
    	ACTIVE_SOUND="";
    	TIMERON="OFF";
    	ACTIVE_TIMERON="";    			
    	Intent in1= new Intent(Intent.ACTION_MAIN);
    	in1.addCategory(in1.CATEGORY_HOME);
    	in1.setFlags(in1.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(in1);
    }
    /*
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
        	SOUNDON="ON";
        	ACTIVE_SOUND="";
        	TIMERON="OFF";
        	ACTIVE_TIMERON="";    			
        	Intent in1= new Intent(Intent.ACTION_MAIN);
        	in1.addCategory(in1.CATEGORY_HOME);
        	in1.setFlags(in1.FLAG_ACTIVITY_NEW_TASK);
        	startActivity(in1);	
        	  return true;
        default:
            return false;
        }
    }*/
}
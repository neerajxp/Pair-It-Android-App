package twintest.mobile.namespace;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class results extends Activity {
    /** Called when the activity is first created. */
	public static GameMainClass gmresullt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);           
        setContentView(R.layout.results);
        if(TwinTestActivity.TIMERON.equalsIgnoreCase("OFF"))
    	{
        	MovesResult();
    	}
        else
        {
        	TimerResult();
        }
            	    	 
    }
    public void TimerResult()
    {
    	Resources res = this.getResources();
    	int per=0;
    	if(gmresullt.gameover.equalsIgnoreCase("over"))
		{
    		if(TwinTestActivity.LEVEL.equalsIgnoreCase("Easy" ))
           	{
        		per=gmresullt.timerticks *100/gmresullt.timereasy;    		    		
           	}
    	 	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Medium" ))
           	{
    	 		per=gmresullt.timerticks *100/gmresullt.timermedium;
           	}
    		else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Difficult" ))
           	{
    			per=gmresullt.timerticks *100/gmresullt.timerdifficult;
           	}
        	if(per<85)
    		{
    			gmresullt.ratestars="Genius";
    		}
        	else
    		{
    			gmresullt.ratestars="Excellent";
    		}
		}
    	else
    	{
    		gmresullt.ratestars="Poor";
    	}
    	
    	
    	
		TextView tvs=(TextView)findViewById(R.id.textViewscore);
		/*
		if(gmresullt.timerticks>=60)
		{
			gmresullt.minutes=	gmresullt.timerticks/60;
			gmresullt.seconds=	gmresullt.timerticks%60;
		}
		else
		{
			gmresullt.minutes=	00;
			gmresullt.seconds=	gmresullt.timerticks;
		}
    	*/
	
    	String timetaken = String.valueOf(gmresullt.minutes)  + ":" +  String.valueOf(gmresullt.seconds) ;    	
    	tvs.setText("Time....." + timetaken  + "\n\nMoves...." + gmresullt.noofclicks);
    	   	    	    	
    	    
    	ImageView ims =(ImageView)findViewById(R.id.imageviestars);    	        	
        ImageView im =(ImageView)findViewById(R.id.imageviewman);        	
    	String starscount=""; 
    	if (gmresullt.ratestars.equalsIgnoreCase("Genius"))
    	{
    		
    		
    		ims.setImageResource(R.drawable.geniusstars );
    		im.setImageResource(R.drawable.genius);    		     		 
    		
    	}
    	else if  (gmresullt.ratestars.equalsIgnoreCase("Excellent"))
    	{
    	
    		ims.setImageResource(R.drawable.excellentstars  );
    		im.setImageResource(R.drawable.excellent);
    		starscount="4 stars";
    	}
    	else if  (gmresullt.ratestars.equalsIgnoreCase("Average"))
    	{
    	
    		ims.setImageResource(R.drawable.averagestars);
    		im.setImageResource(R.drawable.average);
    		starscount="3 stars";
    	}        	 
    	else 
    	{
    	
    		ims.setImageResource(R.drawable.poorstars);
    		im.setImageResource(R.drawable.poor);
    		starscount="2 stars";
    	}
    	  
		TextView tvtarget=(TextView)findViewById(R.id.textViewtarget);        	
		tvtarget.setText("");
		TextView tvtargetlevel=(TextView)findViewById(R.id.textViewtargetlevel);   
		tvtargetlevel.setText("");
		TextView tvtarget1=(TextView)findViewById(R.id.textViewtargetstart);        	
		tvtarget1.setText("");
		TextView tvtargetlevel2=(TextView)findViewById(R.id.textViewtargettobe);   
		tvtargetlevel2.setText("");
		
		TextView tvtargetlevel3=(TextView)findViewById(R.id.textViewmovesnext);   
		tvtargetlevel3.setText("");
		TextView tvtargetlevel4=(TextView)findViewById(R.id.textViewNexttarget);   
		tvtargetlevel4.setText("");
		
    	 
    	
    	
    	if(TwinTestActivity.SOUNDON=="ON")
    	{
        	 try {
        		 Thread.sleep(500);
        	 	}
        	 catch(Exception ex)
        	 {
        		 String s=ex.toString();
        	 }
        	     	     	 
        	  AssetFileDescriptor afd;
        	  MediaPlayer mp= new MediaPlayer();
        	  int soundresidresult=0;
        	  if (gmresullt.ratestars.equalsIgnoreCase("Genius"))
        	  {
    			//int soundresidresult= res.getIdentifier("genius", "raw", this.getPackageName());
    			//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
    			soundresidresult= res.getIdentifier("genius", "raw", this.getPackageName());
    				
    				try
    				{    	
    				mp.reset();    					    
    			     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
    		 else if  (gmresullt.ratestars.equalsIgnoreCase("Excellent"))
    		 {
    				//int soundresidresult= res.getIdentifier("excellent", "raw", this.getPackageName());
    			//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
    					try
    					{    
    						soundresidresult= res.getIdentifier("excellent", "raw", this.getPackageName());
    							mp.reset();    					    
    							afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
    			else if  (gmresullt.ratestars.equalsIgnoreCase("Average"))    		
    			{
    						//int soundresidresult= res.getIdentifier("average", "raw", this.getPackageName());
    					//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
    					try
    					{  
    							soundresidresult= res.getIdentifier("average", "raw", this.getPackageName());
    								mp.reset();    					    
    							     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
    			else
    			{
    			//	int soundresidresult= res.getIdentifier("poor", "raw", this.getPackageName());
    			//	MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
    					try
    					{  
    						
    						soundresidresult= res.getIdentifier("poor", "raw", this.getPackageName());
    							mp.reset();    					    
    						     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
    	gmresullt.gameover="start";
    }
public void MovesResult()
{
	Resources res = this.getResources();
    
		TextView tvs=(TextView)findViewById(R.id.textViewscore);       	       	
	String timetaken = String.valueOf(gmresullt.minutes)  + ":" +  String.valueOf(gmresullt.seconds) ;    	
	tvs.setText("Time....." + timetaken  + "\n\nMoves...." + gmresullt.noofclicks);
	   	
	ImageView ims =(ImageView)findViewById(R.id.imageviestars);    	        	
    ImageView im =(ImageView)findViewById(R.id.imageviewman);        	
	String starscount=""; 
	if (gmresullt.ratestars.equalsIgnoreCase("Genius"))
	{
		
		
		ims.setImageResource(R.drawable.geniusstars );
		im.setImageResource(R.drawable.genius);    		     		 
		
	}
	else if  (gmresullt.ratestars.equalsIgnoreCase("Excellent"))
	{
	
		ims.setImageResource(R.drawable.excellentstars  );
		im.setImageResource(R.drawable.excellent);
		starscount="4 stars";
	}
	else if  (gmresullt.ratestars.equalsIgnoreCase("Average"))
	{
	
		ims.setImageResource(R.drawable.averagestars);
		im.setImageResource(R.drawable.average);
		starscount="3 stars";
	}        	 
	else 
	{
	
		ims.setImageResource(R.drawable.poorstars);
		im.setImageResource(R.drawable.poor);
		starscount="2 stars";
	}
	  
	if (!gmresullt.ratestars.equalsIgnoreCase("Genius"))
	{
		TextView tvtarget=(TextView)findViewById(R.id.textViewtarget);        	
		tvtarget.setText(String.valueOf(gmresullt.target1));
		
		TextView tvtargetlevel=(TextView)findViewById(R.id.textViewtargetlevel);   
		tvtargetlevel.setText( starscount + " (" + gmresullt.targetlevel + ")");         	
	}
	else
	{
		TextView tvtarget=(TextView)findViewById(R.id.textViewtarget);        	
		tvtarget.setText("");
		TextView tvtargetlevel=(TextView)findViewById(R.id.textViewtargetlevel);   
		tvtargetlevel.setText("");
		TextView tvtarget1=(TextView)findViewById(R.id.textViewtargetstart);        	
		tvtarget1.setText("");
		TextView tvtargetlevel2=(TextView)findViewById(R.id.textViewtargettobe);   
		tvtargetlevel2.setText("");
		
		TextView tvtargetlevel3=(TextView)findViewById(R.id.textViewmovesnext);   
		tvtargetlevel3.setText("");
		TextView tvtargetlevel4=(TextView)findViewById(R.id.textViewNexttarget);   
		tvtargetlevel4.setText("");
		
	}
	
	
	if(TwinTestActivity.SOUNDON=="ON")
	{
    	 try {
    		 Thread.sleep(1500);
    	 	}
    	 catch(Exception ex)
    	 {
    		 String s=ex.toString();
    	 }
    	     	     	 
    	  AssetFileDescriptor afd;
    	  MediaPlayer mp= new MediaPlayer();
    	  int soundresidresult=0;
    	  if (gmresullt.ratestars.equalsIgnoreCase("Genius"))
    	  {
			//int soundresidresult= res.getIdentifier("genius", "raw", this.getPackageName());
			//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
			soundresidresult= res.getIdentifier("genius", "raw", this.getPackageName());
				
				try
				{    	
				mp.reset();    					    
			     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
		 else if  (gmresullt.ratestars.equalsIgnoreCase("Excellent"))
		 {
				//int soundresidresult= res.getIdentifier("excellent", "raw", this.getPackageName());
			//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
					try
					{    
						soundresidresult= res.getIdentifier("excellent", "raw", this.getPackageName());
							mp.reset();    					    
							afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
			else if  (gmresullt.ratestars.equalsIgnoreCase("Average"))    		
			{
						//int soundresidresult= res.getIdentifier("average", "raw", this.getPackageName());
					//MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
					try
					{  
							soundresidresult= res.getIdentifier("average", "raw", this.getPackageName());
								mp.reset();    					    
							     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
			else
			{
			//	int soundresidresult= res.getIdentifier("poor", "raw", this.getPackageName());
			//	MediaPlayer.create(getApplicationContext(), soundresidresult).start() ;
					try
					{  
						
							soundresidresult= res.getIdentifier("poor", "raw", this.getPackageName());
							mp.reset();    					    
						     afd = this.getApplicationContext().getResources().openRawResourceFd(soundresidresult);
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
	gmresullt.gameover="start";
}
    public void RePlay(View view)
    {
    	Intent ent=null;
    	if(TwinTestActivity.LEVEL.equalsIgnoreCase("Easy" ))
	       	{
		  		ent= new Intent(getApplicationContext(),  twintest6.class); 		
	       	}
		  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Medium" ))
	       	{
		  		ent= new Intent(getApplicationContext(),  twintestmedium.class);   		       	
	       	}
		  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Difficult" ))
       	{
		  		ent= new Intent(getApplicationContext(),  twintestdifficult.class);
       	}   	    	    	    	    
       	startActivity(ent); 
    }

    @Override    
    public void onBackPressed() {
    	
    	Intent ent=null;
    	if(TwinTestActivity.LEVEL.equalsIgnoreCase("Easy" ))
	       	{
		  		ent= new Intent(getApplicationContext(),  twintest6.class); 		
	       	}
		  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Medium" ))
	       	{
		  		ent= new Intent(getApplicationContext(),  twintestmedium.class);   		       	
	       	}
		  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Difficult" ))
       	{
		  		ent= new Intent(getApplicationContext(),  twintestdifficult.class);
       	}   	    	    	    	    
       	startActivity(ent); 
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
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
	    	Intent ent=null;
	    	if(TwinTestActivity.LEVEL.equalsIgnoreCase("Easy" ))
		       	{
			  		ent= new Intent(getApplicationContext(),  twintest6.class); 		
		       	}
			  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Medium" ))
		       	{
			  		ent= new Intent(getApplicationContext(),  twintestmedium.class);   		       	
		       	}
			  	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Difficult" ))
	       	{
			  		ent= new Intent(getApplicationContext(),  twintestdifficult.class);
	       	}   	    	    	    	    
	       	startActivity(ent); 
	    	 return true;
	    default:
	        return false;
	    }
	}
    
}






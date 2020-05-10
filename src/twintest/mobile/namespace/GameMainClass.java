package twintest.mobile.namespace;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class  GameMainClass extends Activity {
	 public Integer QUIZNUMBERS=99;
	 ArrayList<String> al;	
	 public int currBackgound=1;
	 public int radnind=1;
	 public int TotalButtons;
	 public  int noofclicks;
	 public long starttime;
	 public long endtime;
	 public ArrayList<String> halfarray = new ArrayList<String>();
	 public ArrayList<Integer> UserClicks= new ArrayList<Integer>();  
	 public HashMap<String, Integer> soundlist = new HashMap<String, Integer>();
	 
	 public int selectcount=0;
	 public String gameover="";
	 
	 public int volumelevel;
	 
	 public String ratestars;
	 public int target1=0;
	 public String targetlevel="";
       	
	 public int minForGenius=0;
	 public int minForExcellent=0;
	 public int minForAverage=0;        	        	
	 public long minutes=0;
	 public long seconds=0;
	 public int SoundResouceId;
	 public AssetFileDescriptor afd;
	 public AssetFileDescriptor afdnames;
	 public MediaPlayer mp;
	 public MediaPlayer mpnames;
	 public String CorrectAnswerShowname;
	 public int SoundResouceidResult=0;
	 public int timerticks=0;	 
	 public int timereasy=31000;
	 public int timermedium=61000;
	 public int timerdifficult=121000;
	 public Boolean GenerateVegatables() 
		{
			 try
			 {
			 	Random ran1= new Random();
			 	String vegtab;
			 	
			 	for(int i=0; i<TotalButtons/2;i++)
		        {
		        	int t= ran1.nextInt(TwinTestActivity.VegetablesArrayList.size());
		        	vegtab=TwinTestActivity.VegetablesArrayList.get(t);		        			        
		        	while(al.contains(vegtab))
		    		{
		        		t= ran1.nextInt(TwinTestActivity.VegetablesArrayList.size());
			        	vegtab=TwinTestActivity.VegetablesArrayList.get(t);
		    		}        
		        	al.add(vegtab);           	
		        }
			 	 Collections.shuffle(al);
			 	 for(int m=0;m<al.size();m++)
			     {
			 		halfarray.add(m, al.get(m));
			     }			 	 
			 }
			 catch(Exception ex)
			 {			 
				 return false; 
			 }
			 return true;	
		}
	 public Boolean GenerateAlphabets() 
		{
			 try
			 {
			 	Random ran1= new Random();
			 	String alphab;
			 	
			 	for(int i=0; i<TotalButtons/2;i++)
		        {
		        	int t= ran1.nextInt(TwinTestActivity.AlphabetsArrayList.size());
		        	alphab=TwinTestActivity.AlphabetsArrayList.get(t);		        			        
		        	while(al.contains(alphab))
		    		{
		        		t= ran1.nextInt(TwinTestActivity.AlphabetsArrayList.size());
		        		alphab=TwinTestActivity.AlphabetsArrayList.get(t);
		    		}        
		        	al.add(alphab);           	
		        }
			 	 Collections.shuffle(al);
			 	 for(int m=0;m<al.size();m++)
			     {
			 		halfarray.add(m, al.get(m));
			     }			 	 
			 }
			 catch(Exception ex)
			 {			 
				 return false; 
			 }
			 return true;	
		}
	 public Boolean GenerateAnimals() 
		{
			 try
			 {
			 	Random ran1= new Random();
			 	String anima;
			 	
			 	for(int i=0; i<TotalButtons/2;i++)
		        {
		        	int t= ran1.nextInt(TwinTestActivity.AnimalsArrayList.size());
		        	anima=TwinTestActivity.AnimalsArrayList.get(t);		        			        
		        	while(al.contains(anima))
		    		{
		        		t= ran1.nextInt(TwinTestActivity.AnimalsArrayList.size());
		        		anima=TwinTestActivity.AnimalsArrayList.get(t);
		    		}        
		        	al.add(anima);           	
		        }
			 	 Collections.shuffle(al);
			 	 for(int m=0;m<al.size();m++)
			     {
			 		halfarray.add(m, al.get(m));
			     }			 	 
			 }
			 catch(Exception ex)
			 {			 
				 return false; 
			 }
			 return true;	
		}
	 public Boolean GenerateNumbers() 
		{
		 	Random ran1= new Random();
	        //Calculate matrix by multipying Rows with Columns and generate numbers
		 	 try
			 {
		        for(int i=0; i<TotalButtons;i++)
		        {
		        	int t= ran1.nextInt(QUIZNUMBERS);
		        	//dont include 1 to 9 numbers
		        	while(t<10)
		        	{
		        		t= ran1.nextInt(QUIZNUMBERS);
		        	}        	
		        	al.add(String.valueOf(t));           	
		        } 
		        Collections.shuffle(al);
		        
		        //pick random numbers half numbers generated out of total numbers	                
		        int maxcount=0;
		        for(int m=0;m<al.size()-1;m++)
		        {
		        	//al.set(m, al.get(m-1));        	//m=m+1;
		        	String temp=al.get(m);        	
		        	//Check if duplicate value is found
		    		while(halfarray.contains(temp))
		    		{
		    			m=m+1;
		    			temp=al.get(m);  
		    		}        		        	 
		    		//temp=al.get(m);    		
		        	halfarray.add(temp);        	
		            maxcount=maxcount+1;
		            if(maxcount >= al.size()/2)
		            {
		            	break;
		            }        	         	
		        }		     		        
			 }
		 	 catch(Exception ex)
			 {			 
				 return false; 
			 }
	        return true;
		}
	public Boolean GenerateRandom() 
	{
		 TotalButtons=TwinTestActivity.QUIZROWS *TwinTestActivity.QUIZCOLS;
		 ///////////Random Number Logic//////////// 		
       al = new ArrayList<String>();        
       if (TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Numbers"))
       {
       	if(GenerateNumbers()==false)
           {
           	return false;        	
           }
       }
       else if (TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Vegetables"))
       {
       	if(GenerateVegatables()==false)
           {
           	return false;        	
           }
       }        
       else if (TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Alphabets"))
       {
       	if(GenerateAlphabets()==false)
           {
           	return false;        	
           }
       }        
       else if (TwinTestActivity.PAIRTYPE.equalsIgnoreCase("Animals"))
       {
       	if(GenerateAnimals()==false)
           {
           	return false;        	
           }
       }        
                               
       Collections.shuffle(halfarray);       
       al.clear();
       //Copy the half array and replicate the other half        
       for(int i=0;i<halfarray.size();i++)
       {
       	al.add(halfarray.get(i));	
       }
       for(int i=halfarray.size()-1;i>=0;i--)
       {
       	al.add(halfarray.get(i));	
       }
       Collections.shuffle(al);
       
       //get a seed within number of questions ///
       Calendar cd= Calendar.getInstance();
       Random r1= new Random(cd.SECOND);
       int seed = cd.SECOND;        
       //if(cd.SECOND > )
       //{
       	seed=cd.SECOND % TotalButtons;
       //}        
       //Swap a question based on seed and the shuffle	
       Random rgen = new Random();	                   
       int randomPosition = rgen.nextInt(al.size());
       try
       {
	        String temp =al.get(seed);        
	        al.set(seed,  al.get(randomPosition));
	        al.set(randomPosition, temp);
       }               
       catch(Exception ex)
       {
       	String s=ex.toString();
       	return false;
       }
       Collections.shuffle(al);                             
       return true;
	}
	 
	
	
	public Boolean setButtonBackground(Context ct)
	{
		if(TwinTestActivity.currDrawable<=0 || TwinTestActivity.currDrawable>7)
		{
			TwinTestActivity.currDrawable=4;
		}
		
		 //Resources res = this.getResources();
		try
		{
		 //Resources res = Context.getResources();		
		 Resources res = ct.getResources();
		 String vegetable=String.valueOf(TwinTestActivity.currDrawable);
		 vegetable="quizbutton"+vegetable;
		 int resID = res.getIdentifier(vegetable, "drawable", ct.getPackageName());			 	
		 currBackgound=resID;
		}
		catch(Exception ex)
		{
			String err=ex.toString();
			return false;
		}
		return true;
	}

	public Boolean CreateGame(Context ct, Activity ac)
	{
		//Reset Variables
		Boolean bFlag=true;
		gameover="start";  		 		 
		 noofclicks=0;
		 selectcount=0;
		 gameover="";		 
		 ratestars="";
		 target1=0;
		 targetlevel="";	       
		 minForGenius=0;
		 minForExcellent=0;
		 minForAverage=0;        	        	
	       			 		 
		  //Generate numbers
       if(GenerateRandom()==false)
       {
       		bFlag= false;
       }
       if(setButtonBackground(ct)==false)
       {
    	   bFlag= false;
       }
       
       String s="";
       int pos=0;
       //TableLayout table1=(TableLayout)findViewById(R.id.tableLayout1);          
 
    	   LinearLayout  table1=(LinearLayout) ac.findViewById(R.id.Linearquizpain);
       
       
       for(int i=0;i<table1.getChildCount();i++)
       {
       	View vw=table1.getChildAt(i);                    	
       	Class c=vw.getClass();   
       	if(c==LinearLayout.class )
       	{
       		//TableRow tablerow1=(TableRow)findViewById(vw.getId());
       		LinearLayout tablerow1=(LinearLayout) ac.findViewById(vw.getId());        		
       		 for(int j=0;j<tablerow1.getChildCount();j++)
       	        {        			 	
       			 	View vw1=tablerow1.getChildAt(j);                    	
       	        	Class c1=vw1.getClass();
       	        	if(c1==Button.class  )
       	        	{
       	        		try
       	        		{        	        			
	        	        		Button B1=(Button) ac.findViewById(vw1.getId());	        	        			        	        			        	        		
	        	        		B1.setBackgroundResource(currBackgound);
	        	        		B1.setTag(al.get(pos).toString());	        	        		
	        	        		B1.setTextSize(28);
	        	        		B1.setVisibility(View.VISIBLE);
	        	        		pos=pos+1;
       	        		}
       	        		catch(Exception ex)
       	        		{
       	                	bFlag= false;
       	        		}
       	        	}        	        
       	        }
       	}        	
       }
       
       return bFlag;
	}
   	
 
   
   public void CheckReset(Context ct, Activity ac)
   {
	       
   	if(al.size()==0)
   	{    		
   		endtime=System.currentTimeMillis();
   	   long totaltime =(endtime - starttime) / 1000;
       minutes=totaltime/60;
       seconds=totaltime%60;

         //NumberFormat formatter = new DecimalFormat("#0.00000");                                 	        	
       	int perGenius=0;
       	int perExcellent=0;
       	int perAverage=0;
       	int perPoor=0;
       	
       	if(TwinTestActivity.LEVEL.equalsIgnoreCase("Easy" ))
       	{
       		//perGenius=0;
       		perGenius=-15;
       		perExcellent=15;
       		perAverage=35;
       		perPoor=60;
       	}
       	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Medium" ))
       	{
       		perGenius=10;
       		perExcellent=30;
       		perAverage=60;
       		perPoor=90;
       	}
       	else if(TwinTestActivity.LEVEL.equalsIgnoreCase("Difficult" ))
       	{
       		perGenius=15;
       		perExcellent=45;
       		perAverage=65;
       		perPoor=100;
       	}
       		
              	        	
       	int calcrating =TotalButtons *2 ;        	
       	int rating =calcrating+calcrating * perGenius/100  ;
       	String targetrating = String.valueOf(rating);
       	        	        	        
       	minForGenius=rating;
       	if(noofclicks <=rating )
       	{
       		ratestars="Genius";
       	}
       	else
       	{
       		 rating =calcrating+calcrating* perExcellent/100 ;
       		 minForExcellent=rating;
           	if(noofclicks <=rating )
           	{
           		target1=minForGenius;
           		ratestars="Excellent";
           		targetlevel="Genius";		//prompt to play again to be genius
           	}
           	else
           	{
           		 rating =calcrating+calcrating* perAverage/100 ;
           		 minForAverage =  rating;
                	if(noofclicks <=rating )
                	{          
                		target1=minForExcellent;
                		ratestars="Average";
               		targetlevel="Excellent";		//prompt to play again to be genius
                	}
                	else
                	{              
                		target1=minForAverage;
                		ratestars="Poor";
               		targetlevel="Average";		//prompt to play again to be genius                 		
                	}                 	            
           	}
       	}   
       	
     	gameover="over";     	     		    
   	}   	
   	
   }
   
   public void  CheckAnswer(Context ct, Activity ac)
   {
   	final Button b1=(Button) ac.findViewById(UserClicks.get(0));		    
   	final Button b2=(Button) ac.findViewById(UserClicks.get(1));
   	
   	if(b1.getTag().toString().equals(b2.getTag().toString()))
   	{    		
   		CorrectAnswerShowname="correct";   			
   		 UserClicks.clear();    		
   		// Integer value=Integer.parseInt(b1.getTag().toString());
   		 String value=b1.getTag().toString();
   		 al.remove(al.indexOf(value));
   		 al.remove(al.indexOf(value));
   		 
   		 long delaywhenfinish=500;
   			if(al.size()==0)
   	    	{    	
   				delaywhenfinish=200;
   	    	}
   		 
   		 
   		 b1.setVisibility(View.VISIBLE);
   		 b1.postDelayed(new Runnable() {
   		         public void run() {
   		             b1.setVisibility(View.INVISIBLE);
   		         }
   		     }, delaywhenfinish);
   		 
   		 b2.setVisibility(View.VISIBLE);
   		 b2.postDelayed(new Runnable() {
   		         public void run() {
   		             b2.setVisibility(View.INVISIBLE);
   		         }
   		     }, delaywhenfinish);
   		
   		 //play sound here
   		 try
   		 {
   			 if(TwinTestActivity.SOUNDON=="ON")
   			 {
   				//AudioManager am = (AudioManager)ac.getSystemService(ct.AUDIO_SERVICE);   				 								
				 if(soundlist.containsKey(value))
				 {	
					  					  
					 // Uri a= Uri.parse("file:///android_asset/camel.mp3");
					
					 
					 //int soundresid=soundlist.get(value);
				     //MediaPlayer.create(ct.getApplicationContext(), soundresid).start() ;
					
				     
					
					/*
					 int soundresid=soundlist.get(value);
					// mpnames.reset();
					 mpnames.create(ct.getApplicationContext(), soundresid);
					 //afdnames = ct.getApplicationContext().getResources().openRawResourceFd(soundresid);
					 //mpnames.setDataSource(afdnames.getFileDescriptor(), afdnames.getStartOffset(), afdnames.getDeclaredLength());					 
					 //mp.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
					mpnames.setAudioStreamType(AudioManager.STREAM_MUSIC);	
					 //mpnames.setAudioStreamType(AudioManager.STREAM_MUSIC );
					
					 mpnames.setVolume(20,0);     
					 
					//mpnames.setLooping(true);
					 //mpnames.prepare();				 				 			 
					 mpnames.start();
				
					 */
				     
				     mpnames.reset();
				     int soundresid=soundlist.get(value);
					// mpnames.setDataSource(afdnames.getFileDescriptor(), afdnames.getStartOffset(), afdnames.getDeclaredLength());
				     afdnames = ct.getApplicationContext().getResources().openRawResourceFd(soundresid);
				     mpnames.setDataSource(afdnames.getFileDescriptor(), afdnames.getStartOffset(), afdnames.getDeclaredLength());
					 //mpnames.setAudioStreamType(AudioManager.STREAM_RING);
				     mpnames.setAudioStreamType(AudioManager.STREAM_MUSIC);
					 mpnames.prepare();				 				 			 
					 mpnames.start();
					 
				 }
				 else
				 {
					 mp.reset();
					 mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
					 //mp.setAudioStreamType(AudioManager.STREAM_RING);
					 mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
					 mp.prepare();				 				 			 
					 mp.start();
				 }
   			 }
   		 }
   		 catch(Exception ex)
   		 {
   			 String s="error"; 
   		 }
   		 
   		 //
   		 CheckReset(ct,  ac);
 		 
   	}
   	else
   	{    		
   		CorrectAnswerShowname="incorrect";
   		//b1.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.btn_default));    		
   		b1.setBackgroundResource(currBackgound);    		
   		b1.setText("");
   		UserClicks.remove(0);	    		
   	}    	   	
   }    
   
  
}



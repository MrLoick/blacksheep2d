package it.uniroma2.framework;

import java.util.Properties;

import it.uniroma2.framework.audio.Audio;
import it.uniroma2.framework.event.Event;
import it.uniroma2.framework.event.Message;
import it.uniroma2.framework.event.RTSimulationKernel;
import it.uniroma2.framework.mind.MindAsyncTask;
import it.uniroma2.framework.physic.PhysicAsyncTask;
import it.uniroma2.framework.render.GameView;
import it.uniroma2.framework.render.ImageMap;
import it.uniroma2.framework.stage.Stage;
import it.uniroma2.framework.stage.StageManager;
import it.uniroma2.framework.xml.SaxXmlParser;
import it.uniroma2.framework.xml.SaxXmlParserMessage;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


/*******************************************************************************
 * 
 * @author Valentino Colatosti
 * 
 * Copyright (C) 2012 valentino.colatosti@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

public class Game extends Activity {
    /** Called when the activity is first created. */
	
	private static Context context;
	
	private static float scaleHeight;
	private static float scaleWidth;
	
	private GameView gameView;
	
	private ImageMap imageMap;
	private Audio soundClip;
	
	public static Context getContext(){
		return context;
	}
	
	public static float getScaleHeight(){
		return scaleHeight;
	}
	
	public static float getScaleWidth(){
		return scaleWidth;
	}
	
	
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        context=this;
        
        //set full screen 
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //disable lock screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       
		Properties properties=GameProperties.getIstance().getPropeties();
		
		
		
		if(properties.get("SCREEN_ORIENTATION")!=null){
			if(properties.get("SCREEN_ORIENTATION").equals("PORTRAIT"))
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			if(properties.get("SCREEN_ORIENTATION").equals("LANDSCAPE"))
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		
		// calculate the scale value for adapt the screen resolution
		Float height=new Float(properties.getProperty("SCREEN_HEIGHT", "480.0f"));
        Float width=new Float(properties.getProperty("SCREEN_WIDTH", "320.0f"));
        
        //invert default value for landscape
        if("LANDSCAPE".equals(properties.get("SCREEN_ORIENTATION"))){
        	height=new Float(properties.getProperty("SCREEN_HEIGHT", "320.0f"));
            width=new Float(properties.getProperty("SCREEN_WIDTH", "480.0f"));
        }
        
        
		 
		scaleHeight=(float)getResources().getDisplayMetrics().heightPixels/height;
		scaleWidth=(float)getResources().getDisplayMetrics().widthPixels/width;
		
		gameView=new GameView(this);
        setContentView(gameView);
              
        RTSimulationKernel kernel;
        kernel=RTSimulationKernel.getIstance();
             
        StageManager stageManager=StageManager.getIstance();
        kernel.register(stageManager);
        
        
        imageMap=ImageMap.getIstance();
        soundClip=Audio.getIstance();
        resources();       
        
        //new Thread(MindManager.getIstance()).start();
        (new MindAsyncTask()).doInBackground();
        //new Thread(AdaptJBox2D.getIstance()).start();
        (new PhysicAsyncTask()).doInBackground();
        
        new SaxXmlParserMessage().parse();
        new SaxXmlParser().parse();
        
        new ExitStage("EXIT",null);  
        
           
    }
    
    
    
    public final void onStart(){
    	super.onStart();
    	  	
        RTSimulationKernel kernel;
        kernel=RTSimulationKernel.getIstance();
		Event event=kernel.requestEventIstance();
		event.set(Message.get("MAINMENU"));
 		kernel.SendEvent(event);
        
        
    }
    
    
    public void onResume(){
    	super.onResume();
    	Log.i("blacksheep"," onResume");
    }
    
    public void onPause(){
    	super.onPause();
    	//StageManager.getIstance().destroy();
    	Log.i("blacksheep"," onPause");
    }
    
    public void onStop(){
    	super.onStop();
    	Log.i("blacksheep"," onStop");
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	Log.i("blacksheep"," onDestroy");
    }
    
    private class ExitStage extends Stage{

		public ExitStage(String message, String id) {
			super(message, id);
		}
    	
		public void onLoad(){
			Log.i("blacksheep", "exit game");
			finish();
		}
    	
    }
    
    public void loadAudio(int resId){
    	soundClip.load(resId);
    }
    
    public void loadImage(int resId){
    	imageMap.load(resId);
    }
   
    public void resources(){
    	
    }
    
    
} 
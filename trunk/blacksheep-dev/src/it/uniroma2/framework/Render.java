package it.uniroma2.framework;

import java.text.DecimalFormat;
import java.util.ArrayList;

import it.uniroma2.framework.collisionmanager.CollisionBox2D;
import it.uniroma2.framework.entity.IDrawable;
import it.uniroma2.framework.mind.MindManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;



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

public class Render extends Thread {
	
	private SurfaceHolder surfaceHolder;
	//private Context context;
	
	private final static int 	MAX_FPS = 50;	
	// maximum number of frames to be skipped
	private final static int	MAX_FRAME_SKIPS = 5;	
	// the frame period
	private final static int	FRAME_PERIOD = 1000 / MAX_FPS;
	
	
	private ArrayList<IDrawable> drawableList;
	
	
	
	 
		    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
		    // we'll be reading the stats every second
		    private final static int    STAT_INTERVAL = 1000; //ms
		    // the average will be calculated by storing
		    // the last n FPSs
		    private final static int    FPS_HISTORY_NR = 10;
		    // last time the status was stored
		    private long lastStatusStore = 0;
		    // the status time counter
		    private long statusIntervalTimer    = 0l;
		    // number of frames skipped since the game started
		    private long totalFramesSkipped         = 0l;
		    // number of frames skipped in a store cycle (1 sec)
		    private long framesSkippedPerStatCycle  = 0l;
		 
		    // number of rendered frames in an interval
		    private int frameCountPerStatCycle = 0;
		    private long totalFrameCount = 0l;
		    // the last FPS values
		    private double  fpsStore[];
		    // the number of times the stat has been read
		    private long    statsCount = 0;
		    // the average FPS since the game started
		    private double  averageFps = 0.0;
		 
	
	
	
	private boolean run=false;
	
	private Paint paint;
	
	private long startTime;
	private long elapsed;
	
	private CollisionBox2D collisionBox2D;
	private MindManager mindManager;
	
	
	public Render(SurfaceHolder surfaceHolder/*, Context context,Handler handler*/){
		Log.i("game", "render");
		this.surfaceHolder= surfaceHolder;
		//this.context=context;
		mindManager=MindManager.getIstance();
		collisionBox2D=CollisionBox2D.getIstance();
		
		drawableList=new ArrayList<IDrawable>();
		paint =new Paint();
		paint.setColor(Color.WHITE);
		
	}
		
	 
	
	private void doDraw(Canvas canvas){
		//canvas.drawARGB(0,255, 255, 255);
		//startTime = System.currentTimeMillis();
		canvas.drawColor(Color.WHITE);
		for(int i=0;i<drawableList.size();i++){
			
			try{
			if(!drawableList.get(i).draw(canvas))
				remove(drawableList.get(i));
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		//elapsed = System.currentTimeMillis() - startTime;
		//canvas.drawText("FPS: "+Math.round(1000f / elapsed) , 10, 10, paint);
		//startTime = System.currentTimeMillis();		 
	}
	

	
	public void run() {
		Log.i("game", "render2");
		long beginTime; // the time when the cycle begun
		long timeDiff; // the time it took for the cycle to execute
		int sleepTime; // ms to sleep (<0 if we're behind)
		int framesSkipped; // number of frames being skipped
		
		initTimingElements();

		sleepTime = 0;

		while (run) {
			Canvas canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();

					framesSkipped = 0; // resetting the frames skipped

					mindManager.run();

					// aggiornare fisica
					
					collisionBox2D.run();
					

					// render state to the screen
					// draws the canvas on the panel
					doDraw(canvas);
					canvas.drawText("FPS: "+df.format(averageFps), 10,10, paint);
					// chiamare onDrawFrame(GL10 gl) di gl render

					// calculate how long did the cycle take
					timeDiff = System.currentTimeMillis() - beginTime;
					// calculate sleep time
					sleepTime = (int) (FRAME_PERIOD - timeDiff);

					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
						}
					}

					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						// we need to catch up
						// this.gamePanel.update(); // update without rendering
						// //fisica
						mindManager.run();

						collisionBox2D.run();
						sleepTime += FRAME_PERIOD; // add frame period to check
													// if in next frame
						framesSkipped++;
					}
					framesSkippedPerStatCycle += framesSkipped;
					storeStats();
				}

			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);

				}
			}
		}
	}
            
	

	public void setRun(boolean run) {
		this.run=run;		
	}
	
	public void add(IDrawable drawable){
		drawableList.add(drawable);
	}
	
	public void remove(IDrawable drawable){
		drawableList.remove(drawable);
	}
	
	public void clearDrawable(){
		drawableList.clear();
	}
	
	
	  private void storeStats() {
		 	        frameCountPerStatCycle++;
		  	        totalFrameCount++;
		  	 
		  	        // check the actual time
		  	        statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);
		  	 
		  	        if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
		  	            // calculate the actual frames pers status check interval
		  	            double actualFps = (double)(frameCountPerStatCycle / (STAT_INTERVAL / 1000));
		  	 
		              //stores the latest fps in the array
		              fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;
		  	 
		 	            // increase the number of times statistics was calculated
		  	            statsCount++;
		  	 
		  	            double totalFps = 0.0;
		 	            // sum up the stored fps values
		  	            for (int i = 0; i < FPS_HISTORY_NR; i++) {
		  	                totalFps += fpsStore[i];
		  	            }
		  
		  // obtain the average
		  	            if (statsCount < FPS_HISTORY_NR) {
		  	                // in case of the first 10 triggers
		  	                averageFps = totalFps / statsCount;
		  	            } else {
		 	                averageFps = totalFps / FPS_HISTORY_NR;
			            }
		  	            // saving the number of total frames skipped
		  	            totalFramesSkipped += framesSkippedPerStatCycle;
		  	            // resetting the counters after a status record (1 sec)
		  	            framesSkippedPerStatCycle = 0;
		  	            statusIntervalTimer = 0;
		  	            frameCountPerStatCycle = 0;
		  	 
		  	            statusIntervalTimer = System.currentTimeMillis();
		  	            lastStatusStore = statusIntervalTimer;
		  	//          Log.d(TAG, "Average FPS:" + df.format(averageFps));
		  	            //gamePanel.setAvgFps("FPS: " + df.format(averageFps));
		  	        }
		  	    }
		  	 
		  	    private void initTimingElements() {
		  	        // initialise timing elements
		  	        fpsStore = new double[FPS_HISTORY_NR];
		  	        for (int i = 0; i < FPS_HISTORY_NR; i++) {
		  	            fpsStore[i] = 0.0;
		  	        }
		 	       
		 	    }
	
}


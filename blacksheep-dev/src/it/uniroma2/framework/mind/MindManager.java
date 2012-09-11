package it.uniroma2.framework.mind;


import java.util.ArrayList;

import android.util.Log;


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

public class MindManager /*extends LinearLayout*/ implements Runnable{//Thread {
	
	private static MindManager mindManager;
	private boolean run;
	//private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	private ArrayList<IMind> mindList;
	
	
	//private Timer timer;

	private MindManager(/*Context context*/){
		//super(context);
		mindList=new ArrayList<IMind>();
		//timer=new Timer();
			
    	//scheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(1);
    	

	}
	
	public static MindManager getIstance(){
		
		if(mindManager==null)
			mindManager=new MindManager(/*ContextReference.getIstance().getContext()*/);
		return mindManager;
	}
	
	
	public void add(IMind mind){
		if(mind!=null&& !mindList.contains(mind)){
			mindList.add(mind);
			if(!run){
				run=true;
				//this.start();
			}
				
		}
			
		
		//scheduledThreadPoolExecutor.
	}
	
	public void clear(){
		mindList.clear();
	}
	
	public void remove(IMind mind){
		if(mindList.contains(mind)){
			mindList.remove(mind);
			if(mindList.size()==0){
				run=false;
				//this.stop();
			}
		}
			
	}
	
	/*public void start(){
		//super.start();
		//timer.schedule(this,0,100);
    	//scheduledThreadPoolExecutor.scheduleAtFixedRate(mindManager,0, 50, TimeUnit.MILLISECONDS);

	}*/
	
	
	public void run() {
		run = true;
		
		//long elapsed = 0;
		for (int index = 0; index < mindList.size(); index++)
			if (!mindList.get(index).mind())
				remove(mindList.get(index));
		//postInvalidate();
	}
	
	
	
	
}

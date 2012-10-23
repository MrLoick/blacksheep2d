package it.uniroma2.framework.mind;


import java.util.ArrayList;

import android.os.Handler;


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

public class MindManager implements Runnable{//Thread {
	
	private static MindManager mindManager;
	//private boolean run;
	
	private ArrayList<IMind> mindList;
	
	private boolean run;
	private Handler handler;

	private MindManager(){
		
		mindList=new ArrayList<IMind>();
		
		handler=new Handler();    	

	}
	
	public static MindManager getIstance(){
		
		if(mindManager==null)
			mindManager=new MindManager();
		return mindManager;
	}
	
	
	public void add(IMind mind){
		if(mind!=null&& !mindList.contains(mind)){
			mindList.add(mind);
							
		}
	}
	
	public void clear(){
		mindList.clear();
	}
	
	public void remove(IMind mind){
		if(mindList.contains(mind)){
			mindList.remove(mind);
		}
				
	}
		
	
	public void run() {

		for (int index = 0; index < mindList.size(); index++)
			mindList.get(index).mind();
				

		handler.postDelayed(this, 30);
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	
	
	
	
}

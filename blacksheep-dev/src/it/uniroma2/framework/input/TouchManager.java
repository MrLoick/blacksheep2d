package it.uniroma2.framework.input;

import it.uniroma2.framework.event.Event;
import it.uniroma2.framework.event.Message;
import it.uniroma2.framework.event.RTSimulationKernel;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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

public class TouchManager {
	
	private static TouchManager touchManager;
	
	private ArrayList<ITouchable> touchableList;

	private TouchManager(){
		touchableList=new ArrayList<ITouchable>();
	}
	
	public static TouchManager getIstance(){
		if(touchManager==null)
			touchManager=new TouchManager();		
		return touchManager;
	}
	
	public void add(ITouchable touchable){
		if(touchable.onTouchEvent(null))
			touchableList.add(touchable);
	}
	
	public void remove(ITouchable touchable){
		touchableList.remove(touchable);
	}
	
	public void clear(){
		touchableList.clear();
	}
	
	public boolean onTouchEvent(MotionEvent motionEvent){
		  //RTSimulationKernel kernel=RTSimulationKernel.getIstance();
		 /* Event event=kernel.requestEventIstance();
		  event.set(Message.EVENT_TEST);
		  kernel.SendEvent(event);*/
		  
		  for(int i=0;i<touchableList.size();i++){
			if(!touchableList.get(i).onTouchEvent(motionEvent))
				remove(touchableList.get(i));
		}
		return true;
	}
	
	//multitouch
	/*public boolean onTouch(View v, MotionEvent event) {
	      //ImageView view = (ImageView) v;

	      // Dump touch event to log
	      //dumpEvent(event);

	      // Handle touch events here...
	      switch (event.getAction() & MotionEvent.ACTION_MASK) {
	      }

	      // Perform the transformation
	      //view.setImageMatrix(matrix);

	      return true; // indicate event was handled
	}*/

}

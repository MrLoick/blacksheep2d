package it.uniroma2.blacksheep.physics.entity;


import it.uniroma2.blacksheep.physics.R;
import it.uniroma2.framework.physic.AdaptJBox2D;
import it.uniroma2.framework.entity.GameEntity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;


/***************************************************************************
 * @author Valentino Colatosti
 * 
 * Copyright (C) 2012 valentino.colatosti@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

public class PhysicsSquare extends GameEntity {
	
	private Bitmap invader;
		
	public PhysicsSquare(){
		
		invader=getBitmap(R.drawable.sheep);	
		
	}
	
	
	public boolean draw(Canvas canvas){	
		invader=sizeBitmap(invader);
        canvas.save();
        canvas.rotate(getAngle(),getPointX()+(getLengthX()/2),getPointY()+(getLengthY()/2));
        canvas.drawBitmap(invader, getPointX(), getPointY(), null);
		canvas.restore();
		
		return true;
	}

	private boolean drag=false;
	
	
	public boolean onTouchEvent(MotionEvent motionEvent) {
		
		if (motionEvent != null) {
			int tx = (int) motionEvent.getX();
			int ty = (int) motionEvent.getY();
			
			if (tx >= getPointX() && tx <= getPointX() + getLengthX() && ty >= getPointY() && ty <= getPointY() + getLengthY()
					&& MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
								
					drag=true;
				
			}
			 
			if(MotionEvent.ACTION_UP == motionEvent.getAction()){
				drag=false;
			}
			
			if(drag)
				moveEntity(tx,ty);
			
		}
		return true;
	}
	
	public void onSensorChanged(SensorEvent event) {
		
		AdaptJBox2D.getIstance().setGravity(event.values[0],event.values[1]);
		
		//Log.i("box2d","gravity 0"+event.values[0]+" 1 "+event.values[1]+" timestamp "+event.timestamp);
	}	

}

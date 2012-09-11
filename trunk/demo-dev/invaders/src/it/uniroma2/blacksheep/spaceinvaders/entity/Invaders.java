package it.uniroma2.blacksheep.spaceinvaders.entity;

import org.jbox2d.dynamics.contacts.Contact;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import it.uniroma2.blacksheep.spaceinvaders.R;
import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;

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


public class Invaders extends GameEntity {
	
	private Bitmap invaders;
	
	private boolean left;
	
	
	public Invaders (){
		
		invaders=getBitmap(R.drawable.invaders1);
		left=true;
		
	}

	public boolean draw(Canvas canvas){
		invaders=sizeBitmap(invaders);
		canvas.drawBitmap(invaders, getPointX(), getPointY(), null);
		return true;
	}
	
	public void receiveEvent(Event event) {
		
		if("INVERTLEFT".equals(event.getMessage().getText())/*&& active==false*/){
			left=true;
		}
		if("INVERTRIGHT".equals(event.getMessage().getText())/*&& active==false*/){
			left=false;
		}
	
	}
	
	
	private long startTime;
	//private long elapsed;
	
	private long getElapsed(){
		long elapsed=0;
		if(startTime!=0){
		
			elapsed=System.currentTimeMillis()-startTime;
		}								
		startTime = System.currentTimeMillis();
		return elapsed;
	}
	
	private float speedX=3;
	
	public boolean mind(){
		float delta=0;
				
		
		long elapsed= getElapsed();
		Log.i("blacksheep","INVADERS MIND "+elapsed);
		
		delta =(speedX * (elapsed / 25f));
		
		Log.i("blacksheep","delta "+ delta);
		if(left){
			moveEntity(getPointX()-(int)delta,getPointY());
		}
		else{
			moveEntity(getPointX()+(int)delta,getPointY());
		}

		if((getPointX()-3)<0)
			sendMessage("INVERTRIGHT");
			
		if((getPointX()+3)>getDisplayWidth()-getLengthX())			
			sendMessage("INVERTLEFT");
		
		return true;
	}
	
	public boolean receiveCollisionEvent(Contact contact){
		Log.i("blacksheep","# invader receive collision event");
		if(contact.m_fixtureA.getUserData() instanceof Fire){
			Log.i("blacksheep","#fire è a");
			unregister();
		}
		if(contact.m_fixtureB.getUserData() instanceof Fire){	
			Log.i("blacksheep","# fire è b");
			unregister();
		}
		return true;	
	}

}

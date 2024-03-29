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


public class Fire extends GameEntity {
	
	private Bitmap fire;
	private boolean active=false;
	
	public Fire(){
		fire = getBitmap(R.drawable.fire);
	}
	
	
	public boolean draw(Canvas canvas){
		if(active){
			fire=sizeBitmap(fire);
			canvas.drawBitmap(fire, getPointX(), getPointY(), null);
		}	
		return true;
	}
	
	
	public void receiveEvent(Event event) {
		if(active==false)
			if("FIRE".equals(event.getMessage().getText())){
				moveEntity(-10,-10);
				int px=((Integer) event.getMessageInfo().get("POINTX"));
				int py=((Integer) event.getMessageInfo().get("POINTY"));
				moveEntity(px, py);
				play(R.raw.fire);
				active=true;
			}
	}
	

	public void mind(){
		
		if(active){
			if(getPointY()>=0){
				moveEntity(getPointX(),getPointY()-10);
			}
			if(getPointY()<0){
				active=false;
				moveEntity(-10,-10);
			}	
		}
	}
	
	
	/*public boolean receiveCollisionEvent(Contact contact){
		
		if(contact.m_fixtureA.getUserData() instanceof Invaders){
			moveEntity(-10,-10);
			active=false;
			//Log.i("blacksheep","invaders è a collision");
		}
		
		if(contact.m_fixtureB.getUserData() instanceof Invaders){
			moveEntity(-10,-10);
			active=false;
			//Log.i("blacksheep","invaders è b collision");
		}
				
		return true;	
	}*/
	
	public void reset(){
		Log.i("blacksheep","prova reset");
		moveEntity(-10,-10);
		active=false;
	}

}

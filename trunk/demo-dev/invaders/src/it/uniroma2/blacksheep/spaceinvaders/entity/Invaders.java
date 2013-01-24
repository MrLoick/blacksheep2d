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
	
	private int row;
	private int column;
	
	
	public Invaders (){
		
		invaders=getBitmap(R.drawable.invaders1);
		
	}

	public boolean draw(Canvas canvas){
		invaders=sizeBitmap(invaders);
		canvas.drawBitmap(invaders, getPointX(), getPointY(), null);
		return true;
	}
	
	public void receiveEvent(Event event) {
		
		if("DOWN".equals(event.getMessage().getText())){
			moveEntity(getPointX(),getPointY()+10);
		}
		
		if("MOVE".equals(event.getMessage().getText())){
				
				int px=((Integer) event.getMessageInfo().get("DELTAX"));
				
				moveEntity(getPointX()+px,getPointY());
								
		}
		
		
	
	}
	
	public void mind(){
		
		if((getPointX())<0)
			sendMessage("INVERTRIGHT");
			
		if((getPointX())>getDisplayWidth()-getLengthX())			
			sendMessage("INVERTLEFT");
	}
	
	public boolean receiveCollisionEvent(Contact contact){
		Log.i("blacksheep","# invader receive collision event");
		if(contact.m_fixtureA.getUserData() instanceof Fire){
			Log.i("blacksheep","#fire è a");
			sendMessage("DEATH");
			unregister();
		}
		if(contact.m_fixtureB.getUserData() instanceof Fire){	
			Log.i("blacksheep","# fire è b");
			sendMessage("DEATH");
			unregister();
		}
		return true;	
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	
	

}

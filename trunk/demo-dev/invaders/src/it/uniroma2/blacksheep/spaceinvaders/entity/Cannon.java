package it.uniroma2.blacksheep.spaceinvaders.entity;

import java.util.HashMap;

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

public class Cannon extends GameEntity {
	
	private Bitmap cannon;
	
	private HashMap<String,Object> messageInfo;
	
	
	public Cannon(){
		cannon=getBitmap(R.drawable.cannon);
		messageInfo=new HashMap<String,Object>();
	}
	
	public boolean draw(Canvas canvas) {
		cannon=sizeBitmap(cannon);
		canvas.drawBitmap(cannon, getPointX(), getPointY(), null);

		return true;
	}
	
	public void receiveEvent(Event event){
		Log.i("blacksheep", "evento ricevuto §§§§§§§§§§§§§§§§§§§§§§§§ "+event.getMessage().getText());
		if("MOVELEFT".equals(event.getMessage().getText())){
			Log.i("blacksheep", "leftmove §§§§§§§§§§§§§§§§§§§§§§§§");
			moveEntity(getPointX()-3,getPointY());
		}
			
		if("MOVERIGHT".equals(event.getMessage().getText())){
			Log.i("blacksheep", "MOVERIGHT §§§§§§§§§§§§§§§§§§§§§§§§");
			moveEntity(getPointX()+3,getPointY());
		}
		
		if("CANNONFIRE".equals(event.getMessage().getText())){
			
			//event.setMessageInfo("POINTX",);
			
			messageInfo.put("POINTX", getPointX()+(getLengthX()/2));
			messageInfo.put("POINTY", getPointY());
			
			Log.i("blacksheep", "fire pountX "+getPointX()+" pointY "+getPointY());
			
			sendMessage("FIRE",messageInfo);
			
			//setPointY((Integer) event.getMessageInfo().get("POINTY"));
			
			//sendMessage("FIRE");
		}
		
	}

}

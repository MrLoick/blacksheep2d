package it.uniroma2.blacksheep.spaceinvaders.entity;

import java.util.HashMap;

import android.util.Log;

import it.uniroma2.blacksheep.spaceinvaders.R;
import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;

public class Grid extends GameEntity {
	
	private boolean left=true;
	
	private static HashMap<String, Object> messageInfoSX = new HashMap<String, Object>();
	private static HashMap<String, Object> messageInfoDX = new HashMap<String, Object>();
	private long moveTime =350;
	private long timeTiker=0;
	private long timeTikerSpeed=0;
	private long speedTime=20000;
	private boolean moveDown;
	private int baseAcc;
	private int baseAcc2;
	
	public Grid(){
		messageInfoSX.put("DELTAX", -5);
		messageInfoDX.put("DELTAX", 5);
	}

	
	public void mind() {
		long currentTime =System.currentTimeMillis();
		long diff = currentTime - timeTiker;
		long diffSpeed =currentTime-timeTikerSpeed;
		
		if(diffSpeed>=speedTime){
			Log.i("blacksheep","speed time changed");
			if(moveTime>100)
				moveTime=moveTime-50;
			timeTikerSpeed=currentTime;		
		}
		
		
		
		if (diff > moveTime) {
			
			baseAcc++;
			if(baseAcc==3){
				if(baseAcc2!=3){
					play(R.raw.base);
					baseAcc2++;
				}else{
					baseAcc2=0;
				}
				baseAcc=0;
			}
				
			if (left) {
				sendMessage("MOVE", messageInfoSX);
				if(moveDown){
					sendMessage("MOVEDOWN");
					moveDown=false;
				}
			} else {
				sendMessage("MOVE", messageInfoDX);
				if(moveDown){
					sendMessage("MOVEDOWN");
					moveDown=false;
				}
			}
			timeTiker=currentTime;
		}
	}

	public void receiveEvent(Event event) {

		if ("INVERTLEFT".equals(event.getMessage().getText())) {
			left = true;
			moveDown=true;			
		}
		if ("INVERTRIGHT".equals(event.getMessage().getText())) {
			left = false;
			moveDown=true;
		}
		

	}
	

}

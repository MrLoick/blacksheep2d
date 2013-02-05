package it.uniroma2.blacksheep.spaceinvaders.entity;

import java.util.HashMap;

import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;

public class Grid extends GameEntity {
	
	private boolean left=true;
	
	private int totalRow;
	private int totalColumn;
	private static HashMap<String, Object> messageInfoSX = new HashMap<String, Object>();
	private static HashMap<String, Object> messageInfoDX = new HashMap<String, Object>();
	private long moveTime =200;
	private long timeTiker=0;
	private boolean moveDown;
	
	public Grid(){
		messageInfoSX.put("DELTAX", -5);
		messageInfoDX.put("DELTAX", 5);
	}

	
	public void mind() {
		long currentTime =System.currentTimeMillis();
		long diff = currentTime - timeTiker;
		if (diff > moveTime) {
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
	
	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalColumn() {
		return totalColumn;
	}

	public void setTotalColumn(int totalColumn) {
		this.totalColumn = totalColumn;
	}

}
package it.uniroma2.framework.stage;

import java.util.ArrayList;

import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;
import it.uniroma2.framework.event.Message;
import it.uniroma2.framework.xml.SaxXmlParserStage;


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

public class Stage implements IStage {
	
	private ArrayList<GameEntity> gameEntityList;
	private Message message;
	private String id;

	public Stage(String message, String id){
		this.message=Message.get(message);
		this.id=id;
		gameEntityList=new ArrayList<GameEntity>();
		StageManager stageManager=StageManager.getIstance();
		stageManager.add(this);
	}
	
	
	public final void receiveEvent(Event event) {
		for(int i=0;i<gameEntityList.size();i++){
			gameEntityList.get(i).receiveEvent(event);
		}
	}
	
	public final void add(GameEntity gameEntity) {
		gameEntityList.add(gameEntity);
	}

	public final void remove(GameEntity gameEntity) {
		gameEntityList.remove(gameEntity);	
	}
	
	public final void clear(){
		gameEntityList.clear();
	}

	public final ArrayList<GameEntity> getGameEntity() {
		return gameEntityList;
	}
	
	public void onLoad(){
		(new SaxXmlParserStage(id+".xml",this)).parse();
	}


	public Message getKey() {
		return message;
	}

}

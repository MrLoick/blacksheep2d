package it.uniroma2.framework.stage;

import java.util.ArrayList;
import java.util.HashMap;

import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;
import it.uniroma2.framework.event.IPerceptor;
import it.uniroma2.framework.event.Message;
import it.uniroma2.framework.input.TouchManager;
import it.uniroma2.framework.mind.MindManager;
import it.uniroma2.framework.render.Render;

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

public class StageManager implements IPerceptor {
	
	private IStage stage;
	
	private HashMap<Message ,IStage> stageMap;
	
	private static StageManager stageManager;
	
	
	private StageManager(){
		stageMap=new HashMap<Message ,IStage>();		
	}
	
	public static StageManager getIstance(){
		if(stageManager==null)
			stageManager=new StageManager();
		return stageManager;
		
	}

	public void receiveEvent(Event event) {
		ArrayList<GameEntity> gameEntityList;
		// verifica se l'evento preve un cambio di livello
		if ((stageMap.get(event.getMessage())) != null) {
			//Log.i("blacksheep", "stageManager registrazione componenti "
			//		+ event.getMessage());
			// unregister GameEntity from stage for stage change
			gameEntityList = stage.getGameEntity();
			for (int i = 0; i < gameEntityList.size(); i++) {
				gameEntityList.get(i).unregister();
			}
			// set new stage
			TouchManager.getIstance().clear();
			MindManager.getIstance().clear();
			//RenderReference.getIstance().getRender().clearDrawable();
			Render.getIstance().clearDrawable();
			stage.clear();
			stage = stageMap.get(event.getMessage());
			stage.onLoad();
			gameEntityList = stage.getGameEntity();
			for (int i = 0; i < gameEntityList.size(); i++) {
				gameEntityList.get(i).register();
			}
		} else {
			stage.receiveEvent(event);
		}
	}
	
	public void add(IStage stage){
		//stage.getKey()
		if(this.stage==null)
			this.stage=stage;
		stageMap.put(stage.getKey(), stage);
		
		//Log.i("register", "stage size"+stageMap.size());
	}
	
	public void remove(IStage stage){
		stageMap.remove(stage.getKey());
	}
		
			
		
}

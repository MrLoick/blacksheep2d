package it.uniroma2.framework.input;

import it.uniroma2.framework.entity.IKeyInput;

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

import java.util.ArrayList;

import android.view.KeyEvent;

public class KeyManager {
	
private static KeyManager keyManager;
	
	private ArrayList<IKeyInput> keyInputList;

	private KeyManager(){
		keyInputList=new ArrayList<IKeyInput>();
	}
	
	public static KeyManager getIstance(){
		if(keyManager==null)
			keyManager=new KeyManager();		
		return keyManager;
	}
	
	public void add(IKeyInput keyInput){
		if(keyInput.onKeyDown(0, null))
			keyInputList.add(keyInput);
	}
	
	public void remove(IKeyInput keyInput){
		keyInputList.remove(keyInput);
	}
	
	 public boolean onKeyDown(int keyCode, KeyEvent msg) {
		for(int i=0;i<keyInputList.size();i++){
			keyInputList.get(i).onKeyDown(keyCode, msg);
		}
		return true;
	}

}

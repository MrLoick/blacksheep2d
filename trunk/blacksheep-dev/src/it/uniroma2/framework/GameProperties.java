package it.uniroma2.framework;



import java.io.IOException;
import java.util.Properties;

import android.content.res.AssetManager;
import android.content.res.Resources;


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


public class GameProperties {
	
	private Properties properties;
	
	private static GameProperties gameProperties;
	
	private GameProperties() {

		
		//AssetManager assetManager = null;
		// InputStream rawResource = ContextReference.getIstance().getContext().getResources().openRawResource(R.raw.props);
		
		Resources resources = Game.getContext().getResources();
		AssetManager assetManager = resources.getAssets();
		
		properties = new Properties();
		try {
			
			//InputStream inputStream = assetManager.open("props.properties");
			properties.load(assetManager.open("props.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Log.i("blacksheep", "property PI" + properties.get("PI"));
		//Log.i("blacksheep", "property SCREEN" + properties.get("SCREEN_ORIENTATION"));
		//if(properties.get("SCREEN_ORIENTATION").equals("PORTRAIT"));
		//if(properties.get("SCREEN_ORIENTATION").equals("LANDSCAPE"))
			//properties.setProperty("SCREEN_ORIENTATION",ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
	}
	
	
	public static GameProperties getIstance(){
		if(gameProperties==null)
			gameProperties=new GameProperties();
		
		return gameProperties;
	}

	public Properties getPropeties() {
		return properties;
	}

}

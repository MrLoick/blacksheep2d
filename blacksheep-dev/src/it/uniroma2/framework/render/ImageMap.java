package it.uniroma2.framework.render;

import it.uniroma2.framework.Game;

import java.util.HashMap;

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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageMap {
	
	private static ImageMap imageMap;
	
	private HashMap<Integer, Bitmap> bitmapMap;
	
	private ImageMap(){
		bitmapMap=new HashMap<Integer, Bitmap>();
	}
	
	public static ImageMap getIstance(){
		if(imageMap==null)
			imageMap=new ImageMap();
		return imageMap;
	}
	
	public void load(int resId){
		Bitmap bitmap=BitmapFactory.decodeResource(Game.getContext().getResources(), resId );
		bitmapMap.put(resId, bitmap);		
	}
	
	public Bitmap getBitmap(int resId){
		return bitmapMap.get(resId);
	}

}

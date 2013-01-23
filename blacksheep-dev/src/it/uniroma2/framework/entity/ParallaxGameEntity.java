package it.uniroma2.framework.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;


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

public class ParallaxGameEntity extends GameEntity {
	
	private Bitmap bitmap;		// the animation sequence
	//private Rect sourceRectA;	// the rectangle to be drawn from the animation bitmap
	//private Rect sourceRectB;
	//private int frameNr;		// number of frames in animation
	//private int currentFrame;	// the current frame
	private long frameTicker;	// the time of the last frame update
	private int framePeriod;	// milliseconds between each frame (1000/fps)
	
	//private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
	//private int spriteHeight;	// the height of the sprite
	private int newX=0;
	private int bitmapMoveX=0;
	private int loop;			//milliseconds for loop the bitmap
	
	
	
	public ParallaxGameEntity() {
		//this.bitmap = bitmap;
		
		//currentFrame = 0;
		//frameNr = frameCount;
		///spriteWidth = width;//bitmap.getWidth() ;
		//spriteHeight = height; //bitmap.getHeight();
		//sourceRectA = new Rect(0, 0, spriteWidth, spriteHeight);
		//sourceRectB=new Rect(0, 0, 0, spriteHeight);
		framePeriod = 1000 / 5;
		frameTicker = 0l;
		
	}
	
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


	
	// the draw method which draws the corresponding frame
	public final boolean draw(Canvas canvas) {
			
		bitmap=sizeBitmap(bitmap);
		//canvas.drawBitmap(bitmap, getPointX(), getPointY(), null);
		
		//long gameTime=System.currentTimeMillis();
		long currentTime =System.currentTimeMillis();
		
		long diff =currentTime-frameTicker;
		
		//calculate scroll
		float scroll=getLengthX()*((float)(diff)/(float)(loop));
		
		//Log.i("blacksheep", "cmt "+currentTime +" frameTicker "+frameTicker+" diff "+diff+" scroll "+scroll);
		
		frameTicker=currentTime;

		
		bitmapMoveX = (int) (bitmapMoveX - scroll);
			
		newX = bitmap.getWidth() - (-bitmapMoveX);
		
		
		 // if we have scrolled all the way, reset to start
        if (newX <= 0) {
            bitmapMoveX = 0;
            // only need one draw
            canvas.drawBitmap(bitmap, bitmapMoveX, getPointY(), null);

        } else {
            // need to draw original and wrap
            canvas.drawBitmap(bitmap, bitmapMoveX,getPointY(), null);
            canvas.drawBitmap(bitmap, newX, getPointY(), null);
        }
		
        
        return true;
	}


	public int getLoop() {
		return loop;
	}


	public void setLoop(int loop) {
		this.loop = loop;
	}

}

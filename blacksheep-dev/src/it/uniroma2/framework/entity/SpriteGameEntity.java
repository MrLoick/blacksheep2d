package it.uniroma2.framework.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


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

public class SpriteGameEntity extends GameEntity {
	
	private Bitmap bitmap;		// the animation sequence
	private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
	private Rect destRect;		// the rectangle to do draw the animation
	private int frameNr;		// number of frames in animation
	private int currentFrame;	// the current frame
	private long frameTicker;	// the time of the last frame update
	private int framePeriod;	// milliseconds between each frame (1000/fps)
	private int fps;			// frame per second of animation
	
	
	
	private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
	//private int spriteHeight;	// the height of the sprite
	
	//private boolean run;		//true run animation false draw frist image
	
	
	
	public SpriteGameEntity(){
		currentFrame = 0;
		//sotto entrambi i valori sono 0 non usabili 
		//sourceRect = new Rect(0,0,41,60);
		sourceRect=new Rect();
		//framePeriod = 1000 / 1;
		destRect = new Rect();
		frameTicker = 01;
		//frameNr=5;
		
	}
	
	
	
	public boolean draw(Canvas canvas) {
		//Log.i("blacksheep", "draw sprite");
		
		//update(System.currentTimeMillis());
		
		update(System.currentTimeMillis());		
		
		
		//Log.i("blacksheep", "scale bitmap parameter lengthX "+getLengthX()+" lengthY "+getLengthY() +" frame number"+ frameNr);
		
		bitmap=Bitmap.createScaledBitmap(bitmap, getLengthX()*frameNr, getLengthY()  , true);
		//Log.i("blacksheep", "draw scaled");
		//Log.i("blacksheep", "bitmap height "+bitmap.getHeight()+" bitmap width"+bitmap.getWidth());
		
		destRect.set(getPointX(), getPointY(), getPointX() + getLengthX(), getPointY() + getLengthY());
		if (getAngle() != 0)
			canvas.rotate(getAngle(), getPointX() + (getLengthX() / 2),	getPointY() + (getLengthY() / 2));

		//Log.i("blacksheep", "surce rect"+sourceRect);
		//Log.i("blacksheep", "dest Rect"+ destRect);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		
		
		//canvas.drawBitmap(bitmap, 0, 0, null);

		return true;
	}
	
	private void update(long gameTime) {
		framePeriod=1000/fps;
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
				sourceRect.set(0,0,getLengthX(),getLengthY());
			}
		}
		// define the rectangle to cut out sprite
		spriteWidth = bitmap.getWidth() / frameNr;
		//sourceRect.top=getLengthX();
		//sourceRect.bottom=getLengthY();
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public int getFrameNr() {
		return frameNr;
	}
	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
	
	public Rect getSourceRect() {
		return sourceRect;
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}

}

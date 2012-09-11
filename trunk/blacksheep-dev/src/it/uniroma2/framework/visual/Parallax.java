package it.uniroma2.framework.visual;

import it.uniroma2.framework.entity.GameEntity;
import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Parallax {
	
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
	private int bgMoveX=0;
	
	private boolean run;		//true run animation false draw frist image
	
	public Parallax(Bitmap bitmap/*, int width, int height*/) {
		this.bitmap = bitmap;
		
		//currentFrame = 0;
		//frameNr = frameCount;
		///spriteWidth = width;//bitmap.getWidth() ;
		//spriteHeight = height; //bitmap.getHeight();
		//sourceRectA = new Rect(0, 0, spriteWidth, spriteHeight);
		//sourceRectB=new Rect(0, 0, 0, spriteHeight);
		framePeriod = 1000 / 5;
		frameTicker = 0l;
		run=false;
	}
	
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

/*	public int getFrameNr() {
		return frameNr;
	}
	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}
	*/
	public int getFramePeriod() {
		return framePeriod;
	}
	public void setFramePeriod(int framePeriod) {
		this.framePeriod = framePeriod;
	}
	
	
	public void setRun(boolean run){
		this.run=run;
	}
	
	private void update(long gameTime) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			/*currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}*/
		}
		// define the rectangle to cut out sprite
		//this.sourceRect.left = currentFrame * spriteWidth;
		//this.sourceRect.right = this.sourceRect.left + spriteWidth;
		//sourceRectA.left=sourceRectA.left+5;
		
		//sourceRectB.left=;
	}
	
	// the draw method which draws the corresponding frame
	public void draw(Canvas canvas, GameEntity gameEntity, boolean run) {
		if(run){
			update(System.currentTimeMillis());
		}
		bgMoveX = bgMoveX - 1;
		
		newX = bitmap.getWidth() - (-bgMoveX);
		
		 // if we have scrolled all the way, reset to start
        if (newX <= 0) {
            bgMoveX = 0;
            // only need one draw
            canvas.drawBitmap(bitmap, bgMoveX, gameEntity.getPointY(), null);

        } else {
            // need to draw original and wrap
            canvas.drawBitmap(bitmap, bgMoveX, gameEntity.getPointY(), null);
            canvas.drawBitmap(bitmap, newX, gameEntity.getPointY(), null);
        }
		// where to draw the sprite
		/*Rect destRect = new Rect(gameEntity.getPointX(), gameEntity.getPointY(), gameEntity.getPointX() + gameEntity.getLengthX(),gameEntity.getPointY() + gameEntity.getLengthX());
		
		
		canvas.drawBitmap(bitmap, sourceRectA, destRect, null);
		canvas.drawBitmap(bitmap, sourceRectB, destRect, null);*/
	}
	

}

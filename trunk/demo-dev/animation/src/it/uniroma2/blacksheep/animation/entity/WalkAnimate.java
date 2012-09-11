package it.uniroma2.blacksheep.animation.entity;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import it.uniroma2.blacksheep.animation.R;
import it.uniroma2.framework.Game;
import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.visual.Sprite;

/***************************************************************************
 * @author Valentino Colatosti
 * 
 * Copyright (C) 2012 valentino.colatosti@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


public class WalkAnimate extends GameEntity{
	
	private Sprite sprite;
	
	public WalkAnimate(){
		
		//setAngle(30);
		
		sprite = new Sprite(
				BitmapFactory.decodeResource(Game.getContext().getResources(), R.drawable.walk) 
				, 30, 47	// width and height of sprite
				, 5);	// FPS and number of frames in the animation
		
		
		
	}

	
	public boolean draw(Canvas canvas){	
		//Log.i("box2d","pointx: "+getPhysicsBody().getPosition().x+" pointy"+ getPhysicsBody().getPosition().y+" angle: "+getPhysicsBody().getAngle());
		/*invader=resizeBitmap(invader, getLengthX(), getLengthY());
        canvas.save();
        canvas.rotate(getAngle(),getPointX()+(getLengthX()/2),getPointY()+(getLengthY()/2));
        canvas.drawBitmap(invader, getPointX(), getPointY(), null);
		canvas.restore();
		*/
		
		if(sprite!=null)
			sprite.draw(canvas,this,true);
		//canvas.drawBitmap(invader, getPointX(), getPointY(), null);
		return true;
	}
	
	
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	

}

package it.uniroma2.blacksheep.physics.entity;

import org.jbox2d.dynamics.contacts.Contact;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import it.uniroma2.blacksheep.physics.R;
import it.uniroma2.framework.entity.GameEntity;

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

public class PhysicsBall extends GameEntity {
	
	private Bitmap ball;
	//private Vec2 touchPoint;
		
	public PhysicsBall(){
		
		ball=getBitmap(R.drawable.ball);
		
	}
	
	
	public boolean draw(Canvas canvas){	
		//Log.i("box2d","pointx: "+getPhysicsBody().getPosition().x+" pointy"+ getPhysicsBody().getPosition().y+" angle: "+getPhysicsBody().getAngle());
		ball=sizeBitmap(ball);
        //canvas.save();
        //canvas.rotate(getAngle(),getPointX()+(getLengthX()/2),getPointY()+(getLengthY()/2));
        canvas.drawBitmap(ball, getPointX(), getPointY(), null);
		//canvas.restore();
		
		//canvas.drawBitmap(invader, getPointX(), getPointY(), null);
		return true;
	}
	
	public boolean receiveCollisionEvent(Contact contact){
		Log.i("blacksheep", "ball receive a contact");
		
		
		return true;
	}

}

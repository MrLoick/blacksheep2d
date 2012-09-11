package it.uniroma2.blacksheep.physics.entity;


import it.uniroma2.blacksheep.physics.R;
import it.uniroma2.framework.entity.GameEntity;
import android.graphics.Bitmap;
import android.graphics.Canvas;

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

public class Ground extends GameEntity {
	
	private Bitmap ground;
	
	public Ground(){
		//paint=new Paint();
		ground=getBitmap(R.drawable.ground);
		
		
	}
	

	public boolean draw(Canvas canvas) {
		
		ground=sizeBitmap(ground);
        canvas.save();
        canvas.rotate(getAngle(),getPointX()+(getLengthX()/2),getPointY()+(getLengthY()/2));
        canvas.drawBitmap(ground, getPointX(), getPointY(), null);
		canvas.restore();
		
		return true;
	}

}

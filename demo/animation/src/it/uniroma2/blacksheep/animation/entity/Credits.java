package it.uniroma2.blacksheep.animation.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import it.uniroma2.blacksheep.animation.R;
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


public class Credits extends GameEntity {
	
	private Bitmap credit;
	
	public Credits(){
		
		credit=getBitmap(R.drawable.credits);
		
	}
	
public boolean draw(Canvas canvas) {
		
		credit=sizeBitmap(credit);
		canvas.drawBitmap(credit, getPointX(), getPointY(), null);

		return true;
	}

}

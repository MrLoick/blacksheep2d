package it.uniroma2.blacksheep.spaceinvaders.entity;

import it.uniroma2.blacksheep.spaceinvaders.R;
import it.uniroma2.framework.entity.GameEntity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

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


public class FireButton extends GameEntity{
	
	private Bitmap fireButton;

	public FireButton() {
		fireButton = getBitmap(R.drawable.firebutton);
	}

	public boolean draw(Canvas canvas) {
		fireButton=sizeBitmap(fireButton);
		canvas.drawBitmap(fireButton, getPointX(), getPointY(), null);

		return true;
	}

	public boolean onTouchEvent(MotionEvent motionEvent) {
		if (motionEvent != null) {

			int tx = (int) motionEvent.getX();
			int ty = (int) motionEvent.getY();

			if (tx >= getPointX() && tx <= getPointX() + getLengthX() && ty >= getPointY() && ty <= getPointY() + getLengthY()
					&& MotionEvent.ACTION_UP == motionEvent.getAction()) {
				sendMessage("CANNONFIRE");
			}

		}
		return true;
	}
	
	
}

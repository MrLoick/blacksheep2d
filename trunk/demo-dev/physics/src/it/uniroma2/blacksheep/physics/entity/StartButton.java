package it.uniroma2.blacksheep.physics.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
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

public class StartButton extends GameEntity {
	
	private Bitmap startButton;
	
	private boolean touchFlag;

	public StartButton() {
		startButton = getBitmap(R.drawable.startbutton);
		touchFlag=false;
	}

	public boolean draw(Canvas canvas) {
		startButton=sizeBitmap(startButton);
		canvas.drawBitmap(startButton, getPointX(), getPointY(), null);

		return true;
	}

	public boolean onTouchEvent(MotionEvent motionEvent) {
		if (motionEvent != null) {

			int tx = (int) motionEvent.getX();
			int ty = (int) motionEvent.getY();
			
			if (tx >= getPointX() && tx <= getPointX() + getLengthX() && ty >= getPointY() && ty <= getPointY() + getLengthY()
					&& MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
				touchFlag=true;
			}

			if (touchFlag){

				if (tx >= getPointX() && tx <= getPointX() + getLengthX() && ty >= getPointY() && ty <= getPointY() + getLengthY()
						&& MotionEvent.ACTION_UP == motionEvent.getAction()) {
					play(R.raw.click);
					sendMessage("STARTGAME");
				}
			}

		}
		return true;
	}

}

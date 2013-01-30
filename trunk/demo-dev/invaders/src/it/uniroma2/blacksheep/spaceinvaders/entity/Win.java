package it.uniroma2.blacksheep.spaceinvaders.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import it.uniroma2.blacksheep.spaceinvaders.R;
import it.uniroma2.framework.entity.GameEntity;

public class Win extends GameEntity {
	
private Bitmap win;
	
	public Win() {
		win=getBitmap(R.drawable.win);
	}
	
	public boolean draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		win=sizeBitmap(win);
		canvas.drawBitmap(win, getPointX(), getPointY(), null);

		return true;
	}

}

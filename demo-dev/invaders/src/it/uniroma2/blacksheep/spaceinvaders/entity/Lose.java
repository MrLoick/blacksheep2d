package it.uniroma2.blacksheep.spaceinvaders.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import it.uniroma2.blacksheep.spaceinvaders.R;
import it.uniroma2.framework.entity.GameEntity;

public class Lose extends GameEntity {
	
private Bitmap lose;
	
	public Lose() {
		lose=getBitmap(R.drawable.lose);
	}
	
	public boolean draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		lose=sizeBitmap(lose);
		canvas.drawBitmap(lose, getPointX(), getPointY(), null);

		return true;
	}

}

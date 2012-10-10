package it.uniroma2.blacksheep.animation.entity;

import android.graphics.Bitmap;
import it.uniroma2.blacksheep.animation.R;
import it.uniroma2.framework.entity.ParallaxGameEntity;

public class Cactus extends ParallaxGameEntity {
	
private Bitmap backgroundCactus;
	
	public Cactus(){
		
		backgroundCactus=getBitmap(R.drawable.parallax_background_layer_front);
		
		setBitmap(backgroundCactus);
		
	}

}

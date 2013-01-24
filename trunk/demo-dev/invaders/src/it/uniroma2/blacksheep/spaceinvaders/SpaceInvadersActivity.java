package it.uniroma2.blacksheep.spaceinvaders;

import it.uniroma2.framework.Game;

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


public class SpaceInvadersActivity extends Game {
	/** Called when the activity is first created. */

	
	public void resources() {

		// load all game bitmap
		loadImage(R.drawable.startbutton);
		loadImage(R.drawable.creditsbutton);
		loadImage(R.drawable.exitbutton);
		loadImage(R.drawable.title);
		loadImage(R.drawable.firebutton);
		loadImage(R.drawable.leftbutton);
		loadImage(R.drawable.cannon);
		loadImage(R.drawable.rightbutton);
		loadImage(R.drawable.invaders1);
		loadImage(R.drawable.credits);
		loadImage(R.drawable.okbutton);
		loadImage(R.drawable.fire);
		
		loadAudio(R.raw.click);
		loadAudio(R.raw.fire);
	}

}
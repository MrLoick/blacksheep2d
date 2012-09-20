package it.uniroma2.blacksheep.spaceinvaders;

import it.uniroma2.framework.Game;
import it.uniroma2.framework.render.ImageMap;

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
		ImageMap image = ImageMap.getIstance();
		image.load(R.drawable.startbutton);
		image.load(R.drawable.creditsbutton);
		image.load(R.drawable.exitbutton);
		image.load(R.drawable.title);
		image.load(R.drawable.firebutton);
		image.load(R.drawable.leftbutton);
		image.load(R.drawable.cannon);
		image.load(R.drawable.rightbutton);
		image.load(R.drawable.invaders1);
		image.load(R.drawable.credits);
		image.load(R.drawable.okbutton);
		image.load(R.drawable.fire);
	}

}
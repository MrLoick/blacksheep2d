package it.uniroma2.blacksheep.animation;



import android.util.Log;
import it.uniroma2.blacksheep.animation.R;
import it.uniroma2.framework.Game;
import it.uniroma2.framework.ImageMap;
import it.uniroma2.framework.audio.SoundClip;

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

public class BlackSheepDemoAnimationActivity extends Game {
	
	public void resources(){
		ImageMap imageMap=ImageMap.getIstance();
		imageMap.load(R.drawable.startbutton);
		imageMap.load(R.drawable.creditsbutton);
		imageMap.load(R.drawable.exitbutton);
		imageMap.load(R.drawable.parallax_background_layer_back);
		imageMap.load(R.drawable.parallax_background_layer_front);
		imageMap.load(R.drawable.parallax_background_layer_mid);
		imageMap.load(R.drawable.walk);
		imageMap.load(R.drawable.credits);	
		imageMap.load(R.drawable.okbutton);
		imageMap.load(R.drawable.horse);
		
		SoundClip soundClip=SoundClip.getIstance();
		soundClip.load(R.drawable.click);
	
	}

	
}
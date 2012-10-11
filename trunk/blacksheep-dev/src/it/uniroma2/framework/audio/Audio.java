package it.uniroma2.framework.audio;

import java.util.HashMap;

/*******************************************************************************
 * 
 * @author Valentino Colatosti
 * 
 * Copyright (C) 2012 valentino.colatosti@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

import it.uniroma2.framework.Game;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Audio {
	
	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap;
	
	private static Audio soundClip;
	
	private Context context;
	private AudioManager audioManager;
	
	private Audio(){
		soundPool=new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		//ContextReference contextReference = ContextReference.getIstance();
		context= Game.getContext();
		soundPoolMap=new HashMap<Integer, Integer>();
		audioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
	}
	
	public static Audio getIstance(){
		if(soundClip==null)
			soundClip= new Audio();
		return soundClip;
		
	}
	
	/*
	public SoundPool getSoundPool(){
		return soundPool;
		
	}*/
	
	public void load(int resId){
		int soundId=soundPool.load(context, resId, 1);
		soundPoolMap.put(resId,soundId);
	}
	
	public void play(int resId){
		int soundId=soundPoolMap.get(resId);
		float volume= (float) audioManager.getStreamVolume(AudioManager.STREAM_RING);
		soundPool.play(soundId, volume , volume, 1, 0, 1);
	}
	
	public void playLoop(int resId){
		int soundId=soundPoolMap.get(resId);
		float volume= (float) audioManager.getStreamVolume(AudioManager.STREAM_RING);
		soundPool.play(soundId, volume, volume , -1, 0, 1);
	}
	
	public void playLoop(int resId, int loop){
		int soundId=soundPoolMap.get(resId);
		soundPool.play(soundId, 1 , 1, loop, 0, 1);
	}
	
/*	public void autoPause(){
		soundPool.autoPause();
	}
	
	public void autoResume(){
		soundPool.autoResume();
	}*/
	

}

package it.uniroma2.framework.render;


import it.uniroma2.framework.input.KeyManager;
import it.uniroma2.framework.input.TouchManager;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



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

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	
	private Render render;
	
	private TouchManager touchManager=TouchManager.getIstance();
	
	private Thread renderThread;

	public GameView(Context context) {
		super(context);
				
		getHolder().addCallback(this);
		render=Render.getIstance();
		setFocusable(true);
		setFocusableInTouchMode(true);
				
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		renderThread=new Thread(render);
		render.setSurfaceHolder(holder);
		render.setRun(true);
		renderThread.start();
		
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
		render.setRun(false);
		//render.interrupt();
		render.clearDrawable();

	}
	
/*	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent msg) {
		 KeyManager keyManager=KeyManager.getIstance();
		 keyManager.onKeyDown(keyCode, msg);
      return true;
  }
	*/ 
 
	
	 public boolean onTouchEvent(MotionEvent motionEvent){

		touchManager.onTouchEvent(motionEvent);
		
		return true;
		
	 }
	
}

package it.uniroma2.framework.render;


import it.uniroma2.framework.input.KeyManager;
import it.uniroma2.framework.input.TouchManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Canvas;
//import android.util.AttributeSet;
import android.test.TouchUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;


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
	
	//private int width;
	//private int height;

	public GameView(Context context) {
		super(context);
		//getHolder().addCallback(this);
		
		//surfaceCreated(getHolder());
		
		getHolder().addCallback(this);
		this.render = new Render(getHolder()/*, getContext()*/);
		
		Log.i("game", "GameView1");
		setFocusable(true);
		setFocusableInTouchMode(true);
	/*	LayoutParams layoutParams=new LayoutParams(context);
		//LayoutParams.FILL_PARENT;
		setLayoutParams(layoutParams);*/
			
	}
	
	
	public Render getRender(){
		return render;
	}
	

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i("game", "surfaceChanged");
		//Log.i("bitmap","game view width "+width+" height "+height);
		 //render.setSurfaceSize(width, height);
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		render.setRun(true);
		render.start();
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
		render.setRun(false);
		render.interrupt();

	}
	
/*	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent msg) {
		 KeyManager keyManager=KeyManager.getIstance();
		 keyManager.onKeyDown(keyCode, msg);
      return true;
  }
	*/ 
 
	
	 public boolean onTouchEvent(MotionEvent motionEvent){
		//int pointerCount = motionEvent.getPointerCount();
		int id;
		MotionEvent event;
	
		TouchManager touchManager=TouchManager.getIstance();
		touchManager.onTouchEvent(motionEvent);
		//Log.i("input", "pointerCount"+pointerCount);
		
		return true;
		
	 }
	 

/*public boolean onTouch(View v, MotionEvent event) {
	
	
	TouchManager touchManager=TouchManager.getIstance();
	invalidate();
	return touchManager.onTouchEvent(event);
}
	*/
   /* public void onDraw(Canvas canvas) {
    	canvas.drawColor(Color.BLACK);
    }
*/
}

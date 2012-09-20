package it.uniroma2.framework.render;

import it.uniroma2.framework.input.KeyManager;
import it.uniroma2.framework.input.TouchManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameGLSurfaceView extends GLSurfaceView {
	
	private GLRenderer glRenderer;

	public GameGLSurfaceView(Context context) {
		super(context);
		
		getHolder().addCallback(this);
		glRenderer = new GLRenderer(getHolder()/*, getContext()*/);
		
		Log.i("game", "GameView1");
		setFocusable(true);
		setFocusableInTouchMode(true);
		
	}
	
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent msg) {
		 KeyManager keyManager=KeyManager.getIstance();
		 keyManager.onKeyDown(keyCode, msg);
      return true;
	 }
	 
 
	 
	 public boolean onTouchEvent(MotionEvent motionEvent){
		TouchManager touchManager=TouchManager.getIstance();
		return touchManager.onTouchEvent(motionEvent);
		
	 }


}

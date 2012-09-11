package it.uniroma2.framework;

import java.util.ArrayList;


import it.uniroma2.framework.entity.IDrawable;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.SurfaceHolder;

public class GLRenderer implements Renderer{
	
	private ArrayList<IDrawable> drawableList;
	private SurfaceHolder surfaceHolder;
	//private boolean run=false;
	private Paint paint;
	private long startTime;
	private long elapsed;
	private boolean run=false;
	
	public GLRenderer(SurfaceHolder surfaceHolder/*, Context context,Handler handler*/){
		Log.i("game", "render");
		this.surfaceHolder= surfaceHolder;
		//this.context=context;
		
		drawableList=new ArrayList<IDrawable>();
		
	}
	
	

	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		
		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Reset the Modelview Matrix
		gl.glLoadIdentity();
	
		
		
		
		startTime = System.currentTimeMillis();
		//canvas.drawColor(Color.CYAN);
		for(int i=0;i<drawableList.size();i++){
			//if(!drawableList.get(i).draw(gl))
				remove(drawableList.get(i));
		}
		
		elapsed = System.currentTimeMillis() - startTime;
		//canvas.drawText("FPS: "+Math.round(1000f / elapsed) , 10, 10, paint);
		startTime = System.currentTimeMillis();
		
	}
	
	

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}
	
	

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Load the texture for the square
		//square.loadGLTexture(gl, this.context);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

	}
	
	public void add(IDrawable drawable){
		drawableList.add(drawable);
		Log.i("register", "drawable size "+drawableList.size());
	}
	
	public void remove(IDrawable drawable){
		drawableList.remove(drawable);
	}
	
	public void clearDrawable(){
		drawableList.clear();
	}

}

package it.uniroma2.framework.render;

import java.text.DecimalFormat;
import java.util.ArrayList;

import it.uniroma2.framework.entity.IDrawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;



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

public class Render extends Thread {
	
	private SurfaceHolder surfaceHolder;

	private ArrayList<IDrawable> drawableList;
	
	private boolean run=false;
	
	private Paint paint;
	
	//private static Render render;
	
	public Render(SurfaceHolder surfaceHolder){
		
		this.surfaceHolder=surfaceHolder;
		drawableList=new ArrayList<IDrawable>();
		paint =new Paint();
		paint.setColor(Color.WHITE);
		
	}
	
	/*private Render(){
		
	}
	
	public static Render getIstance(SurfaceHolder surfaceHolder){
		if(render==null)
			render=new Render(surfaceHolder);
		return render;
	}*/
		
		
	public void run() {
		while (run) {
			Canvas canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {

					canvas.drawColor(Color.WHITE);
					for(int i=0;i<drawableList.size();i++){
						
						try{
						if(!drawableList.get(i).draw(canvas))
							remove(drawableList.get(i));
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}				
				
				}

			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);

				}
			}
		}
	}
            
	

	public void setRun(boolean run) {
		this.run=run;		
	}
	
	public void add(IDrawable drawable){
		drawableList.add(drawable);
	}
	
	public void remove(IDrawable drawable){
		drawableList.remove(drawable);
	}
	
	public void clearDrawable(){
		drawableList.clear();
	}
	
		
	/*public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
	}*/

	
		
}


package it.uniroma2.framework.entity;

import android.graphics.Canvas;

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

public interface IDrawable {
	
	//public void draw(/*Graphics frame*/);
	
	public boolean draw(Canvas canvas);
	
	public int getPointX();
	
	public int getPointY();
	
	public int getLengthX();
	
	public int getLengthY();
	
	public void setPointX(int pointX);
	
	public void setPointY(int pointY);
	

}

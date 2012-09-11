package it.uniroma2.framework;


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


public class RenderReference {
	
	private Render render;
	
	private static RenderReference renderReference;
	
	private RenderReference(){
				
	}
	
	public static RenderReference getIstance(){
		if(renderReference==null){
			renderReference=new RenderReference();
		}
		return renderReference;
	}
	
	public void setRender(Render render){
		this.render=render;
	}
	
	public Render getRender(){
		return render;
	}

}

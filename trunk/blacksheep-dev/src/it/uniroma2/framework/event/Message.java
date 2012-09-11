package it.uniroma2.framework.event;

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

public class Message {	
	
	
	private static HashMap<String, Message> messageMap=new HashMap<String, Message>();
	
	public static Message get(String message){
		return messageMap.get(message);
	}

    private final String message;   
   
	public Message(String message)
	{
		this.message=message;
		messageMap.put(message, this);
	}
	
	public String getText() {return this.message;}
	
	
	public boolean equals(Object o){
		boolean flag=false;
		if(message.equals(((Message)o).getText())){
			flag=true;
		}
		return flag;
	}
}

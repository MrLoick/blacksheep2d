package it.uniroma2.framework.event;

import java.util.ArrayList;

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

public class EventFactory {

	private static EventFactory istance=null;
	private ArrayList<Event> events;
	private int numOfEvent=10;

	private EventFactory()
	{	
		this.events=new ArrayList<Event>();
		for(int i=0; i<numOfEvent; i++ )
		{
			this.events.add(new Event());
		}
	}
	
	/**
	 * 
	 * @return l'istanza unica della classe eventFactory
	 */
	public static EventFactory getIstance()
	{
		if(istance==null)
			istance=new EventFactory();
		return istance;
	}
	
	/**
	 * rende di nuovo disponibile un evento non più necessario
	 * @param event evento da rendere nuovamente disponibile
	 */
	public void releaseEvent(Event event)
	{
		event.clear();
		if(events.size()<numOfEvent)
			events.add(event);
	}
	
	/**
	 * 
	 * @return ritorna un evento non utilizzato a chi ne fa richiesta
	 */
	public Event getEvent()
	{
		Event event;
		if(events.size()==0)
			event=new Event();
		else
			{
				event=this.events.get(events.size()-1);
				events.remove(events.size()-1);
			}
		return event;
	}
	
}


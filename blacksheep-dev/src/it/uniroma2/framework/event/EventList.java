package it.uniroma2.framework.event;

import java.util.ArrayList;
import java.util.Timer;

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

public class EventList {

	private ArrayList<Event> eventList;
	//private long nextEventDelay;
	//private Timer timer;


	public EventList(){
		this.eventList=new ArrayList<Event>();
	}
	/**
	 * memorizza gli eventi in attesa di esecuzione
	 * @param event evento da memorizzare
	 */
	public void storeEvent(Event event) 
	{		
		/*if (event.getDelay()>=0)
		{
			if(event.getDelay()!=0)
			{
				boolean posizioneTrovata=false;
				int i=0;
				while(i<eventList.size()||posizioneTrovata==true)
				{
					if(((Event)eventList.get(i)).getDelay()>event.getDelay())
					{
						eventList.add(i,event);
						posizioneTrovata=true;
					}
					i++;
				}
				if(posizioneTrovata==false)
					eventList.add(event);
			}else
			{
				this.eventList.add(0,event);
			}*/
			
			//setNextEventDelay();
			
			//System.out.println("rtSimulationKernel.EventList | storeEvent -> eventDelay: "+getNextEventDelay());
			//EventTaskScheduler ets = new EventTaskScheduler();
			//timer = new Timer();
			//timer.schedule(ets, getNextEventDelay());
		//}
		eventList.add(event);
	}

	/**
	 * 
	 * @return ritorna il prossimo evento da eseguire 
	 */
	public Event getNextEvent()
	{
		return eventList.get(0);
	}


	public Event releaseNextEvent()
	{
		Event event = eventList.get(0);
		eventList.remove(0);
		return event;
	}
	/**
	 * 
	 * @return next event delay to wait.
	 */
	/*private long getNextEventDelay()
	{
		return this.nextEventDelay;
	}	

	private void setNextEventDelay()
	{
		this.nextEventDelay = getNextEvent().getDelay();
	}*/
}

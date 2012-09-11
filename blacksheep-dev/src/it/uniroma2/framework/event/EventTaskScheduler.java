package it.uniroma2.framework.event;

import java.util.TimerTask;

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


public class EventTaskScheduler extends TimerTask {

	private Dispatcher dispatcher;
	private EventList listaEventi;

	public EventTaskScheduler()
	{
		//System.out.print("rtSimulationKernel.EventTaskScheduler | ");
	}

	@Override
	public void run() {
		
		this.dispatcher= Dispatcher.getIstance();
		this.listaEventi = dispatcher.getListaEventi();
		dispatcher.dispatchEvent(this.listaEventi.releaseNextEvent());
	}


}

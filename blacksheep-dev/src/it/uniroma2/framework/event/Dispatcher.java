package it.uniroma2.framework.event;


import java.util.ArrayList;

import android.util.Log;

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


public class Dispatcher {


	private static Dispatcher istance=null;
	private EventList listaEventi;
	private ArrayList<IPerceptor> registredList;
	
	/**
	 * il costruttore è privato dato che tramite il pattern singleton si vuole avere una sola
	 * istanza del Dispatcher
	 */
	private Dispatcher()
	{
		listaEventi=new EventList();
		registredList=new ArrayList<IPerceptor>();
	}
	
	/**
	 * 
	 * @return ritorna l'unica istanza del Dispatcher
	 */
	public static Dispatcher getIstance()
	{
		
		if(istance==null)
			istance=new Dispatcher();
		return istance;
	}
	
	/**
	 * invia l'evento a tutte le classi registrate che implementano 
	 * l'interfaccia Receiver
	 * @param event il parametro da inviare ai receiver registrati
	 */
	
	public void sendEvent(Event event){
		//event.setDelay(event.getDelay());
		//listaEventi.storeEvent(event);
		for(int i=0;i<registredList.size();i++){
			((IPerceptor)registredList.get(i)).receiveEvent(event);
		}
	}
	
	/*public void dispatchEvent(Event event)
	{
		for(int i=0;i<registredList.size();i++){
			
			Log.i("blacksheep", "event dispatcher event dispaching");
			((IPerceptor)registredList.get(i)).receiveEvent(event);
		}
	}*/
	
	/**
	 * permette di registrarsi nel Dispatcher per reicevere eventi 
	 * @param receiver classe che implementa l'interfaccia Receiver
	 */
	public void register(IPerceptor receiver)
	{
		if(!registredList.contains(receiver))
			registredList.add(receiver);
	}
	
	/**
	 * permette di eliminare la propria registrazione al dispatcher
	 * @param receiver classe che implementa l'interfaccia Receiver
	 */
	public void unregister(IPerceptor receiver)
	{
		registredList.remove(receiver);
	}
	
	public EventList getListaEventi() {
		return listaEventi;
	}
}

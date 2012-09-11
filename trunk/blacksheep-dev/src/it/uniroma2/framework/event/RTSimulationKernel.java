package it.uniroma2.framework.event;

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

public class RTSimulationKernel {

	private static RTSimulationKernel istance=null;
	private EventFactory eventFactory;
	private Dispatcher dispatcher;
	
	private RTSimulationKernel(){
		eventFactory=EventFactory.getIstance();
		dispatcher=Dispatcher.getIstance();
		
	}
	/**
	 * 
	 * @return ritorna l'unica istanza del RTSimulationKernel
	 */
	public static RTSimulationKernel getIstance(){
		if(istance==null)
			istance=new RTSimulationKernel();
		return istance;
	}
	
	/**
	 * incapsula la funzionalità di eventFactory di rilascio degli eventi
	 * @param event
	 */
	public void releaseEventIstance(Event event)
	{
		eventFactory.releaseEvent(event);
	}
	
	/**
	 * incapsula la richiesta di un evento non utilizzato di eventFactory
	 * @return
	 */
	public Event requestEventIstance()
	{
		return eventFactory.getEvent();
	}
	
	/**
	 * incapsula la capacita del Dispatcher di inviare eventi a tutti le istanze
	 * delle classi che implementano l'interfaccia receiver che si sono registrati
	 * @param event
	 */
	public void SendEvent(Event event)
	{
		//System.out.println("rtSimulationKernel sendEvent delay: "+event.getDelay());
		dispatcher.sendEvent(event);
	}
	
	/**
	 * permette alle classi che implementano l'interfaccia Receiver di
	 * registrarsi sul dispatcher per ricevere eventi
	 * @param receiver
	 */
	public void register(IPerceptor receiver)
	{
		dispatcher.register(receiver);
	}
	
	/**
	 * permette alle classi che implementano l'interfaccia Receiver di
	 * cancellare la propria registrazione sul dispatcher
	 * @param receiver
	 */
	public void unregister(IPerceptor receiver)
	{
		dispatcher.unregister(receiver);
	}
	
}

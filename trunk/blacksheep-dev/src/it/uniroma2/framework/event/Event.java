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

public class Event {


	private long delay;
	//private String sender;
	//private String messaggio;
	private Message messaggio;
	private HashMap<String, Object> messageInfo;

	/**
	 * costruttore di un evento
	 *
	 */
	public Event()
	{
		setDelay(0);
	}
	
	/**
	 * permette di settare i dati in un evento
	 * NOTA!!! andrebbe rivisto per generalizzare i dati trasportabili da un evento
	 * @param sender
	 * @param tipo
	 */
	public void set(Message messaggio)
	{
		this.messaggio=messaggio;
		this.delay=0;
		//this.sender=sender;
		//this.messaggio=messaggio;
	}
	
	/*
	public String getSender()
	{
		return sender;
	}
	
	public String getMessaggio()
	{
		return messaggio;
	}*/
	/**
	 * imposta il tempo di delay per l'esecuzione di un evento
	 * @param delay il tempo di attesa
	 */
	public void setDelay(long delay)
	{
		this.delay=delay;
	}
	
	public long getDelay()
	{
		//System.out.println("prova: "+this.delay);
		return this.delay;
	}
	
	/**
	 * elimina i dati trasportati dall'evento
	 *
	 */
	public void clear()
	{
		//this.sender=null;
		this.messaggio=null;
		setDelay(0);
	}

	public Message getMessage() {
		return messaggio;
	}

	public void setMessageInfo(HashMap<String, Object>  messageInfo) {
		this.messageInfo = messageInfo;
	}

	public HashMap<String, Object>  getMessageInfo() {
		return messageInfo;
	}
	
}

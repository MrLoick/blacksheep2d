package it.uniroma2.framework.xml;

import it.uniroma2.framework.Game;
import it.uniroma2.framework.event.Message;

import java.io.InputStream;

import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.sax.Element;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

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

public class SaxXmlParserMessage {
	
	
	static final  String BEGIN= "begin";
	static final  String BLACKSHEEP= "blacksheep";
	static final  String BEAN = "bean";
	
	private InputStream rawResource;
		
	public SaxXmlParserMessage() {
		Resources resources = Game.getContext().getResources();
		AssetManager assetManager = resources.getAssets();
		try {
			rawResource =assetManager.open("message.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parse() {
		
		RootElement root = new RootElement(BEGIN);
		Element bean = root.getChild(BEAN);
		
		bean.setStartElementListener(new StartElementListener(){

			public void start(Attributes attributes) {
				new Message(attributes.getValue("message"));
			
			}
		
			
		});
	

		try {
			Xml.parse(rawResource, Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}

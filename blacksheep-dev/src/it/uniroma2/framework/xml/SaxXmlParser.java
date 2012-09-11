package it.uniroma2.framework.xml;


import it.uniroma2.framework.Game;
import it.uniroma2.framework.stage.Stage;

import java.io.InputStream;

import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.sax.Element;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
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

public class SaxXmlParser {

	static final  String BEGIN= "begin";// begin
	static final  String BLACKSHEEP= "blacksheep";
	static final  String PROPERTY = "property";
	static final  String BEAN = "bean";
	
	private InputStream rawResource;
		
	public SaxXmlParser() {
		Log.i("blacksheep", "SaxParser");
		Resources resources = Game.getContext().getResources();
		AssetManager assetManager = resources.getAssets();
		try {
			rawResource =assetManager.open("game.xml");
		} catch (Exception e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
	}

	public void parse() {

		RootElement root = new RootElement(BEGIN);
		Element bean = root.getChild(BEAN);
		bean.setStartElementListener(new StartElementListener() {

			public void start(Attributes attributes) {

				new Stage(attributes.getValue("message"), attributes
						.getValue("id"));

			}

		});

		try {
			Xml.parse(rawResource, Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

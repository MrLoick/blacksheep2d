package it.uniroma2.framework.xml;

import it.uniroma2.framework.Game;
import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.stage.Stage;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
//import android.util.Log;
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

public class SaxXmlParserStage {
	
	static final  String BEGIN= "begin";// begin
	static final  String BLACKSHEEP= "blacksheep";
	static final  String PROPERTY = "property";
	static final  String BEAN = "bean";
	
	
	
	private InputStream rawResource;
	private Map<String, GameEntity> beans;
	private String id;
	private Stage stage;
	
	private static boolean isSetter(Method method){
		  if(!method.getName().startsWith("set")) return false;
		  if(method.getParameterTypes().length != 1) return false;
		  return true;
		}
		
	public SaxXmlParserStage(String filename, Stage stage) {
		//Log.i("blacksheep", "SaxParserStage");
		Resources resources = Game.getContext().getResources();
		AssetManager assetManager = resources.getAssets();
		this.stage=stage;
		beans = new HashMap<String, GameEntity>();
		try {
			rawResource =assetManager.open(filename);
			//Log.i("blacksheep", "SaxParserStage open resurce");
		} catch (Exception e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
	}

	public void parse() {
		//final String id=new String();	
		//Log.i("blacksheep", "parse stage");
		RootElement root = new RootElement(BEGIN);
		Element bean = root.getChild(BEAN);		
		bean.setStartElementListener(new StartElementListener(){
			public void start(Attributes attributes) {
				//Log.i("blacksheep", "attributes id"+attributes.getValue("id"));
				
				GameEntity gameEntity;
				
				
				try {
					Class aClass=Class.forName(attributes.getValue("class"));
					gameEntity=(GameEntity)aClass.newInstance();
					beans.put(attributes.getValue("id"),gameEntity);
					
					id=attributes.getValue("id");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

		});
			
		bean.getChild(PROPERTY).setStartElementListener(new StartElementListener(){
			public void start(Attributes attributes) {
				//Log.i("blacksheep", "property");
				GameEntity gameEntity=(GameEntity) beans.get(id);
				Method[] methods=gameEntity.getClass().getMethods();
				String field=attributes.getValue("field");
				String methodName="set"+(field.substring(0,1)).toUpperCase()+(field.substring(1));
				
				try {
					
					for(int i=0; i<methods.length;i++){
						if(methodName.equals(methods[i].getName()))
							if(isSetter(methods[i])){
								
								if(methods[i].getParameterTypes()[0].getName().equals("float"))
									methods[i].invoke(gameEntity, new Object[] { Float.parseFloat(attributes.getValue("value"))});
								else if(methods[i].getParameterTypes()[0].getName().equals("int"))
									methods[i].invoke(gameEntity, new Object[] { Integer.parseInt(attributes.getValue("value"))});
									else if(methods[i].getParameterTypes()[0].getName().equals("boolean"))
										methods[i].invoke(gameEntity, new Object[] { Boolean.parseBoolean(attributes.getValue("value"))});
							
								
								//Log.i("blacksheep", "angle"+gameEntity.getAngle());
							}
							
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		bean.getChild(PROPERTY).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				//Log.i("blacksheep", "property"+body);
			}
		});
		
		bean.setEndElementListener(new EndElementListener(){

			public void end() {
				//Log.i("blacksheep", "end beans");
				
				stage.add((GameEntity) beans.get(id));
			}
		});
		

		try {
			Xml.parse(rawResource, Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}

}

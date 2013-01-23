package it.uniroma2.framework.render;

import it.uniroma2.framework.entity.IDrawable;

public interface IRender {

	public void run();

	public void add(IDrawable drawable);

	public void remove(IDrawable drawable);

	public void clearDrawable();

	/*public void stop();

	public void start();
*/
}
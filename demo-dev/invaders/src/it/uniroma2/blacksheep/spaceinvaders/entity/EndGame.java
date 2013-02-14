package it.uniroma2.blacksheep.spaceinvaders.entity;

import it.uniroma2.framework.entity.GameEntity;
import it.uniroma2.framework.event.Event;

public class EndGame extends GameEntity {

	private int deathCount;

	public void receiveEvent(Event event) {

		if ("DEATH".equals(event.getMessage().getText())) {
			deathCount--;
		}
		if (deathCount == 0) {
			sendMessage("WINSTAGE");
		}
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

}

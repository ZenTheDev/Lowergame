package com.teto.main;
import java.awt.Graphics;
import java.util.LinkedList;
public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	@SuppressWarnings("hiding") 
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	@SuppressWarnings("hiding") 
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
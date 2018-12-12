package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class CustomPoint extends JComponent {

	private Rectangle rectangle;

	private Color color = Color.BLACK;

	public CustomPoint(int x, int y) {
		rectangle = new Rectangle(x, y, 1, 1);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getWidth());
	}

	@Override
	public void repaint() {
		color = Color.RED;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

}

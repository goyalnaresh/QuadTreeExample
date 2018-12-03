package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private List<Point> points;
	private Rectangle rectangle;

	public MyPanel(List<Point> points, Rectangle rectangle) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.points = points;
		this.rectangle = rectangle;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		points.forEach(point -> g.drawRect((int) point.getX(), (int) point.getY(), 1, 1));
		g.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(),
				(int) rectangle.getHeight());
	}

}

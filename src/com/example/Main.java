package com.example;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Main {

	private static final int SIDE = 400;
	private static Random random = new Random();

	public static void main(String[] args) {
		JFrame frmMain = new JFrame();
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		List<CustomPoint> points = getPoints(100);
		Rectangle rectangle = new Rectangle(0, 0, SIDE, SIDE);

		frmMain.setSize(SIDE + 20, SIDE + 20);
		MyPanel mainPanel = new MyPanel(points, rectangle);
		frmMain.add(mainPanel);
		frmMain.setVisible(true);

		QuadTree tree = new QuadTree(new Point(0, 0), new Dimension(SIDE, SIDE));
		points.forEach(p -> tree.insert(p));

		List<CustomPoint> intersectectPoints = tree.getIntersectectPoints(rectangle, null);

		intersectectPoints.forEach(CustomPoint::repaint);
	}

	private static List<CustomPoint> getPoints(int length) {
		CustomPoint[] points = new CustomPoint[length];
		for (int i = 0; i < length; i++) {
			points[i] = new CustomPoint(getNext(), getNext());
		}
		return Arrays.asList(points);
	}

	public static int getNext() {
		return random.nextInt(SIDE);
	}

}

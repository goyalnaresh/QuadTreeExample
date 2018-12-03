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

		List<Point> points = getPoints(5000);
		Rectangle rectangle = new Rectangle(getNext(), getNext(), getNext(), getNext());
		
		frmMain.setSize(SIDE, SIDE);
		MyPanel mainPanel = new MyPanel(points, rectangle);
		frmMain.add(mainPanel);
		frmMain.setVisible(true);

		QuadTree tree = new QuadTree(new Point(0, 0), new Dimension(SIDE, SIDE));
		points.forEach(p -> tree.insert(p));
		
		List<Point> intersectectPoints = tree.getIntersectectPoints(rectangle, null);
		System.out.println(intersectectPoints.size());
	}

	private static List<Point> getPoints(int length) {
		Point[] points = new Point[length];
		for (int i = 0; i < length; i++) {
			points[i] = new Point(getNext(), getNext());
		}
		return Arrays.asList(points);
	}

	public static int getNext() {
		return random.nextInt(SIDE);
	}

}

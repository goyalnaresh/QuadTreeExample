package com.example;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {

	int capacity = 4;
	boolean isDivided;
	List<Point> points;
	QuadTree northWest;
	QuadTree northEast;
	QuadTree southEast;
	QuadTree southWest;

	Rectangle boundary;

	public QuadTree(Point upperPoint, Dimension dimension) {
		boundary = new Rectangle(upperPoint, dimension);
		points = new ArrayList<>(capacity);
	}

	public boolean insert(Point p) {
		if (!intersect(p)) {
			return false;
		} else {
			if (points.size() < capacity) {
				points.add(p);
				return true;
			} else {
				if (!isDivided) {
					divide();
				}
				return northWest.insert(p) || northEast.insert(p) || southEast.insert(p) || southWest.insert(p);
			}
		}
	}

	private void divide() {
		int side = (int) boundary.getWidth() / 2;
		int x = (int) boundary.getX();
		int y = (int) boundary.getY();
		Dimension dimension = new Dimension(side, side);
		northWest = new QuadTree(boundary.getLocation(), dimension);
		northEast = new QuadTree(new Point(x + side, y), dimension);
		southEast = new QuadTree(new Point(x + side, y + side), dimension);
		southWest = new QuadTree(new Point(x, y + side), dimension);
		isDivided = true;
	}

	private boolean intersect(Point p) {
		return boundary.contains(p);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("points : ");
		points.forEach(p -> builder.append(p.getX()).append(", ").append(p.getY()));
		if (isDivided) {
			builder.append("\nnorthWest : ").append(northWest.toString());
			builder.append("\nnorthEast : ").append(northEast.toString());
			builder.append("\nsouthEast : ").append(southEast.toString());
			builder.append("\nsouthWest : ").append(southWest.toString());
		}
		return builder.toString();
	}

	public List<Point> getIntersectectPoints(Rectangle rectangle, List<Point> result) {
		if (result == null) {
			result = new ArrayList<>();
		}
		if (!boundary.intersects(rectangle)) {
			return null;
		} else {
			for (Point p : points) {
				if (rectangle.contains(p)) {
					result.add(p);
				}
			}
			if (isDivided) {
				northWest.getIntersectectPoints(rectangle, result);
				northEast.getIntersectectPoints(rectangle, result);
				southEast.getIntersectectPoints(rectangle, result);
				southWest.getIntersectectPoints(rectangle, result);
			}
		}
		return result;
	}

}

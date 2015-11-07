package main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CircleLayout implements LayoutManager2 {
	
	private Component center;
	private List<Component> edge;
	
	public CircleLayout() {
		edge = new ArrayList<>();
	}

	@Override
	@Deprecated
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub
		if (center == comp)
			center = null;
		else
			edge.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return new Dimension(parent.getWidth(), parent.getHeight());
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return preferredLayoutSize(parent);
	}

	@Override
	public void layoutContainer(Container parent) {
		// TODO Auto-generated method stub
		Point origin = new Point(parent.getWidth() / 2, parent.getHeight() / 2);
		double angle = Math.toRadians(360./edge.size());
		int radius = parent.getHeight() / 2 - 100;
		int compEdge = 150;
		for (int i = 0; i < edge.size(); i++) {
			Component comp = edge.get(i);
			double theta = i * angle;
			int dx = (int) (radius * Math.sin(theta));
			int dy = (int) (-radius * Math.cos(theta));
			int x = origin.x + dx - compEdge/2;
			int y = origin.y + dy - compEdge/2;
			comp.setBounds(x, y, compEdge, compEdge);
		}
		Dimension centerSize = center.getPreferredSize();
		int dx = (int) centerSize.getWidth()/2;
		int dy = (int) centerSize.getHeight()/2;
		center.setBounds(origin.x - dx, origin.y - dy, 2 * dx, 2 * dy);
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		// TODO Auto-generated method stub
		if (!(constraints instanceof Position)) 
			throw new IllegalArgumentException();
		Position pos = (Position) constraints;
		switch (pos) {
		case EDGE:
			edge.add(comp);
		case CENTER:
			center = comp;
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		// TODO Auto-generated method stub
		return new Dimension(target.getWidth(), target.getHeight());
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {
		// TODO Auto-generated method stub
		// do nothing
	}
	
	
	
	private enum Position {
		EDGE,
		CENTER;
	}
	
	public static final Object EDGE = Position.EDGE;
	public static final Object CENTER = Position.CENTER;

}

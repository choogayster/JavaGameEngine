package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 12.02.2016.
 * Class store information about rails where enemies should move
 * List of instances must be stored at the GameWorld class
 * Each enemy must have variable with one of instance
 */

public class EnemyRails {

    private List<Point> points;

    public EnemyRails() {
        points = new ArrayList<>();
    }

    public int size() {
        return points.size();
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void addPoint(int x, int y, Point next) {
        points.add(new Point (x, y, next));
    }

    public void addPoint(int x, int y) {
        points.add(new Point (x, y));
    }

    public void bind(Point firstPoint, Point secondPoint) {
        // bind first point with second
        firstPoint.next.add(secondPoint);
        // bind second point with first
        firstPoint.next.add(secondPoint);
    }

    public void bindById(int firstId, int secondId) {
        if (points.size() > 0 && points.size() > secondId && points.size() > firstId) {
            // bind first point with second
            points.get(firstId).next.add( points.get(secondId) );
            // bind second point with first
            points.get(secondId).next.add( points.get(firstId) );
        }
    }

    class Point {
        public int x;
        public int y;
        public List<Point> next;
        public Point() {
            next = new ArrayList<>();
        }
        public Point(int x, int y, Point next) {
            this.x = x;
            this.y = y;
            this.next = new ArrayList<>();
            this.next.add(next);
        }
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.next = new ArrayList<>();
            this.next.add(this);
        }
    }
}

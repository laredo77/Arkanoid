/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class.
 */
public class Rectangle {
    private Point upperLeft; // Up left rectangle point
    private double width;    // Rectangle with
    private double height;   // Rectangle height

    /**
     * Constructor.
     * Instantiates a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the rectangle width
     * @param height    the rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Gets up right point method.
     *
     * @return the up right point
     */
    public Point getUpRightPoint() {
        return new Point(getWidth() + getUpperLeft().getX(),
                getUpperLeft().getY());
    }

    /**
     * Gets down right point.
     *
     * @return the down right point
     */
    public Point getDownRightPoint() {
        return new Point(getWidth() + getUpperLeft().getX(),
                getHeight() + getUpperLeft().getY());
    }

    /**
     * Gets down left point.
     *
     * @return the down left point
     */
    public Point getDownLeftPoint() {
        return new Point(getUpperLeft().getX(),
                getHeight() + getUpperLeft().getY());
    }

    /**
     * Gets up line.
     *
     * @return the up line
     */
    public Line getUpLine() {
        return new Line(getUpperLeft(), getUpRightPoint());
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return new Line(getUpRightPoint(), getDownRightPoint());
    }

    /**
     * Gets down line.
     *
     * @return the down line
     */
    public Line getDownLine() {
        return new Line(getDownRightPoint(), getDownLeftPoint());
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return new Line(getDownLeftPoint(), getUpperLeft());
    }


    /**
     * This method gets a point and check on given rectangle
     * to which of its edges the point belong to.
     *
     * @param p the point
     * @return a line that the given point is on it.
     */
    public Line getMatchLine(Point p) {
        if (Line.isPointOnSegment(getUpLine(), p)) {
            return getUpLine();
        } else if (Line.isPointOnSegment(getRightLine(), p)) {
                return getRightLine();
        } else if (Line.isPointOnSegment(getDownLine(), p)) {
                return getDownLine();
        } else {
            return getLeftLine();
        }
    }

    /**
     * This method is holding ArrayList to count the intersection
     * points of a line with rectangle. It return a
     * List of intersection points with the specified line.
     *
     * @param line the line to check intersect with
     * @return the List of points. (possibly empty)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Initialize ArrayList of Points.
        List<Point> intersectionPoints = new ArrayList<Point>();

        /*
         * Initialize points for each of the rectangle edges.
         * If no intersection, point dosen't initialize and
         * not added to the List. otherwise, it add the point to the
         * List.
         */
        Point p1 = getUpLine().intersectionWith(line);
        if (p1 != null) {
            intersectionPoints.add(p1);
        }
        Point p2 = getRightLine().intersectionWith(line);
        if (p2 != null) {
            intersectionPoints.add(p2);
        }
        Point p3 = getDownLine().intersectionWith(line);
        if (p3 != null) {
            intersectionPoints.add(p3);
        }
        Point p4 = getLeftLine().intersectionWith(line);
            if (p4 != null) {
                intersectionPoints.add(p4);
        }
        return intersectionPoints;
    }

    /**
     * Gets width method.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets int width.
     *
     * @return the width casting to int.
     */
    public int getIntWidth() {
        return (int) this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets int height.
     *
     * @return the height casting to int.
     */
    public int getIntHeight() {
        return (int) this.height;
    }

    /**
     * Gets upper left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}

/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.shapes;

/**
 * A Line class.
 * This class is using Point class.
 * The main purpose of the class is to use it for future tasks
 * and geometric calculations
 */
public class Line {
    private Point start; // Start point of a line
    private Point end;   // End point of a line

    /**
     * constructor #1.
     * Create a Line from two points: start and end
     *
     * @param start start will hold the (x,y) coordinate of the start point
     * @param end   end will hold the (x,y) coordinate of the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor #2:
     * initialize the start and end points with x,y values.
     * declaring new 2 points.
     *
     * @param x1 the x coordinate of start point
     * @param y1 the y coordinate of start point
     * @param x2 the x coordinate of end point
     * @param y2 the y coordinate of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length calculate the distance between start point to end point.
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * middle calculate the middle point of a line.
     * xm is the average number between the x coordinate of start and end point.
     * ym is the average number between the y coordinate of start and end point.
     * together, (xm,ym) is the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xm = (start.getX() + end.getX()) / 2;
        double ym = (start.getY() + end.getY()) / 2;
        return new Point(xm, ym);
    }

    /**
     * line got start and end points that contain (x,y) coordinates.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * line got start and end points that contain (x,y) coordinates.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Line that the x coordinate of both start and end points are the same.
     * intersection with vertical line could be difficult so the
     * method help to know the situation if the line vertical or not.
     *
     * @return true if the line vertical, false otherwise
     */
    public boolean isVertical() {
        return (((end.getY() - start.getY()) != 0)
                && ((end.getX() - start.getX()) == 0));
    }

    /**
     * Line witch the y coordinate of both points start and end are the same.
     * It is good to know if the line is horizontal.
     *
     * @return true if it horizontal line, false otherwise
     */
    public boolean isHorizontal() {
        return (((end.getY() - start.getY()) == 0)
                && ((end.getX() - start.getX()) != 0));
    }

    /**
     * slope method calculate the slope of a line.
     * the method use the form of calculating slope
     * between two points, start and end.
     * as common, the slope is dy/dx.
     * In case dx = 0 its means the line vertical known as infinity slope.
     * In case dy = 0 its means the line horizontal  known as 0 slope.
     *
     * @return the slope of a line
     */
    public double slope() {
        // Initializing dy as the change in Y axle, dx as the change in X axle
        // And 'inf' variable in case the slope is infinity.
        double dy = end.getY() - start.getY();
        double dx = end.getX() - start.getX();
        double inf = Double.POSITIVE_INFINITY;

        if (isHorizontal()) {
            // The line is horizontal
            return dy; // The slope of horizontal line is 0 which is dy
        } else if (isVertical()) {
            // The line is vertical
            return inf; // The slope of vertical line is known as infinity
        } else {
            // The slope is valid and known as dy/dx.
            return dy / dx;
        }
    }

    /**
     * Basing on the known line equation: y = m * x + b
     * intersectionPoint will consider the segment as infinity lines
     * to check if could be any intersection point.
     * with the information of the method it will help later to
     * decide if the point in the limits of the segments.
     * m represent the slope of the line.
     * b represent constant.
     *
     * @param other is the line to check with intersection.
     * @return the x coordinate of the intersection point. (OR null (Note2)).
     * Note: the y coordinate is dosen't matter right now.
     * Note2: if both of the lines vertical, its return null.
     */
    public Point intersectionPoint(Line other) {
        if (this.isVertical() && other.isVertical()) {
            // No intersection: The lines parallel/equal/one subset other
            return null;
        } else if (this.isVertical()) {
            // Case this line is vertical
            if (other.isHorizontal()) {
                // Option that other line is horizontal
                // the intersection point will be X from this and Y from other.
                return new Point(this.start.getX(), other.start.getY());
            } else {
                // Option other line is not horizontal
                double slopeM = other.slope(); // Initialize variable for slope
                double constantB = other.start.getY()
                        - (slopeM * other.start.getX());
                // Initialize var for 'y' coordinate of the intersection point
                double yIPoint = slopeM * this.start.getX() + constantB;
                return new Point(this.start.getX(), yIPoint);
            }
        } else if (other.isVertical()) {
            // Case other line is vertical
            if (this.isHorizontal()) {
                // Option that this line is horizontal
                // The intersection point will be X from other and Y from this.
                return new Point(other.start.getX(), this.start.getY());
            } else {
                // Option this line is not horizontal
                double slopeM = this.slope(); // Initialize variable for slope
                double constantB = this.start.getY()
                        - (slopeM * this.start.getX());
                // Initialize var for 'y' coordinate of the intersection point
                double yIPoint = slopeM * other.start.getX() + constantB;
                return new Point(this.start.getX(), yIPoint);
            }
        } else {
            // Finding the intersection x coordinate by using the known equation
            // developed from the equation above: -(b1-b2) / (m1-m2)
            double slopeM1 = this.slope();
            double constantB1 = this.start.getY()
                    - (slopeM1 * this.start.getX());
            double slopeM2 = other.slope();
            double constantB2 = other.start.getY()
                    - (slopeM2 * other.start.getX());
            double x1 = -(constantB1 - constantB2) / (slopeM1 - slopeM2);
            double y1 = slopeM1 * x1 + constantB1;
            return new Point(x1, y1);
        }
    }

    /**
     * This boolean method is using intersectionPoint to decide if
     * occur intersection between two lines.
     *
     * @param other is the line to check with intersection.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionPoint(other) != null) {
            // There is intersection.
            // Initialize point for the intersection point
            Point p1 = this.intersectionPoint(other);
            return (isPointOnSegment(this.getLine(), p1)
                    && isPointOnSegment(other.getLine(), p1));
        }
        return false; // No intersection.
    }

    /**
     * This method calls isIntersecting to figure out whether
     * there is an intersection. If there is, it returns the
     * intersection points between the lines.
     *
     * @param other is the line to check with the intersection.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            // There is intersection
            return this.intersectionPoint(other);
        } else {
            // No intersection
            return null;
        }
    }

    /**
     * equals method check if two lines given is sharing the same points.
     *
     * @param other the line to check equality
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.getX() == other.start.getX()
                && this.start.getY() == other.start.getY()
                && this.end.getX() == other.end.getX()
                && this.end.getY() == other.end.getY());
    }

    /**
     * This method checks the closest intersection.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle to check it lines.
     * @return the intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(getLine()).isEmpty()) {
            // No intersection
            return null;
        } else {
            // There is intersection.
            // Initialize variable for closest point
            Point closest = new Point(Double.POSITIVE_INFINITY,
                    Double.POSITIVE_INFINITY);

            /*
             * Running the loop on list that intersectionPoints sent of optional
             * intersection points, and figure which is the closest point.
             */
            for (int i = 0; i < rect.intersectionPoints(getLine()).size(); i++) {
                if (rect.intersectionPoints(
                        getLine()).get(i).distance(this.start())
                        < closest.distance(this.start())) {
                    // Found closer point and update closest.
                    closest = rect.intersectionPoints(getLine()).get(i);
                }
            }
            return closest;
        }
    }

    /**
     * This boolean method check if the given point is on the
     * given segment.
     *
     * @param line  the line to check
     * @param point the point to check
     * @return boolean value if it fulfill.
     */
    public static boolean isPointOnSegment(Line line, Point point) {
        // Initialize Epsilon for checking deviations.
        double epsilon = 0.000000001;
        // Initialize variables for minimum
        // and maximum line start and end points
        double minimumX = Math.min(line.start.getX(), line.end.getX());
        double maximumX = Math.max(line.start.getX(), line.end.getX());
        double minimumY = Math.min(line.start.getY(), line.end.getY());
        double maximumY = Math.max(line.start.getY(), line.end.getY());

        return minimumX - epsilon <= point.getX() && point.getX()
                <= maximumX + epsilon && minimumY - epsilon <= point.getY()
                && point.getY() <= maximumY + epsilon;
    }

    /**
     * Calling this method to initialize new line from two points.
     *
     * @return the line.
     */
    public Line getLine() {
        return new Line(this.start, this.end);
    }
}

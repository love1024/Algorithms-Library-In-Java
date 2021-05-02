package ConvexHull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class GrahamScan {
    // Starting point needed for sorting points with reference
    // to the first point
    private Point2d startingPoint;

    private enum Orientation {
        COLINEAR,
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }

    // Point class with x and y coordinates
    private class Point2d {
        int x;
        int y;

        Point2d(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // A utility function to find next to top in a stack
    private Point2d nextToTop(Stack<Point2d> S) {
        // Remove top once
        Point2d top = S.pop();

        // See next element and push top again
        Point2d next = S.peek();
        S.push(top);

        return next;
    }

    // A utility function to return square of distance
    // between a and b
    int distSq(Point2d a, Point2d b) {
        return ((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }

    // To find orientation of ordered triplet (a, b, c).
    // The function returns following values
    // 0 --> a, b and c are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private Orientation orientation(Point2d a, Point2d b, Point2d c) {
        int val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);

        if(val == 0) return Orientation.COLINEAR;
        return (val > 0) ? Orientation.CLOCKWISE : Orientation.COUNTER_CLOCKWISE;
    }

    // Compare two points w
    private int compare(Point2d a, Point2d b) {
        Orientation o = orientation(this.startingPoint, a, b);
        if(o == Orientation.COLINEAR)
            return (distSq(this.startingPoint, a) >= distSq(this.startingPoint, b)) ? 1 : -1;
        return (o == Orientation.COUNTER_CLOCKWISE) ? -1 : 1;
    }

    // Start Graham scan on given points and print the
    // Poins which are on the hull
    private void start(int[] x, int y[]) {

        // Convert x and y coordinates to a list of points
        List<Point2d> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(new Point2d(x[i], y[i]));
        }

        // Find minimum y coordinate
        int minY = list.get(0).y, minYIdx = 0;
        for (int i = 0; i < list.size(); i++) {

            // Pick the bottom-most or chose the left
            // most point in case of tie
            if((list.get(i).y < minY) || (list.get(i).y == minY && list.get(i).x < list.get(minYIdx).x)) {
                minY = list.get(i).y;
                minYIdx = i;
            }
        }

        // Retrieve the minimum element and create sublist of others
        // Putting first element ot the min index position
        this.startingPoint = list.get(minYIdx);
        list.set(minYIdx, list.get(0));
        list = list.subList(1, list.size());

        // Sort by polar angle with respect to minimum y found
        Comparator<Point2d> com = (a, b) -> this.compare(a, b);
        list.sort(com);

        //Add starting element to first position
        list.add(0, this.startingPoint);

        // Finding unique points from the starting points
        // Only choosing the farthest point fro colinear ones
        int m = 1;
        for(int i = 1; i < list.size(); i++) {

            while(i < (list.size() - 1) && orientation(this.startingPoint, list.get(i), list.get(i+1)) == Orientation.COLINEAR) {
                i++;
            }
            list.set(m, list.get(i));
            m++;
        }

        // If there are less than 3 unique points,
        // we can't create convex hull
        if(m < 3) return;

        // Initalize a stack
        Stack<Point2d> S = new Stack<>();
        S.push(list.get(0));
        S.push(list.get(1));
        S.push(list.get(2));

        // Start Graham scan
        for(int i = 3; i < m; i++) {
            // Until we found which ic counterlist
            // Pop elements from top
            while(orientation(nextToTop(S), S.peek(), list.get(i)) != Orientation.COUNTER_CLOCKWISE)
                S.pop();

            S.push(list.get(i));
        }

        // Print fouund points
        while (!S.empty()) {
            Point2d p = S.pop();
            System.out.println("x: " + p.x + " y: " + p.y);
        }
    }

    public static void main(String[] args) {
        GrahamScan gs = new GrahamScan();
//        int x[] = {0,1,2,4,0,1,3,1};
//        int y[] = {3,1,2,4,0,2,1,3};
//        gs.start(x, y);

        Class cls = gs.getClass();
        Field[] field = cls.getDeclaredFields();
        for(int i = 0; i< field.length; i++)
            System.out.println(field[i]);

    }
}

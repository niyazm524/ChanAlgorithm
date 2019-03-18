package ml.bimdev.chan;

class Point {
    int x;
    int y;

    Point() {

    }

    Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%s %s", x, y);
    }
}

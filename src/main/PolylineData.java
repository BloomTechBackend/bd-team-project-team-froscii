public class PolylineData {
    private final int[] xPoints;
    private final int[] yPoints;
    private int pointCount;
    public PolylineData(int[] xPoints, int[] yPoints, int pointCount) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.pointCount = pointCount;
    }
    public int[] getXPoints() {
        return xPoints;
    }
    public int[] getYPoints() {
        return yPoints;
    }
    public int getPointCount() {
        return pointCount;
    }
}
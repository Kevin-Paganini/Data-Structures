package paganiniK;

/**
 * Creates dots and calculates critical value of dots
 */
public class Dot {
    /**
     * A dots coordinate should never change
     */
    private final double xCoord;
    private final double yCoord;

    /**
     * Creates a dot
     * @param xCoord
     * @param yCoord
     */
    public Dot(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     *  X coordinate getter
     * @return x coordinate
     */
    public double getxCoord() {
        return xCoord;
    }

    /**
     * Y coordinate getter
     * @return Y coordinate
     */
    public double getyCoord() {
        return yCoord;
    }


    /**
     * Calculates distance between dots
     * @param head
     * @param tail
     * @return distance between two dots
     */
    private static double calculateDistance(Dot head, Dot tail){
        return Math.sqrt(Math.pow(head.getxCoord()-tail.getxCoord(), 2) +
                Math.pow(head.getyCoord()-tail.getyCoord(), 2));
    }

    /**
     * Calculates critical value
     * @param previous
     * @param next
     * @return critical value of each dot
     */
    public double calculateCriticalValue(Dot previous, Dot next) {
        double distance12 = calculateDistance(previous, this);
        double distance23 = calculateDistance(this, next);
        double distance13 = calculateDistance(previous, next);
        return (distance12 + distance23 - distance13);
    }

}
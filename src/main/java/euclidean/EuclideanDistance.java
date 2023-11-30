package euclidean;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class EuclideanDistance {
    public static Float calculateDistance(int x1, int y1, int x2, int y2) {
        return (float) sqrt(pow((x1 - x2), 2) + pow((y1 - y2), 2));
    }
}

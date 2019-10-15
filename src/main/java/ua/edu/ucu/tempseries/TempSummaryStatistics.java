package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(double avg, double dev,
                                 double min, double max) {
        avgTemp = avg;
        devTemp = dev;
        minTemp = min;
        maxTemp = max;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(avgTemp + minTemp
                + maxTemp + devTemp).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TempSummaryStatistics)) {
            return false;
        }
        TempSummaryStatistics tempSummaryStatistics =
                (TempSummaryStatistics) obj;
        return tempSummaryStatistics.getAvgTemp() == avgTemp
                && tempSummaryStatistics.getDevTemp() == devTemp
                && tempSummaryStatistics.getMaxTemp() == maxTemp
                && tempSummaryStatistics.getMinTemp() == minTemp;
    }
}

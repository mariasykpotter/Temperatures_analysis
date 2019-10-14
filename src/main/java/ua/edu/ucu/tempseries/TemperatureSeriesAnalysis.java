package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSerie;
    final static int MIN_TEMP = -273;
    private int temperaturesNumber;
    private int temperaturesCapacity;

    public TemperatureSeriesAnalysis() {
        temperatureSerie = new double[1];
        temperaturesNumber = 0;
        temperaturesCapacity = 1;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < MIN_TEMP) {
                throw new InputMismatchException();
            }
        }
        temperatureSerie = temperatureSeries;
        temperaturesNumber = temperatureSerie.length;
        temperaturesCapacity = temperaturesNumber;
    }

    public double average() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < temperaturesNumber; i++) {
            sum += temperatureSerie[i];
        }
        return sum / temperaturesNumber;
    }


    public double deviation() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        double sqDiff = 0;
        double mean = average();
        for (int i = 0; i < temperaturesNumber; i++) {
            sqDiff += (temperatureSerie[i] - mean) *
                    (temperatureSerie[i] - mean);
        }
        return sqDiff / temperaturesNumber;
    }


    public double min() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        double minimum = temperatureSerie[0];
        for (int i = 0; i < temperaturesNumber; i++) {
            minimum = Math.min(temperatureSerie[i], minimum);
        }
        return minimum;
    }


    public double max() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        double maximum = temperatureSerie[0];
        for (int i = 0; i < temperaturesNumber; i++) {
            maximum = Math.max(temperatureSerie[i], maximum);
        }
        return maximum;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        double closestPositive = temperatureSerie[0];
        double closestNegative = temperatureSerie[0];
        for (int i = 0; i < temperaturesNumber; i++) {
            if (temperatureSerie[i] > 0 &&
                    Math.abs(temperatureSerie[i] - 0) <
                    Math.abs(closestPositive - tempValue)) {
                closestPositive = temperatureSerie[i];
            } else if (temperatureSerie[i] < 0
                    && Math.abs(temperatureSerie[i] - 0) <
                    Math.abs(closestNegative - tempValue)) {
                closestNegative = temperatureSerie[i];
            }
        }
        if (Math.abs(closestPositive - tempValue) <= Math.abs(closestNegative - tempValue)) {
            return closestPositive;
        } else {
            return closestNegative;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        TemperatureSeriesAnalysis lessThanTemp =
                new TemperatureSeriesAnalysis();
        for (double temp : temperatureSerie) {
            if (temp < tempValue) {
                lessThanTemp.addTemps(temp);
            }
        }
        double[] resArr = new double[lessThanTemp.temperaturesNumber];
        System.arraycopy(lessThanTemp.temperatureSerie,
                0, resArr, 0, resArr.length);
        return resArr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        TemperatureSeriesAnalysis greaterThanTemp =
                new TemperatureSeriesAnalysis();
        for (double temp : temperatureSerie) {
            if (temp >= tempValue) {
                greaterThanTemp.addTemps(temp);
            }
        }
        double[] resArr = new double[greaterThanTemp.temperaturesNumber];
        System.arraycopy(greaterThanTemp.temperatureSerie,
                0, resArr, 0, resArr.length);
        return resArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(average(),
                deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (temperaturesCapacity == 0) {
            throw new IllegalArgumentException();
        }
        else if (temperaturesCapacity == temperaturesNumber) {
            double[] newArray = new double[2 * temperaturesCapacity];
            System.arraycopy(temperatureSerie, 0, newArray, 0, temperaturesNumber);
            temperatureSerie = newArray;
            temperaturesCapacity *= 2;
        }
        for (double temp : temps) {
            if (temp < MIN_TEMP) {
                throw new InputMismatchException();
            }
            temperatureSerie[temperaturesNumber] = temp;
            temperaturesNumber++;
        }
        return temperaturesNumber;

    }
}


package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    double[] temperatureSerie;
    static final int MIN_TEMP = -273;
    int temperaturesNumber;
    int temperaturesCapacity;

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
            sqDiff += (temperatureSerie[i] - mean) * (temperatureSerie[i] - mean);
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
        double closest_positive = temperatureSerie[0];
        double closest_negative = temperatureSerie[0];
        for (int i = 0; i < temperaturesNumber; i++) {
            if (temperatureSerie[i] > 0 && (Math.abs(temperatureSerie[i] - 0) < Math.abs(closest_positive - tempValue))) {
                closest_positive = temperatureSerie[i];
            } else if (temperatureSerie[i] < 0 && (Math.abs(temperatureSerie[i] - 0) < Math.abs(closest_negative - tempValue))) {
                closest_negative = temperatureSerie[i];
            }
        }
        if (Math.abs(closest_positive - tempValue) <= Math.abs(closest_negative - tempValue)) {
            return closest_positive;
        } else {
            return closest_negative;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        TemperatureSeriesAnalysis less_than_temp = new TemperatureSeriesAnalysis();
        for (double temp : temperatureSerie) {
            if (temp < tempValue) {
                less_than_temp.addTemps(temp);
            }
        }
        double[] resArr = new double[less_than_temp.temperaturesNumber];
        System.arraycopy(less_than_temp.temperatureSerie, 0, resArr, 0, resArr.length);
        return resArr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        TemperatureSeriesAnalysis greater_than_temp = new TemperatureSeriesAnalysis();
        for (double temp : temperatureSerie) {
            if (temp >= tempValue) {
                greater_than_temp.addTemps(temp);
            }
        }
        double[] resArr = new double[greater_than_temp.temperaturesNumber];
        System.arraycopy(greater_than_temp.temperatureSerie, 0, resArr, 0, resArr.length);
        return resArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperaturesNumber == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (temperaturesCapacity == 0){
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


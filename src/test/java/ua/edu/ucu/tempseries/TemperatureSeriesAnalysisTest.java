package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptyArr;
    private TemperatureSeriesAnalysis simpleArr;
    private TemperatureSeriesAnalysis sameElementsArr;

    @Before
    public void setUp() {
        sameElementsArr = new TemperatureSeriesAnalysis(new double[]{5.0, 5.0, 5.0, 5.0});
        emptyArr = new TemperatureSeriesAnalysis();
        simpleArr = new TemperatureSeriesAnalysis(new double[]{1.0, 2.0, 3.0, -2.0});
    }

    @Test
    public void testFindAverage() {
        double actualRes = simpleArr.average();
        double expRes = 1.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindAverageSame() {
        double actualRes = sameElementsArr.average();
        double expRes = 5.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindAverageEmpty() {
        double actualRes = emptyArr.average();
    }

    @Test
    public void testFindDeviation() {
        double actualRes = simpleArr.deviation();
        double expRes = 3.5;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindDeviationSameEl() {
        double actualRes = sameElementsArr.deviation();
        double expRes = 0.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindDeviationEmptyEl() {
        double actualRes = emptyArr.deviation();
    }

    @Test
    public void testFindMin() {
        double actualRes = simpleArr.min();
        double expRes = -2.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindMinSameEl() {
        double actualRes = sameElementsArr.min();
        double expRes = 5.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinEmptyEl() {
        double actualRes = emptyArr.min();
    }

    @Test
    public void testFindMax() {
        double actualRes = simpleArr.max();
        double expRes = 3.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindMaxSameEl() {
        double actualRes = sameElementsArr.max();
        double expRes = 5.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMaxEmptyEl() {
        double actualRes = emptyArr.max();
    }

    @Test
    public void testFindTempClosestToValue() {
        double actualRes = simpleArr.findTempClosestToValue(-2.0);
        double expRes = -2.0;
        assertEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindTempClosestToZero() {
        double actualRes = simpleArr.findTempClosestToZero();
        double expRes = 1.0;
        assertEquals(actualRes, expRes, 0.0);
    }


    @Test
    public void testFindTempsLessThen() {
        double[] actualRes = simpleArr.findTempsLessThen(3.0);
        System.out.println(actualRes);
        double[] expRes = {1.0, 2.0, -2.0};
        assertArrayEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindTempsLessThenSameArr() {
        double[] actualRes = sameElementsArr.findTempsLessThen(3.0);
        double[] expRes = {};
        assertArrayEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenEmptyArr() {
        double[] actualRes = emptyArr.findTempsLessThen(0.0);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] actualRes = simpleArr.findTempsGreaterThen(3.0);
        double[] expRes = {3.0};
        assertArrayEquals(actualRes, expRes, 0.0);
    }

    @Test
    public void testFindTempsGreaterThenSameArr() {
        double[] actualRes = sameElementsArr.findTempsGreaterThen(3.0);
        double[] expRes = {5.0, 5.0, 5.0, 5.0};
        assertArrayEquals(actualRes, expRes, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenEmptyArr() {
        double[] actualRes = emptyArr.findTempsGreaterThen(0.0);
    }

    @Test
    public void testTempSummaryStatistics() {
        TempSummaryStatistics actualRes = simpleArr.summaryStatistics();
        TempSummaryStatistics expRes = new TempSummaryStatistics(1.0, 3.5, -2.0, 3.0);
        assertEquals(expRes, actualRes);
    }

    @Test
    public void testTempSummaryStatisticsWithSameElementsArray() {
        TempSummaryStatistics actualRes = sameElementsArr.summaryStatistics();
        TempSummaryStatistics expRes = new TempSummaryStatistics(5.0, 0.0, 5.0, 5.0);
        assertTrue(actualRes.equals(expRes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTempSummaryStatisticsEmptyArray() {
        TempSummaryStatistics actualRes = emptyArr.summaryStatistics();
    }

    @Test
    public void testAddTemperaturesSimpleArr() {
        int actualRes = simpleArr.addTemps(4.0, 5.0);
        int expRes = 6;
        assertEquals(expRes, actualRes);
    }

    @Test
    public void testAddTemperaturesEmptyArr(){
        int actualRes = emptyArr.addTemps(4.0);
        int expRes = 1;
        assertEquals(expRes, actualRes);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTemperaturesLowTemperature() {
        simpleArr.addTemps(-290);
    }

}

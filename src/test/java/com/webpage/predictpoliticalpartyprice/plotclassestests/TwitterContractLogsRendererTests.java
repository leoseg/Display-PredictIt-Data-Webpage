package com.webpage.predictpoliticalpartyprice.plotclassestests;

import com.webpage.predictpoliticalpartyprice.plotclasses.TwitterContractLogsRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.ShapeUtils;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TwitterContractLogsRendererTests {

    @Test
    void givenRowGreaterThanNumberSeries_rendererGetItemStroke_shouldReturnDashed(){
        Stroke dashed = new BasicStroke(2.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);

        assert twitterContractLogsRenderer.getItemStroke(5,0).equals(dashed);
    }

    @Test
    void givenRowSmallerThanNumberSeries_rendererGetItemStroke_shouldNotReturnDashed(){
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        Stroke dashed = new BasicStroke(2.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);
        assert twitterContractLogsRenderer.getItemStroke(3,0).equals(xyLineAndShapeRenderer.getItemStroke(3,0));


    }

    @Test
    void givenRowGreaterThanNumberSeries_rendererGetItemShape_shouldReturnDiagonalCross(){
        Shape cross = ShapeUtils.createDiagonalCross(1,1);
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);
        Shape result = twitterContractLogsRenderer.getItemShape(5,0);
       // assert result.getBounds2D() == cross.getBounds2D();
        assert result.getBounds().equals(cross.getBounds());

    }

    @Test
    void givenRowSmallerThanNumberSeries_rendererGetItemShape_shouldReturnRectangle(){
        Shape rectangle = new Rectangle(1,1);
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);
        assert twitterContractLogsRenderer.getItemShape(3,0).equals(rectangle);
    }

    @Test
    void givenRowSmallerThanNumberSeries_rendererGetItemPaint_shouldReturnParentGetItemPaint(){
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);
        assert twitterContractLogsRenderer.getItemPaint(3,0).equals(Color.MAGENTA);
    }

    @Test
    void givenRowGreaterThanNumberSeries_rendererGetItemPaint_shouldReturnParentGetItemPaintShiftedByNumberSeries(){
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        TwitterContractLogsRenderer twitterContractLogsRenderer = new TwitterContractLogsRenderer();
        twitterContractLogsRenderer.setNumberSeries(4);
        assert twitterContractLogsRenderer.getItemPaint(7,0).equals(Color.MAGENTA);
    }

    @Test
    void rendererGetLengendItem_shouldReturnCustomLegendItem() {
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries( new TimeSeries("testseries1"));
        timeSeriesCollection.addSeries( new TimeSeries("testseries2"));
        JFreeChart chart = ChartFactory.createTimeSeriesChart("testchart","testtimeaxis","textxaxis",timeSeriesCollection);
        TwitterContractLogsRenderer twitterContractLogsRenderer = Mockito.spy(new TwitterContractLogsRenderer());
        LegendItem legendItem = Mockito.spy(new LegendItem("testlabel"));
        doReturn(legendItem).when(twitterContractLogsRenderer).getParentLegendItem(0,1);
        Shape rect = new Rectangle(1,1);
        doReturn(rect).when(twitterContractLogsRenderer).getItemShape(1,0);
        chart.getXYPlot().setRenderer(twitterContractLogsRenderer);
        twitterContractLogsRenderer.getLegendItem(0, 1);
        Mockito.verify(legendItem, Mockito.times(1)).setShape(rect);
        Mockito.verify(legendItem, Mockito.times(1)).setFillPaint(twitterContractLogsRenderer.getItemPaint(1, 0));
        Mockito.verify(legendItem, Mockito.times(1)).setOutlinePaint(twitterContractLogsRenderer.getItemPaint(1, 0));
        Mockito.verify(legendItem, Mockito.times(1)).setLinePaint(twitterContractLogsRenderer.getItemPaint(1, 0));
        Mockito.verify(legendItem, Mockito.times(1)).setLabelPaint(twitterContractLogsRenderer.getItemPaint(1, 0));
    }
}

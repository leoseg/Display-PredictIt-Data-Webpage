package com.webpage.predictpoliticalpartyprice.plotclasses;

import org.jfree.chart.LegendItem;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.ShapeUtils;
import org.springframework.stereotype.Component;

import java.awt.*;


/**
 * Custom renderer for the plots with predictit contracts and twitterhashtagcount logs
 */
@Component
public class TwitterContractLogsRenderer extends XYLineAndShapeRenderer {

    int numberSeries;

    Stroke dashed = new BasicStroke(2.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);





    /**
     * Overrides the parent method to customize the line drawing, because the first number of series is contracts
     * all greater than that are twitterhashtagcounts and are retunred as dashed line
     * @param row row which marks the series
     * @param column which marks the entry of a series
     * @return BasicStroke object
     */
    @Override
    public Stroke getItemStroke(int row, int column){
        if (row >=numberSeries){
            return dashed;
        }else {
            return super.getItemStroke(row, column);
        }
    }

    /**
     * Overrides the parent method to customize the shape of the points of the plots,
     * because the first number of series is contracts
     * all greater than that are twitterhashtagcounts and are returned as diagonalcross
     * @param row row which marks the series
     * @param column which marks the entry of a series
     * @return Shape object
     */
    @Override
    public Shape getItemShape(int row,int column){
        if (row >=numberSeries){
            return ShapeUtils.createDiagonalCross(1,1);
        }else {
            return new Rectangle(1,1);
        }
    }

    /**
     * Overrides the parent method to customize the paint of the points of the plots,
     * so twitterhashtagcounts and the corresponding contract have the same color, does that by repeating the
     * parent method for getItemPaint for all twitterhashtagcount series (row number bigger than numberSeries)
     * @param row row which marks the series
     * @param column which marks the entry of a series
     * @return Paint object
     */
    @Override
    public Paint getItemPaint(int row,int column){
        if (row >=numberSeries){
            return super.getItemPaint(row -numberSeries, column);
        }else{
            return super.getItemPaint(row,column);
        }

    }

    /**
     * Alters the legend so it has the the same colors and shape like the items in the plot
     * @param datasetIndex index of the dataset
     * @param series series to add legend for
     * @return LegendItem
     */
    @Override
    public LegendItem getLegendItem(int datasetIndex, int series){
        LegendItem legendItem = this.getParentLegendItem(datasetIndex,series);
        legendItem.setLabelPaint(this.getItemPaint(series,0));
        legendItem.setLinePaint(this.getItemPaint(series,0));
        legendItem.setFillPaint(this.getItemPaint(series,0));
        legendItem.setOutlinePaint(this.getItemPaint(series,0));
        legendItem.setShape(this.getItemShape(series,0));

        return legendItem;
    }

    public LegendItem getParentLegendItem(int datasetIndex,int series){
        return super.getLegendItem(datasetIndex,series);
    }


    /**
     * Sets the number of series, the complete plot has two times the numberSeries where the first half are all
     * contract series and the second half are twitterhashtagcount series
     * @param numberSeries int for the number of series
     */
    public void setNumberSeries(int numberSeries) {
        this.numberSeries = numberSeries;
    }
}

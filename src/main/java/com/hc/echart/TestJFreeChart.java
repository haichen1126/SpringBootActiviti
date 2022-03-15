package com.hc.echart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.imageio.ImageIO;

/**
 * @means JFreeChart Line Chart（折线图）
 * @Author HC
 * @Date 2021/4/20
 * @Param
 * @return
 **/
public class TestJFreeChart {
    /**
     * 创建JFreeChart Line Chart（折线图）
     */
    public static void main(String[] args) {
        // 步骤1：创建CategoryDataset对象（准备数据）
        CategoryDataset dataset = createDataset();
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "D:\\line.jpg", 600, 400);
        String string = Base64.img2base64("D:\\line.jpg",1);
        System.out.print(string);
//        Base64.deleteFile("D:\\line.jpg");

    }

    /**
     * @return void
     * @means 保存为文件
     * @Author HC
     * @Date 2021/4/20
     * @Param [chart, outputPath, weight, height]
     **/
    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            // ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
            //1.0.9版本默认是红色背景设imagetype为2手动设置为1
            BufferedImage bi = chart.createBufferedImage(weight, height, 1, null);
            ImageIO.write(bi, "jpeg", out);
            // 保存为JPEG
            ChartUtilities.writeChartAsJPEG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * @return
     * @means 根据CategoryDataset创建JFreeChart对象
     * @Author HC
     * @Date 2021/4/20
     * @Param
     **/
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart("省检修公司生产作业信息数", // 标题
                "日期", // categoryAxisLabel （category轴，横轴，X轴标签）
                "数量", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);

        // 设置背景颜色
        plot.setBackgroundPaint(Color.WHITE);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setNoDataMessage("暂无数据显示！");// 没有数据显示的时候显示这个提示

//        // 获取显示线条的对象
//        LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
//        // 设置拐点是否可见/是否显示拐点
//        lasp.setBaseShapesVisible(false);
//        // 设置拐点不同用不同的形状
//        lasp.setDrawOutlines(true);
//        // 设置线条是否被显示填充颜色
//        lasp.setUseFillPaint(true);
//        // 设置拐点颜色
//        lasp.setBaseFillPaint(Color.blue);//蓝色
//        // 设置折线加粗
//        //lasp.setSeriesStroke(0, new BasicStroke(3F));
//        lasp.setSeriesOutlineStroke(0, new BasicStroke(1.0F));//设置折点的大小
//        lasp.setSeriesOutlineStroke(1, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(2, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(3, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(4, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(5, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(6, new BasicStroke(1.0F));
//        lasp.setSeriesOutlineStroke(7, new BasicStroke(1.0F));
//        plot.setNoDataMessage("没有相关统计数据");


        // 取得纵轴
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // 设置纵轴的字体
        numberAxis.setLabelFont(new Font("黑体", Font.PLAIN, 18));
        numberAxis.setUpperMargin(0.15);//设置最高数据显示与顶端的距离
        numberAxis.setLowerMargin(2);//设置最低的一个值与图片底端的距离

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
                .getRenderer();// 设置样式
        renderer.setSeriesStroke(0, new BasicStroke(3.0F));//设置折线大小
        renderer.setSeriesPaint(0, Color.BLUE);//蓝色
        renderer.setSeriesStroke(1, new BasicStroke(3.0F));
        renderer.setSeriesPaint(1, Color.CYAN);//蓝色



        // 其他设置 参考 CategoryPlot类
//        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
//        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setUseSeriesOffset(true); // 设置偏移量
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
//        //抗锯齿关闭
//        jfreechart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        return jfreechart;
    }

    /**
     * @return org.jfree.data.category.CategoryDataset
     * @means 创建CategoryDataset对象
     * @Author HC
     * @Date 2021/4/20
     * @Param []
     **/
    public static CategoryDataset createDataset() {
        String[] rowKeys = {"蓝色","红色"};
        String[] colKeys = {"3月15日", "3月16日", "3月17日", "3月18日", "3月19日", "3月20日",
                "3月21日", "3月22日"};
        double[][] data = {{90, 120, 105, 116, 118, 123, 101, 136},{1,1,1,1,1,1,18,8}};
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");
        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

}

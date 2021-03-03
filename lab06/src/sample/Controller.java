package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    //sample data for BarChart
    private static double[] avgHousingPricesByYear = { //red
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = { //blue
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //sample data for PieChart
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color. AQUA , Color. GOLD , Color. DARKORANGE ,
            Color. DARKSALMON , Color. LAWNGREEN , Color. PLUM
    };

    @FXML
    public void initialize(){
        gc= mainCanvas.getGraphicsContext2D();

        drawBarChart(300, 300, avgHousingPricesByYear,Color.RED, avgCommercialPricesByYear, Color.BLUE);
        drawPieChart(350, 0, 300, 300,purchasesByAgeGroup,pieColours);
    }



    //-----------------------Bar Chart------------------------------------------------

    public void drawBarChart(int w, int h, double[] data1, Color color1, double[] data2, Color color2){
        gc.setFill(color1);
        double xInc=w/data1.length;

        //Finding minValue and maxValue within two arrays
        double maxValue=Double.MIN_VALUE;
        double minValue=Double.MAX_VALUE;

        //creating a concatenated array to find max and min
        double[] data = new double[data1.length + data2.length];
        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);

        for (double val:data){
            if (val>maxValue)
                maxValue=val;
            if (val<minValue)
                minValue=val;
        }


        //Plot the red bars
        double x=0.0;
        for (double val:data1){
            double height=(val/maxValue)*h;
            gc.fillRect(x, h-height, xInc/2, height);
            x+=xInc;
        }

        //Plot the blue bars
        gc.setFill(color2);
        x=xInc/2;
        for (double val:data2){
            double height=(val/maxValue)*h;

            gc.fillRect(x, h-height, xInc/2, height);
            x+=xInc;
        }
    }


    //-----------------------Pie Chart------------------------------------------------


    private void drawPieChart(int centerX, int centerY, int w, int h, int[] data, Color[] pieColours) {
        double startAngle=0.0;

        int sum = 0;
        for (int i : data)
            sum += i;

        int colorIndex=0;

        for (double val:data){
            gc.setFill(pieColours[colorIndex]);
            double angle=(val/sum)*360;
            gc.fillArc(centerX,centerY,w,h,startAngle,angle,ArcType.ROUND);
            startAngle+=angle;
            colorIndex++;
        }

    }


}

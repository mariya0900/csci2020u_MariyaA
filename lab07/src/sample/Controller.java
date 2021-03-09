package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    public void initialize(){
        gc= mainCanvas.getGraphicsContext2D();
        FileLoader loader = new FileLoader("weatherwarnings-2015.csv");
        Map<String, Integer> weather=loader.loadFile();
        drawPieChart(350, 0, 300, 300, weather,pieColours);
    }

    private static Color[] pieColours = {
            Color. AQUA , Color. GOLD , Color. DARKORANGE ,
            Color. DARKSALMON , Color. LAWNGREEN , Color. PLUM
    };



    private void drawPieChart(int centerX, int centerY, int w, int h,  Map<String, Integer> weather, Color[] pieColours) {
        double startAngle=0.0;
        int sum = 0;
        Collection<Integer> occurencies= weather.values();
        for (int i: occurencies) {
            sum += i;
        }

        //text for the legend
        Font font = new Font("Verdana", 16);
        gc.setFont(font);
        gc.setStroke(Color.BLACK);

        int colorIndex=0;
        int yRect=50;
        for (String weatherType: weather.keySet()){
            gc.setFill(pieColours[colorIndex]);
            int val=weather.get(weatherType);
            double angle=(Double.valueOf(val)/sum)*360.0;

            //drawing the piechart
            gc.fillArc(centerX,centerY,w,h,startAngle,angle, ArcType.ROUND);
            startAngle+=angle;
            colorIndex++;

            //drawing the legend
            gc.fillRect(50, yRect,30,30);
            gc.strokeText(weatherType, 100,yRect+15);
            gc.setFill(Color.BLACK);
            gc.fillText(weatherType, 100,yRect+15);
            yRect+=50;

        }

    }



}

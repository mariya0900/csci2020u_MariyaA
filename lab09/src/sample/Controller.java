package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    List<Float> googleClosingValues=downloadStockPrices("GOOG");
    List<Float> appleClosingValues=downloadStockPrices("AAPL");


    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        drawLinePlot(googleClosingValues, appleClosingValues);

    }

    public void drawLinePlot(List<Float> google, List<Float> apple){
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,50,50, mainCanvas.heightProperty().intValue()-50);
        gc.strokeLine(50,mainCanvas.heightProperty().intValue()-50, mainCanvas.widthProperty().intValue()-50,mainCanvas.heightProperty().intValue()-50);
        Float maxValue=Float.MIN_VALUE;
        for (Float value: google){
            if (value>maxValue){
                maxValue=value;
            }
        }
        gc.setStroke(Color.RED);
        plotLine(google, maxValue);

        gc.setStroke(Color.BLUE);
        plotLine(apple, maxValue);


    }
    public void plotLine(List<Float> values, Float maxValue){
        float w=mainCanvas.widthProperty().floatValue()-100;
        float h=mainCanvas.heightProperty().floatValue()-100;
        float xInc=w/values.size();
        float hCanvas=mainCanvas.heightProperty().floatValue()-50;
        float x=50.0f;

        //PLOT x AND y AXIS
        for (int i = 0; i < values.size()-1; i++) {
            float h1=(values.get(i)/maxValue)*h;
            float h2=(values.get(i+1)/maxValue)*h;
            gc.strokeLine(x, hCanvas-h1, x+=xInc, hCanvas-h2);
        }

    }

    public static List<Float> downloadStockPrices(String tickerSymbol){
        List<Float> closingValues = new ArrayList<>();
        String url="https://query1.finance.yahoo.com/v7/finance/download/"+tickerSymbol+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        try{

            InputStream file=new URL(url).openStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(file, "UTF-8"));
            String header=reader.readLine();
            String line;


            while ((line=reader.readLine())!=null){
                String closingValue=line.split(",")[4];
                closingValues.add(Float.parseFloat(closingValue));

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        return closingValues;
    }
}

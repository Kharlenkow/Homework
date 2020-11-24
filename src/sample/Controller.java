package sample;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class Controller {
    public Button Confirm;
    public  Button Analysis;
    public LineChart Ax;
    public XYChart.Series series;
    public Axis<Double> X_Axis;
    public Axis<Double> Y_Axis;

    public TextField FreQ;
    public Slider FS;

    Stage stage;

    public void initialize(Stage stage)
    {
    }

    public void Confirm_Click()
    {
        Ax.getData().clear();
        series=new XYChart.Series();
        series.setName("Wave A-t");
        double fre=FS.getValue();
        for(int i=0;i<5000;i++)
        {
            series.getData().add(new XYChart.Data(i/5000.0,sin(fre*2*3.14*i/5000.0)));
        }
        Ax.getData().add(series);
    }
    public void Analysis_Click()
    {
        List<XYChart.Data<Double,Double>> a= series.getData();

        Vector<Double> datal=new Vector<>();

        for(int i=0;i<a.size()-1;i++) {
            if (a.get(i).getYValue() == 0.0)
                datal.add(a.get(i).getXValue());
            else if (a.get(i).getYValue() * a.get(i + 1).getYValue() < 0.0)
                datal.add(a.get(i).getXValue() + (a.get(i + 1).getXValue() - a.get(i).getXValue()) * abs(a.get(i).getYValue()) / abs(a.get(i).getYValue() - a.get(i + 1).getYValue()));
        }

        double resuf=datal.lastElement()- datal.firstElement();
        resuf=resuf*2.0/(double)(datal.size()-1);
        resuf=1.0/resuf;
        String result=""+resuf;
        FreQ.setText(result);
    }
}

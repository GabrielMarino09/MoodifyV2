package graph;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class PieChartSample extends Application{

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            // TODO Auto-generated method stub
            //Instantiating the pie-chart class
            PieChart piechart = new PieChart();

//setting the data of the pie chart.
            piechart.setData(getChartData());

            //Creating Layout
            StackPane root = new StackPane();

            //Adding pie-chart to the layout
            root.getChildren().add(piechart);

            //configuring scene
            Scene scene = new Scene(root,400,400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("PieChart Example");
            primaryStage.show();

        }
        //creating getChartData method to set the chart data
        private ObservableList<Data> getChartData() {
            ObservableList<Data> list = FXCollections.observableArrayList();
            list.addAll(new PieChart.Data("Slice 1", 45),
                    new PieChart.Data("Slice 2", 10),
                    new PieChart.Data("Slice 3", 15),
                    new PieChart.Data("Slice 4", 30));
            return list;
        }
    }



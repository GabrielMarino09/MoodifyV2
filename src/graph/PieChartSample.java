package graph;
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.personalization.GetUsersTopArtistsAndTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class PieChartSample extends Application{
    public static Connection dbConnector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn= DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;

        }catch(Exception e) {
            return null;
        }
    }
    public static String GGID(){
        Connection conn = null;
        String query = "SELECT * from Uinfo";
        String GID = "";
        try {
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                GID = rs.getString(1);
                System.out.println(GID);
            }
            return GID;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return GGID();
    }
    static final String accessToken = AuthorizationCodeRefresh.authorizationCodeRefresh_Sync();
    private static final String userId = GGID();
    private static final ModelObjectType type = ModelObjectType.ARTIST;
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
            .limit(10)
//          .offset(0)
            .time_range("long_term")
            .build();


    public PieChartSample() throws IOException, ParseException, SpotifyWebApiException {
    }

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



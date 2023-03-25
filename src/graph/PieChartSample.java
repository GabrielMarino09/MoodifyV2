package graph;
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.sql.*;

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
    private static final GetUsersFollowedArtistsRequest getUsersFollowedArtistsRequest = spotifyApi
            .getUsersFollowedArtists(type)
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
            System.out.println("-------------------------------------------------------------------------------------");
            final PagingCursorbased<Artist> artistPagingCursorbased;
            try {
                artistPagingCursorbased = getUsersFollowedArtistsRequest.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SpotifyWebApiException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ObservableList<Data> list = FXCollections.observableArrayList();
            Artist[] ArtistName = artistPagingCursorbased.getItems();
            System.out.println("Artists Found: " + ArtistName.length);
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < ArtistName.length; i++) {
                list.addAll(new PieChart.Data(ArtistName[i].getGenres().toString(), 1));
            }
            return list;
        }
    }



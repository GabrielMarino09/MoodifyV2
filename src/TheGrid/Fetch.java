//Nests Authorization code inside TheGrid
package TheGrid;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin

import StageOne.StageOne;
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import StageOne.MainUI;
import java.awt.*;
import java.awt.Image;
import java.net.URL;
import java.sql.*;
import javax.swing.*;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.CheckUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;


import java.io.IOException;
import java.util.ArrayList;



public class Fetch {

    public static Connection dbConnector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
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
    private static ArrayList<String[]> getData(){
        ArrayList<String[]> table = new ArrayList<>();
        Connection conn = null;
        String query = "SELECT * from TrackInfo";
        String deleteQuery = "DELETE FROM TrackInfo";
        String VAC = "VACUUM";
        /* Maybe...
        String StartQuery = "CREATE TABLE TrackInfo (" +
                "TrackName TEXT," +
                "Artist TEXT," +
                "Explicit TEXT," +
                "TrackID TEXT," +
                "RecommendedTrack TEXT," +
                "RecommendedArtist TEXT," +
                "RecommendedURL TEXT," +
                "Mood TEXT)";
         */
        try{
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(deleteQuery);
            stmt.executeQuery(VAC);
            //stmt.executeQuery(StartQuery);

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        finally {
            closeConnection(conn);
        }
        return table;
    }

    public static void closeConnection(Connection conn){
        try{
            conn.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //UserID present at the User's profile page
    private static final String userId = GGID();
    //private static final String userId = "gwq66wps2n6f7ed2mw0rsk7v3";
    /*
    Access token retrieved from the AuthorizationCodeRefresh Class
    Failed Attempt to implement seamless fetching
    */
    // private static final String accessToken = "BQC0b9ywzKxY1-CFyTaH0toG57ak66Q5bqc3_n5Lc3Em4eVoZlpqB-c-RPsmqsz9EtHiGP5eQm2TVj92a3itvQhF_K5lhcPOxO2MTCn2xNdlGnTlooP414X-X94-OD-KjzrYNo9gPKckwZOSb1RDF5azBWjiLQZSsc0QxUgBvw91z7JqMAjAbbcIU6LStwrTephTYbAsXPa4GCPTFTIOKjbSrn5C6jvJXx3RlCjf4cdbxGFCTdyJ4VE7uXXKSgPEa_VNtV1MZ20zA6aZQoJWURHsdyIl2Swp0N919paVanDrI_pRnQhMYStf_KGrZit5eTEB1U2qixEJw18";
    private static final String accessToken = AuthorizationCodeRefresh.authorizationCodeRefresh_Sync();

    //Specifies what can be retrieved from Spotify by adding the API request inside a variable
    private static final ModelObjectType Artist = ModelObjectType.ARTIST;
    private static final ModelObjectType Album = ModelObjectType.ALBUM;
    private static final ModelObjectType AudioFeatures = ModelObjectType.AUDIO_FEATURES;
    private static final ModelObjectType Genre = ModelObjectType.GENRE;
    private static final ModelObjectType Playlist = ModelObjectType.PLAYLIST;
    private static final ModelObjectType Track = ModelObjectType.TRACK;
    private static final ModelObjectType User = ModelObjectType.USER;

    //Sets all the information needed to get access to Spotify's backend and make requests
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    //Sets the request to a user's saved tracks

    private static final GetUsersSavedTracksRequest getUsersSavedTracksRequest = spotifyApi.getUsersSavedTracks()
            .build();

    //Sets the request to a user's followed artists
    private static final GetUsersFollowedArtistsRequest getUsersFollowedArtistsRequest = spotifyApi
            .getUsersFollowedArtists(Artist)
            .build();

    //Sets the request to a user's playlists
    private static final GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi
            .getListOfUsersPlaylists(userId)
            //        .limit(0)
            .offset(0)
            .build();


//  private static final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
//          .limit(10)
//          .market(CountryCode.SE)
//          .max_popularity(50)
//          .min_popularity(10)
//          .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
//          .seed_genres("electro")
//          .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
//          .target_popularity(20)
//          .build();


    //Initializes Fetch class
    public static void Fetch() {
        getData();
        dbConnector();
        try {
            //Gets the information regarding the users playlists (both public and private)
            System.out.println("-------------------------------------------------------------------------------------");
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();
            PlaylistSimplified[] PlaylistName = playlistSimplifiedPaging.getItems();
            System.out.println("Playlists Found: " + PlaylistName.length);
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < PlaylistName.length; i++) {
                System.out.println("Playlist Name: " + PlaylistName[i].getName());
                System.out.println("Playlist Is Collaborative: " + PlaylistName[i].getIsCollaborative());
                System.out.println("Playlist Is Public Access: " + PlaylistName[i].getIsPublicAccess());
                System.out.println("Playlist Owner: " + PlaylistName[i].getOwner().getDisplayName());
                System.out.println("Number of Tracks: " + PlaylistName[i].getTracks().getTotal());
                System.out.println();
            }

            //Gets the information regarding the users followed artists and their details
            System.out.println("-------------------------------------------------------------------------------------");
            final PagingCursorbased<Artist> artistPagingCursorbased = getUsersFollowedArtistsRequest.execute();
            Artist[] ArtistName = artistPagingCursorbased.getItems();
            System.out.println("Artists Found: " + ArtistName.length);
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < ArtistName.length; i++) {
                System.out.println("Artist Name: " + ArtistName[i].getName());
                System.out.println("Number of Followers: " + ArtistName[i].getFollowers().getTotal());
                System.out.println("Popularity: " + ArtistName[i].getPopularity());
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Recommendations:");
                System.out.println("To Be Tested");
                System.out.println();
                System.out.println();

            }

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            String query = "insert into TrackInfo (TrackName,Artist,Explicit,TrackID,RecommendedTrack,RecommendedArtist,RecommendedURL, Mood, Album, MainArtist) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);

            //Gets the information regarding the users saved tracks and their details
            System.out.println("-------------------------------------------------------------------------------------");
            final Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();
            SavedTrack[] SavedTrack = savedTrackPaging.getItems();
            System.out.println("Tracks Found: " + SavedTrack.length);
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < SavedTrack.length; i++) {
                //------------------------------------------------------------------------------------------------------
                System.out.println("Track Name: " + SavedTrack[i].getTrack().getName());
                pst.setString(1, SavedTrack[i].getTrack().getName());
                //------------------------------------------------------------------------------------------------------
                System.out.println("Track ID: " + SavedTrack[i].getTrack().getId());
                String ID = SavedTrack[i].getTrack().getId();
                pst.setString(4, ID);
                //------------------------------------------------------------------------------------------------------
                GetAudioFeaturesForTrackRequest getAudioFeaturesForTrackRequest = spotifyApi
                        .getAudioFeaturesForTrack(ID)
                        .build();
                final AudioFeatures audioFeatures = getAudioFeaturesForTrackRequest.execute();

                final GetAudioAnalysisForTrackRequest getAudioAnalysisForTrackRequest = spotifyApi
                        .getAudioAnalysisForTrack(ID)
                        .build();
                final AudioAnalysis audioAnalysis = getAudioAnalysisForTrackRequest.execute();

                final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi.getAvailableGenreSeeds()
                        .build();

                String ArtistID = null;
                ArrayList<String> Artists = new ArrayList<String>();
                Artists.clear();

                for (ArtistSimplified artist : SavedTrack[i].getTrack().getArtists()){
                    pst.setString(10, artist.getName());
                }

                for (ArtistSimplified artist : SavedTrack[i].getTrack().getArtists()) {
                    System.out.println("Artist: " + artist.getName());
                    ArtistID = artist.getId();
                    Artists.add(artist.getName());


                    //------------------------------------------------------------------------------------------------------
                    pst.setString(2, String.valueOf(Artists).replace("[","").replace("]","").replace(",",", "));
                    //------------------------------------------------------------------------------------------------------
                }


                System.out.println("Duration (Milliseconds): " + SavedTrack[i].getTrack().getDurationMs());
                System.out.println("Album: " + SavedTrack[i].getTrack().getAlbum().getName());
                pst.setString(9, SavedTrack[i].getTrack().getAlbum().getName());
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Track Analysis");

                System.out.println("Explicit: " + SavedTrack[i].getTrack().getIsExplicit());
                if (SavedTrack[i].getTrack().getIsExplicit().toString().equals("true")){
                    pst.setString(3, "Explicit");
                }
                else{
                    pst.setString(3, "Not Explicit");
                }

                System.out.println("Popularity: " + SavedTrack[i].getTrack().getPopularity());

                System.out.println("Track Energy: " + audioFeatures.getEnergy());
                double EnergyConversion = audioFeatures.getEnergy();
                String Mood = null;
                System.out.println("EC: " + EnergyConversion);
                if (EnergyConversion >= 0.0 && EnergyConversion < 0.2){
                    Mood = "Hopeless";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                    }
                if (EnergyConversion >= 0.2 && EnergyConversion < 0.3){
                    Mood = "Sad";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.3 && EnergyConversion < 0.4){
                    Mood = "Angry";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.4 && EnergyConversion < 0.5){
                    Mood = "Annoyed";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.5 && EnergyConversion < 0.6){
                    Mood = "Neutral";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.6 && EnergyConversion < 0.7){
                    Mood = "Hopeful";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.7 && EnergyConversion < 0.8){
                    Mood = "Happy";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.8 && EnergyConversion < 0.9){
                    Mood = "Excited";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }
                if (EnergyConversion >= 0.9 && EnergyConversion < 1.0){
                    Mood = "Motivated";
                    System.out.println("Mood: " + Mood);
                    pst.setString(8, Mood);
                }


                System.out.println("Track Instrumentalness: " + audioFeatures.getInstrumentalness());
                System.out.println("Track Key: " + audioFeatures.getKey());
                System.out.println("Track Liveness: " + audioFeatures.getLiveness());
                System.out.println("Track Loudness: " + audioFeatures.getLoudness());
                System.out.println("Track Mode: " + audioFeatures.getMode());
                System.out.println("Track Speechines: " + audioFeatures.getSpeechiness());
                System.out.println("Track Tempo: " + audioFeatures.getTempo());
                System.out.println("Track Time Signature: " + audioFeatures.getTimeSignature());
                System.out.println("Track HREF: " + audioFeatures.getTrackHref());
                System.out.println("Track Valence: " + audioFeatures.getValence());

                System.out.println("Key Confidence: " + audioAnalysis.getTrack().getKeyConfidence());

                //System.out.println("Genre: " + getAvailableGenreSeedsRequest;

                final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                        .limit(1)
                        .max_popularity(80)
                        .min_popularity(10)
                        .seed_artists(ArtistID)
                        //.seed_genres()
                        //.seed_genres()
                        .seed_tracks(ID)
                        .target_popularity(50)
                        .build();

                final Recommendations recommendations = getRecommendationsRequest.execute();

                for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("Recommendations:");
                    //------------------------------------------------------------------------------------------------------
                    System.out.println("Recommended Track: " + SpotifyRecommendations.getName());
                    pst.setString(5, SpotifyRecommendations.getName());
                    //------------------------------------------------------------------------------------------------------
                    ArrayList<String> RecArt = new ArrayList<String>();
                    RecArt.clear();
                    for (ArtistSimplified artist : SpotifyRecommendations.getArtists()) {
                        System.out.println("Artist: " + artist.getName());
                        RecArt.add(artist.getName());
                    }
                    pst.setString(6, String.valueOf(RecArt).replace("[","").replace("]","").replace(",",", "));
                    //------------------------------------------------------------------------------------------------------
                    System.out.println("Preview URL: " + SpotifyRecommendations.getPreviewUrl());
                    if (SpotifyRecommendations.getPreviewUrl() == null){
                        pst.setString(7, "Preview not available to region of Spotify account.");
                    }
                    else{
                        pst.setString(7, SpotifyRecommendations.getPreviewUrl());
                    }

                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println();
                    pst.execute();
                }
            }


            //Gets the total number of playlists, number of saved tracks and artists followed
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Total Number of playlists: " + playlistSimplifiedPaging.getTotal());
            System.out.println("Number of Saved Tracks: " + savedTrackPaging.getTotal());
            System.out.println("Number of Artists Followed: " + artistPagingCursorbased.getTotal());
            mainUILauncher();



        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mainUILauncher() {
        JFrame frame = new JFrame("Moodify");
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = StageOne.class.getClassLoader().getResource("Images/Logo.png");
        final Image image = defaultToolkit.getImage(imageResource);
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }
        frame.setIconImage(image);
        frame.setContentPane(new MainUI().MainUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        getData();
        dbConnector();
        Fetch();
    }
}
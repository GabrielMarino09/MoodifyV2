//Main UI is a part of the package named StageOne.
package StageOne;

//Imports authorization.PKCE.PKCE.AuthorizationCodeRefresh, so it can make requests to the Spotify API by using the refresh token.

import authorization.PKCE.PKCE.AuthorizationCodeRefresh;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.library.RemoveUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.library.SaveTracksForUserRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.*;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

//Imports all the necessary libraries to execute this class.
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.hc.core5.http.ParseException;


public class MainUI {

    //Establishes the connection between the form for the MainUI and its contents, so things can be altered, and actions can be executed.
    private JButton HappyButton;
    private JButton ExcitedButton;
    private JButton MotivatedButton;
    private JButton HopefulButton;
    private JButton HopelessButton;
    private JButton AngryButton;
    private JButton SadButton;
    private JButton AnnoyedButton;
    private JButton NeutralButton;
    private JButton Option1Button;
    private JButton Option3Button;
    private JButton Option4Button;
    private JButton Option5Button;
    private JButton Option6Button;
    private JButton Option7Button;
    private JButton Option8Button;
    private JButton Option9Button;
    public JPanel MainUI;
    private JPanel OptionsPanel;
    private JPanel DateTimePanel;
    private JPanel CompanyPanel;
    private JLabel MoodifyLogoLabel;
    private JButton Option2Button;
    private JPanel PlaybackPanel;
    private JPanel VideoPanel;
    private JPanel PreviewPlane;
    private JLabel DayLabel;
    private JLabel DateLabel;
    private JLabel TimeLabel;
    private JLabel VideoLabel;
    private JLabel ImageLBL;
    private JLabel CSongNameLBL;
    private JLabel CAlbumNameLBL;
    private JLabel CArtistNameLBL;
    private JPanel CPPanel;
    private JButton GoBack;
    private JButton Pause;
    private JButton FastForward;
    private JPanel DP;
    private JPanel TP;
    private JFrame frame;

    //Returns the JFrame frame, so it can be used by all parts in this class.
    public JFrame getFrame() {
        return frame;
    }

    //Establishes a connection with the Moodify database.
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

    //Gets the user id from the database.
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
            }
            return GID;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return GGID();
    }

    //Retrieves the refresh token needed to make requests to the Spotify API.
    private static final String userId = GGID();
    private static final String accessToken = AuthorizationCodeRefresh.authorizationCodeRefresh_Sync();

    //Specifies what can be retrieved from Spotify by adding the API request inside a variable.
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

    //Prepares the request to get the users top tracks, which will be processed for playback, amongst other things.
    private static final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
            .limit(10)
            .time_range("long_term")
            .build();
    //Prepares the request to get the users available devices, to be used for playback, and altering playback status.
    private static final GetUsersAvailableDevicesRequest getUsersAvailableDevicesRequest = spotifyApi
            .getUsersAvailableDevices()
            .build();
    //Prepares the request to get the users current playback status,.
    private static final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
            spotifyApi.getInformationAboutUsersCurrentPlayback()
                    .build();


    // Sets the properties of the items that are going to be places on the MainUI form.
    public MainUI() {

        // Retrieves the frame previously created, so that code in this class can interact with it.
        this.frame = new JFrame("Moodify");

        // Specifies that everytime the mouse hovers over the following buttons, the pointer switches to a hand.
        HopefulButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        HappyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        HopelessButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        NeutralButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        AngryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ExcitedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        MotivatedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        AnnoyedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option1Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option3Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option4Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option5Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option6Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option7Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option8Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Option9Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FastForward.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GoBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Pause.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Specifies the image path for the labels and buttons, so it be retrieved by calling a single variable.
        ImageIcon PauseIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/PlayPause.png");
        ImageIcon ShuffleOn = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/ShuffleOn.png");
        ImageIcon ShuffleOff = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Shuffle.png");
        ImageIcon RepeatFalse = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Repeat.png");
        ImageIcon RepeatCT = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/RepeatContext.png");
        ImageIcon RepeatT = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/RepeatTrack.png");
        ImageIcon HappyGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Happy.gif");
        ImageIcon ExcitedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Excited.gif");
        ImageIcon MotivatedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Motivated.gif");
        ImageIcon HopefulGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Hopeful.gif");
        ImageIcon HopelessGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Hopeless.gif");
        ImageIcon AngryGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Angry.gif");
        ImageIcon SadGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Sad.gif");
        ImageIcon AnnoyedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Annoyed.gif");
        ImageIcon NeutralGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Neutral.gif");

        // Sets the pause icon to the pause image as default.
        Pause.setIcon(PauseIMG);

        // Hides the playback panel, until needed.
        PlaybackPanel.setVisible(false);

        // Prepares to request the users current playback information.
        final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                spotifyApi.getInformationAboutUsersCurrentPlayback()
                        .build();

        try {
            // Executes the previous request.
            final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                // If nothing is set to be played, do nothing.
                if (currentlyPlayingContext == null) {
                    ;
                }
                // If something is playing, do the following:
                else{
                    // Get playback state. True if a song is playing, false if the playback is paused.
                    boolean Playing = currentlyPlayingContext.getIs_playing();

                    // Get Shuffle state. True if on, false if off.
                    boolean Shuffle = currentlyPlayingContext.getShuffle_state();

                    // Get Repeat state. This should have a value of false, context or track.
                    String Repeat =  currentlyPlayingContext.getRepeat_state();

                    // If shuffle is on, then do the following:
                    if (Shuffle == true){
                        // Set the shuffle icon to the ShuffleOn image.
                        Option6Button.setIcon(ShuffleOn);
                    }
                    // If shuffle is off, then do the following:
                    else{
                        // Set the shuffle icon to the ShuffleOff image.
                        Option6Button.setIcon(ShuffleOff);
                    }

                    // If the repeat variable equals to track, do the following:
                    if (Repeat == "track") {
                        // Set the repeat icon to the RepeatT image.
                        Option9Button.setIcon(RepeatT);
                    }
                    // If the repeat variable equals to context, do the following:
                    if (Repeat == "context"){
                        // Set the repeat icon to the RepeatCT image.
                        Option9Button.setIcon(RepeatCT);
                    }
                    // If the repeat variable equals to off, do the following:
                    if (Repeat == "off") {
                        // Set the repeat icon to the RepeatFalse image.
                        Option9Button.setIcon(RepeatFalse);
                    }

                    // If there is a song currently playing, then  do the following:
                    if (Playing == true){

                        // Display the playback label.
                        PlaybackPanel.setVisible(true);

                        // Hide the video label.
                        VideoLabel.setVisible(false);

                        // Retrieves the ID of the current playing track.
                        final String NPID = currentlyPlayingContext.getItem().getId();

                        // Stores this ID on a string to make another request.
                        final String id = NPID;

                        // Gets the current playing track based on the ID saved.
                        final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                                .build();

                        // Executes the previous request.
                        final Track track = getTrackRequest.execute();

                        // Sets this label to the name of the current playing track.
                        CSongNameLBL.setText(track.getName());

                        // Sets this label to the album name of the current playing track.
                        CAlbumNameLBL.setText(track.getAlbum().getName());

                        // Prepares to get the main artist of the track, which is a specific object, hence the for loop.
                        for (ArtistSimplified artist : track.getArtists()) {
                            // Gets the name of the artist
                            artist.getName();
                            // Adds the name to the label.
                            CArtistNameLBL.setText(artist.getName());
                            // Stops the loop, as any other artist is not the main artist.
                            break;
                        }


                        // Gets the album image for the url of the current playing track. This normally retrives three URLs, the first being the one in the highest definition.
                        se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();

                        // Gets the image from the first URL.
                        String ImageURLBIG = String.valueOf(IURL[0].getUrl());

                        //Adds that URL to a new variable, for processing purposes.
                        URL getImageUrl = new URL(ImageURLBIG);

                        //Read the previous variable, preparing an image buffer for it to be processed.
                        BufferedImage ImageBuffer = ImageIO.read(getImageUrl);

                        // Resized that image to 200x200, so it fits the frame nicely.
                        Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

                        // Gets the resized image, and transforms it into an Image Icon.
                        ImageIcon IA = new ImageIcon(ResizedAlbumCover);

                        // Adds the resized image to its label.
                        ImageLBL.setIcon(IA);

                        // Switches the Play/Pause icon to PauseIMG.
                        Pause.setIcon(PauseIMG);
                    }
                }
        }
        // Standard exceptions for Input and Output.
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Standard exceptions for the Spotify API, such as bad requests.
        catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }
        // Standard exceptions for Parsing.
        catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Defines the actions that will occur when the Option 2 Button is clicked.
        Option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Prepares to open a new modal window as a JDialog Window.
                RecentPlaylist recentPlaylist = null;
                try {
                    // Specifies that recentPlaylist retrieves the frame, tittle and modal specification for the RecentPlaylist class and form.
                    recentPlaylist = new RecentPlaylist(frame, "Moodify", true);

                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Parsing.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }

                //Specifies the ContentPane that will be used.
                recentPlaylist.getContentPane().add(recentPlaylist.RecentPlaylistPanel);

                //Specifies that this is a modal JDialog.
                recentPlaylist.setModal(true);

                //Packs the form.
                recentPlaylist.pack();

                //Allows ContentPane to be visible.
                recentPlaylist.setVisible(true);
            }
        });

        // Defines the actions that will occur when the Option 2 Button is clicked.
        Option3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies that recentPlaylist retrieves the frame, tittle and modal specification for the DatabaseUI class and form.
                DatabaseUI modalDB = new DatabaseUI(frame, "Moodify", true);

                //Specifies the ContentPane that will be used.
                modalDB.getContentPane().add(modalDB.DatabaseUIPanel);

                //Specifies that this is a modal JDialog.
                modalDB.setModal(true);

                //Packs the form.
                modalDB.pack();

                //Allows ContentPane to be visible.
                modalDB.setVisible(true);
            }
        });

        // Defines the actions that will occur when the Annoyed Button is clicked.
        AnnoyedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.4;

                // Specifies the maximum energy level that will be used
                double MaE = 0.5;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, AnnoyedGif);
            }
        });

        // Defines the actions that will occur when the Motivated Button is clicked.
        MotivatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.9;

                // Specifies the maximum energy level that will be used
                double MaE = 1.0;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, MotivatedGif);
            }
        });

        // Defines the actions that will occur when the Excited Button is clicked.
        ExcitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.8;

                // Specifies the maximum energy level that will be used
                double MaE = 0.9;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float) MiE, (float) MaE, ExcitedGif);
            }
        });

        // Defines the actions that will occur when the Happy Button is clicked.
        HappyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.7;

                // Specifies the maximum energy level that will be used
                double MaE = 0.8;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, HappyGif);
            }
        });

        // Defines the actions that will occur when the Hopeful Button is clicked.
        HopefulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.6;

                // Specifies the maximum energy level that will be used
                double MaE = 0.7;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, HopefulGif);
            }
        });

        // Defines the actions that will occur when the Hopeless Button is clicked.
        HopelessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.0;

                // Specifies the maximum energy level that will be used
                double MaE = 0.2;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, HopelessGif);
            }
        });

        // Defines the actions that will occur when the Angry Button is clicked.
        AngryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.3;

                // Specifies the maximum energy level that will be used
                double MaE = 0.4;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, AngryGif);
            }
        });

        // Defines the actions that will occur when the Annoyed Button is clicked.
        SadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.2;

                // Specifies the maximum energy level that will be used
                double MaE = 0.3;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, SadGif);
            }
        });

        // Defines the actions that will occur when the Neutral Button is clicked.
        NeutralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Specifies the minimum energy level that will be used
                double MiE = 0.5;

                // Specifies the maximum energy level that will be used
                double MaE = 0.6;

                // Executes the getTrack method, with the previously declared values for the energy levels and a gif that matches the mood
                getTrack((float)MiE, (float) MaE, NeutralGif);
            }
        });

        // Defines the actions that will occur when the FastForward Button is clicked.
        FastForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prepares an array for devices available for playback.
                final Device[] devices;

                try {
                    //Executes the request to get the available devices for playback.
                    devices = getUsersAvailableDevicesRequest.execute();

                    // Prepares a string to be used. This will contain the Device ID for the user's computer, allowing for playback.
                    String DID = "";

                    // If the length of the array is greater than 0, then do the following:
                    if (devices.length > 0) {
                        // Device ID is now the ID for the first device.
                        DID = devices[0].getId();
                    }
                    // If the length of the array is 0, then do the following:
                    if (devices.length == 0) {
                        // Prints message asking for the spotify App to be launched on the user's computer.
                        System.out.println("Please launch the Spotify App on your computer");
                    }

                    // Prepares the request to skip track.
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();

                    // Executes this request.
                    skipUsersPlaybackToNextTrackRequest.execute();

                    // Waits 5 seconds to proceed. This is to avoid errors, as the artwork, track name and artist name requires a little bit of time to update.
                    Thread.sleep(5000);

                    // Executes the request to get the users current playing track information.
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Stores this ID on a string to make another request.
                    final String NPID = currentlyPlayingContext.getItem().getId();

                    // Stores this ID on a string to make another request.
                    final String id = NPID;

                    // Gets the current playing track based on the ID saved.
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();

                    // Executes the previous request.
                    final Track track = getTrackRequest.execute();

                    // Sets this label to the name of the current playing track.
                    CSongNameLBL.setText(track.getName());

                    // Sets this label to the album name of the current playing track.
                    CAlbumNameLBL.setText(track.getAlbum().getName());

                    // Prepares to get the main artist of the track, which is a specific object, hence the for loop.
                    for (ArtistSimplified artist : track.getArtists()) {
                        // Gets the name of the artist
                        artist.getName();
                        // Adds the name to the label.
                        CArtistNameLBL.setText(artist.getName());
                        // Stops the loop, as any other artist is not the main artist.
                        break;
                    }

                    // Gets the album image for the url of the current playing track. This normally retrives three URLs, the first being the one in the highest definition.
                    se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();

                    // Gets the image from the first URL.
                    String ImageURLBIG = String.valueOf(IURL[0].getUrl());

                    //Adds that URL to a new variable, for processing purposes.
                    URL getImageUrl = new URL(ImageURLBIG);

                    //Read the previous variable, preparing an image buffer for it to be processed.
                    BufferedImage ImageBuffer = ImageIO.read(getImageUrl);

                    // Resized that image to 200x200, so it fits the frame nicely.
                    Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

                    // Gets the resized image, and transforms it into an Image Icon.
                    ImageIcon IA = new ImageIcon(ResizedAlbumCover);

                    // Adds the resized image to its label.
                    ImageLBL.setIcon(IA);

                    // Switches the Play/Pause icon to PauseIMG.
                    Pause.setIcon(PauseIMG);
                }

                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Parsing.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the GoBack Button is clicked.
        GoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prepares an array for devices available for playback.
                final Device[] devices;

                try {
                    //Executes the request to get the available devices for playback.
                    devices = getUsersAvailableDevicesRequest.execute();

                    // Prepares a string to be used. This will contain the Device ID for the user's computer, allowing for playback.
                    String DID = "";

                    // If the length of the array is greater than 0, then do the following:
                    if (devices.length > 0) {
                        // Device ID is now the ID for the first device.
                        DID = devices[0].getId();
                    }
                    // If the length of the array is 0, then do the following:
                    if (devices.length == 0) {
                        // Prints message asking for the spotify App to be launched on the user's computer.
                        System.out.println("Please launch the Spotify App on your computer");
                    }

                    // Prepares the request to skip track.
                    final SkipUsersPlaybackToPreviousTrackRequest skipUsersPlaybackToPreviousTrackRequest = spotifyApi
                            .skipUsersPlaybackToPreviousTrack()
                            .device_id(DID)
                            .build();

                    // Executes this request.
                    skipUsersPlaybackToPreviousTrackRequest.execute();

                    // Waits 5 seconds to proceed. This is to avoid errors, as the artwork, track name and artist name requires a little bit of time to update.
                    Thread.sleep(5000);

                    // Executes the request to get the users current playing track information.
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Stores this ID on a string to make another request.
                    final String NPID = currentlyPlayingContext.getItem().getId();

                    // Stores this ID on a string to make another request.
                    final String id = NPID;

                    // Gets the current playing track based on the ID saved.
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();

                    // Executes the previous request.
                    final Track track = getTrackRequest.execute();

                    // Sets this label to the name of the current playing track.
                    CSongNameLBL.setText(track.getName());

                    // Sets this label to the album name of the current playing track.
                    CAlbumNameLBL.setText(track.getAlbum().getName());

                    // Prepares to get the main artist of the track, which is a specific object, hence the for loop.
                    for (ArtistSimplified artist : track.getArtists()) {
                        // Gets the name of the artist
                        artist.getName();
                        // Adds the name to the label.
                        CArtistNameLBL.setText(artist.getName());
                        // Stops the loop, as any other artist is not the main artist.
                        break;
                    }

                    // Gets the album image for the url of the current playing track. This normally retrieves three URLs, the first being the one in the highest definition.
                    se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();

                    // Gets the image from the first URL.
                    String ImageURLBIG = String.valueOf(IURL[0].getUrl());

                    //Adds that URL to a new variable, for processing purposes.
                    URL getImageUrl = new URL(ImageURLBIG);

                    //Read the previous variable, preparing an image buffer for it to be processed.
                    BufferedImage ImageBuffer = ImageIO.read(getImageUrl);

                    // Resized that image to 200x200, so it fits the frame nicely.
                    Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

                    // Gets the resized image, and transforms it into an Image Icon.
                    ImageIcon IA = new ImageIcon(ResizedAlbumCover);

                    // Adds the resized image to its label.
                    ImageLBL.setIcon(IA);

                    // Switches the Play/Pause icon to PauseIMG.
                    Pause.setIcon(PauseIMG);


                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Parsing.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the Pause Button is clicked.
        Pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prepares an array for devices available for playback.
                final Device[] devices;

                try {
                    //Executes the request to get the available devices for playback.
                    devices = getUsersAvailableDevicesRequest.execute();

                    // Prepares a string to be used. This will contain the Device ID for the user's computer, allowing for playback.
                    String DID = "";

                    // If the length of the array is greater than 0, then do the following:
                    if (devices.length > 0) {
                        // Device ID is now the ID for the first device.
                        DID = devices[0].getId();
                    }
                    // If the length of the array is 0, then do the following:
                    if (devices.length == 0) {
                        // Prints message asking for the spotify App to be launched on the user's computer.
                        System.out.println("Please launch the Spotify App on your computer");
                    }

                    // Prepares a variable to store the string value of the icon of the pause button.
                    String CurrentIMG = Pause.getIcon().toString();

                    // Prepares a variable to store the path of the icon of the pause button.
                    ImageIcon PauseIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/PlayPause.png");

                    // Prepares a variable to store the path of the icon of the play button.
                    ImageIcon PlayIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Pause.png");

                    // If the current icon matches the pause image, then do the following:
                    if (CurrentIMG == PauseIMG.toString()){
                        // Prepares the request to pause the track.
                        final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
                                .device_id(DID)
                                .build();

                        // Executes the request.
                        pauseUsersPlaybackRequest.execute();

                        // Switch icon to PlayIMG
                        Pause.setIcon(PlayIMG);

                    }
                    // Else, do the following
                    else {
                        // Prepares the request to play the track.
                        final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
                                .startResumeUsersPlayback()
                                .device_id(DID)
                                .build();

                        // Executes the request.
                        startResumeUsersPlaybackRequest.execute();

                        // Switch icon to PauseIMG
                        Pause.setIcon(PauseIMG);
                    }
                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the  Option5Button is clicked.
        Option5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Changes the background color of these components to 225,220,210.
                MainUI.setBackground(new Color(225, 220, 210));
                CompanyPanel.setBackground(new Color(225, 220, 210));
                PlaybackPanel.setBackground(new Color(225, 220, 210));
                OptionsPanel.setBackground(new Color(225, 220, 210));

                // Changes the foreground color of these components to 92,82,63.
                MoodifyLogoLabel.setForeground(new Color(92, 82, 63));
            }
        });

        // Defines the actions that will occur when the  Option4Button is clicked.
        Option4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Changes the background color of these components to 92,82,63.
                MainUI.setBackground(new Color(92, 82, 63));
                CompanyPanel.setBackground(new Color(92, 82, 63));
                PlaybackPanel.setBackground(new Color(92, 82, 63));
                OptionsPanel.setBackground(new Color(92, 82, 63));
                // Changes the foreground color of these components to 225,220,210.
                MoodifyLogoLabel.setForeground(new Color(225, 220, 210));
            }
        });

        // Defines the actions that will occur when the  Option7Button is clicked.
        Option7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prepares the request to get the users current playing track information.
                final CurrentlyPlayingContext currentlyPlayingContext;

                try {
                    // Executes the request to get the users current playing track information.
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Stores this ID on a string to make another request.
                    final String NPID = currentlyPlayingContext.getItem().getId();

                    // Stores this ID on a string to make another request.
                    final String id = NPID;

                    // Gets the current playing track based on the ID saved.
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();

                    // Executes the previous request.
                    final Track track = getTrackRequest.execute();

                    // Gets the id for the track.
                    String CID = track.getId();

                    // Prepares request to add track to user's saved track playlist.
                    final SaveTracksForUserRequest saveTracksForUserRequest = spotifyApi.saveTracksForUser(CID)
                            .build();

                    // Executes the previous request.
                    saveTracksForUserRequest.execute();

                    // Displays diaglog window confirming action.
                    JOptionPane.showMessageDialog(null, "Current track is now added to user's liked songs");

                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the  Option8Button is clicked.
        Option8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prepares the request to get the users current playing track information.
                final CurrentlyPlayingContext currentlyPlayingContext;
                try {
                    // Executes the request to get the users current playing track information.
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Stores this ID on a string to make another request.
                    final String NPID = currentlyPlayingContext.getItem().getId();

                    // Stores this ID on a string to make another request.
                    final String id = NPID;

                    // Gets the current playing track based on the ID saved.
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();

                    // Executes the previous request.
                    final Track track = getTrackRequest.execute();

                    // Gets the id for the track.
                    String CID = track.getId();

                    // Prepares request to remove track to user's saved track playlist.
                    final RemoveUsersSavedTracksRequest removeUsersSavedTracksRequest = spotifyApi
                            .removeUsersSavedTracks(CID)
                            .build();

                    // Executes the previous request.
                    removeUsersSavedTracksRequest.execute();

                    // Displays diaglog window confirming action.
                    JOptionPane.showMessageDialog(null, "Current track is now removed from user's liked songs");

                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the  Option6Button is clicked.
        Option6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Prepares the request to get the users current playing track information.
                    final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                            spotifyApi.getInformationAboutUsersCurrentPlayback()
                                    .build();

                    // Executes the request to get the users current playing track information.
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Gets the current shuffle state.
                    boolean Shuffle = currentlyPlayingContext.getShuffle_state();

                    // Prepares the request to toggle users current shuffle state.
                    final ToggleShuffleForUsersPlaybackRequest toggleShuffleForUsersPlaybackRequest;

                    // If shuffle equals to false
                    if (Shuffle == false){
                        // Prepares the request to toggle users current shuffle state to true.
                        toggleShuffleForUsersPlaybackRequest = spotifyApi
                                .toggleShuffleForUsersPlayback(true)
                                .build();

                        // Executes previous request.
                        toggleShuffleForUsersPlaybackRequest.execute();

                        // Changes the icon to ShuffleOn
                        Option6Button.setIcon(ShuffleOn);

                    }
                    // If shuffle does not equal to false
                    else{
                        // Prepares the request to toggle users current shuffle state to false.
                        toggleShuffleForUsersPlaybackRequest = spotifyApi
                                .toggleShuffleForUsersPlayback(false)
                                .build();

                        // Executes previous request.
                        toggleShuffleForUsersPlaybackRequest.execute();

                        // Changes the icon to ShuffleOff
                        Option6Button.setIcon(ShuffleOff);
                    }
                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Parsing.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the  Optio9Button is clicked.
        Option9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Prepares the request to get the users current playing track information.
                    final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                            spotifyApi.getInformationAboutUsersCurrentPlayback()
                                    .build();

                    // Executes the request to get the users current playing track information.
                    final CurrentlyPlayingContext currentlyPlayingContext;

                    // Executes the request to get the users current playing track information.
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    // Gets the current Repeat state.
                    String Repeat = currentlyPlayingContext.getRepeat_state();

                    // If repeat equals to off, then do the following:
                    if (Objects.equals(Repeat, "off")) {
                        // Prepares the request to toggle users current repeat state to context.
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("context")
                                .build();

                        // Executes previous request.
                        setRepeatModeOnUsersPlaybackRequest.execute();

                        // Changes the icon to RepeatCT
                        Option9Button.setIcon(RepeatCT);
                    }
                    // If repeat equals to context, then do the following:
                    if (Objects.equals(Repeat, "context")) {
                        // Prepares the request to toggle users current repeat state to track.
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("track")
                                .build();

                        // Executes previous request.
                        setRepeatModeOnUsersPlaybackRequest.execute();

                        // Changes the icon to RepeatT
                        Option9Button.setIcon(RepeatT);
                    }
                    // If repeat equals to track, then do the following:
                    if (Objects.equals(Repeat, "track")) {
                        // Prepares the request to toggle users current repeat state to off.
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("off")
                                .build();

                        // Executes previous request.
                        setRepeatModeOnUsersPlaybackRequest.execute();

                        // Changes the icon to RepeatFalse
                        Option9Button.setIcon(RepeatFalse);
                    }
                }
                // Standard exceptions for Input and Output.
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for the Spotify API, such as bad requests.
                catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Parsing.
                catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Defines the actions that will occur when the  Option1Button is clicked.
        Option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exits the program, with status 0, indicating no errors.
                System.exit(0);
            }
        });
    }

    private void getTrack(float MiE, float MaE, ImageIcon ExcitedGif) {
        // Displays the playback panel.
        PlaybackPanel.setVisible(true);

        //Sets the VideoLabel to a suiting GIF
        VideoLabel.setIcon(ExcitedGif);

        // Displays the videolabel.
        VideoLabel.setVisible(true);
        try {
            // Prepares the request to get the users available devices.
            final Device[] devices = getUsersAvailableDevicesRequest.execute();

            // Prepares a string to be used. This will contain the Device ID for the user's computer, allowing for playback.
            String DID = "";

            // If the length of the array is 0, then do the following:
            if (devices.length == 0) {
                // Prints message asking for the spotify App to be launched on the user's computer.
                System.out.println("Please launch the Spotify App on your computer");
            }

            // If the length of the array is greater than 0, then do the following:
            else {
                // Device ID is now the ID for the first device.
                DID = devices[0].getId();
            }

            // Executes request, asking for user's top tracks.
            final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

            // Prepares arraylist to store tracks IDs.
            ArrayList<String> TLAR = new ArrayList<String>();

            // Repeats this for every single item in list.
            for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                // Adds ID to previously declared array.
                TLAR.add(List.getId());
            }

            // Prepares the request to get recommendations.
            final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(1)
                    .max_popularity(80)
                    .min_popularity(10)
                    .seed_tracks(TLAR.get(0))
                    .seed_tracks(TLAR.get(1))
                    .seed_tracks(TLAR.get(2))
                    .seed_tracks(TLAR.get(3))
                    .seed_tracks(TLAR.get(4))
                    .seed_tracks(TLAR.get(5))
                    .seed_tracks(TLAR.get(6))
                    .seed_tracks(TLAR.get(7))
                    .seed_tracks(TLAR.get(8))
                    .seed_tracks(TLAR.get(9))
                    .min_energy(MiE)
                    .max_energy(MaE)
                    .target_popularity(50)
                    .build();

            // Executes previous request.
            final Recommendations recommendations = getRecommendationsRequest.execute();

            // Repeats this for every single item in SpotifyRecomendations.
            for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                // Prepares the request to add track to user's queue.
                final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                        .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                        .device_id(DID)
                        .build();
                // Executes previous request.
                addItemToUsersPlaybackQueueRequest.execute();
            }

            // Prepares the request to skip to next track in user's queue.
            final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                    .skipUsersPlaybackToNextTrack()
                    .device_id(DID)
                    .build();
            // Executes previous request.
            skipUsersPlaybackToNextTrackRequest.execute();

            // Waits 5 seconds to proceed. This is to avoid errors, as the artwork, track name and artist name requires a little bit of time to update.
            Thread.sleep(5000);

            // Executes the request to get the users current playing track information.
            final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

            // Stores this ID on a string to make another request.
            final String NPID = currentlyPlayingContext.getItem().getId();

            // Stores this ID on a string to make another request.
            final String id = NPID;

            // Gets the current playing track based on the ID saved.
            final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                    .build();

            // Executes the previous request.
            final Track track = getTrackRequest.execute();

            // Sets this label to the name of the current playing track.
            CSongNameLBL.setText(track.getName());

            // Sets this label to the album name of the current playing track.
            CAlbumNameLBL.setText(track.getAlbum().getName());

            // Prepares to get the main artist of the track, which is a specific object, hence the for loop.
            for (ArtistSimplified artist : track.getArtists()) {
                // Gets the name of the artist
                artist.getName();
                // Adds the name to the label.
                CArtistNameLBL.setText(artist.getName());
                // Stops the loop, as any other artist is not the main artist.
                break;
            }

            // Gets the album image for the url of the current playing track. This normally retrives three URLs, the first being the one in the highest definition.
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();

            // Gets the image from the first URL.
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());

            //Adds that URL to a new variable, for processing purposes.
            URL getImageUrl = new URL(ImageURLBIG);

            //Read the previous variable, preparing an image buffer for it to be processed.
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);

            // Resized that image to 200x200, so it fits the frame nicely.
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            // Gets the resized image, and transforms it into an Image Icon.
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);

            // Adds the resized image to its label.
            ImageLBL.setIcon(IA);

        }
        // Standard exceptions for Input and Output, Parsing, and for the Spotify API, such as bad requests.
        catch (IOException | SpotifyWebApiException | ParseException | InterruptedException p) {
            throw new RuntimeException(p);
        }
    }


    public static void main(String[] args) {
        // Code to add Moodify icon to taskbar.
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = StageOne.class.getClassLoader().getResource("Images/Logo.png");
        final Image image = defaultToolkit.getImage(imageResource);
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            taskbar.setIconImage(image);
        }
        catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        }
        catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        //Code to display MainUI.
        MainUI mainUI = new MainUI();
        JFrame frame = mainUI.getFrame();
        frame.setIconImage(image);
        frame.setContentPane(mainUI.MainUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    //Code automatically generated by IntelliJ IDEA, not used for current class.
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
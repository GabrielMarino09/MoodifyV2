//Main UI is a part of the package named StageOne.
package StageOne;

//Imports authorization.PKCE.PKCE.AuthorizationCodeRefresh, so it can make requests to the Spotify API by using the refresh token.
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;

//Imports the necessary libraries to connect to the Spotify API. This API was developed by Michael Thelin and maintained by Jonas Thelemann.
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.library.RemoveUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.library.SaveTracksForUserRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.*;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import se.michaelthelin.spotify.requests.data.player.ToggleShuffleForUsersPlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.SetRepeatModeOnUsersPlaybackRequest;

//Imports all the other necessary libraries to for MainUI.
import org.apache.hc.core5.http.ParseException;
import java.awt.Cursor;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


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

    private static final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
            spotifyApi.getInformationAboutUsersCurrentPlayback()
//                  .market(CountryCode.SE)
//                  .additionalTypes("track,episode")
                    .build();


    public MainUI() {
        this.frame = new JFrame("Moodify");
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
        ImageIcon PauseIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/PlayPause.png");
        ImageIcon ShuffleOn = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/ShuffleOn.png");
        ImageIcon ShuffleOff = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Shuffle.png");

        ImageIcon RepeatFalse = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Repeat.png");
        ImageIcon RepeatCT = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/RepeatContext.png");
        ImageIcon RepeatT = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/RepeatTrack.png");

        Pause.setIcon(PauseIMG);
        PlaybackPanel.setVisible(false);
        ImageIcon HappyGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Happy.gif");
        ImageIcon ExcitedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Excited.gif");
        ImageIcon MotivatedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Motivated.gif");
        ImageIcon HopefulGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Hopeful.gif");
        ImageIcon HopelessGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Hopeless.gif");
        ImageIcon AngryGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Angry.gif");
        ImageIcon SadGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Sad.gif");
        ImageIcon AnnoyedGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Annoyed.gif");
        ImageIcon NeutralGif = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/GIFs/Neutral.gif");

        final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                spotifyApi.getInformationAboutUsersCurrentPlayback()
                        .build();

        try {
            final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                if (currentlyPlayingContext == null) {
                    ;
                } else{
                    boolean Playing = currentlyPlayingContext.getIs_playing();
                    boolean Shuffle = currentlyPlayingContext.getShuffle_state();
                    String Repeat =  currentlyPlayingContext.getRepeat_state();

                    if (Shuffle == true){
                        Option6Button.setIcon(ShuffleOn);
                    } else{
                        Option6Button.setIcon(ShuffleOff);
                    }

                    if (Repeat == "track") {
                        Option9Button.setIcon(RepeatT);
                    }
                    if (Repeat == "context"){
                        Option9Button.setIcon(RepeatCT);
                    }
                    if (Repeat == "off") {
                        Option9Button.setIcon(RepeatFalse);
                    }


                    if (Playing == true){
                        PlaybackPanel.setVisible(true);
                        VideoLabel.setVisible(false);
                        final String NPID = currentlyPlayingContext.getItem().getId();
                        final String id = NPID;
                        final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                                .build();
                        final Track track = getTrackRequest.execute();

                        CSongNameLBL.setText(track.getName());
                        CAlbumNameLBL.setText(track.getAlbum().getName());
                        for (ArtistSimplified artist : track.getArtists()) {
                            artist.getName();
                            CArtistNameLBL.setText(artist.getName());
                            break;
                        }

                        se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();
                        String ImageURLBIG = String.valueOf(IURL[0].getUrl());
                        URL getImageUrl = new URL(ImageURLBIG);
                        BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
                        Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon IA = new ImageIcon(ResizedAlbumCover);
                        ImageLBL.setIcon(IA);
                        Pause.setIcon(PauseIMG);
                    }
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        Option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecentPlaylist recentPlaylist = null;
                try {
                    recentPlaylist = new RecentPlaylist(frame, "Moodify", true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
                recentPlaylist.getContentPane().add(recentPlaylist.RecentPlaylistPanel);
                recentPlaylist.setModal(true);
                recentPlaylist.pack();
                recentPlaylist.setVisible(true);
            }
        });
        Option3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseUI modalDB = new DatabaseUI(frame, "Moodify", true);
                modalDB.getContentPane().add(modalDB.DatabaseUIPanel);
                modalDB.setModal(true);
                modalDB.pack();
                modalDB.setVisible(true);
            }
        });

        AnnoyedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.4;
                double MaE = 0.5;
                getTrack((float)MiE, (float) MaE, AnnoyedGif);
            }
        });

        MotivatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double MiE = 0.9;
                double MaE = 1.0;
                getTrack((float)MiE, (float) MaE, MotivatedGif);
            }
        });
        ExcitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.8;
                double MaE = 0.9;
                getTrack((float) MiE, (float) MaE, ExcitedGif);
            }
        });

        HappyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.7;
                double MaE = 0.8;
                getTrack((float)MiE, (float) MaE, HappyGif);
            }
        });

        HopefulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.6;
                double MaE = 0.7;
                getTrack((float)MiE, (float) MaE, HopefulGif);
            }
        });

        HopelessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.0;
                double MaE = 0.2;
                getTrack((float)MiE, (float) MaE, HopelessGif);
            }
        });
        AngryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.3;
                double MaE = 0.4;
                getTrack((float)MiE, (float) MaE, AngryGif);
            }
        });

        AnnoyedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.4;
                double MaE = 0.5;
                getTrack((float) MiE, (float) MaE, AnnoyedGif);
            }
        });

        SadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double MiE = 0.2;
                double MaE = 0.3;
                getTrack((float)MiE, (float) MaE, SadGif);
            }
        });

        NeutralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double MiE = 0.5;
                double MaE = 0.6;
                getTrack((float)MiE, (float) MaE, NeutralGif);
            }
        });
        FastForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Device[] devices;
                try {
                    devices = getUsersAvailableDevicesRequest.execute();
                    String DID = "";
                    if (devices.length > 0) {
                        DID = devices[0].getId();
                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                    Thread.sleep(5000);
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                    final String NPID = currentlyPlayingContext.getItem().getId();
                    final String id = NPID;
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();
                    final Track track = getTrackRequest.execute();

                    CSongNameLBL.setText(track.getName());
                    CAlbumNameLBL.setText(track.getAlbum().getName());
                    for (ArtistSimplified artist : track.getArtists()) {
                        artist.getName();
                        CArtistNameLBL.setText(artist.getName());
                        break;
                    }

                    se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();
                    String ImageURLBIG = String.valueOf(IURL[0].getUrl());
                    URL getImageUrl = new URL(ImageURLBIG);
                    BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
                    Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon IA = new ImageIcon(ResizedAlbumCover);
                    ImageLBL.setIcon(IA);
                    Pause.setIcon(PauseIMG);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        GoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Device[] devices;
                try {
                    devices = getUsersAvailableDevicesRequest.execute();
                    String DID = "";
                    if (devices.length == 1) {
                        DID = devices[0].getId();
                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }
                    final SkipUsersPlaybackToPreviousTrackRequest skipUsersPlaybackToPreviousTrackRequest = spotifyApi
                            .skipUsersPlaybackToPreviousTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToPreviousTrackRequest.execute();

                    Thread.sleep(5000);
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                    final String NPID = currentlyPlayingContext.getItem().getId();
                    final String id = NPID;
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();
                    final Track track = getTrackRequest.execute();

                    CSongNameLBL.setText(track.getName());
                    CAlbumNameLBL.setText(track.getAlbum().getName());
                    for (ArtistSimplified artist : track.getArtists()) {
                        artist.getName();
                        CArtistNameLBL.setText(artist.getName());
                        break;
                    }

                    se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();
                    String ImageURLBIG = String.valueOf(IURL[0].getUrl());
                    URL getImageUrl = new URL(ImageURLBIG);
                    BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
                    Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon IA = new ImageIcon(ResizedAlbumCover);
                    ImageLBL.setIcon(IA);
                    Pause.setIcon(PauseIMG);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Device[] devices;
                try {
                    devices = getUsersAvailableDevicesRequest.execute();
                    String DID = "";
                    if (devices.length == 1) {
                        DID = devices[0].getId();
                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }
                    String CurrentIMG = Pause.getIcon().toString();
                    ImageIcon PauseIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/PlayPause.png");
                    ImageIcon PlayIMG = new ImageIcon("/Users/gabriel/IdeaProjects/MoodifyV2/src/Images/Icons/Pause.png");
                    if (CurrentIMG == PauseIMG.toString()){
                        final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
                                .device_id(DID)
                                .build();
                        pauseUsersPlaybackRequest.execute();
                        Pause.setIcon(PlayIMG);

                    }
                    else {
                        final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
                                .startResumeUsersPlayback()
                                .device_id(DID)
                                .build();
                        startResumeUsersPlaybackRequest.execute();
                        Pause.setIcon(PauseIMG);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Option5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainUI.setBackground(new Color(225, 220, 210));
                CompanyPanel.setBackground(new Color(225, 220, 210));
                PlaybackPanel.setBackground(new Color(225, 220, 210));
                MoodifyLogoLabel.setForeground(new Color(92, 82, 63));
                OptionsPanel.setBackground(new Color(225, 220, 210));

            }
        });
        Option4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    MainUI.setBackground(new Color(92, 82, 63));
                    CompanyPanel.setBackground(new Color(92, 82, 63));
                    PlaybackPanel.setBackground(new Color(92, 82, 63));
                    MoodifyLogoLabel.setForeground(new Color(225, 220, 210));
                    OptionsPanel.setBackground(new Color(92, 82, 63));
            }
        });

        Option7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final CurrentlyPlayingContext currentlyPlayingContext;
                try {
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                    final String NPID = currentlyPlayingContext.getItem().getId();
                    final String id = NPID;
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();
                    final Track track = getTrackRequest.execute();
                    String CID = track.getId();
                    final SaveTracksForUserRequest saveTracksForUserRequest = spotifyApi.saveTracksForUser(CID)
                            .build();

                    saveTracksForUserRequest.execute();
                    JOptionPane.showMessageDialog(null, "Current track is now added to user's liked songs");

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Option8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final CurrentlyPlayingContext currentlyPlayingContext;
                try {
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                    final String NPID = currentlyPlayingContext.getItem().getId();
                    final String id = NPID;
                    final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                            .build();
                    final Track track = getTrackRequest.execute();
                    String CID = track.getId();
                    final RemoveUsersSavedTracksRequest removeUsersSavedTracksRequest = spotifyApi
                            .removeUsersSavedTracks(CID)
                            .build();

                    removeUsersSavedTracksRequest.execute();
                    JOptionPane.showMessageDialog(null, "Current track is now removed from user's liked songs");

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Option6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                            spotifyApi.getInformationAboutUsersCurrentPlayback()
                                    .build();
                    final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

                    boolean Shuffle = currentlyPlayingContext.getShuffle_state();

                    final ToggleShuffleForUsersPlaybackRequest toggleShuffleForUsersPlaybackRequest;

                    if (Shuffle == false){
                        toggleShuffleForUsersPlaybackRequest = spotifyApi
                                .toggleShuffleForUsersPlayback(true)
                                .build();
                        toggleShuffleForUsersPlaybackRequest.execute();
                        Option6Button.setIcon(ShuffleOn);

                    } else{
                        toggleShuffleForUsersPlaybackRequest = spotifyApi
                                .toggleShuffleForUsersPlayback(false)
                                .build();
                        toggleShuffleForUsersPlaybackRequest.execute();
                        Option6Button.setIcon(ShuffleOff);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Option9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                            spotifyApi.getInformationAboutUsersCurrentPlayback()
                                    .build();
                    final CurrentlyPlayingContext currentlyPlayingContext;
                    currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
                    String Repeat = currentlyPlayingContext.getRepeat_state();

                    if (Objects.equals(Repeat, "off")) {
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("context")
                                .build();
                        setRepeatModeOnUsersPlaybackRequest.execute();
                        Option9Button.setIcon(RepeatCT);
                    }
                    if (Objects.equals(Repeat, "context")) {
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("track")
                                .build();
                        setRepeatModeOnUsersPlaybackRequest.execute();
                        Option9Button.setIcon(RepeatT);
                    }
                    if (Objects.equals(Repeat, "track")) {
                        final SetRepeatModeOnUsersPlaybackRequest setRepeatModeOnUsersPlaybackRequest = spotifyApi
                                .setRepeatModeOnUsersPlayback("off")
                                .build();
                        setRepeatModeOnUsersPlaybackRequest.execute();
                        Option9Button.setIcon(RepeatFalse);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SpotifyWebApiException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        Option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void getTrack(float MiE, float MaE, ImageIcon ExcitedGif) {
        PlaybackPanel.setVisible(true);
        VideoLabel.setIcon(ExcitedGif);
        VideoLabel.setVisible(true);
        try {
            final Device[] devices = getUsersAvailableDevicesRequest.execute();

            String DID = "";
            if (devices.length == 0) {
                System.out.println("Please launch the Spotify App on your computer");
            } else {
                DID = devices[0].getId();
            }


            final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

            final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

            ArrayList<String> TLAR = new ArrayList<String>();
            for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                TLAR.add(List.getId());
            }


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

            final Recommendations recommendations = getRecommendationsRequest.execute();

            for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                        .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                        .device_id(DID)
                        .build();
                addItemToUsersPlaybackQueueRequest.execute();
            }

            final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                    .skipUsersPlaybackToNextTrack()
                    .device_id(DID)
                    .build();
            skipUsersPlaybackToNextTrackRequest.execute();

            Thread.sleep(5000);
            final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
            final String NPID = currentlyPlayingContext.getItem().getId();
            final String id = NPID;
            final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                    .build();
            final Track track = getTrackRequest.execute();

            CSongNameLBL.setText(track.getName());
            CAlbumNameLBL.setText(track.getAlbum().getName());
            for (ArtistSimplified artist : track.getArtists()) {
                artist.getName();
                CArtistNameLBL.setText(artist.getName());
                break;
            }

            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            ImageLBL.setIcon(IA);


        } catch (IOException | SpotifyWebApiException | ParseException | InterruptedException p) {
            throw new RuntimeException(p);
        }
    }


    public static void main(String[] args) {
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
        MainUI mainUI = new MainUI();
        JFrame frame = mainUI.getFrame();
        frame.setIconImage(image);
        frame.setContentPane(mainUI.MainUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        /*
        while (true){
        }
         */
        // TODO: place custom component creation code here
    }
}
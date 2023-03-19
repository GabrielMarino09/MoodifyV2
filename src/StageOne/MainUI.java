
package StageOne;

import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.AddItemToUsersPlaybackQueueRequest;
import se.michaelthelin.spotify.requests.data.player.GetUsersAvailableDevicesRequest;
import se.michaelthelin.spotify.requests.data.player.SkipUsersPlaybackToNextTrackRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainUI {
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
    private JPanel MainUI;
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
    private static final String userId = "2o6dxgdhlsl9wdmtahmuy3vm6";
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

    private static final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
            .limit(10)
//          .offset(0)
            .time_range("long_term")
            .build();
    private static final GetUsersAvailableDevicesRequest getUsersAvailableDevicesRequest = spotifyApi
            .getUsersAvailableDevices()
            .build();

    public MainUI() {


        Option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello From Option 1 Button");
            }
        });
        Option3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Moodify");
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                final URL imageResource = StageOne.class.getClassLoader().getResource("Images/Logo.png");
                final Image image = defaultToolkit.getImage(imageResource);
                final Taskbar taskbar = Taskbar.getTaskbar();
                try {
                    taskbar.setIconImage(image);
                } catch (final UnsupportedOperationException a) {
                    System.out.println("The os does not support: 'taskbar.setIconImage'");
                } catch (final SecurityException a) {
                    System.out.println("There was a security exception for: 'taskbar.setIconImage'");
                }
                frame.setIconImage(image);
                frame.setContentPane(new DatabaseUI().DatabaseUIPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
        HappyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();
                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.7;
                    double MaE = 0.8;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });
        ExcitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.8;
                    double MaE = 0.9;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });
        MotivatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.9;
                    double MaE = 1.0;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });
        HappyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.7;
                    double MaE = 0.8;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });
        HopefulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.5;
                    double MaE = 0.6;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });

        HappyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.0;
                    double MaE = 0.2;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });

        AngryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.3;
                    double MaE = 0.4;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });

        SadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.2;
                    double MaE = 0.3;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });

        AnnoyedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final Device[] devices = getUsersAvailableDevicesRequest.execute();

                    String DID = "";
                    System.out.println("Length: " + devices.length);
                    if (devices.length == 1) {
                        System.out.println("Device ID: " + devices[0].getId());
                        DID = devices[0].getId();


                    }
                    if (devices.length == 0) {
                        System.out.println("Please launch the Spotify App on your computer");
                    }
                    if (devices.length > 1) {
                        System.out.println("Please close every other spotify app but your computer's");
                    }


                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

                    System.out.println("Total: " + trackPaging.getTotal());

                    final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();

                    ArrayList<String> TLAR = new ArrayList<String>();
                    for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
                        System.out.println("Name: " + List.getName());
                        System.out.println("ID: " + List.getId());
                        System.out.println("Uri: " + List.getUri());
                        TLAR.add(List.getId());
                    }

                    double MiE = 0.4;
                    double MaE = 0.5;
                    final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                            .limit(10)
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
                            .min_energy((float) MiE)
                            .max_energy((float) MaE)
                            .target_popularity(50)
                            .build();

                    final Recommendations recommendations = getRecommendationsRequest.execute();

                    for (TrackSimplified SpotifyRecommendations : recommendations.getTracks()) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Recommendations:");
                        //------------------------------------------------------------------------------------------------------
                        System.out.println("Recommended Track Name: " + SpotifyRecommendations.getName());
                        System.out.println("Recommended Track ID: " + SpotifyRecommendations.getId());
                        System.out.println("Recommended Track Uri: " + SpotifyRecommendations.getUri());
                        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                                .addItemToUsersPlaybackQueue(SpotifyRecommendations.getUri())
                                .device_id(DID)
                                .build();
                        addItemToUsersPlaybackQueueRequest.execute();
                        //------------------------------------------------------------------------------------------------------
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
                            .skipUsersPlaybackToNextTrack()
                            .device_id(DID)
                            .build();
                    skipUsersPlaybackToNextTrackRequest.execute();

                } catch (IOException | SpotifyWebApiException | ParseException p) {
                    throw new RuntimeException(p);
                }
            }
        });



    }


    public static void main(String[] args) {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
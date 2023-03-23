package StageOne;

import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import com.sun.jdi.ArrayType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.AddItemToUsersPlaybackQueueRequest;
import se.michaelthelin.spotify.requests.data.player.SkipUsersPlaybackToNextTrackRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Random;

import java.awt.Cursor;
import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static StageOne.MainUI.GGID;

public class RecentPlaylist {
    public JPanel RecentPlaylistPanel;
    private JPanel DateTimePanel;
    private JLabel DayLabel;
    private JLabel DateLabel;
    private JLabel TimeLabel;
    private JPanel CompanyPanel;
    private JLabel MoodifyLogoLabel;
    private JPanel ListNamePanel;
    private JPanel RRPanelRRPanel;
    private JLabel SongHeadingLabel;
    private JButton BackButton;
    private JButton RR1;
    private JButton RR3;
    private JButton RR2;
    private JButton RR4;
    private JButton RR5;
    private JLabel MoodifyPlaylists;
    private JButton MP1;
    private JButton MP2;
    private JButton MP3;
    private JButton MP4;
    private JButton MP5;

    private static final String userId = GGID();

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
            .time_range("short_term")
            .build();

    public RecentPlaylist() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

        System.out.println("Total: " + trackPaging.getTotal());

        final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();
        int max1= 19, min1=0;

        int int_random0 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));

        ArrayList<String> TLAR = new ArrayList<String>();
        for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
            TLAR.add(List.getId());

        }
        final GetTrackRequest getTrackRequestT1 = spotifyApi.getTrack(TLAR.get(int_random0))
                .build();
        final Track track;
        try {
            track = getTrackRequestT1.execute();
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR1.setIcon(IA);
            RR1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            RR1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  //Desktop.getDesktop().browse(URI.create(trackURLRR1));

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int int_random1 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        while (int_random1 == int_random0){
            int_random1 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        }


        final GetTrackRequest getTrackRequestT2 = spotifyApi.getTrack(TLAR.get(int_random1))
                .build();
        final Track track2;
        try {
            track2 = getTrackRequestT2.execute();
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track2.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR2.setIcon(IA);
            RR2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        int int_random2 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        while (int_random2 == int_random0 || int_random2 == int_random1){
            int_random2 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        }
        final GetTrackRequest getTrackRequestT3 = spotifyApi.getTrack(TLAR.get(int_random2))
                .build();
        final Track track3;
        try {
            track3 = getTrackRequestT3.execute();
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track3.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR3.setIcon(IA);
            RR3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int int_random3 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        while (int_random3 == int_random0 || int_random3 == int_random1 || int_random3 == int_random2){
            int_random3 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        }
        final GetTrackRequest getTrackRequestT4 = spotifyApi.getTrack(TLAR.get(int_random3))
                .build();
        final Track track4;
        try {
            track4 = getTrackRequestT4.execute();
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track4.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR4.setIcon(IA);
            RR4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int int_random4 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        while (int_random4 == int_random0 || int_random4 == int_random1 || int_random4 == int_random2 ||int_random4 == int_random3){
            int_random4 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
        }
        final GetTrackRequest getTrackRequestT5 = spotifyApi.getTrack(TLAR.get(int_random4))
                .build();
        final Track track5;
        try {
            track5 = getTrackRequestT5.execute();
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track5.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR5.setIcon(IA);
            RR5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        final GetUsersFollowedArtistsRequest getUsersFollowedArtistsRequest = spotifyApi
                .getUsersFollowedArtists(Artist)
                .build();

        getUsersFollowedArtistsRequest.execute();
        final PagingCursorbased<Artist> artistPagingCursorbased = getUsersFollowedArtistsRequest.execute();
        try {
            Artist[] ArtistName = artistPagingCursorbased.getItems();
            System.out.println("Artists Found: " + ArtistName.length);
            int max= ArtistName.length-1, min=0;

            int int_random01 = (min + (int)(Math.random() * ((max - min) + 1)));
            int int_random02 = (min + (int)(Math.random() * ((max - min) + 1)));

            while (int_random01 == int_random02){
                int_random02 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));
            }

            int int_random03 = (min + (int)(Math.random() * ((max - min) + 1)));

            while (int_random03 == int_random01 || int_random3 == int_random02) {
                int_random03 = (min1 + (int) (Math.random() * ((max1 - min1) + 1)));
            }

            int int_random04 = (min + (int)(Math.random() * ((max - min) + 1)));

            while (int_random04 == int_random01 || int_random04 == int_random02 || int_random04 == int_random03) {
                int_random04 = (min1 + (int) (Math.random() * ((max1 - min1) + 1)));
            }

            int int_random05 = (min + (int)(Math.random() * ((max - min) + 1)));

            while (int_random05 == int_random01 || int_random05 == int_random02 || int_random05 == int_random03 ||int_random05 == int_random04) {
                int_random05 = (min1 + (int) (Math.random() * ((max1 - min1) + 1)));
            }



            System.out.println("-------------------------------------------------------------------------------------");
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = ArtistName[int_random01].getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG);
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            MP1.setIcon(IA);
            MP1.setCursor(new Cursor(Cursor.HAND_CURSOR));


            IURL = ArtistName[int_random02].getImages();
            String ImageURLBIG2 = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG2);
            URL getImageUrl2 = new URL(ImageURLBIG2);
            BufferedImage ImageBuffer2 = ImageIO.read(getImageUrl2);
            Image ResizedAlbumCover2 = ImageBuffer2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA2 = new ImageIcon(ResizedAlbumCover2);
            MP3.setIcon(IA2);
            MP3.setCursor(new Cursor(Cursor.HAND_CURSOR));



            IURL = ArtistName[int_random03].getImages();
            String ImageURLBIG3 = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG3);
            URL getImageUrl3 = new URL(ImageURLBIG3);
            BufferedImage ImageBuffer3 = ImageIO.read(getImageUrl3);
            Image ResizedAlbumCover3 = ImageBuffer3.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA3 = new ImageIcon(ResizedAlbumCover3);
            MP4.setIcon(IA3);
            MP4.setCursor(new Cursor(Cursor.HAND_CURSOR));


            IURL = ArtistName[int_random04].getImages();
            String ImageURLBIG4 = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG4);
            URL getImageUrl4 = new URL(ImageURLBIG4);
            BufferedImage ImageBuffer4 = ImageIO.read(getImageUrl4);
            Image ResizedAlbumCover4 = ImageBuffer4.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA4 = new ImageIcon(ResizedAlbumCover4);
            MP2.setIcon(IA4);
            MP2.setCursor(new Cursor(Cursor.HAND_CURSOR));


            IURL = ArtistName[int_random05].getImages();
            String ImageURLBIG5 = String.valueOf(IURL[0].getUrl());
            System.out.println("Image: " + ImageURLBIG5);
            URL getImageUrl5 = new URL(ImageURLBIG5);
            BufferedImage ImageBuffer5 = ImageIO.read(getImageUrl5);
            Image ResizedAlbumCover5 = ImageBuffer5.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA5 = new ImageIcon(ResizedAlbumCover5);
            MP5.setIcon(IA5);

            MP5.setCursor(new Cursor(Cursor.HAND_CURSOR));


        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
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
        frame.setContentPane(new RecentPlaylist().RecentPlaylistPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

//RecentPlaylist is a part of the package named StageOne.
package StageOne;

import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
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
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import static StageOne.MainUI.GGID;

public class RecentPlaylist extends JDialog {
    public JPanel RecentPlaylistPanel;
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
    private JPanel RP;
    private JLabel RRLABEL1;
    private JLabel RRLABEL2;
    private JLabel RRLABEL3;
    private JLabel RRLABEL4;
    private JLabel RRLABEL5;

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

    public RecentPlaylist(JFrame frame, String title, boolean modal) throws IOException, ParseException, SpotifyWebApiException {
        super(frame, "Moodify", true);
        final Paging<se.michaelthelin.spotify.model_objects.specification.Track> trackPaging = getUsersTopTracksRequest.execute();

        final Paging<se.michaelthelin.spotify.model_objects.specification.Track> Tracklist = getUsersTopTracksRequest.execute();
        int max1= 19, min1=0;

        int int_random0 = (min1 + (int)(Math.random() * ((max1 - min1) + 1)));

        ArrayList<String> TLAR = new ArrayList<String>();
        for (se.michaelthelin.spotify.model_objects.specification.Track List : Tracklist.getItems()) {
            TLAR.add(List.getId());

        }
        final GetTrackRequest getTrackRequestT1 = spotifyApi.getTrack(TLAR.get(int_random0))
                .build();
        final Track track1;
        try {
            track1 = getTrackRequestT1.execute();
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] TR = new ExternalUrl[]{track1.getExternalUrls()};
            String TURL = (TR[0].getExternalUrls()).toString();
            String PTURL = TURL.replace("{spotify=","");
            String FTURL = PTURL.replace("}","");
            RR1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FTURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track1.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            RR1.setIcon(IA);
            RR1.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] TR = new ExternalUrl[]{track2.getExternalUrls()};
            String TURL = (TR[0].getExternalUrls()).toString();
            String PTURL = TURL.replace("{spotify=","");
            String FTURL = PTURL.replace("}","");
            RR2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FTURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track2.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
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
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] TR = new ExternalUrl[]{track3.getExternalUrls()};
            String TURL = (TR[0].getExternalUrls()).toString();
            String PTURL = TURL.replace("{spotify=","");
            String FTURL = PTURL.replace("}","");
            RR3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FTURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track3.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
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
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] TR = new ExternalUrl[]{track4.getExternalUrls()};
            String TURL = (TR[0].getExternalUrls()).toString();
            String PTURL = TURL.replace("{spotify=","");
            String FTURL = PTURL.replace("}","");
            RR4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FTURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track4.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
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
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] TR = new ExternalUrl[]{track5.getExternalUrls()};
            String TURL = (TR[0].getExternalUrls()).toString();
            String PTURL = TURL.replace("{spotify=","");
            String FTURL = PTURL.replace("}","");
            RR5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FTURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = track5.getAlbum().getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
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
            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] AMP1 = new ExternalUrl[]{ArtistName[int_random01].getExternalUrls()};
            String AURLMP1 = (AMP1[0].getExternalUrls()).toString();
            String PAURLMP1 = AURLMP1.replace("{spotify=","");
            String FAURLMP1 = PAURLMP1.replace("}","");
            MP1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FAURLMP1));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            se.michaelthelin.spotify.model_objects.specification.Image[] IURL = ArtistName[int_random01].getImages();
            String ImageURLBIG = String.valueOf(IURL[0].getUrl());
            URL getImageUrl = new URL(ImageURLBIG);
            BufferedImage ImageBuffer = ImageIO.read(getImageUrl);
            Image ResizedAlbumCover = ImageBuffer.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA = new ImageIcon(ResizedAlbumCover);
            MP1.setIcon(IA);
            MP1.setCursor(new Cursor(Cursor.HAND_CURSOR));


            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] AMP3 = new ExternalUrl[]{ArtistName[int_random02].getExternalUrls()};
            String AURLMP3 = (AMP3[0].getExternalUrls()).toString();
            String PAURLMP3 = AURLMP3.replace("{spotify=","");
            String FAURLMP3 = PAURLMP3.replace("}","");
            MP3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FAURLMP3));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            IURL = ArtistName[int_random02].getImages();
            String ImageURLBIG2 = String.valueOf(IURL[0].getUrl());
            URL getImageUrl2 = new URL(ImageURLBIG2);
            BufferedImage ImageBuffer2 = ImageIO.read(getImageUrl2);
            Image ResizedAlbumCover2 = ImageBuffer2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA2 = new ImageIcon(ResizedAlbumCover2);
            MP3.setIcon(IA2);
            MP3.setCursor(new Cursor(Cursor.HAND_CURSOR));



            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] AMP4 = new ExternalUrl[]{ArtistName[int_random03].getExternalUrls()};
            String AURLMP4 = (AMP4[0].getExternalUrls()).toString();
            String PAURLMP4 = AURLMP4.replace("{spotify=","");
            String FAURLMP4 = PAURLMP4.replace("}","");
            MP4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FAURLMP4));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            IURL = ArtistName[int_random03].getImages();
            String ImageURLBIG3 = String.valueOf(IURL[0].getUrl());
            URL getImageUrl3 = new URL(ImageURLBIG3);
            BufferedImage ImageBuffer3 = ImageIO.read(getImageUrl3);
            Image ResizedAlbumCover3 = ImageBuffer3.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA3 = new ImageIcon(ResizedAlbumCover3);
            MP4.setIcon(IA3);
            MP4.setCursor(new Cursor(Cursor.HAND_CURSOR));

            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] AMP2 = new ExternalUrl[]{ArtistName[int_random04].getExternalUrls()};
            String AURL = (AMP2[0].getExternalUrls()).toString();
            String PAURL = AURL.replace("{spotify=","");
            String FAURL = PAURL.replace("}","");
            MP2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FAURL));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            IURL = ArtistName[int_random04].getImages();
            String ImageURLBIG4 = String.valueOf(IURL[0].getUrl());
            URL getImageUrl4 = new URL(ImageURLBIG4);
            BufferedImage ImageBuffer4 = ImageIO.read(getImageUrl4);
            Image ResizedAlbumCover4 = ImageBuffer4.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA4 = new ImageIcon(ResizedAlbumCover4);
            MP2.setIcon(IA4);
            MP2.setCursor(new Cursor(Cursor.HAND_CURSOR));

            se.michaelthelin.spotify.model_objects.specification.ExternalUrl[] AMP5 = new ExternalUrl[]{ArtistName[int_random05].getExternalUrls()};
            String AURLMP5 = (AMP5[0].getExternalUrls()).toString();
            String PAURLMP5 = AURLMP5.replace("{spotify=","");
            String FAURLMP5 = PAURLMP5.replace("}","");
            MP5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create(FAURLMP5));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            IURL = ArtistName[int_random05].getImages();
            String ImageURLBIG5 = String.valueOf(IURL[0].getUrl());
            URL getImageUrl5 = new URL(ImageURLBIG5);
            BufferedImage ImageBuffer5 = ImageIO.read(getImageUrl5);
            Image ResizedAlbumCover5 = ImageBuffer5.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon IA5 = new ImageIcon(ResizedAlbumCover5);
            MP5.setIcon(IA5);

            MP5.setCursor(new Cursor(Cursor.HAND_CURSOR));

            //Code that allows the BackButton to close the RecentPlaylist Window and returns to the MainUI.
            BackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        // Standard exceptions for Input and Output.
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

        public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
            //Code to display RecentPlaylist.
            JFrame frame = new JFrame("Moodify");
            RecentPlaylist ui = new RecentPlaylist(frame, "Moodify", true);
            Image image = getImage();
            frame.setIconImage(image);
            frame.setContentPane(ui.RP);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
        }
    private static Image getImage() {
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
        return image;
    }

    //Code automatically generated by IntelliJ IDEA, not used for current class.
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

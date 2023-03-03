//Nests Authorization code inside TheGrid
package TheGrid;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;

import java.io.IOException;

public class Fetch {

    //UserID present at the User's profile page
    private static final String userId = "2o6dxgdhlsl9wdmtahmuy3vm6";

    //Access token retrieved from the AuthorizationCodeRefresh Class
    private static final String accessToken = "BQBBUqHv3AgcdhQDK2PmZ80qsOCGq5eDE5qIgumx35DG1tfKQj8m5et5ljtROoK_napy8tIKRTd6GOKlXaP3B7CxOnogSM-TGkHv36IeZJxJIBoR0w5f2Q6EJBtXh2NsstN2aPbdr9oVwz4SdA4Kez1wqQkpN3fv-7Wzxn3nod16d_t8BKtT93qxlm3cTTUh0-8uHXNVSFJ5bHwvQTxUQiUZuPBifyI-PEE48iu-nXDe4wXkkzzf-bOLulvueAHAYfursc7MpkN_D99sjhJ8qSxIYNV3WNYE2t8D_r_I662F_noj20K2IIhiNm-q_D34TMEbbd38BTCl_hA";

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
                System.out.println();System.out.println();

            }

            //Gets the information regarding the users saved tracks and their details
            System.out.println("-------------------------------------------------------------------------------------");
            final Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();
            SavedTrack[] SavedTrack = savedTrackPaging.getItems();
            System.out.println("Tracks Found: " + SavedTrack.length);
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < SavedTrack.length; i++) {
                System.out.println("Track Name: " + SavedTrack[i].getTrack().getName());
                System.out.println("Track ID: " + SavedTrack[i].getTrack().getId());
                String ID = SavedTrack[i].getTrack().getId();
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
                final String[] strings = getAvailableGenreSeedsRequest.execute();
                System.out.println("Length: " + strings.length);

                for(ArtistSimplified artist: SavedTrack[i].getTrack().getArtists()){
                    System.out.println("Artist: " + artist.getName());
                }


                System.out.println("Duration (Milliseconds): " + SavedTrack[i].getTrack().getDurationMs());
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Track Analysis");
                System.out.println("Explicit: " + SavedTrack[i].getTrack().getIsExplicit());
                System.out.println("Popularity: " + SavedTrack[i].getTrack().getPopularity());

                System.out.println("Track Energy: " + audioFeatures.getEnergy());
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
                System.out.println();

            }

            //Gets the total number of playlists, number of saved tracks and artists followed
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Total Number of playlists: " + playlistSimplifiedPaging.getTotal());
            System.out.println("Number of Saved Tracks: " + savedTrackPaging.getTotal());
            System.out.println("Number of Artists Followed: " + artistPagingCursorbased.getTotal());



        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Fetch();
    }
}
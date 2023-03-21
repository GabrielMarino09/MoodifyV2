//Nests Authorization code URI inside authorization.PKCE package
package authorization.PKCE.PKCE;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

//Imports all the necessary Java libraries
import java.io.IOException;
import java.net.URI;

//Initializes AuthorizationCodeUri class
public class AuthorizationCodeUri {

    //Client ID present at the Spotify Developer Dashboard
    private static final String clientId = "01f9b0a52b434813b45b933eb1d9fd1b";

    //Client Secret present at the Spotify Developer Dashboard
    private static final String clientSecret = "a69d39607db842f788821dbb92e88c27";

    //URI to be redirected after User authorizes application
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://www.greengates.edu.mx/");

    //Sets all the information that will be used by Spotify to generate an authorization page
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    //Specifies all the permissions needed by Moodify
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .scope("user-read-playback-state," +
                    "user-modify-playback-state,user-read-currently-playing," +
                   "ugc-image-upload,app-remote-control,streaming,playlist-read-private,playlist-read-collaborative," +
                    "playlist-modify-private,playlist-modify-public,user-follow-modify,user-follow-read," +
                    "user-read-playback-position,user-top-read,user-read-recently-played,user-library-modify," +
                    "user-library-read,user-read-private ")
            .build();

    //Retrieves the URI from Spotify generating a link
    public static void authorizationCodeUri_Sync() {
        final URI uri = authorizationCodeUriRequest.execute();
        System.out.println("URI: " + uri.toString());
        String URISearch = String.valueOf(uri);
        try {
            java.awt.Desktop.getDesktop().browse(URI.create(URISearch));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Executes what was previously set to happen
    public static void main(String[] args) {
        authorizationCodeUri_Sync();
    }
}
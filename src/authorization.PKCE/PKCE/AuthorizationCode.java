//Nests Authorization code inside authorization.PKCE package
package authorization.PKCE.PKCE;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin
import StageOne.GetCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import org.apache.hc.core5.http.ParseException;


//Imports all the necessary Java libraries
import java.io.IOException;
import java.net.URI;

//Initializes AuthorizationCode class
public class AuthorizationCode extends AuthorizationCodeUri {

    //Client ID present at the Spotify Developer Dashboard
    private static final String clientId = "01f9b0a52b434813b45b933eb1d9fd1b";

    //Client Secret present at the Spotify Developer Dashboard
    private static final String clientSecret = "a69d39607db842f788821dbb92e88c27";

    //URI to be redirected after User authorizes application
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://sites.google.com/greengates.edu.mx/moodify-music/home");

    //Code retrieved from the redirected URL
    private static final String code = GetCode.GCode();

    //Sets all the information that will be used by Spotify to generate a token
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    //Sets the request for the authorisation code to be generated
    private static final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
            .build();

    //Retrieves the authorization code from Spotify
    public static String authorizationCode_Sync() {
        String TR = "";
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            //Gets both Access Token and Refresh Token from Spotify
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            TR = authorizationCodeCredentials.getRefreshToken();
            System.out.println("Refresh Token: " + authorizationCodeCredentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error on AC: " + e.getMessage());
        }
        return TR;
    }

    //Executes what was previously set to happen
    public static void main(String[] args) {
        authorizationCode_Sync();
    }
}
//Nests Authorization code inside authorization.PKCE package
package authorization.PKCE.PKCE;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import org.apache.hc.core5.http.ParseException;

//Imports all the necessary Java libraries
import java.io.IOException;

//Initializes AuthorizationCodeRefresh class
public class AuthorizationCodeRefresh extends AuthorizationCode{

    //Client ID present at the Spotify Developer Dashboard
    private static final String clientId = "01f9b0a52b434813b45b933eb1d9fd1b";

    //Client Secret present at the Spotify Developer Dashboard
    private static final String clientSecret = "a69d39607db842f788821dbb92e88c27";

    //Refresh Token collected at the Authorization Code Class
    private static final String refreshToken = "AQAluW7CDR3tWmQbbhyMGY7XdES3PQDOKuNFC3eOKqgoC8cskijk5-ckEVL7RYetz-9jICkiyuwprG9HFa81ZLn935i7wP5q3hh6q4yXURSw8nevICGkax6iaLHZqYAajDA";

    //Sets all the information that will be used by Spotify to generate a token
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken)
            .build();

    //Sets the request for the authorisation code refresh to be generated
    private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
            .build();

    //Retrieves the authorization code refresh from Spotify
    public static void authorizationCodeRefresh_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            //Gets new Access Token from Spotify
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
            System.out.println("Expires in: " + authorizationCodeCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Executes what was previously set to happen
    public static void main(String[] args) {
        authorizationCodeRefresh_Sync();

    }
}
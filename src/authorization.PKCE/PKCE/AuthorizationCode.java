//Nests Authorization code inside authorization.PKCE package
package authorization.PKCE.PKCE;

//Imports all the necessary libraries from the Spotify API developed by Michael Thelin
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
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://www.greengates.edu.mx/");

    //Code retrieved from the redirected URL
    private static final String code = "AQDE1J-KkHnU1tLONg-3q5pMy8gEgpY4mz-5r1yvm8Fp9abBgjsT2l31XRDUSA1eFJRiux54AIAanwzlM1TPCiAA99x-VgKWVKA9a-1fNYTvHbeAXYm--RPm6Hip33msxLSQMh83VO18deCSiuRpqgIrJQGqerUBWzHsRIK6eiv28yNtbjKvelHAHX_OVamO-mu05jWBKfKlloBYz5SbPkimF3D2SQ9SeJoht4OdpYvGgx2o9SDSeRFAgksI8JLQYsUpo20jTiS2wHp8ELyP-c8uCodx-L0aTRmlwqY7OljvMany1r31hPz9rpzNnEdz-dFrkyAN5x14TFQRKkzEG7Dg8EDBVCl1MBAkE9KKaI1umHbKqfFredneaMFe8gqBK42iw_BNcVQWUUcDAQYQb9eVj0jDN1sckF3jyD7DslYqehuoQFBDobwPFI00ODl1qgdGvSDYkQx79aOsMDjMVDuA6Q6cIj-uGh4_o9lOO4_RqUtezev9OmGYKQzeH-9vE_gCKyx_LK8cKhoyZruSvJc-U2_QEZzkC_uvW1d5jwd2t3Uq2F1xhv7na3zpxMtUzOUQlrNrOlhc9eb7i9LdSclq2KJ-YtEdetZCArOoZ1IfFeAcB0zWuhchBVXCJJU2rrZ_nsaqAPh0bPMGHgBevbbjoVF_ShjIe8RBhuX4cHH9alppz1gMWQgeX2mjLb2c2nYK";

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
    public static void authorizationCode_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            //Gets both Access Token and Refresh Token from Spotify
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
            System.out.println("Expires in: " + authorizationCodeCredentials.getAccessToken());
            System.out.println("Expires in: " + authorizationCodeCredentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Executes what was previously set to happen
    public static void main(String[] args) {
        authorizationCode_Sync();
    }
}
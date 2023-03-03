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
    private static final String code = "AQCDLsq_gNfwsmTSl28YlRieUB9bCJVuHN0xr2-ZImM5CzhxRO_HoDb6Noql4HLNraZbdm5914FcbWi8E7cA82-0suuqbA4nRm9M0fuFMB9S73vGgjZbhbPUzjskpOZHGVT9DXfoxsRtrDv2p-Yka4p4Wn1ynlsvZN8eghb1g-q2Lztv2WXSXch5S6b8IO5kXYp3dZwl0HNkzgoRvMmkEMWqtWLi_K2OqU_cCZOJD4hyHbkr0YH5d6x17i8SZjjhGAjs7aUN7tu37q-YSYuFhSWG9VQayHFV89_Jx_2quzVT67AxyCaBSZD_F1GWbApVBZaiEKHi22HaRGfD6T7yOLy_MkK8WhoEStKBpIHbk-FJxU8qifO3cEM_3yXPMbRNiWzRVkC5sfYTg8jM7F8x087ipurKMdgJbOuSfQCx_HlG7BY0cEl0PvsrczfJHXdRPSLG75nNPIDsRvjs7UWWa6KAvw4lVwQt_gybanT4vzV43WLW5FjoVCiHIssafOcVv0eH83FqqE3-N4mTNIolRqPzILCnblFqzLc7zh2TBi3YBFmuZiBy1l-nbbc3c1lcKIQSf0W97DyD2JZrMIZAJQS22tQhbq-V0eQ9Dq00Nr-X21I5n-k3fB7mxJrZ9dNeK15Yz4kMIZ9TnPeIsTnx74Rk1TdDDCkJNaZ0xlgkFC-rhinIXQIfweZfrQSviz8fbjEk";

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
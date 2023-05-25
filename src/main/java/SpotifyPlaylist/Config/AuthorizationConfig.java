package SpotifyPlaylist.Config;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

import java.io.IOException;
import java.net.URI;

public class AuthorizationConfig {
    private static final String CLIENT_ID = "f44ba55629874250bddde56310980a50";
    private static final String CLIENT_SECRET = "c7446a870f4b4e399078cc75eb30b5fd";
    private static final String REDIRECT_URI = "http://localhost:8080/callback";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(URI.create(REDIRECT_URI))
            .build();

    private static String accessToken;
    private static String refreshToken;

    public static void authorize(String code) {
        try {
            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                    .build();

            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            accessToken = authorizationCodeCredentials.getAccessToken();
            refreshToken = authorizationCodeCredentials.getRefreshToken();

            spotifyApi.setAccessToken(accessToken);
            spotifyApi.setRefreshToken(refreshToken);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void refreshAccessToken() {
        try {
            AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
                    .refresh_token(refreshToken)
                    .build();

            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            accessToken = authorizationCodeCredentials.getAccessToken();

            spotifyApi.setAccessToken(accessToken);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }
}

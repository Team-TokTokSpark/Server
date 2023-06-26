package SpotifyPlaylist.global.oauth2.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes; // 추상클래스를 상속받는 클래스에서만 사용할 수 있도록 protected 제어자를 사용했습니다.

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId(); //소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"

    public abstract String getNickname();

    public abstract String getProfileImage();
}

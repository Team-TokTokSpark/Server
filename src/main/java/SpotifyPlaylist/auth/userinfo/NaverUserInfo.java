package SpotifyPlaylist.auth.userinfo;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    private Map<String, Object> attributesResponse;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
//        if (!attributes.containsKey("response") || attributes.get("response") == null) {
//            throw new IllegalArgumentException("Response attribute is missing or null");
//        }
//        this.attributesResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("email"));
//        if (attributesResponse == null) {
//            // 기본값 반환 또는 예외 발생
//            throw new RuntimeException("attributesResponse is null");
//        }
//        return attributesResponse.get("email").toString();
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get("name"));
//        if (attributesResponse == null) {
//            // 기본값 반환 또는 예외 발생
//            throw new RuntimeException("attributesResponse is null");
//        }
//        return attributesResponse.get("name").toString();
    }
}

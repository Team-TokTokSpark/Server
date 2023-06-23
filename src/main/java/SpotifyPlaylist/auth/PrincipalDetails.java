package SpotifyPlaylist.auth;


import SpotifyPlaylist.auth.userinfo.OAuth2UserInfo;
import SpotifyPlaylist.user.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// 시큐리티가 "/login" 주소 요청이 오면 낚아 채서 로그인을 진행해준다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security Session(Session안에 특정영역))
// 해당 세션안에는 Authentication 타입객체가 들어간다.
// Authentication 은 UserDetails 타입 객체가 들어갈수 있다.
// UserDetails 안에 use(사용자)를 가지고 있는다.

@Data
public class PrincipalDetails implements UserDetails , OAuth2User {

    private User user;
    private Map<String, Object> attributes;
    private OAuth2UserInfo oAuth2UserInfo;


    //일반 로그인 생성자
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // OAuth 로그인 생성자
    public PrincipalDetails(User user, Map<String, Object> attributes ) {
        this.user = user;
        this.attributes = attributes;
    }


    /**
     * OAuth2User 인터페이스 메소드
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
//        return oAuth2UserInfo.getAttributes();
    }


    /**
     * UserDetails 인터페이스 메소드
     */
    // 해당 User의 권한을 리턴하는 곳!(role)
    // SecurityFilterChain에서 권한을 체크할 때 사용됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return String.valueOf(user.getRole());
            }
        });
        return collection;
    }

    /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     *  true : 만료안됨
     *  false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     *  true : 잠기지 않음
     *  false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     *  true : 만료 안됨
     *  false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     *  true : 활성화
     *  false : 비활성화
     */
    @Override
    public boolean isEnabled() {

        //우리사이트!! 1년 동안 사용하지 않으면 휴면 계정으로 바꾼다.
        // 현재 시간 - 마지막 로그인 시간 => 1년을 초기하면 return false로 바꾼다.
        // 이러한 로직을 여기 넣는다.
        return true;
    }

    @Override
    public String getName() {
        return null;
//        return oAuth2UserInfo.getProviderId();
    }
}
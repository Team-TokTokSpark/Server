package SpotifyPlaylist.Config;

import SpotifyPlaylist.user.service.PrincipalOauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 1. 코드받기(인증), 2. 액세스토큰(권한), 3. 사용자프로필 정보를 가져오고
// 4-1 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함
// 4-2 (이메일, 전화번호, 이름, 아이디) 쇼핑몰의 경우 집주소, 백화점몰의 경우 vip등급 이런게 필요함.


@Configuration
@EnableWebSecurity //시큐리티 활성화 -> 스프링 시큐리티 필터(SecurityConfig)가 기본 스프링 필터 체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig {

    @Autowired
    private PrincipalOauthUserService principalOauthUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주
                .antMatchers("/manager/**").hasAuthority("MANAGER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().permitAll() // 나머지 주소는 모두 허용

                .and()
                .formLogin()
                .loginPage("/loginForm") //미인증자일경우 해당 uri를 호출. 권한이 없는 사용자가 접속하면 loginForm으로 넘어감.
                .loginProcessingUrl("/login") //login 주소가 호출되면 시큐리티가 낚아 채서(post로 오는것) 대신 로그인 진행 -> 컨트롤러를 안만들어도 된다.
                .defaultSuccessUrl("/")

                .and()
                .oauth2Login()
                .loginPage("/loginForm") // 구글 로그인이 완료된 뒤의 후처리가 필요함. 코드를 받는게 아니라 (엑세스 토큰 + 사용자 프로필정보를 한번에 받아옴)
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(principalOauthUserService);//구글 로그인이 완료된(구글회원) 뒤의 후처리가 필요함 . Tip.코드x, (엑세스 토큰+사용자 프로필 정보를 받아옴)


        return http.build();
    }
}
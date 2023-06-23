package SpotifyPlaylist.Controller;


import SpotifyPlaylist.auth.PrincipalDetails;
import SpotifyPlaylist.user.domain.Role;
import SpotifyPlaylist.user.domain.User;
import SpotifyPlaylist.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @ResponseBody
    @GetMapping("/test/login")
    public String testLogin(
            Authentication authentication,
            @AuthenticationPrincipal PrincipalDetails userDetails) { //세션 정보 받아오기 (DI 의존성 주입)

        //방법 1
        System.out.println("/test/login =============================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication:" + principalDetails.getUser());


        //방법 2
        System.out.println("userDetails:" + userDetails.getUser());

        return "세션 정보 확인";
    }
    @ResponseBody
    @GetMapping("/test/oauth/login")
    public String testOAuthLogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oauth
    ) { //세션 정보 받아오기 (DI 의존성 주입)

        //방법 1
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication: " + oAuth2User.getAttributes());

        //방법 2
        System.out.println("OAuth2User:" + oauth.getAttributes());

        return "OAuth 세션 정보 확인";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("GetMapping(/user) ==========================");
        System.out.println("principalDetails: " + principalDetails );

        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @ResponseBody
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    //스프링 시큐리티가 낚아 챈다(post로 오는것!!)!! -> config를 통해 해결
//    @ResponseBody
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        System.out.println("회원가입 정보: " + user);
        user.setRole(Role.USER);
        user.setCreateDate(LocalDateTime.now());

        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        userRepository.save(user);

        return "redirect:/loginForm"; // @GetMapping("/loginForm")이 붙은 메서드 호출
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

}
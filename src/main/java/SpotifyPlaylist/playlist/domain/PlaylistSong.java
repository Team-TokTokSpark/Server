package SpotifyPlaylist.playlist.domain;

import SpotifyPlaylist.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistSongId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playlist_id")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String spotifyId;

    private String title;

    private String artist;

    private String albumImageUrl;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "playlistSong")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id")
    private Sticker sticker; // 스티커 이미지

}
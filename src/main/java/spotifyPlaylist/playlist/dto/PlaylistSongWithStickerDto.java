package spotifyPlaylist.playlist.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistSongWithStickerDto {
    private Long playlistSongId;
    private String title;
    private String artist;
    private String albumImageUrl;
    private StickerDto sticker;
}

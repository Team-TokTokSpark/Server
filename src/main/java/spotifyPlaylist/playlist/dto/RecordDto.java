package spotifyPlaylist.playlist.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecordDto {
    private Long userId;
    private String nickname;
    private Long playlistId;
    private String playlistName;
    private String content;
    private Long backgroundIdx;
    private List<PlaylistSongDto> playlistSongs;
}


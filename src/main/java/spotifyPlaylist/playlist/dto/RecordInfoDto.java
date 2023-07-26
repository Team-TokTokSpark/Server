package spotifyPlaylist.playlist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordInfoDto {
    private String playlistName;
    private Long backgroundIdx;
    private Long userIdx;
    private int imageIdx;
    private String type;
    private String content;
    private Long playlistIdx;
}

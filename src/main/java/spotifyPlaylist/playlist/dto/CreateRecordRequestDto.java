package spotifyPlaylist.playlist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecordRequestDto {
    private String playlistName;
    private Long backgroundIdx;
    private String type;
    private String content;
    private int imageIdx;
}
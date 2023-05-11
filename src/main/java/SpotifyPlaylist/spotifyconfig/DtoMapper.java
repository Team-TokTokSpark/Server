package SpotifyPlaylist.spotifyconfig;

import SpotifyPlaylist.spotifyconfig.DTO.SearchResponseDto;

public class DtoMapper {

    public static SearchResponseDto toSearchDto(String artistName, String title, String albumName, String imageUrl) {
        return SearchResponseDto.builder()
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .build();
    }
}

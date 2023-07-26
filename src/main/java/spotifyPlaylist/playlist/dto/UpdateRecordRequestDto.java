package spotifyPlaylist.playlist.dto;

public class UpdateRecordRequestDto {
        private String playlistName;
        private int backgroundIdx;

        private String content;

        // Getters and setters

        public String getPlaylistName() {
            return playlistName;
        }

        public void setPlaylistName(String playlistName) {
            this.playlistName = playlistName;
        }

        public int getBackgroundIdx() {
            return backgroundIdx;
        }

        public void setBackgroundIdx(int backgroundIdx) {
            this.backgroundIdx = backgroundIdx;
        }

        public String getContent(){ return content;}

        public void setContent(String content){ this.content = content;}
}

package Audio;

import java.io.IOException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Theme {
	private Media media;
    private MediaPlayer mediaPlayer;

    public Theme(String mediaUrl) {
    	try {
            URL resource = getClass().getResource(mediaUrl);
            if (resource != null) {
                media = new Media(resource.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            } else {
                throw new IOException("Resource not found: " + mediaUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}

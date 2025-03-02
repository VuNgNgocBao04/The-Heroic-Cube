package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class Game {
	 private TheCube player;
	    private TheBoss boss;
	    private List<TheBullet> bullets;
	    
	    public Game() {
	        // Khởi tạo nhân vật chính với hình ảnh từ file và 3 máu
	        Image playerImage = new Image(getClass().getResource("NewHeroChar.png").toExternalForm());
	        player = new TheCube(100, 300, playerImage, 3);
	        
	        // Khởi tạo boss với hình ảnh từ file và 100 máu (bạn có thể điều chỉnh)
	        Image bossImage = new Image(getClass().getResource("FinalbossChar.png").toExternalForm());
	        boss = new TheBoss(600, 100, bossImage, 100);
	        
	        bullets = new ArrayList<>();
	    }
	    
	    public TheCube getPlayer() { return player; }
	    public TheBoss getBoss() { return boss; }
	    public List<TheBullet> getBullets() { return bullets; }
}

package View;

import Model.Game;
import Model.TheBullet;
import javafx.scene.layout.Pane;

public class Window {
	private Pane root;
    private Game model;
    
    public Window(Pane root, Game model) {
        this.root = root;
        this.model = model;
        initialize();
    }

    private void initialize() {
        // Thêm ảnh của nhân vật chính
        root.getChildren().add(model.getPlayer().getImageView());
        // Thêm ảnh và thanh máu của boss
        root.getChildren().add(model.getBoss().getImageView());
        root.getChildren().add(model.getBoss().getHealthBar());
        // Các đạn sẽ được thêm vào khi được tạo ra
    }
    
    public Pane getRoot() {
        return root;
    }
    
    public void addBullet(TheBullet bullet) {
        root.getChildren().add(bullet.getImageView());
    }
    
    public void removeBullet(TheBullet bullet) {
        root.getChildren().remove(bullet.getImageView());
    }
}
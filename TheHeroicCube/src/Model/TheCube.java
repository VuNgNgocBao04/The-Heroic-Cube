package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TheCube {
	private double x;
    private double y;
    private double speed = 5;       // Vận tốc di chuyển thường
    private double dashSpeed = 15;  // Vận tốc khi dash
    private int lives;
    private ImageView imageView;
    
    public TheCube(double x, double y, Image image, int lives) {
        this.x = x;
        this.y = y;
        this.lives = lives;
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }
    
    public ImageView getImageView() {
        return imageView;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    // Di chuyển theo hướng (dx, dy)
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        updatePosition();
    }
    
    // Thực hiện dash
    public void dash(double dx, double dy) {
        x += dx * dashSpeed;
        y += dy * dashSpeed;
        updatePosition();
    }
    
    private void updatePosition() {
        imageView.setX(x);
        imageView.setY(y);
    }
    
    public int getLives() {
        return lives;
    }
    
    public void loseLife() {
        lives--;
    }
}

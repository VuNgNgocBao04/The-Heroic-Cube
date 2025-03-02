package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TheBoss {
	private double x;
    private double y;
    private double speed = 2;  // Vận tốc di chuyển (horizontally)
    private int health;
    private ImageView imageView;
    private Rectangle healthBar;
    
    public TheBoss(double x, double y, Image image, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        
        // Thanh máu đặt phía trên boss
        healthBar = new Rectangle(x, y - 20, 100, 10);
        healthBar.setFill(Color.GREEN);
    }
    
    public ImageView getImageView() { return imageView; }
    public Rectangle getHealthBar() { return healthBar; }
    
    // Cập nhật thanh máu dựa trên sức khỏe hiện tại
    public void updateHealthBar() {
        double ratio = (double) health / 100;
        healthBar.setWidth(100 * ratio);
    }
    
    // Di chuyển boss
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        imageView.setX(x);
        imageView.setY(y);
        healthBar.setX(x);
        healthBar.setY(y - 20);
    }
    
    // Thuật toán di chuyển tùy chỉnh (ví dụ: di chuyển ngang qua lại)
    public void updateMovement() {
        // Nếu boss chạm biên màn hình (giả sử game width = 800) thì đổi hướng
        if (x <= 0 || x + 100 >= 800) {
            speed = -speed;
        }
        move(speed, 0);
    }
    
    public int getHealth() {
        return health;
    }
    
    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        updateHealthBar();
    }
}

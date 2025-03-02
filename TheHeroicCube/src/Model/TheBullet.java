package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TheBullet {
	private double x;
    private double y;
    private double dx;
    private double dy;
    private double speed = 5;
    private ImageView imageView;
    
    /**
     * @param angle Góc bắn tính theo độ (0° hướng phải, 90° xuống dưới, 180° trái, 270° lên trên)
     */
    
	public TheBullet(double x, double y, double angle) {
	    	this.x = x;
	        this.y = y;
	        double rad = Math.toRadians(angle);
	        dx = speed * Math.cos(rad);
	        dy = speed * Math.sin(rad);
	        
	        // Tải ảnh đạn từ file (đảm bảo có file "bullet.png" trong folder resources)
	        Image bulletImage = new Image(getClass().getResource("Bullet.png").toExternalForm());
	        imageView = new ImageView(bulletImage);
	        imageView.setX(x);
	        imageView.setY(y);
	        imageView.setFitWidth(20);
	        imageView.setFitHeight(20);
	}
	
	public ImageView getImageView() {
        return imageView;
    }
	
	// Cập nhật vị trí đạn
    public void update() {
        x += dx;
        y += dy;
        imageView.setX(x);
        imageView.setY(y);
    }
    
    // Kiểm tra đạn có ra ngoài màn hình không
    public boolean isOutOfBounds(double width, double height) {
        return x < 0 || x > width || y < 0 || y > height;
    }
}

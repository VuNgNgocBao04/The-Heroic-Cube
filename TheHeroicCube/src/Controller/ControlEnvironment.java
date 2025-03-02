package Controller;

import Model.Game;
import Model.TheBoss;
import Model.TheBullet;
import View.Window;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControlEnvironment {
    private Game model;
    private Window view;
    private Scene scene;
    
    // Các biến cờ để theo dõi phím đang được nhấn
    private boolean up, down, left, right, dash;
    
    // Thời điểm bắn đạn cuối cùng của boss (nanosecond)
    private long lastBossShoot = 0;
    
    public ControlEnvironment(Game model, Window view, Scene scene) {
        this.model = model;
        this.view = view;
        this.scene = scene;
        initializeInput();
    }
    
    private void initializeInput() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                switch (code) {
                    case UP:
                    case W:
                        up = true;
                        break;
                    case DOWN:
                    case S:
                        down = true;
                        break;
                    case LEFT:
                    case A:
                        left = true;
                        break;
                    case RIGHT:
                    case D:
                        right = true;
                        break;
                    case SHIFT:
                        dash = true;
                        break;
                    default:
                        break;
                }
            }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                switch (code) {
                    case UP:
                    case W:
                        up = false;
                        break;
                    case DOWN:
                    case S:
                        down = false;
                        break;
                    case LEFT:
                    case A:
                        left = false;
                        break;
                    case RIGHT:
                    case D:
                        right = false;
                        break;
                    case SHIFT:
                        dash = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    public void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
        timer.start();
    }
    
    private void update(long now) {
        // Xử lý di chuyển nhân vật chính dựa trên phím được nhấn
        double dx = 0, dy = 0;
        if (up)    dy -= 1;
        if (down)  dy += 1;
        if (left)  dx -= 1;
        if (right) dx += 1;
        
        if (dx != 0 || dy != 0) {
            // Chuẩn hóa vector chuyển động
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
            if (dash) {
                model.getPlayer().dash(dx, dy);
            } else {
                model.getPlayer().move(dx * model.getPlayer().getSpeed(), dy * model.getPlayer().getSpeed());
            }
        }
        
        // Cập nhật di chuyển của boss
        model.getBoss().updateMovement();
        
        // Boss bắn đạn mỗi 1 giây
        if (now - lastBossShoot > 1_000_000_000L) { // 1s = 1 tỷ nanosecond
            shootBossBullets();
            lastBossShoot = now;
        }
        
        // Cập nhật vị trí các viên đạn
        for (int i = 0; i < model.getBullets().size(); i++) {
            TheBullet bullet = model.getBullets().get(i);
            bullet.update();
            // Loại bỏ đạn nếu ra ngoài màn hình
            if (bullet.isOutOfBounds(view.getRoot().getWidth(), view.getRoot().getHeight())) {
                view.removeBullet(bullet);
                model.getBullets().remove(i);
                i--;
            } else {
                // Kiểm tra va chạm giữa đạn và nhân vật chính
                if (bullet.getImageView().getBoundsInParent().intersects(
                        model.getPlayer().getImageView().getBoundsInParent())) {
                    // Va chạm: giảm 1 máu cho nhân vật
                    model.getPlayer().loseLife();
                    System.out.println("Player hit! Lives left: " + model.getPlayer().getLives());
                    view.removeBullet(bullet);
                    model.getBullets().remove(i);
                    i--;
                    // (Có thể thêm xử lý game over nếu lives <= 0)
                }
            }
        }
    }
    
    // Boss bắn ra 3 viên đạn theo hình quạt (góc: -30°, 0°, +30° so với hướng xuống dưới)
    private void shootBossBullets() {
        TheBoss boss = model.getBoss();
        // Tọa độ tâm của boss
        double bossCenterX = boss.getImageView().getX() + boss.getImageView().getFitWidth() / 2;
        double bossCenterY = boss.getImageView().getY() + boss.getImageView().getFitHeight() / 2;
        
        double baseAngle = 90; // 90° nghĩa là bắn xuống dưới
        double[] angles = { baseAngle - 30, baseAngle, baseAngle + 30 };
        
        for (double angle : angles) {
            TheBullet bullet = new TheBullet(bossCenterX, bossCenterY, angle);
            model.getBullets().add(bullet);
            view.addBullet(bullet);
        }
    }
}

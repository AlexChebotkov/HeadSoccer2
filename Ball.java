import java.awt.*;

public class Ball {

    private Vector pos, prevPos;
    private final int radius;
    private Vector speed = new Vector(13, 0);

    public Ball(Field field, int radius) {
        prevPos = pos = new Vector(field.getBallStartPos());
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public Point getPos() {
        return pos.getPoint();
    }

    public void move(double g) { //обновляем предыдущуюю позицию
        prevPos = new Vector(pos);
        speed.y += g;
        pos.add(speed);
        double airLoss = 0.002;
        speed.mul(1 - airLoss);
    }

    public void CollisionProcessing(Rectangle rect, boolean isCircle) {
        Vector n;
         if (isCircle)
             n = Physics.CheckCollision(pos.getPoint(), radius, new Point(rect.x, rect.y), rect.width);
         else
             n = Physics.CheckCollision(pos.getPoint(), radius, rect);
        if (!n.is_zeros()) { // если есть столкновение
            pos = new Vector(prevPos); //чтобы шар не залезал в текстуры возвращаем его на такую позицияю гдеон точно не столкнется и увеличиваем скорость
            speed.setAngle(Math.PI + 2 * n.getAngle() - speed.getAngle());
            double collisionLoss = 0.05;
            speed.mul(1 - collisionLoss);
        }
    }
}

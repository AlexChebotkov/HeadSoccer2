import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    private final GameLogic gameLogic;

    public Panel(GameLogic gameLogic, int fps) {
        this.gameLogic = gameLogic;//панель в себе запоминает всю логику игры откуда ей нужно будет вытащить коорды шаров и игроков
        Timer timer = new Timer(1000 / fps, this);//создает таймер который раз  в секнуду вызывает метод actionPerformed
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.setBackground(Color.BLACK);

        Graphics2D g = (Graphics2D) graphics;//вызывает gameLogic.update и отрисовывает все что нужно

        gameLogic.update();//GameLogic

        drawField(g);
        drawPlayer(g);
        drawBall(g);
    }

    private void drawBall(Graphics2D g) {
        g.setColor(Color.CYAN);
        Point ballPos = gameLogic.getBallPos();
        int delta = (int) Physics.eps;
        int r = gameLogic.getBallRadius() + 2 * delta;
        g.fillOval(ballPos.x - delta, ballPos.y - delta, r, r);
    }

    private void drawPlayer(Graphics2D g) {
        g.setColor(Color.RED);
        for (Rectangle rect : gameLogic.getPlayerRects())
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        for (Rectangle rect : gameLogic.getPlayerCircles())
             g.fillOval(rect.x, rect.y, rect.width, rect.height);
    }

    private void drawField(Graphics2D g) {
        g.setColor(Color.GREEN);
        for (Rectangle rect : gameLogic.getBorderRects())
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //заново рисует paintComponent
        repaint();
    }
}
import java.awt.*;

public class Game {

    private final Field field = new Field();//игра создает поле
    private final Ball ball = new Ball(field, 40);//игра создает шарик
    private final Player[] players = {new Player(field, new Point(80, 120), true), new Player(field, new Point(80, 120), false)};//игра создает двух игроков

    private final GameLogic gameLogic = new GameLogic(ball, players, field, 0.2);//создает логику игры

    public void run() {
        Frame frame = new Frame(gameLogic, 1200, 1000, 60);// метод run создает окно
    }
}

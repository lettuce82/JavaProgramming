package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.interfaces.Bounceable;
import com.example.interfaces.Bounded;
import com.example.interfaces.HitListener;
import com.example.interfaces.Movable;

public class CannonWorld extends MovableWorld implements MouseMotionListener, KeyListener, ComponentListener {
    static final int WALL_THICKNESS = 200;
    static final int BAR_WIDTH = 100;
    static final int BAR_THICKNESS = 20;
    static final int BAR_SPEED = 10;
    static final int MIN_HEIGHT = WALL_THICKNESS * 2 + BAR_THICKNESS;
    static final int MIN_WIDTH = WALL_THICKNESS * 2 + BAR_WIDTH;
    int blockHeight = 30;
    int blockWidth = 70;
    Vector gravity = new Vector(0, 0);
    Vector windSpeed = new Vector(0, 0);
    Vector angle = new Vector(40, -20);
    private boolean gameOver = false;

    final Box leftWall;
    final Box rightWall;
    final Box topWall;
    final Box bottomWall;
    // final BounceableBox bar;
    CannonShape cannon;
    final List<Box> boxList = new LinkedList<>();
    final List<Ball> ballList = new LinkedList<>();
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    Random random = new Random();

    final Color[] colors = { Color.YELLOW, Color.WHITE, Color.BLUE, Color.GREEN };

    public CannonWorld(int x, int y, int width, int height) {
        super();

        setBounds(x, y, width, height);

        leftWall = new PaintableBox(-WALL_THICKNESS / 2, y + height / 2, WALL_THICKNESS, height);
        rightWall = new PaintableBox(width + WALL_THICKNESS / 2, y + height / 2, WALL_THICKNESS, height);
        topWall = new PaintableBox(width / 2, y - WALL_THICKNESS / 2, MIN_WIDTH, WALL_THICKNESS);
        bottomWall = new PaintableBox(width / 2, y + height + WALL_THICKNESS / 2, width + 2 * WALL_THICKNESS,
                WALL_THICKNESS);

        add(leftWall);
        add(rightWall);
        add(topWall);
        add(bottomWall);

        bottomWall.setHitListener(other -> {
            if (other instanceof Bounceable) {
                Vector motion = ((Movable) other).getMotion();

                motion.multiply(0.5);
                ((Movable) other).setMotion(motion);
            }
        });

        cannon = new CannonShape(100, height - BAR_THICKNESS / 2, 100, 20, Color.BLUE);
        add(cannon);

        setFocusable(true);
        addKeyListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);

    }

    public void init() {
        int y = getHeight() - 10;
        for (int line = 0; line < random.nextInt(15) + 5; line++) {
            int x = 200 + BAR_WIDTH * 2;
            for (int row = 0; row < random.nextInt(5) + 1; row++) {
                Box box = new BrittleBox(x, y, blockWidth, blockHeight, Color.GRAY);
                boxList.add(box);
                add(box);
                box.setHitListener(other -> remove(box));
                x += blockWidth;
            }
            y -= blockHeight;
        }

        Box tartgetBox = new BrittleBox(800, getHeight() - 10, blockWidth + 20, blockHeight + 20, colors[random.nextInt(colors.length)], "hit");
        add(tartgetBox);
        tartgetBox.setHitListener(other -> {
            gameOver();
        });
    }

    @Override
    public void add(Bounded object) {
        super.add(object);
        if (object instanceof Movable) {
            threadPool.execute((Movable) object);
        }

    }

    public void start() {
        BounceableBall ball = new BounceableBall(cannon.getPoint().x, cannon.getPoint().y, 15, Color.RED);

        ball.setMotion(angle);
        ball.setDT(getDT());

        ball.addStartedActionListener(() -> {
        });

        ball.addMovedActionListener(() -> {
            List<Bounded> removeList = new LinkedList<>();

            Vector newMotion = ball.getMotion();
            newMotion.add(gravity);
            newMotion.add(windSpeed);
            ball.setMotion(newMotion);

            if (ball instanceof Bounceable) {
                for (int j = 0; j < getCount(); j++) {
                    Bounded other = get(j);

                    if (ball != other && ball.isCollision(other.getBounds())) {
                        ((Bounceable) ball).bounce(other);
                        if (other instanceof HitListener) {
                            ((HitListener) other).hit(ball);

                        }
                    }
                }
            }

            for (Bounded item : removeList) {
                remove(item);
            }
        });
        add(ball);
    }

    public void clear() {
        List<Bounded> removeList = new LinkedList<>();

        for (int i = 0; i < getCount(); i++) {
            if (get(i) instanceof BounceableBall) {
                removeList.add(get(i));
            }
        }

        for (Bounded bounded : removeList) {
            ((BounceableBall) bounded).stop();
            remove(bounded);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            cannon.move(new Vector(-BAR_SPEED, 0));
            if (cannon.getMinX() < 0) {
                cannon.setLocation(new Point(cannon.getWidth() / 2, cannon.getCenterY()));
            }
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            cannon.move(new Vector(BAR_SPEED, 0));
            if (cannon.getMaxX() > getWidth()) {
                cannon.setLocation(new Point(getWidth() - cannon.getWidth() / 2, cannon.getCenterY()));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        //
        logger.info("{}", event.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent event) {
        //
        logger.info("{}", event.getKeyCode());
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        logger.info("Hidden");
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        logger.info("Moved");
    }

    @Override
    public void componentResized(ComponentEvent event) {
        if (ballList.isEmpty() && (getWidth() > BAR_WIDTH) && (getHeight() > BAR_THICKNESS)) {
            leftWall.setBounds(new Bounds(-WALL_THICKNESS, -WALL_THICKNESS,
                    WALL_THICKNESS, WALL_THICKNESS * 2 + getHeight()));
            rightWall.setBounds(new Bounds(getWidth(), -WALL_THICKNESS,
                    WALL_THICKNESS, WALL_THICKNESS * 2 + getHeight()));
            topWall.setBounds(new Bounds(-WALL_THICKNESS, -WALL_THICKNESS,
                    getWidth() + WALL_THICKNESS * 2, WALL_THICKNESS));
            bottomWall.setBounds(new Bounds(-WALL_THICKNESS, getHeight(),
                    getWidth() + WALL_THICKNESS * 2, WALL_THICKNESS));
        }
    }

    @Override
    public void componentShown(ComponentEvent event) {
        logger.info("Shown");
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (cannon != null) {
            if (event.getX() > cannon.getWidth() / 2 && event.getX() < getWidth() - cannon.getWidth() / 2) {
                cannon.setLocation(new Point(event.getX(), cannon.getCenterY()));
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    public void setWindSpeed(int value) {
        windSpeed.set(new Vector(value, 0));
    }

    public void setSpeed(int value) {
        setDT(DEFAULT_DT - value);
    }

    public void setAngle(int value) {
        double magnitude = Math.sqrt(Math.pow(angle.getDX(), 2) + Math.pow(angle.getDY(), 2));
        double radian = Math.toRadians(value);
        int newDX = (int) (magnitude * Math.cos(radian));
        int newDY = (int) (magnitude * Math.sin(radian));
        cannon.setCannonAngle(value);
        angle.set(new Vector(newDX, -newDY));
    }

    public void setGravity(int value) {
        gravity.set(new Vector(0, value));
    }

    private void gameOver() {
        gameOver = true;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // 배경 불투명하게 그리기
        if (gameOver) {
            Color transparentBlack = new Color(0, 0, 0, 100); // 불투명 검은색
            g2d.setColor(transparentBlack);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Game Over 문구 표시
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String gameOverMessage = "You are Win!";
            int stringWidth = g.getFontMetrics().stringWidth(gameOverMessage);
            int x = (getWidth() - stringWidth) / 2;
            int y = getHeight() / 2;
            g.drawString(gameOverMessage, x, y);
        }
    }

}

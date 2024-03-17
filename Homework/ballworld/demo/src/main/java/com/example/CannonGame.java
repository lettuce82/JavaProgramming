package com.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CannonGame extends JFrame implements ComponentListener {
    static final int FRAME_WIDTH = 1200;
    static final int FRAME_HEIGHT = 700;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int MIN_WIDTH = 10;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 10;
    static final int MAX_HEIGHT = 70;
    static final int FIXED_BALL_COUNT = 0;
    static final int FIXED_BOX_COUNT = 3;
    static final int BOUNDED_BALL_COUNT = 5;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 50;
    static final int BLOCK_WIDTH = 80;
    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    Logger logger = LogManager.getLogger();

    CannonWorld world;

    public CannonGame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(this);
        setLayout(null);
        world = new CannonWorld(300, 0, FRAME_HEIGHT -300, FRAME_HEIGHT-200);
        world.setDT(DT);
        world.setBounds(300,0, FRAME_WIDTH-300, FRAME_HEIGHT-200);
        world.setBackground(Color.WHITE);
        add(world);

        JButton button1 = new JButton("Fire!");
        button1.setBounds(50, 550, 100, 70);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
            }
        });

        JButton button2 = new JButton("Clear!");
        button2.setBounds(180, 550, 100, 70);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.clear();
            }
        });
        add(button1);
        add(button2);

        addSpeedComponent();
        addAngleComponent();
        addGravityComponent();
        addWindComponent();

        world.init();
    }

    public void addSpeedComponent() {
        JLabel label1 = new JLabel("속도");
        label1.setBounds(30, 50, 100, 100);
        add(label1);

        JSlider speed = new JSlider( 0, 1000, 500);
        speed.setBounds(50, 100, 200, 50);
        speed.setPaintTrack(true);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.setMajorTickSpacing(200);
        speed.setMinorTickSpacing(50);

        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setSpeed(speed.getValue());
            }
        });
        add(speed);
    }

    public void addAngleComponent() {

        JLabel label2 = new JLabel("각도");
        label2.setBounds(30, 150, 100, 100);
        add(label2);

        JSlider angle = new JSlider( 0, 90, 45);

        angle.setBounds(50, 200, 200, 50);
        angle.setPaintTrack(true);
        angle.setPaintTicks(true);
        angle.setPaintLabels(true);

        angle.setMajorTickSpacing(20);
        angle.setMinorTickSpacing(5);

        angle.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                world.setAngle(angle.getValue());
            }

        });

        add(angle);
    }

    // 중력은 world 내부에 공유
    public void addGravityComponent() {
        JLabel label3 = new JLabel("중력");
        label3.setBounds(30, 250, 100, 100);
        add(label3);

        JSlider gravity = new JSlider( 0, 10, 5);
        gravity.setBounds(50, 300, 200, 50);
        gravity.setPaintTrack(true);
        gravity.setPaintTicks(true);
        gravity.setPaintLabels(true);

        gravity.setMajorTickSpacing(2);
        gravity.setMinorTickSpacing(1);


        gravity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setGravity(gravity.getValue());
            }
        });
        add(gravity);
    }

    public void addWindComponent() {
        JLabel label4 = new JLabel("바람");
        label4.setBounds(30,350, 100, 100);
        add(label4);

        JSlider windSpeed = new JSlider( -10, 10, 0);
        windSpeed.setBounds(50, 400, 200, 50);
        windSpeed.setPaintTrack(true);
        windSpeed.setPaintTicks(true);
        windSpeed.setPaintLabels(true);
        windSpeed.setMajorTickSpacing(2);
        windSpeed.setMinorTickSpacing(1);

        windSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setWindSpeed(windSpeed.getValue());
            }
        });
        add(windSpeed);
    }


    public void start() {
        setVisible(true);
        setEnabled(true);
        world.run();
    }

    public static void main(String[] args) {
        CannonGame frame = new CannonGame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.start();
    }

    public void componentHidden(ComponentEvent event) {
        //
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        //
    }

    @Override
    public void componentResized(ComponentEvent event) {
        if (getWidth() % BLOCK_WIDTH != 0) {
            setSize(getWidth() / BLOCK_WIDTH * BLOCK_WIDTH, getHeight());
        }
    }

    @Override
    public void componentShown(ComponentEvent event) {
        //
    }
}
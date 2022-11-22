package UT1.Tests.ClockApp;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Frame extends JFrame {
    protected static final int FRAME_WIDTH = 500;
    protected static final int FRAME_HEIGHT = 200;
    private LabelFormat timeLabel;
    private LabelFormat dateLabel;
    private CloseButton closeButton;
    private Panel panel;
    private String time;
    private String day;
    private String date;

    Frame() {
        frameConfig();
        frameElements();
        setTime();
    }
    private void frameConfig(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ClockApp");
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);
//        Color al frame
//        this.getContentPane().setBackground(Color.BLACK);
    }
    private void frameElements(){
        panel = new Panel();
        this.add(panel);
        closeButton = new CloseButton();
        panel.add(closeButton);
        closeButton.addActionListener(e -> System.exit(0));
        timeLabel = new LabelFormat(10);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
        timeLabel.setForeground(new Color(0x00FF00));

        dateLabel = new LabelFormat(85);
        dateLabel.setFont(new Font("Ink Free", Font.PLAIN, 25));

        panel.add(timeLabel);
        panel.add(dateLabel);
    }

    public void setTime() {
        //https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(day + ", " + date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


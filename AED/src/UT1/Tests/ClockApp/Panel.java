package UT1.Tests.ClockApp;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static UT1.Tests.ClockApp.Frame.*;

public class Panel extends JPanel{
    Panel(){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
//        this.setBorder(new LineBorder(Color.BLUE,3,true));
        this.setBorder(new BevelBorder(0,Color.red,Color.blue));
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
    }
}

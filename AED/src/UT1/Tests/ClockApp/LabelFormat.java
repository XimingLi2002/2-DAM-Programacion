package UT1.Tests.ClockApp;

import javax.swing.*;
import java.awt.*;

import static UT1.Tests.ClockApp.Frame.FRAME_WIDTH;

public class LabelFormat extends JLabel {
    LabelFormat(int positionY){
        this.setHorizontalAlignment(SwingConstants.CENTER); //Para alinear texto al medio SwingConstants.
        this.setBounds(5, positionY, FRAME_WIDTH-10, 75);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }
}

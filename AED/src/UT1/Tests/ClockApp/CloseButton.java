package UT1.Tests.ClockApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static UT1.Tests.ClockApp.Frame.*;

public class CloseButton extends JButton{
	private ImageIcon icon;
	CloseButton() {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(Objects.requireNonNull(CloseButton.class.getClassLoader().getResource("./Tests/ClockApp/exit.png")).getFile()));
//			bufferedImage = ImageIO.read(new File("./AED/src/UT1.Tests/ClockApp/exit.png"));
			Image image = bufferedImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			System.err.println("Archivo no encontrado (CloseButton)");
		}
		this.setBounds(FRAME_WIDTH-100, FRAME_HEIGHT-40, 90, 30);
		this.setText("EXIT");
		this.setFocusable(false);
		this.setIcon(icon);
//		this.setHorizontalTextPosition(JButton.RIGHT);
		//Ubicación del texto a la imagen
//		this.setVerticalTextPosition(JButton.BOTTOM);
		this.setFont(new Font("Comic Sans", Font.BOLD, 18));
//		this.setIconTextGap(-15);
		this.setForeground(Color.cyan);
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createEtchedBorder());
	}
}

package UT1.EjercicioFicheros.ListasDeFicheros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {

    private JButton button = new JButton("Select");

    GUI() {
        ButtonConfig();
        this.setLocationRelativeTo(null);
        this.add(button);
        this.setResizable(false);
        this.setSize(200, 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void ButtonConfig() {
        button.setFocusable(false);
        button.addActionListener(this);
        button.setFont(new Font("Comic Sans", Font.BOLD, 18));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.black);
        button.setBorder(BorderFactory.createEtchedBorder());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("./")); //default folder
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //forces to select directories

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            String[] files = file.list();

            for (String filesName : files) {
                System.out.println(filesName);
            }
        }
    }
}

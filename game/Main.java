package game;

import javax.swing.*;
import java.io.IOException;

public class Main implements Constants
{
    public static void main(String[] args) throws IOException {
        new FramePanel(new World(Integer.parseInt(JOptionPane.showInputDialog("Enter width")),
                Integer.parseInt(JOptionPane.showInputDialog("Enter height"))));
    }
}

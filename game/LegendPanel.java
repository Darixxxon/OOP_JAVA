package game;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LegendPanel extends JPanel {

    public LegendPanel(int height) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] animalNames = {
                "Human", "Chad", "Antelope", "Fox", "Sheep", "Turtle", "Wolf",
                "Belladonna", "Grass", "Guarana", "Sosnowsky", "Sow_thistle", "Meadow"
        };
        Border blackBorder = new LineBorder(Color.BLACK, 2);

        for (String animalName : animalNames) {
            String photoFilePath = System.getProperty("user.dir") + "/Java Project/src/" + animalName + ".png";
            ImageIcon photoIcon = new ImageIcon(photoFilePath);
            Image scaledImage = photoIcon.getImage().getScaledInstance(height/ animalNames.length, height/ animalNames.length, Image.SCALE_SMOOTH);
            ImageIcon scaledPhotoIcon = new ImageIcon(scaledImage);

            JLabel photoLabel = new JLabel(scaledPhotoIcon);
            JLabel descriptionLabel = new JLabel(animalName);

            JPanel legendPanel = new JPanel(new BorderLayout());
            legendPanel.setBorder(blackBorder);
            legendPanel.add(photoLabel, BorderLayout.WEST);
            legendPanel.add(descriptionLabel, BorderLayout.CENTER);

            add(legendPanel);
        }

    }
}
package game;

import game.organisms.Organism;
import game.organisms.animals.*;
import game.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class BoardButtons extends JPanel implements Constants {
    public int x_tiles;
    public int y_tiles;
    private int tileSize;
    private final int height;
    private final World world;
    private JButton[][] board; // Change the type to JButton
    private final LegendPanel legendPanel;

    public BoardButtons(World world, int x_tiles, int y_tiles, int height) {
        this.world = world;
        this.x_tiles = x_tiles;
        this.y_tiles = y_tiles;
        this.height = height;
        this.tileSize = height / this.x_tiles;
        this.board = new JButton[x_tiles][y_tiles]; // Change the type to JButton
        this.legendPanel = new LegendPanel(height - 56);
        createBoard();
    }

    public void createBoard() {
        x_tiles = world.getTilesX();
        y_tiles = world.getTilesY();
        tileSize = height / this.x_tiles;
        JPanel boardContainer = new JPanel(new GridLayout(x_tiles, y_tiles, 0, 0));
        this.setLayout(new BorderLayout());
        this.add(boardContainer, BorderLayout.WEST);
        this.add(legendPanel, BorderLayout.EAST);

        for (int row = 0; row < x_tiles; row++) {
            for (int column = 0; column < y_tiles; column++) {
                board[row][column] = new JButton(); // Create a JButton
                board[row][column].setPreferredSize(new Dimension(tileSize, tileSize));
                board[row][column].addMouseListener(new ButtonMouseListener(row, column));

                boardContainer.add(board[row][column]);
            }
        }
    }

    private ImageIcon createIcon(ImageIcon icon) {
        x_tiles = world.getTilesX();
        y_tiles = world.getTilesY();
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(getHeight() / x_tiles, getHeight() / x_tiles, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public void draw() {
        x_tiles = world.getTilesX();
        y_tiles = world.getTilesY();
        this.board = new JButton[x_tiles][y_tiles];
        createBoard();

        for (int column = 0; column < y_tiles; column++) {
            for (int row = 0; row < x_tiles; row++) {
                if (world.getOrganism(row, column) != null) {
                    if (Objects.equals(world.getOrganism(row, column).getName(), NAME_HUMAN) && world.getOrganism(row, column).getDUR() > 0) {
                        ImageIcon icon = createIcon(new ImageIcon(System.getProperty("user.dir") + "/Java Project/src/" + "chad.png"));
                        board[row][column].setIcon(icon);
                        board[row][column].addMouseListener(new ButtonMouseListener(row, column));
                    } else {
                        ImageIcon icon = createIcon(new ImageIcon(System.getProperty("user.dir") + "/Java Project/src/" +
                                world.getOrganism(row, column).getName() + ".png"));
                        board[row][column].setIcon(icon);
                        board[row][column].addMouseListener(new ButtonMouseListener(row, column));
                    }
                } else {
                    ImageIcon icon = createIcon(new ImageIcon(System.getProperty("user.dir") + "/Java Project/src/meadow.png"));
                    board[row][column].setIcon(icon);
                    board[row][column].addMouseListener(new ButtonMouseListener(row, column));
                }
            }
        }

        // Add ActionListener to each button
        for (int row = 0; row < x_tiles; row++) {
            for (int column = 0; column < y_tiles; column++) {
                board[row][column].addMouseListener(new ButtonMouseListener(row, column));
            }
        }
    }
    private class ButtonMouseListener extends MouseAdapter {
    private final int row;
    private final int column;

    public ButtonMouseListener(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem[] menuItems = new JMenuItem[11];
            menuItems[0] = new JMenuItem("Antelope");
            menuItems[1] = new JMenuItem("Fox");
            menuItems[2] = new JMenuItem("Sheep");
            menuItems[3] = new JMenuItem("Turtle");
            menuItems[4] = new JMenuItem("Wolf");
            menuItems[5] = new JMenuItem("Belladonna");
            menuItems[6] = new JMenuItem("Grass");
            menuItems[7] = new JMenuItem("Guarana");
            menuItems[8] = new JMenuItem("Sosnowsky");
            menuItems[9] = new JMenuItem("Sow_thistle");
            for (int i = 0; i < 10; i++) {
                final int index = i;
                menuItems[i].addActionListener(e1 -> {
                    if(index==0 )
                    {
                        Organism antelope = new Antelope(world, row, column);
                        world.addOrganism(antelope);
                    }
                    else if(index==1 )
                    {
                        Organism fox = new Fox(world, row, column);
                        world.addOrganism(fox);
                    }
                    else if(index==2 )
                    {
                        Organism sheep = new Sheep(world, row, column);
                        world.addOrganism(sheep);
                    }
                    else if(index==3 )
                    {
                        Organism turtle = new Turtle(world, row, column);
                        world.addOrganism(turtle);
                    }
                    else if(index==4 )
                    {
                        Organism wolf = new Wolf(world, row, column);
                        world.addOrganism(wolf);
                    }
                    else if(index==5 )
                    {
                        Organism belladonna = new Belladonna(world, row, column);
                        world.addOrganism(belladonna);
                    }
                    else if(index==6 )
                    {
                        Organism grass = new Grass(world, row, column);
                        world.addOrganism(grass);
                    }
                    else if(index==7 )
                    {
                        Organism guarana = new Guarana(world, row, column);
                        world.addOrganism(guarana);
                    }
                    else if(index==8 )
                    {
                        Organism sosnowsky = new Sosnowsky(world, row, column);
                        world.addOrganism(sosnowsky);
                    }
                    else {
                        Organism sow_thistle = new Sow_thistle(world, row, column);
                        world.addOrganism(sow_thistle);
                    }
                });
                popupMenu.add(menuItems[i]);
            }

            JButton button = (JButton) e.getSource();
            popupMenu.show(button, e.getX(), e.getY());
        }
    }
}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

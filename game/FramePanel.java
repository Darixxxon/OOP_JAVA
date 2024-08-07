package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FramePanel extends JFrame implements ActionListener, Constants, MouseListener, KeyListener
{
    private final int x_tiles, y_tiles;
    private JButton bNextTurn;
    private JMenuItem mOpen, mSave;
    private final World world;
    private BoardButtons board;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenHeight = (int) screenSize.getHeight();

    public FramePanel(World world) throws IOException {
        this.world = world;
        this.x_tiles = world.getTilesX();
        this.y_tiles = world.getTilesY();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        createFrame();
        this.setResizable(false);
        this.setVisible(true);
        world.makeTurn();
        board.draw();
    }
    private void createFrame()
    {
        this.setPreferredSize(new Dimension((int) ((screenHeight * 0.9 - MENU_SIZE - BUTTON_SIZE)/x_tiles*(y_tiles+2)), (int) (screenHeight * 0.9)));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(BORDER, BORDER));
        this.board = new BoardButtons(world, x_tiles, y_tiles, (int) (screenHeight * 0.9 - MENU_SIZE - BUTTON_SIZE));
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(buttonPanel(), BorderLayout.PAGE_END);
        this.add(mainPanel);
        this.menuPanel();
        this.setTitle("Dariusz Morzuch 193074 meadow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
    private void menuPanel()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("Game");

        mOpen = new JMenuItem("Open");
        mOpen.addActionListener(this);

        mSave = new JMenuItem("Save");
        mSave.addActionListener(this);

        menuFile.add(mSave);
        menuFile.add(mOpen);

        mOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        mSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        menuBar.add(menuFile);
    }
    private JPanel buttonPanel()
    {
        JPanel buttonPanel = new JPanel();
        bNextTurn = new JButton("Next turn");
        bNextTurn.addActionListener(this);
        bNextTurn.setFocusable(false);
        buttonPanel.add(bNextTurn, BorderLayout.PAGE_END);
        return buttonPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object action = e.getSource();
        if (action == bNextTurn)
        {
            if (world.isHumanLive_==0)
            {
                try {
                    world.makeTurn();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                board.draw();
                return;
            }
        }
        if (action == mOpen)
        {
            JFileChooser toOpen = new JFileChooser();
            if (toOpen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = toOpen.getSelectedFile();
                try
                {
                    world.open(file);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Opened file " + file.getAbsolutePath());
            }
        }
        if (action == mSave)
        {
            JFileChooser toSave = new JFileChooser();
            if (toSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = toSave.getSelectedFile();
                try
                {
                    world.save(file);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "You saved game at " + file.getAbsolutePath());
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent k)
    {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_UP -> world.movement = 1;
            case KeyEvent.VK_DOWN -> world.movement = 2;
            case KeyEvent.VK_LEFT -> world.movement = 3;
            case KeyEvent.VK_RIGHT -> world.movement = 4;
            case KeyEvent.VK_R -> world.movement = 5;
            case KeyEvent.VK_SPACE -> {
                if (world.isHumanLive_==0)
                {
                    try {
                        world.makeTurn();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    board.draw();
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
    @Override
    public void keyReleased(KeyEvent k)
    {

    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e)
    {

    }
    @Override
    public void mouseReleased(MouseEvent e)
    {

    }
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }
    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

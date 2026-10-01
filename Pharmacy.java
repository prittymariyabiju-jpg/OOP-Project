import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


    class titlebar extends JFrame{

    private final JPanel titleBar;
    private final JLabel titleLabel;
    private final JLabel titleLabel2;
    private final JButton minimizeButton;
    private final JButton maximizeButton;
    private final JButton closeButton;

    private Point mousePosition;

    public titlebar() {

        // Remove default title bar
        setUndecorated(true);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

        // =========================
        // CUSTOM TITLE BAR
        // =========================

        titleBar = new JPanel(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(1000, 45));
        titleBar.setBackground(new Color(1,74,182));

        // titlebar
        JLabel titlebarLabel = new JLabel();

        ImageIcon icon = new ImageIcon("logo.jpeg");

        Image titlebarImage = icon.getImage().getScaledInstance(
                30,
                30,
                Image.SCALE_SMOOTH
        );

        titlebarLabel.setIcon(new ImageIcon(titlebarImage));
        titlebarLabel.setBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 5)
        );

        // Title
        titleLabel = new JLabel("Pharma");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );
        titleLabel2 = new JLabel("Care");
        titleLabel2.setForeground(new Color(6,162,238));
        titleLabel2.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        // Left side
        JPanel leftTitle = new JPanel(new FlowLayout(
                FlowLayout.LEFT,
                0,
                7
        ));

        leftTitle.setOpaque(false);
        leftTitle.add(titlebarLabel);
        leftTitle.add(titleLabel);
        leftTitle.add(titleLabel2);

        titleBar.add(leftTitle, BorderLayout.WEST);

        // =========================
        // WINDOW BUTTONS
        // =========================

        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 3)
        );

        buttonPanel.setOpaque(false);

        minimizeButton = createButton("—");
        maximizeButton = createButton("□");
        closeButton = createButton("X");

        buttonPanel.add(minimizeButton);
        buttonPanel.add(maximizeButton);
        buttonPanel.add(closeButton);

        titleBar.add(buttonPanel, BorderLayout.EAST);

        // Minimize
        minimizeButton.addActionListener(e -> {
            setState(JFrame.ICONIFIED);
        });

        // Maximize / Restore
        maximizeButton.addActionListener(e -> {

            if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.NORMAL);
            } else {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        // Close
        closeButton.addActionListener(e -> {
            System.exit(0);
        });

        // =========================
        // DRAG WINDOW
        // =========================

        titleBar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePosition = e.getPoint();
            }
        });

        titleBar.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                int x = e.getXOnScreen() - mousePosition.x;
                int y = e.getYOnScreen() - mousePosition.y;

                setLocation(x, y);
            }
        });

        
    
        // =========================
        // ADD EVERYTHING
        // =========================

     JPanel container = new JPanel(new BorderLayout());

        container.add(titleBar, BorderLayout.NORTH);
        container.add(new login().login1(),BorderLayout.CENTER);

        setContentPane(container);

        setVisible(true);
    } 

    // Create title-bar button
    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setForeground(Color.WHITE);
        button.setBackground(new Color(1,74,182));

        button.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);

        return button;
    }
}

    class login  {

    private JLabel imageLabel;
    private Image originalImage;

    Component login1() {

    
        
        // Main Container Split Panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // Assemble Left and Right Panels
        mainPanel.add(createLeftBrandPanel());
        mainPanel.add(createRightLoginPanel());
        return mainPanel;

       // add(mainPanel);
    }

    // Left Branding Section
    private JPanel createLeftBrandPanel() {
        ImageIcon icon = new ImageIcon("login.jpeg");
        originalImage = icon.getImage();

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        // Left panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(imageLabel, BorderLayout.CENTER);
        // Resize image whenever window size changes
        leftPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage(leftPanel.getWidth(), leftPanel.getHeight());
            }
        });
        return leftPanel;
    }

    private void resizeImage(int width, int height) {

        if (originalImage == null || width <= 0 || height <= 0)
            return;

        Image resizedImage = originalImage.getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        imageLabel.setIcon(new ImageIcon(resizedImage));
    }


    // Right Form Section
    private JPanel createRightLoginPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(248, 250, 252));

        // White Card Container
        JPanel cardContainer = new JPanel();
        cardContainer.setPreferredSize(new Dimension(380, 460));
        cardContainer.setBackground(Color.WHITE);
        cardContainer.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(30, 30, 30, 30)
        ));
        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));

        // Heading: Login
        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        loginTitle.setForeground(new Color(15, 45, 82));
        loginTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Subheading
        JLabel loginSubtitle = new JLabel("Welcome back! Please login to your account.");
        loginSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        loginSubtitle.setForeground(new Color(100, 116, 139));
        loginSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        usernameField.setMargin(new Insets(5, 10, 5, 10));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(0, 8, 0, 8)
        ));
        usernameField.setToolTipText("Username");

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(0, 8, 0, 8)
        ));

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(24,105,180));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginBtn.addActionListener((ActionEvent e) -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            
            if (!user.isEmpty() && !pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Logging in as: " + user, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Exit Button
        JButton exitBtn = new JButton("Exit");
        exitBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        exitBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        exitBtn.setForeground(new Color(24,105,180));
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.setBorder(new LineBorder(new Color(24,105,180), 1, true));
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        exitBtn.addActionListener((ActionEvent e) -> System.exit(0));

        // Assemble Components inside Card Container
        cardContainer.add(loginTitle);
        cardContainer.add(Box.createRigidArea(new Dimension(0, 8)));
        cardContainer.add(loginSubtitle);
        cardContainer.add(Box.createRigidArea(new Dimension(0, 25)));

        cardContainer.add(createInputWrapper("👤", usernameField));
        cardContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        cardContainer.add(createInputWrapper("🔒", passwordField));

        cardContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        cardContainer.add(loginBtn);
        cardContainer.add(Box.createRigidArea(new Dimension(0, 12)));
        cardContainer.add(exitBtn);

        rightPanel.add(cardContainer);
        return rightPanel;
    }

    // Helper wrapper to add icons inside text field areas
    private JPanel createInputWrapper(String iconSymbol, JComponent inputComponent) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        JLabel iconLabel = new JLabel(" " + iconSymbol + " ", SwingConstants.CENTER);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        iconLabel.setForeground(new Color(100, 116, 139));
        iconLabel.setBorder(new LineBorder(new Color(203, 213, 225), 1, false));

        wrapper.add(iconLabel, BorderLayout.WEST);
        wrapper.add(inputComponent, BorderLayout.CENTER);

        return wrapper;
    }
    
}
class Pharmacy{
    public static void main(String[] args){
    
        SwingUtilities.invokeLater(()-> new titlebar());

    }
}
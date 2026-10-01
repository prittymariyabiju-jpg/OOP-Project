import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class sree extends JFrame {

    // PharmaCare theme colors
    private final Color SIDEBAR = new Color(8, 66, 126);
    //private final Color BLUE = new Color(0, 153, 220);
    private final Color DARK = new Color(22, 61, 96);
    private final Color BG = new Color(243, 249, 252);
    private final Color BORDER = new Color(205, 226, 235);

    public sree() {
        setTitle("PharmaCare - Dashboard");
        setSize(1536, 950);
        setMinimumSize(new Dimension(1100, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(BG);

        add(createSidebar(), BorderLayout.WEST);
        add(createMainContent(), BorderLayout.CENTER);
    }

    // ---------------- SIDEBAR ----------------

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(SIDEBAR);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setBackground(SIDEBAR);
        logoPanel.setBorder(
            BorderFactory.createEmptyBorder(22, 12, 22, 12)
        );

        JLabel logo = new JLabel("✚");
        logo.setFont(new Font("SansSerif", Font.BOLD, 66));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel brand = new JLabel("PharmaCare");
        brand.setFont(new Font("SansSerif", Font.BOLD, 30));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Health  •  Care  •  Always");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 11));
        tagline.setForeground(new Color(220, 238, 250));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(logo);
        logoPanel.add(Box.createVerticalStrut(3));
        logoPanel.add(brand);
        logoPanel.add(Box.createVerticalStrut(7));
        logoPanel.add(tagline);

        sidebar.add(logoPanel, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(SIDEBAR);
        menu.setBorder(BorderFactory.createEmptyBorder(8, 9, 8, 9));

        String[][] items = {
            {"⌂", "Dashboard"},
            {"◆", "Add Medicine"},
            {"▣", "Medicine Inventory"},
            {"☑", "Update Medicine"},
            {"♟", "Add Customer"},
            {"▤", "Sales / POS"},
            {"▣", "Billing"},
            {"▰", "Supplier"},
            {"▧", "Expiry / Low Stock"},
            {"▤", "Reports"},
            {"♙", "User / Profile"}
        };

        for (int i = 0; i < items.length; i++) {
            JButton button = createMenuButton(
                items[i][0], items[i][1], i == 0
            );

            menu.add(button);
            menu.add(Box.createVerticalStrut(5));
        }

        sidebar.add(menu, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(SIDEBAR);
        bottom.setBorder(
            BorderFactory.createEmptyBorder(10, 18, 20, 15)
        );

        JSeparator line = new JSeparator();
        line.setForeground(new Color(55, 133, 184));

        JButton logout = createMenuButton("⇥", "Logout", false);

        bottom.add(line, BorderLayout.NORTH);
        bottom.add(logout, BorderLayout.CENTER);

        sidebar.add(bottom, BorderLayout.SOUTH);

        return sidebar;
    }

    private JButton createMenuButton(
            String symbol, String name, boolean selected) {

        JButton button = new JButton("  " + symbol + "     " + name);
        button.setFont(new Font("SansSerif", Font.PLAIN, 15));
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBackground(
            selected ? new Color(15, 125, 190) : SIDEBAR
        );
        button.setMaximumSize(new Dimension(240, 49));
        button.setPreferredSize(new Dimension(230, 49));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            if (name.equals("Logout")) {
                int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            } else if (!name.equals("Dashboard")) {
                JOptionPane.showMessageDialog(
                    this,
                    name + " interface can be connected here.",
                    name,
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        return button;
    }

    // ---------------- MAIN CONTENT ----------------

    private JPanel createMainContent() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BG);

        main.add(createTopBar(), BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(0, 22));
        content.setBackground(BG);
        content.setBorder(
            BorderFactory.createEmptyBorder(22, 24, 22, 24)
        );

        content.add(createWelcomeBanner(), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(0, 22));
        center.setBackground(BG);

        center.add(createSummaryCards(), BorderLayout.NORTH);
        center.add(createTablesSection(), BorderLayout.CENTER);

        content.add(center, BorderLayout.CENTER);
        main.add(content, BorderLayout.CENTER);

        return main;
    }

    // ---------------- TOP BAR ----------------

    private JPanel createTopBar() {
        JPanel top = new JPanel(new BorderLayout());
        top.setPreferredSize(new Dimension(0, 80));
        top.setBackground(new Color(232, 246, 252));
        top.setBorder(
            BorderFactory.createMatteBorder(
                0, 0, 1, 0, BORDER
            )
        );

        JPanel right = new JPanel(new FlowLayout(
            FlowLayout.RIGHT, 25, 20
        ));
        right.setBackground(new Color(232, 246, 252));

        JLabel admin = new JLabel("👤  Admin  ▾");
        admin.setFont(new Font("SansSerif", Font.PLAIN, 15));
        admin.setForeground(DARK);

        JSeparator separator = new JSeparator(
            SwingConstants.VERTICAL
        );
        separator.setPreferredSize(new Dimension(1, 35));

        JLabel date = new JLabel(
            "<html><b>▦ &nbsp; 24 Sep 2025</b>"
            + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 10:34 AM</html>"
        );
        date.setFont(new Font("SansSerif", Font.PLAIN, 13));
        date.setForeground(DARK);

        right.add(admin);
        right.add(separator);
        right.add(date);

        top.add(right, BorderLayout.EAST);
        return top;
    }

    // ---------------- WELCOME BANNER ----------------

    private JPanel createWelcomeBanner() {
        RoundedPanel banner = new RoundedPanel(
            15, new Color(225, 243, 252)
        );
        banner.setLayout(new BorderLayout(22, 0));
        banner.setBorder(
            BorderFactory.createEmptyBorder(20, 28, 20, 28)
        );

        JPanel branding = new JPanel(new FlowLayout(
            FlowLayout.LEFT, 20, 15
        ));
        branding.setOpaque(false);

        JLabel cross = new JLabel("✚");
        cross.setFont(new Font("SansSerif", Font.BOLD, 82));
        cross.setForeground(new Color(0, 119, 195));

        JPanel words = new JPanel();
        words.setLayout(new BoxLayout(words, BoxLayout.Y_AXIS));
        words.setOpaque(false);

        JLabel title = new JLabel("PharmaCare");
        title.setFont(new Font("SansSerif", Font.BOLD, 45));
        title.setForeground(new Color(12, 64, 115));

        JLabel tagline = new JLabel("H e a l t h   •   C a r e   •   A l w a y s");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tagline.setForeground(new Color(51, 119, 163));

        words.add(title);
        words.add(Box.createVerticalStrut(5));
        words.add(tagline);

        branding.add(cross);
        branding.add(words);

        JPanel divider = new JPanel();
        divider.setBackground(new Color(180, 211, 226));
        divider.setPreferredSize(new Dimension(1, 95));

        JPanel welcome = new JPanel();
        welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));
        welcome.setOpaque(false);

        JLabel heading = new JLabel(
            "<html>Welcome Back,<br><font color='#0787CF'>Admin!</font></html>"
        );
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setForeground(DARK);

        JLabel subtitle = new JLabel(
            "Manage your pharmacy easily and efficiently."
        );
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setForeground(new Color(74, 123, 157));

        welcome.add(heading);
        welcome.add(Box.createVerticalStrut(5));
        welcome.add(subtitle);

        banner.add(branding, BorderLayout.WEST);
        banner.add(divider, BorderLayout.CENTER);
        banner.add(welcome, BorderLayout.EAST);

        banner.setPreferredSize(new Dimension(0, 175));
        return banner;
    }

    // ---------------- SUMMARY CARDS ----------------

    private JPanel createSummaryCards() {
        JPanel cards = new JPanel(new GridLayout(1, 4, 24, 0));
        cards.setBackground(BG);

        cards.add(createStatCard(
            "💊", "Total Medicines", "248",
            new Color(229, 243, 255),
            new Color(0, 108, 185)
        ));

        cards.add(createStatCard(
            "▣", "Total Stock", "3,412",
            new Color(229, 248, 241),
            new Color(13, 126, 89)
        ));

        cards.add(createStatCard(
            "⚠", "Low Stock", "18",
            new Color(255, 247, 235),
            new Color(226, 132, 30)
        ));

        cards.add(createStatCard(
            "▦", "Expired Medicine", "6",
            new Color(255, 237, 242),
            new Color(196, 48, 78)
        ));

        cards.setPreferredSize(new Dimension(0, 190));
        return cards;
    }

    private JPanel createStatCard(
            String symbol, String title, String value,
            Color background, Color accent) {

        RoundedPanel card = new RoundedPanel(14, background);
        card.setLayout(new BorderLayout(8, 4));
        card.setBorder(
            BorderFactory.createEmptyBorder(18, 20, 15, 18)
        );

        JLabel icon = new JLabel(symbol, SwingConstants.CENTER);
        icon.setOpaque(true);
        icon.setBackground(accent);
        icon.setForeground(Color.WHITE);
        icon.setFont(new Font("SansSerif", Font.BOLD, 25));
        icon.setPreferredSize(new Dimension(55, 55));
        icon.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        JPanel iconWrap = new JPanel(new FlowLayout(
            FlowLayout.LEFT, 0, 0
        ));
        iconWrap.setOpaque(false);
        iconWrap.add(icon);

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("SansSerif", Font.PLAIN, 16));
        heading.setForeground(DARK);

        JLabel number = new JLabel(value);
        number.setFont(new Font("SansSerif", Font.BOLD, 36));
        number.setForeground(accent);

        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setOpaque(false);
        details.add(heading);
        details.add(Box.createVerticalStrut(5));
        details.add(number);

        JLabel arrow = new JLabel("›", SwingConstants.CENTER);
        arrow.setFont(new Font("SansSerif", Font.BOLD, 28));
        arrow.setForeground(accent);
        arrow.setOpaque(true);
        arrow.setBackground(new Color(
            accent.getRed(), accent.getGreen(),
            accent.getBlue(), 25
        ));
        arrow.setPreferredSize(new Dimension(38, 38));

        card.add(iconWrap, BorderLayout.NORTH);
        card.add(details, BorderLayout.CENTER);
        card.add(arrow, BorderLayout.EAST);

        return card;
    }

    // ---------------- TABLES ----------------

    private JPanel createTablesSection() {
        JPanel section = new JPanel(new GridLayout(1, 2, 24, 0));
        section.setBackground(BG);

        section.add(createRecentSales());
        section.add(createLowStockTable());

        return section;
    }

    private JPanel createRecentSales() {
        String[] columns = {
            "Date", "Bill No", "Customer Name",
            "Total Amount", "Payment Mode"
        };

        Object[][] data = {
            {"24-09-2025", "BIL-1006", "Anjali S", "₹ 560", "UPI"},
            {"24-09-2025", "BIL-1005", "Rakesh", "₹ 230", "Cash"},
            {"23-09-2025", "BIL-1004", "Meena", "₹ 780", "Card"},
            {"23-09-2025", "BIL-1003", "Suresh", "₹ 410", "Cash"},
            {"22-09-2025", "BIL-1002", "Deepa", "₹ 250", "UPI"}
        };

        return createTablePanel(
            "◷", "Recent Sales", columns, data
        );
    }

    private JPanel createLowStockTable() {
        String[] columns = {
            "Medicine ID", "Name", "Stock", "Status"
        };

        Object[][] data = {
            {"M001", "Paracetamol", "3", "Low Stock"},
            {"M017", "Amoxicillin", "5", "Low Stock"},
            {"M023", "Vitamin D3", "8", "Low Stock"},
            {"M034", "Cetirizine", "6", "Low Stock"},
            {"M041", "ORS", "4", "Low Stock"}
        };

        return createTablePanel(
            "⚠", "Low Stock Alert", columns, data
        );
    }

    private JPanel createTablePanel(
            String symbol, String title,
            String[] columns, Object[][] data) {

        RoundedPanel panel = new RoundedPanel(13, Color.WHITE);
        panel.setLayout(new BorderLayout(0, 15));
        panel.setBorder(
            BorderFactory.createEmptyBorder(18, 15, 15, 15)
        );

        JPanel heading = new JPanel(new BorderLayout());
        heading.setOpaque(false);

        JLabel titleLabel = new JLabel(
            symbol + "    " + title
        );
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(DARK);

        JButton viewAll = new JButton("View All");
        viewAll.setFont(new Font("SansSerif", Font.PLAIN, 13));
        viewAll.setForeground(new Color(0, 125, 185));
        viewAll.setBorderPainted(false);
        viewAll.setContentAreaFilled(false);
        viewAll.setFocusPainted(false);
        viewAll.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewAll.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this, "Connect this button to the full "
                + title + " page."
            )
        );

        heading.add(titleLabel, BorderLayout.WEST);
        heading.add(viewAll, BorderLayout.EAST);

        DefaultTableModel model = new DefaultTableModel(
            data, columns
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setForeground(DARK);
        table.setRowHeight(46);
        table.setGridColor(new Color(220, 233, 240));
        table.setShowGrid(true);
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(new Color(220, 239, 250));

        table.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );
        table.getTableHeader().setForeground(DARK);
        table.getTableHeader().setBackground(
            new Color(231, 244, 252)
        );
        table.getTableHeader().setPreferredSize(
            new Dimension(0, 43)
        );
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(
            BorderFactory.createLineBorder(
                new Color(218, 233, 240)
            )
        );
        scroll.getViewport().setBackground(Color.WHITE);

        panel.add(heading, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // ---------------- ROUNDED PANEL ----------------

    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color panelColor;

        RoundedPanel(int radius, Color color) {
            this.radius = radius;
            this.panelColor = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(panelColor);
            g2.fill(new RoundRectangle2D.Float(
                0, 0, getWidth() - 1, getHeight() - 1,
                radius, radius
            ));

            g2.setColor(new Color(207, 228, 237));
            g2.draw(new RoundRectangle2D.Float(
                0, 0, getWidth() - 1, getHeight() - 1,
                radius, radius
            ));

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> /*{
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }*/

            new sree().setVisible(true));
    }
}
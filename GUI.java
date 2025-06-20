import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private JTextPane chatPane;
    private JTextField inputField;
    private Chatbot bot;
    private StyledDocument doc;

    public GUI() {
        bot = new Chatbot();

        setTitle("Java AI Chatbot - Formal Dark Theme");
        setSize(600, 700); // slightly larger frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Main Panel with Dark Gray Background =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(46, 46, 46));
        setContentPane(mainPanel);

        // ===== Chat Area with Bigger Font and Padding =====
        chatPane = new JTextPane();
        chatPane.setEditable(false);
        chatPane.setBackground(new Color(58, 58, 58));
        chatPane.setFont(new Font("SansSerif", Font.PLAIN, 18)); // bigger font
        chatPane.setMargin(new Insets(15, 15, 15, 15));
        doc = chatPane.getStyledDocument();

        JScrollPane scrollPane = new JScrollPane(chatPane);
        scrollPane.setPreferredSize(new Dimension(580, 600)); // larger area
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(new Color(58, 58, 58));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ===== Bigger Input Field =====
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(0, 50)); // taller field
        inputField.setBackground(new Color(68, 68, 68));
        inputField.setForeground(new Color(230, 230, 230));
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18)); // larger font
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(90, 90, 90)));
        inputField.setMargin(new Insets(10, 15, 10, 15));
        mainPanel.add(inputField, BorderLayout.SOUTH);

        // ===== Input Handler =====
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (!userInput.isEmpty()) {
                    appendMessage("You: " + userInput + "\n", new Color(176, 196, 222));
                    String botReply = bot.getResponse(userInput);
                    appendMessage("Bot: " + botReply + "\n", new Color(220, 220, 220));
                    inputField.setText("");
                }
            }
        });

        setVisible(true);
    }

    // ===== Styled Message Output =====
    private void appendMessage(String message, Color color) {
        Style style = chatPane.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        StyleConstants.setFontFamily(style, "SansSerif");
        StyleConstants.setFontSize(style, 18); // match chat font size
        try {
            doc.insertString(doc.getLength(), message, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

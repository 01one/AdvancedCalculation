import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Goldbach extends Frame {
    TextField txt = new TextField(8);
    TextArea result = new TextArea(8, 40);
    Label processing = new Label("                    ");
    Button button = new Button("Calculate");
    int q;

    static String calculate(int x) {
        if (x % 2 != 0 || x <= 2) return "Enter an even number greater than two";
        boolean[] c = new boolean[x + 1];
        for (int i = 2; i <= x; i++) if (!c[i]) for (int j = 2 * i; j <= x; j += i) c[j] = true;
        java.util.List<String> l = new ArrayList<>();
        for (int a = x / 2; a <= x - 2; a++) if (!c[a] && !c[x - a]) l.add("[" + a + ", " + (x - a) + "]");
        return l.toString();
    }

    Goldbach() {
        super("Goldbach's Conjecture");
        setLayout(new FlowLayout());
        add(txt); add(button); add(processing); add(result);
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= ' ' && (!Character.isDigit(c) || txt.getText().length() >= 8)) e.consume();
            }
        });
        ActionListener start = e -> {
            if (txt.getText().isEmpty()) return;
            int x_input = Integer.parseInt(txt.getText()), id = ++q;
            result.setText("");
            processing.setText("Processing");
            new Thread(() -> {
                String r = calculate(x_input);
                EventQueue.invokeLater(() -> { if (id == q) { result.setText(r); processing.setText(""); } });
            }).start();
        };
        txt.addActionListener(start);
        button.addActionListener(start);
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } });
        setSize(520, 300);
        setVisible(true);
    }

    public static void main(String[] args) { new Goldbach(); }
}

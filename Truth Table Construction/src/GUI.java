import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table;
    private JTable table_1;
    private JTable expression1;
    private JTable expression2;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 910, 742);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.WEST);

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        final JButton btnEnter = new JButton("Enter");
        btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
            }
        });

        table = new JTable();

        table_1 = new JTable();

        expression1 = new JTable();

        expression2 = new JTable();
        final GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(table_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel.createSequentialGroup()
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
                                        .addGap(68)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnEnter)
                                .addGroup(gl_panel.createSequentialGroup()
                                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(expression2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(expression1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                                        .addGap(26)
                                        .addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(122, Short.MAX_VALUE))
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(14)
                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(31)
                        .addComponent(btnEnter)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_panel.createSequentialGroup()
                                        .addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
                                        .addGap(32)
                                        .addComponent(table_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
                                .addComponent(expression1, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addComponent(expression2, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))
                );
        panel.setLayout(gl_panel);
    }
}

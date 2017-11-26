import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUI {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTable expression2;
    private  TableColumn tableModel;
    private JTable expression1;
    final String[] columns = new String [500];
    DefaultTableModel model  = new DefaultTableModel(columns,0);
    DefaultTableModel model2 = new DefaultTableModel(columns,0);


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
        textField.setText("");
        textField_1.setText("");

        final JButton btnEnter = new JButton("Enter");
        btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnEnter.addActionListener(new ActionListener() {
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            @Override
            public void actionPerformed(final ActionEvent e) {

                final String expression1 = textField.getText();
                final String expression2 = textField_1.getText();
                final ValidateAndCalculate calculate = new ValidateAndCalculate();
                final ValidateAndCalculate calculate2 = new ValidateAndCalculate();

                calculate.setExpression(expression1);
                calculate2.setExpression(expression2);
                if (calculate.isAvalidExpression() == false || expression1.length() == 0 )
                {
                    if(expression1.length()!=0)
                        JOptionPane.showMessageDialog(null, "INVALID INPUT", "InfoBox: Error" , JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    final GenerateInput generator1 = new GenerateInput();
                    final Output output1 = new Output();
                    output1.equation = expression1;
                    final HashMap<Character, Boolean> input1 = new HashMap<>();
                    generator1.getVariables(expression1);
                    generator1.generateAllValidInputs(input1, 0, output1);
                    final String[] variables = new String [generator1.variables.size()+1];

                    for(int i = 0; i < generator1.variables.size(); i++){
                        variables[i] = generator1.variables.get(i).toString();
                    }
                    variables[generator1.variables.size()] = "Output";
                    final Object[] firstrow = {variables};
                    model.getDataVector().removeAllElements();
                    model.addRow(variables);
                    for(int i=0;i<output1.allInputs.size();i++)
                    {
                        final String[] row = new String [generator1.variables.size() + 1];
                        for(int j = 0; j < generator1.variables.size(); j++){
                            row[j] = output1.allInputs.get(i).get(generator1.variables.get(j)).toString();

                        }
                        row[generator1.variables.size()] = output1.results.get(i);
                        model.addRow(row);
                    }

                }
                if (calculate.isAvalidExpression() == false ||expression2.length()==0 )
                {
                    if(expression2.length()!=0)
                        JOptionPane.showMessageDialog(null, "INVALID INPUT", "InfoBox: Error" , JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    final GenerateInput generator1 = new GenerateInput();
                    final Output output = new Output();
                    output.equation = expression2;
                    final HashMap<Character, Boolean> input = new HashMap<>();
                    generator1.getVariables(expression2);
                    generator1.generateAllValidInputs(input, 0, output);
                    final String[] variables = new String [generator1.variables.size()+1];

                    for(int i = 0; i < generator1.variables.size(); i++){
                        variables[i] = generator1.variables.get(i).toString();
                    }
                    variables[generator1.variables.size()] = "Output";
                    final Object[] firstrow = {variables};

                    model2.getDataVector().removeAllElements();
                    GUI.this.expression2.getColumnModel().getColumn(0).setPreferredWidth(36);
                    model2.addRow(variables);
                    for(int i=0;i<output.allInputs.size();i++)
                    {
                        final String[] row = new String [generator1.variables.size() + 1];
                        for(int j = 0; j < generator1.variables.size(); j++){
                            row[j] = output.allInputs.get(i).get(generator1.variables.get(j)).toString();

                        }
                        row[generator1.variables.size()] = output.results.get(i);
                        model2.addRow(row);
                    }

                }
            }
        });




        expression2 = new JTable(model2);
        GUI.this.expression2.setAutoscrolls(true);
        final JButton btnLoad = new JButton("Load");
        btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 18));
        final JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
        expression1 = new JTable(model);
        final GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(20)
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(expression2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(expression1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(gl_panel.createSequentialGroup()
                                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel.createSequentialGroup()
                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(68)
                                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(gl_panel.createSequentialGroup()
                                                        .addComponent(btnEnter)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(11)
                                                        .addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())))
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(14)
                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnEnter)
                                        .addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                        .addGap(23)
                        .addComponent(expression1, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(expression2, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                        .addGap(67))
                );
        panel.setLayout(gl_panel);
    }
}

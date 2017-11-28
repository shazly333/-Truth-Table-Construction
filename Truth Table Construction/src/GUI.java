import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    final String[] columns = new String [40];
    DefaultTableModel model  = new DefaultTableModel(columns,0);
    DefaultTableModel model2 = new DefaultTableModel(columns,0);
    Output saveOutPut = new Output();
    JLabel lblNewLabel = new JLabel("");
    JLabel state1 = new JLabel("");

    JLabel state2 = new JLabel("");
    private JLabel lblNewLabel_1;

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
    public Output fillTruthTable(final String expression, final DefaultTableModel model)
    {
        final GenerateInput generator = new GenerateInput();
        final Output output = new Output();
        output.equation = expression;
        final HashMap<Character, Boolean> input = new HashMap<>();
        generator.getVariables(expression);
        generator.generateAllValidInputs(input, 0, output);
        final String[] variables = new String [generator.variables.size()+1];

        for(int i = 0; i < generator.variables.size(); i++){
            variables[i] = generator.variables.get(i).toString();
        }
        variables[generator.variables.size()] = "Output";
        final Object[] firstrow = {variables};
        model.getDataVector().removeAllElements();
        model.addRow(variables);
        for(int i=0;i<output.allInputs.size();i++)
        {
            final String[] row = new String [generator.variables.size() + 1];
            for(int j = 0; j < generator.variables.size(); j++){
                row[j] = output.allInputs.get(i).get(generator.variables.get(j)).toString();

            }
            row[generator.variables.size()] = output.results.get(i);
            model.addRow(row);
        }
        output.variables = generator.variables;
        return output;
    }
    final boolean equal(final Output output1, final Output output2) {
        if(output1.variables.size() != output2.variables.size())
            return false;
        for(int i = 0; i < output1.variables.size(); i++) {
            int flag = 0;
            for(int j = 0; j < output2.variables.size(); j++) {
                if(output1.variables.get(i).equals(output2.variables.get(j))){
                    flag = 1;
                }
            }
            if(flag == 0)
                return false;
        }
        for(int i = 0; i < output1.results.size(); i++) {
            int flag = 0;
            for(int j = 0; j < output2.results.size(); j++) {
                if(output1.results.get(i).equals(output2.results.get(j))) {
                    flag = 1;
                }
            }
            if(flag == 0)
                return false;
        }

        return true;
    }
    public int contradiction (final Output output){
        int tatology = 0;
        int contradiction = 0;
        for(int i=0;i<output.results.size();i++) {
            if(output.results.get(i).equals("false"))
                contradiction = 1;
            else if(output.results.get(i).equals("true"))
                tatology = 1;
        }
        if(tatology == 1 && contradiction == 0) {
            return 1;
        }
        else if(tatology == 0 && contradiction == 1) {
            return 0;
        }
        return -1;

    }
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 910, 893);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.WEST);

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField.setText("");
        textField_1.setText("");
        final JButton btnSave = new JButton("Save");
        btnSave.setEnabled(false);
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
                Output output1 = new Output();
                Output output2 = new Output();

                if (calculate.isAvalidExpression() == false || expression1.length() == 0 )
                {
                    if(expression1.length()!=0)
                        JOptionPane.showMessageDialog(null, "INVALID INPUT", "InfoBox: Error" , JOptionPane.ERROR_MESSAGE);
                }
                else
                {

                    saveOutPut = fillTruthTable(expression1, model);
                    btnSave.setEnabled(true);
                    output1 = saveOutPut;
                    if(contradiction(output1) == -1) {
                        state1.setText("Expression is NOt Tatoulgy and is Not Contradiction");
                    }
                    else  if(contradiction(output1) == 1) {
                        state1.setText("Expression is Tatoulgy");
                    }
                    else if(contradiction(output1) == 0) {
                        state1.setText("Expression is Contradiction");
                    }

                }
                if (calculate.isAvalidExpression() == false ||expression2.length()==0 )
                {
                    if(expression2.length()!=0)
                        JOptionPane.showMessageDialog(null, "INVALID INPUT", "InfoBox: Error" , JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    output2 = fillTruthTable(expression2, model2);
                    if(contradiction(output2) == -1) {
                        state2.setText("Expression is NOt Tatoulgy and is Not Contradiction");
                    }
                    else  if(contradiction(output2) == 1) {
                        state2.setText("Expression is Tatoulgy");
                    }
                    else if(contradiction(output2) == 0) {
                        state2.setText("Expression is Contradiction");
                    }
                    if (calculate.isAvalidExpression() == true && expression2.length()!=0 &&  expression1.length() != 0)
                    {
                        if(equal(output1, output2))
                        {
                            lblNewLabel.setText("TWO Expression is equal");
                        }
                        else
                            lblNewLabel.setText("TWO Expression is Not equal");
                    }
                }
            }
        });
        expression2 = new JTable(model2);
        GUI.this.expression2.setAutoscrolls(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    try {
                        final File file = fileChooser.getSelectedFile();
                        final FileOutputStream is = new FileOutputStream(file);
                        final OutputStreamWriter osw = new OutputStreamWriter(is);
                        final BufferedWriter  wr = new BufferedWriter(osw);
                        wr.newLine();
                        String row ="";
                        for(int i=0;i<saveOutPut.allInputs.size();i++)
                        {
                            row ="";
                            for(int j = 0; j < saveOutPut.variables.size(); j++) {
                                row+=(saveOutPut.allInputs.get(i).get(saveOutPut.variables.get(j)).toString())+"    ";
                            }
                            row+=(saveOutPut.results.get(i));
                            wr.write(row);
                            wr.newLine();
                        }
                        wr.close();
                    } catch (final IOException e) {
                        throw null;
                    }
                }
            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
        expression1 = new JTable(model);

        lblNewLabel_1 = new JLabel("To save first input only");


        final GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(20)
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(state2)
                                .addComponent(state1)
                                .addComponent(lblNewLabel)
                                .addGroup(gl_panel.createSequentialGroup()
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
                                        .addGap(68)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(expression2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(btnEnter)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                                .addGap(44)
                                                .addComponent(lblNewLabel_1)
                                                .addGap(2747))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(expression1, GroupLayout.PREFERRED_SIZE, 1849, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED))))
                        .addGap(85))
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(14)
                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(56)
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                .addComponent(btnEnter)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1)))
                        .addGap(23)
                        .addComponent(expression1, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(expression2, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                        .addGap(32)
                        .addComponent(lblNewLabel)
                        .addGap(36)
                        .addComponent(state1)
                        .addGap(34)
                        .addComponent(state2)
                        .addGap(51))
                );
        panel.setLayout(gl_panel);
    }
}

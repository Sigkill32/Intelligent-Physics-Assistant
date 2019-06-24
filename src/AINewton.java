import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Created by Administrator on 2/4/2018.
 */
public class AINewton extends JFrame implements ActionListener{

    JTextArea jTextArea;
    JButton solve,clear;
    JLabel jLabel,jLabel1,eq;

    AINewton() {
        jTextArea = new JTextArea(10,30);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        solve = new JButton("Solve");
        clear = new JButton("Clear");
        jLabel1 = new JLabel("Enter your problem below ");
        jLabel = new JLabel("");
        eq = new JLabel("");
        solve.addActionListener(this);
        clear.addActionListener(this);
        setTitle("NEWTON - Let The Apple Fall Again");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(jLabel1);
        add(jTextArea);
        add(solve);
        add(clear);
        add(eq);
        add(jLabel);
        setLayout(new FlowLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solve) {
            AiHandler aiHandler = new AiHandler();
            SolveEquation solveEquation = new SolveEquation();

            String unknown;
            String ps;
            float solution;
            int uflag, eqnD;
            String[] tokens, strData;
            int tokenLen, eqn=0;
            float[][] value;
            ps = jTextArea.getText();
            jTextArea.setLineWrap(true);
            jTextArea.setWrapStyleWord(true);
            tokens = aiHandler.tokenizer(ps);
            tokenLen = tokens.length;
            strData = aiHandler.strDataExtract(tokens,tokenLen);
            value = solveEquation.valunitSeparate(strData,ps);
            unknown = aiHandler.unknownFnd(ps);
            uflag = aiHandler.unFlag(unknown);
            eqn = solveEquation.eqnDecide(value,ps);
            solution = solveEquation.solveEqun(value,uflag,eqn);
            //jLabel1.setText();
            eqnD = solveEquation.eqnDecide(value,ps);

            if (eqnD == 1)
                eq.setText("            Equation to be used: v = u + a*t            ");
            else if (eqnD == 2)
                eq.setText("            Equation to be used: s = u*t + 0.5*a*t*t            ");
            else if (eqnD == 3)
                eq.setText("            Equation to be used: v*v - u*u = 2*a*s          ");


            if (uflag == 1)
                jLabel.setText("            Solution :"+String.valueOf(solution)+"m/s           ");
            else if (uflag == 2)
                jLabel.setText("            Solution :"+String.valueOf(solution)+"m/s           ");
            else if (uflag == 3)
                jLabel.setText("            Solution :"+String.valueOf(solution)+"m/ssq         ");
            else if (uflag == 4)
                jLabel.setText("            Solution :"+String.valueOf(solution)+"s             ");
            else if (uflag == 5)
                jLabel.setText("            Solution :"+String.valueOf(solution)+"m             ");
        }

        else if (e.getSource() == clear) {
            jTextArea.setText("");
            jLabel.setText("");
            eq.setText("");
        }
    }

    public static void main(String[] args) {
        AINewton aiNewton = new AINewton();
        aiNewton.setSize(400,330);
        aiNewton.setVisible(true);
    }
}

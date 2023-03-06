import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeskovkaForm extends JFrame {
    private JPanel mainPanel;
    private JTextField viewText;
    private JCheckBox checkBox;
    private JRadioButton btnLike1;
    private JRadioButton btnLike2;
    private JRadioButton btnLike3;
    private JLabel mainLabel;
    private JButton btnNext;
    private JButton btnBack;

    int pocetStranek = 0;

    public DeskovkaForm() {
        vypis(0);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            SouborDeskovka deskovka = new SouborDeskovka();
            pocetStranek++;
                vypis(pocetStranek);
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (pocetStranek > 0){
                   pocetStranek--;
                   vypis(pocetStranek);
               }else{
                   pocetStranek = 0;
               }
            }
        });
    }

    void vypis(int index){
            SouborDeskovka souborDeskovka = new SouborDeskovka();
            List<Deskovka> seznam = souborDeskovka.vypisSoubor("deskovky.txt", ";");
            Deskovka pocetSeznam = seznam.get(index);
            viewText.setText(pocetSeznam.getNazev());
            if (pocetSeznam.isZakoupeno() == true){
                checkBox.setSelected(true);
            }else {
                checkBox.setSelected(false);
            }
            if (pocetSeznam.getOblibenost() == 1){
                btnLike1.setSelected(true);
            }else{
                btnLike1.setSelected(false);
            }
            if(pocetSeznam.getOblibenost() == 2){
                btnLike2.setSelected(true);
            }else{
                btnLike2.setSelected(false);
            }
            if(pocetSeznam.getOblibenost() == 3){
                btnLike3.setSelected(true);
            }else{
                btnLike3.setSelected(false);
            }
        }
    public static void main(String[] args) {
        DeskovkaForm deskovka = new DeskovkaForm();
        deskovka.setContentPane(deskovka.mainPanel);
        deskovka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deskovka.pack();
        deskovka.setTitle("Deskovka");
        deskovka.setVisible(true);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(deskovka.btnLike1);
        btnGroup.add(deskovka.btnLike2);
        btnGroup.add(deskovka.btnLike3);
    }
}

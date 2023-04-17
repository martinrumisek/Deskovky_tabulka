import javax.swing.*;
import javax.swing.event.TableModelListener;
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
    private JTable table;
    private JScrollPane scrollPaneTable;
    private int pocetStranek = 0;
    private List<Deskovka> seznam = new ArrayList<>();

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
                pocetStranek--;
                vypis(pocetStranek);
            }
        });
        SouborDeskovka souborDeskovka = new SouborDeskovka();
        seznam = souborDeskovka.vypisSoubor("deskovky.txt", ";");
        table.setModel(new javax.swing.table.TableModel() {
            private final String[] COLUMNS = {"Název", "Koupeno", "Oblíbenost"};
            @Override
            public int getRowCount() {
                return seznam.size();
            }

            @Override
            public int getColumnCount() {
                return COLUMNS.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return COLUMNS[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(getValueAt(0, columnIndex)!= null){
                    return getValueAt(0, columnIndex).getClass();
                }else {
                    return Object.class;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex){
                    case 0 -> seznam.get(rowIndex).getNazev();
                    case 1 -> seznam.get(rowIndex).isZakoupeno();
                    case 2 -> seznam.get(rowIndex).getOblibenost();
                    default -> "-";
                };
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        });
        /*SouborDeskovka souborDeskovka = new SouborDeskovka();
        seznam = souborDeskovka.vypisSoubor("deskovky.txt", ";");
        TableModel tableModel = new TableModel(seznam);
        table.setModel(tableModel);*/
    }

    void vypis(int index) {
        SouborDeskovka souborDeskovka = new SouborDeskovka();
        seznam = souborDeskovka.vypisSoubor("deskovky.txt", ";");
        if (index == seznam.size() - 1) {
            btnBack.setEnabled(true);
            btnNext.setEnabled(false);
        } else if (index == 0) {
            btnBack.setEnabled(false);
            btnNext.setEnabled(true);
        } else {
            btnBack.setEnabled(true);
            btnNext.setEnabled(true);
        }
        Deskovka pocetSeznam = seznam.get(index);
        viewText.setText(pocetSeznam.getNazev());
        if (pocetSeznam.isZakoupeno() == true) {
            checkBox.setSelected(true);
        } else {
            checkBox.setSelected(false);
        }
        if (pocetSeznam.getOblibenost() == 1) {
            btnLike1.setSelected(true);
        } else {
            btnLike1.setSelected(false);
        }
        if (pocetSeznam.getOblibenost() == 2) {
            btnLike2.setSelected(true);
        } else {
            btnLike2.setSelected(false);
        }
        if (pocetSeznam.getOblibenost() == 3) {
            btnLike3.setSelected(true);
        } else {
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

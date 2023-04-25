import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddProductToAll extends JPanel implements ActionListener {
    JFrame MainWindow;

    JTextArea Info;
    JButton Back;
    JTextField NewProduct;

    JTextField NewCategory;

    JComboBox<String> ChooseUnit;


    JButton Accept;

    int decision;


    public AddProductToAll(JFrame main_w) {

        Font fontToBackButton = new Font("Helvetica", Font.BOLD, 38);
        Font fontToInfo = new Font("Helvetica", Font.BOLD, 30);
        Font fontToAcceptButton = new Font("Helvetica", Font.BOLD, 38);

        MainWindow = main_w;
        Back = new JButton("←");
        Back.setForeground(Color.BLACK);
        Back.setFont(fontToBackButton);
        Back.setBackground(Color.pink);
        Back.setAlignmentX(Component.LEFT_ALIGNMENT);
        Back.setBorder(null);
        Back.addActionListener(this);

        JPanel pagePanel = new JPanel();
        pagePanel.setBackground(Color.pink);
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        pagePanel.add(Box.createRigidArea(new Dimension(5, 30)));
        pagePanel.add(Back);


        Info = new JTextArea(" Uzupełnij kolejno: \n     -kategorię\n     -nazwę\n     -jednostki");
        Info.setForeground(Color.BLACK);
        Info.setFont(fontToInfo);
        Info.setMaximumSize(new Dimension(265, 160));
        Info.setBackground(Color.pink);
        Info.setAlignmentX(Component.CENTER_ALIGNMENT);


        NewCategory = new JTextField();
        NewCategory.setMaximumSize(new Dimension(300, 80));
        NewCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        NewCategory.setToolTipText("Nazwa Kategorii");


        NewProduct = new JTextField();
        NewProduct.setMaximumSize(new Dimension(300, 80));
        NewProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        NewProduct.setToolTipText("Nazwa Produktu");


        ChooseUnit = new JComboBox<>();
        ChooseUnit.addItem("Kilogramy");
        ChooseUnit.addItem("Metry");
        ChooseUnit.addItem("Sztuki");
        ChooseUnit.addItem("Litry");
        ChooseUnit.setMaximumSize(new Dimension(300, 80));
        ChooseUnit.setAlignmentX(Component.CENTER_ALIGNMENT);
        ChooseUnit.setToolTipText("Jednostki");
        ChooseUnit.addActionListener(this);


        Accept = new JButton("Dodaj");
        Accept.setFont(fontToAcceptButton);
        ChooseUnit.setMaximumSize(new Dimension(300, 80));
        Accept.setAlignmentX(Component.CENTER_ALIGNMENT);
        Accept.addActionListener(this);


        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.PINK);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(Info);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(NewCategory);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(NewProduct);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(ChooseUnit);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(Accept);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        setLayout(new BorderLayout());
        add(pagePanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == Back) {
            setVisible(false);
            MainWindow.remove(this);
            Menu menu = new Menu(MainWindow);
            MainWindow.add(menu);
        } else if (source == Accept) {
            String category_to_check = NewCategory.getText();
            String product_to_check = NewProduct.getText();
            decision = ChooseUnit.getSelectedIndex();
            if (allLetter(category_to_check)) {
                NewCategory.setBackground(Color.white);
                if (allLetter(product_to_check)) {
                    NewProduct.setBackground(Color.white);
                } else {
                    NewProduct.setBackground(Color.RED);
                    return;
                }
            } else {
                NewCategory.setBackground(Color.red);
                if (allLetter(product_to_check)) {
                    NewProduct.setBackground(Color.white);
                } else {
                    NewProduct.setBackground(Color.red);
                }
                return;
            }
            int category_exist = 0;
            int i;
            for (i = 0; i < Frame.All.size(); i++) {
                if (Objects.equals(category_to_check, Frame.All.get(i).getNameCategory())) {
                    category_exist = 1;
                    break;
                }
            }
            int j;
            if (category_exist == 1) {
                for (j = 0; j < Frame.All.get(i).size(); j++) {
                    if (Objects.equals(product_to_check, Frame.All.get(i).getProduct(j).getNameProduct())) {
                        Info.setText("Ten produkt już\n     jest w puli!");
                        Info.setForeground(Color.red);
                        Timer TimerToInfo = new Timer(2000, evt -> Info.setText(" Uzupełnij kolejno: \n     -kategorię\n     -nazwę\n     -jednostki"));
                        TimerToInfo.start();
                        Timer TimerToColor = new Timer(2000, evt -> Info.setForeground(Color.BLACK));
                        TimerToColor.start();
                        break;
                    }
                }
                if (j == Frame.All.get(i).size()) {
                    if (decision == 0) {
                        Frame.All.get(i).addProduct(product_to_check, "kilogramy", numbers_format.doubles, 0);
                    } else if (decision == 1) {
                        Frame.All.get(i).addProduct(product_to_check, "metry", numbers_format.doubles, 0);
                    } else if (decision == 2) {
                        Frame.All.get(i).addProduct(product_to_check, "sztuki", numbers_format.integers, 0);
                    } else {
                        Frame.All.get(i).addProduct(product_to_check, "litry", numbers_format.doubles, 0);
                    }
                    setVisible(false);
                    MainWindow.remove(this);
                    Menu menu = new Menu(MainWindow);
                    MainWindow.add(menu);
                }
            } else {
                Frame.All.add(new Category(category_to_check));
                if (decision == 0) {
                    Frame.All.get(i).addProduct(product_to_check, "kilogramy", numbers_format.doubles, 0);
                } else if (decision == 1) {
                    Frame.All.get(i).addProduct(product_to_check, "metry", numbers_format.doubles, 0);
                } else if (decision == 2) {
                    Frame.All.get(i).addProduct(product_to_check, "sztuki", numbers_format.integers, 0);
                } else {
                    Frame.All.get(i).addProduct(product_to_check, "litry", numbers_format.doubles, 0);
                }
                setVisible(false);
                MainWindow.remove(this);
                Menu menu = new Menu(MainWindow);
                MainWindow.add(menu);
            }
        }
    }

    public static boolean allLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}

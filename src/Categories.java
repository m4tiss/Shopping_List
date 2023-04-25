import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Categories extends JPanel implements ActionListener {

    JFrame mainWindow;
    JLabel info;
    JButton back;
    JComboBox<String> chooseCategory;
    JComboBox<String> newBox;

    JTextField amount;

    JButton accept;

    int decision;

    int finalProduct;


    public Categories(JFrame main) {

        Font fontToBackButton = new Font("Helvetica", Font.BOLD, 38);
        Font fontToInfo = new Font("Helvetica", Font.BOLD, 48);
        Font fontToAcceptButton = new Font("Helvetica", Font.BOLD, 38);

        mainWindow = main;
        back = new JButton("←");
        back.setForeground(Color.BLACK);
        back.setFont(fontToBackButton);
        back.setBackground(Color.pink);
        back.setBorder(null);
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.addActionListener(this);

        JPanel pagePanel = new JPanel();
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        pagePanel.add(Box.createRigidArea(new Dimension(5, 30)));
        pagePanel.add(back);


        info = new JLabel("Wybierz kategorię, produkt i ilość");
        info.setForeground(Color.BLACK);
        info.setFont(fontToInfo);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);


        chooseCategory = new JComboBox<>();
        chooseCategory.addItem("Wybierz...");
        for (int i = 0; i < Frame.All.size(); i++) {
            chooseCategory.addItem(Frame.All.get(i).getNameCategory());
        }
        chooseCategory.setMaximumSize(new Dimension(300, 80));
        chooseCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseCategory.addActionListener(this);


        newBox = new JComboBox<>();
        newBox.setMaximumSize(new Dimension(300, 80));
        newBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        newBox.addActionListener(this);
        newBox.setVisible(false);
        finalProduct = 1;

        amount = new JTextField();
        amount.setMaximumSize(new Dimension(300, 80));
        amount.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.setToolTipText("Ilość");
        amount.setVisible(false);


        accept = new JButton("Dodaj");
        accept.setFont(fontToAcceptButton);
        accept.setAlignmentX(Component.CENTER_ALIGNMENT);
        accept.setVisible(false);
        accept.addActionListener(this);


        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.PINK);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(info);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(chooseCategory);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(newBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(amount);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(accept);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        setLayout(new BorderLayout());
        add(pagePanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == back) {
            setVisible(false);
            mainWindow.remove(this);
            Menu menu = new Menu(mainWindow);
            mainWindow.add(menu);
        } else if (source == chooseCategory) {
            newBox.removeAllItems();
            decision = chooseCategory.getSelectedIndex();
            decision--;
            if (decision >= 0) {
                for (int j = 0; j < Frame.All.get(decision).size(); j++) {
                    newBox.addItem(Frame.All.get(decision).getProduct(j).getNameProduct());
                }
                newBox.setVisible(true);
                amount.setVisible(true);
                accept.setVisible(true);
            } else {
                newBox.setVisible(false);
                amount.setVisible(false);
                accept.setVisible(false);
            }

        } else if (source == newBox) {
            finalProduct = newBox.getSelectedIndex();
        } else if (source == accept) {
            String test = amount.getText();
            if (!allDigits(test)) {
                amount.setBackground(Color.RED);
            } else {
                double additional = Double.parseDouble(test);
                int category_exist = 0;
                int i;
                for (i = 0; i < Frame.List.size(); i++) {
                    if (Objects.equals(Frame.All.get(decision).getNameCategory(), Frame.List.get(i).getNameCategory())) {
                        category_exist = 1;
                        break;
                    }
                }
                int j;
                if (category_exist == 1) {
                    for (j = 0; j < Frame.List.get(i).size(); j++) {
                        if (Objects.equals(Frame.All.get(decision).getProduct(finalProduct).getNameProduct(), Frame.List.get(i).getProduct(j).getNameProduct())) {
                            Frame.List.get(i).getProduct(j).addAmount(additional);
                            setVisible(false);
                            mainWindow.remove(this);
                            Menu menu = new Menu(mainWindow);
                            mainWindow.add(menu);
                            break;
                        }
                    }
                    if (j == Frame.List.get(i).size()) {
                        Frame.List.get(i).addProduct(Frame.All.get(decision).getProduct(finalProduct).getNameProduct(), Frame.All.get(decision).getProduct(finalProduct).getUnits(), Frame.All.get(decision).getProduct(finalProduct).getFormat(), additional);
                        setVisible(false);
                        mainWindow.remove(this);
                        Menu menu = new Menu(mainWindow);
                        mainWindow.add(menu);
                    }
                } else {
                    Frame.List.add(new Category(Frame.All.get(decision).getNameCategory()));
                    Frame.List.get(Frame.List.size() - 1).addProduct(Frame.All.get(decision).getProduct(finalProduct).getNameProduct(), Frame.All.get(decision).getProduct(finalProduct).getUnits(), Frame.All.get(decision).getProduct(finalProduct).getFormat(), additional);
                    setVisible(false);
                    mainWindow.remove(this);
                    Menu menu = new Menu(mainWindow);
                    mainWindow.add(menu);
                }
            }
        }
    }

    public static boolean allDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowProductsFromCategory extends JPanel implements ActionListener {


    JFrame mainWindow;

    JLabel info;
    JButton back;
    JComboBox<String> chooseCategory;
    JTextArea areaToShow;

    int decision;

    public ShowProductsFromCategory(JFrame main) {

        Font fontToBackButton = new Font("Helvetica", Font.BOLD, 38);
        Font fontToInfo = new Font("Helvetica", Font.BOLD, 48);
        Font fontToShowList = new Font("Arial", Font.ITALIC, 25);

        mainWindow = main;
        back = new JButton("←");
        back.setForeground(Color.BLACK);
        back.setFont(fontToBackButton);
        back.setBackground(Color.pink);
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.setBorder(null);
        back.addActionListener(this);

        JPanel pagePanel = new JPanel();
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        pagePanel.add(Box.createRigidArea(new Dimension(5, 30)));
        pagePanel.add(back);


        info = new JLabel("Wybierz kategorię do obejrzenia");
        info.setForeground(Color.BLACK);
        info.setFont(fontToInfo);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);


        chooseCategory = new JComboBox<>();
        chooseCategory.addItem("Wybierz...");
        for (int i = 0; i < Frame.List.size(); i++) {
            chooseCategory.addItem(Frame.List.get(i).getNameCategory());
        }
        chooseCategory.setMaximumSize(new Dimension(300, 80));
        chooseCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseCategory.addActionListener(this);


        areaToShow = new JTextArea("");
        areaToShow.setFont(fontToShowList);
        areaToShow.setAlignmentX(Component.CENTER_ALIGNMENT);
        areaToShow.setBackground(Color.pink);
        areaToShow.setMaximumSize(new Dimension(500, 500));
        areaToShow.setVisible(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.PINK);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(info);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(chooseCategory);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(areaToShow);
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
            decision = chooseCategory.getSelectedIndex();
            decision--;
            if (decision >= 0) {
                areaToShow.setText("");
                String temp = areaToShow.getText();
                temp = temp + "\nKATEGORIA: ";
                areaToShow.setText(temp);
                String cat = Frame.List.get(decision).getNameCategory();
                areaToShow.setText(temp + cat + '\n');
                for (int j = 0; j < Frame.List.get(decision).size(); j++) {
                    if (Frame.List.get(decision).getProduct(j).getFormat() == numbers_format.integers) {
                        if (Frame.List.get(decision).getProduct(j).getAmount() == 1) {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(decision).getProduct(j).getAmount() + " sztuka" + '\n');
                        } else if (Frame.List.get(decision).getProduct(j).getAmount() > 1 && Frame.List.get(decision).getProduct(j).getAmount() < 5) {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(decision).getProduct(j).getAmount() + " sztuki" + '\n');
                        } else {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(decision).getProduct(j).getAmount() + " sztuk" + '\n');
                        }
                    } else {
                        if (Frame.List.get(decision).getProduct(j).getUnits().equals("metry")) {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + Frame.List.get(decision).getProduct(j).getAmount() + " metra" + '\n');
                        } else if (Frame.List.get(decision).getProduct(j).getUnits().equals("kilogramy")) {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + Frame.List.get(decision).getProduct(j).getAmount() + " kilograma" + '\n');
                        } else if (Frame.List.get(decision).getProduct(j).getUnits().equals("litry")) {
                            areaToShow.setText(areaToShow.getText() + "-" + Frame.List.get(decision).getProduct(j).getNameProduct() + " " + Frame.List.get(decision).getProduct(j).getAmount() + " litra" + '\n');
                        }

                    }
                }
                areaToShow.setVisible(true);
            } else {
                areaToShow.setVisible(false);
            }
        }
    }
}

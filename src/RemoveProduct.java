import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveProduct extends JPanel implements ActionListener {
    JFrame mainWindow;
    JLabel info;
    JButton back;
    JComboBox<String> chooseCategory;
    JComboBox<String> newBox;

    JButton delete;

    int decision;

    int finalProduct;


    public RemoveProduct(JFrame main) {

        Font fontToBackButton = new Font("Helvetica", Font.BOLD, 38);
        Font fontToInfo = new Font("Helvetica", Font.BOLD, 48);
        Font fontToDeleteButton = new Font("Helvetica", Font.BOLD, 38);

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


        info = new JLabel("Wybierz produkt do usunięcia");
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


        newBox = new JComboBox<>();
        newBox.setMaximumSize(new Dimension(300, 80));
        newBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        newBox.addActionListener(this);
        finalProduct = 1;
        newBox.setVisible(false);


        delete = new JButton("Usuń");
        delete.setFont(fontToDeleteButton);
        newBox.setMaximumSize(new Dimension(300, 80));
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.setVisible(false);
        delete.addActionListener(this);


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
        centerPanel.add(delete);
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
                for (int j = 0; j < Frame.List.get(decision).size(); j++) {
                    newBox.addItem(Frame.List.get(decision).getProduct(j).getNameProduct());
                }
                newBox.setVisible(true);
                delete.setVisible(true);
            } else {
                newBox.setVisible(false);
                delete.setVisible(false);
            }

        } else if (source == newBox) {
            finalProduct = newBox.getSelectedIndex();
        } else if (source == delete) {
            Frame.List.get(decision).removeProduct(finalProduct);
            if (Frame.List.get(decision).size() == 0) {
                Frame.List.remove(decision);
            }
            setVisible(false);
            mainWindow.remove(this);
            Menu menu = new Menu(mainWindow);
            mainWindow.add(menu);
        }
    }
}


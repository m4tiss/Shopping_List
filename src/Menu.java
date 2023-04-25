import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Menu extends JPanel implements ActionListener {

    JFrame mainWindow;
    JLabel topic;
    JButton addProductToListButton;
    JButton showAllListButton;

    JButton showProductsFromCategoryButton;

    JButton removeAllProductsButton;

    JButton removeAllCategoryButton;
    JButton removeProductButton;

    JButton saveOnDiskButton;

    JButton addToAllButton;
    JButton removeFromAllButton;

    public Menu(JFrame main) {


        Font fontToTopic = new Font("Helvetica", Font.BOLD, 48);

        mainWindow = main;

        topic = new JLabel();
        topic.setText("LISTA ZAKUPÓW");
        topic.setAlignmentX(Component.CENTER_ALIGNMENT);
        topic.setForeground(Color.BLACK);
        topic.setFont(fontToTopic);

        addProductToListButton = new JButton("Dodaj produkt na listę");
        addProductToListButton.setPreferredSize(new Dimension(200, 70));
        addProductToListButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        showAllListButton = new JButton("Wyświetl całą listę");
        showAllListButton.setPreferredSize(new Dimension(200, 70));
        showAllListButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        showProductsFromCategoryButton = new JButton("Wyświetl produkty z danej kategorii");
        showProductsFromCategoryButton.setPreferredSize(new Dimension(200, 70));
        showProductsFromCategoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeAllProductsButton = new JButton("Usuń wszystkie produkty");
        removeAllProductsButton.setPreferredSize(new Dimension(200, 70));
        removeAllProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeAllCategoryButton = new JButton("Usuń wszystkie produkty z danej kategorii");
        removeAllCategoryButton.setPreferredSize(new Dimension(200, 70));
        removeAllCategoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeProductButton = new JButton("Usuń produkt z danej kategorii");
        removeProductButton.setPreferredSize(new Dimension(200, 70));
        removeProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveOnDiskButton = new JButton("Zapisz listę na dysk");
        saveOnDiskButton.setPreferredSize(new Dimension(200, 70));
        saveOnDiskButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addToAllButton = new JButton("Dodaj nowe możliwe produkty");
        addToAllButton.setPreferredSize(new Dimension(200, 70));
        addToAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeFromAllButton = new JButton("Usuń możliwe produkty");
        removeFromAllButton.setPreferredSize(new Dimension(200, 70));
        removeFromAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addProductToListButton.addActionListener(this);
        showAllListButton.addActionListener(this);
        showProductsFromCategoryButton.addActionListener(this);
        removeAllProductsButton.addActionListener(this);
        removeAllCategoryButton.addActionListener(this);
        removeProductButton.addActionListener(this);
        saveOnDiskButton.addActionListener(this);
        addToAllButton.addActionListener(this);
        removeFromAllButton.addActionListener(this);

        setBackground(Color.orange);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 80)));
        add(topic);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(addProductToListButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(showAllListButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(showProductsFromCategoryButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(removeAllProductsButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(removeAllCategoryButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(removeProductButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(saveOnDiskButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(addToAllButton);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(removeFromAllButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addProductToListButton) {
            setVisible(false);
            mainWindow.remove(this);
            Categories addProductToList = new Categories(mainWindow);
            mainWindow.add(addProductToList);
        } else if (source == showAllListButton) {
            setVisible(false);
            mainWindow.remove(this);
            AllList showAllList = new AllList(mainWindow);
            mainWindow.add(showAllList);
        } else if (source == showProductsFromCategoryButton) {
            setVisible(false);
            mainWindow.remove(this);
            ShowProductsFromCategory showProductsFromCategory = new ShowProductsFromCategory(mainWindow);
            mainWindow.add(showProductsFromCategory);

        } else if (source == removeAllProductsButton) {

            Timer timerToTopic = new Timer(2000, evt -> topic.setText("LISTA ZAKUPÓW"));
            timerToTopic.start();
            Timer timerToColor = new Timer(2000, evt -> topic.setForeground(Color.BLACK));
            timerToColor.start();
            if (Frame.List.isEmpty()) {
                topic.setForeground(Color.red);
                topic.setText("LISTA JEST JUŻ PUSTA");
            } else {
                Frame.List.clear();
                topic.setForeground(Color.BLUE);
                topic.setText("USUNĄŁEŚ WSZYTSKIE PRODUKTY");
            }
        } else if (source == removeAllCategoryButton) {
            setVisible(false);
            mainWindow.remove(this);
            RemoveCategory removeAllCategory = new RemoveCategory(mainWindow);
            mainWindow.add(removeAllCategory);
        } else if (source == removeProductButton) {
            setVisible(false);
            mainWindow.remove(this);
            RemoveProduct removeProduct = new RemoveProduct(mainWindow);
            mainWindow.add(removeProduct);
        } else if (source == saveOnDiskButton) {
            try {
                saveOnDisk(Frame.List);
                topic.setText("LISTA ZOSTAŁA ZAPISANA");
                topic.setForeground(Color.BLUE);
            } catch (FileNotFoundException ex) {
                topic.setText("NIE UDAŁO SIĘ ZAPISAĆ");
                topic.setForeground(Color.RED);
                throw new RuntimeException(ex);
            }
            Timer timerToTopic = new Timer(2000, evt -> topic.setText("LISTA ZAKUPÓW"));
            timerToTopic.start();
            Timer timerToColor = new Timer(2000, evt -> topic.setForeground(Color.BLACK));
            timerToColor.start();
        } else if (source == addToAllButton) {
            setVisible(false);
            mainWindow.remove(this);
            AddProductToAll addToAll = new AddProductToAll(mainWindow);
            mainWindow.add(addToAll);
        } else if (source == removeFromAllButton) {
            setVisible(false);
            mainWindow.remove(this);
            RemoveProductFromAll removeProductFromAll = new RemoveProductFromAll(mainWindow);
            mainWindow.add(removeProductFromAll);
        }
    }

    public static void saveOnDisk(ArrayList<Category> list) throws FileNotFoundException {
        PrintWriter save = new PrintWriter("Zapisana_lista.txt");
        save.println("Lista zakupów:");
        for (int i = 0; i < list.size(); i++) {
            save.println("Kategoria: " + list.get(i).getNameCategory());
            for (int j = 0; j < list.get(i).size(); j++) {
                if (Frame.List.get(i).getProduct(j).getFormat() == numbers_format.integers) {
                    if (Frame.List.get(i).getProduct(j).getAmount() == 1) {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuka" + '\n');
                    } else if (Frame.List.get(i).getProduct(j).getAmount() > 1 && Frame.List.get(i).getProduct(j).getAmount() < 5) {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuki" + '\n');
                    } else {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuk" + '\n');
                    }
                } else {
                    if (Frame.List.get(i).getProduct(j).getUnits().equals("metry")) {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " metra" + '\n');
                    } else if (Frame.List.get(i).getProduct(j).getUnits().equals("kilogramy")) {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " kilograma" + '\n');
                    } else if (Frame.List.get(i).getProduct(j).getUnits().equals("litry")) {
                        save.println("-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " litra" + '\n');
                    }
                }
            }
        }
        save.close();
    }
}


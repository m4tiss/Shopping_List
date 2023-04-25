import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllList extends JPanel implements ActionListener {

    JFrame main_window;
    JButton back;
    JLabel topic;
    JTextArea list;


    public AllList(JFrame main_w) {
        setLayout(null);
        setBackground(Color.pink);
        main_window = main_w;
        back = new JButton("←");
        back.setForeground(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, 38);
        back.setFont(font);
        back.setBackground(Color.pink);
        back.setLocation(30, 30);
        back.setSize(100, 100);
        back.setBorder(null);

        back.addActionListener(this);

        topic = new JLabel("LISTA WSZYTSKICH ZAKUPÓW");
        topic.setForeground(Color.BLACK);
        topic.setFont(font);
        topic.setLocation(200, 30);
        topic.setSize(600, 100);
        list = new JTextArea();
        list.setForeground(Color.BLACK);
        list.setLocation(250, 120);
        list.setSize(500, 700);
        Font font_text = new Font("Arial", Font.ITALIC, 25);
        list.setFont(font_text);
        list.setBackground(Color.pink);
        if (Frame.List.isEmpty()) {
            list.setText("Lista jest pusta!\nDodaj produkty :)");
        } else {
            for (int i = 0; i < Frame.List.size(); i++) {
                String temp = list.getText();
                temp = temp + "\nKATEGORIA: ";
                list.setText(temp);
                String cat = Frame.List.get(i).getNameCategory();
                list.setText(temp + cat + '\n');
                for (int j = 0; j < Frame.List.get(i).size(); j++) {
                    if (Frame.List.get(i).getProduct(j).getFormat() == numbers_format.integers) {
                        if (Frame.List.get(i).getProduct(j).getAmount() == 1) {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuka" + '\n');
                        } else if (Frame.List.get(i).getProduct(j).getAmount() > 1 && Frame.List.get(i).getProduct(j).getAmount() < 5) {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuki" + '\n');
                        } else {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + (int) Frame.List.get(i).getProduct(j).getAmount() + " sztuk" + '\n');
                        }
                    } else {
                        if (Frame.List.get(i).getProduct(j).getUnits().equals("metry")) {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " metra" + '\n');
                        } else if (Frame.List.get(i).getProduct(j).getUnits().equals("kilogramy")) {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " kilograma" + '\n');
                        } else if (Frame.List.get(i).getProduct(j).getUnits().equals("litry")) {
                            list.setText(list.getText() + "-" + Frame.List.get(i).getProduct(j).getNameProduct() + " " + Frame.List.get(i).getProduct(j).getAmount() + " litra" + '\n');
                        }

                    }
                }
            }

        }
        add(back);
        add(topic);
        add(list);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == back) {
            setVisible(false);
            main_window.remove(this);
            Menu menu = new Menu(main_window);
            main_window.add(menu);
        }
    }
}

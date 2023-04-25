import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Frame extends JFrame {

    public static ArrayList<Category> All = new ArrayList<>();
    public static ArrayList<Category> List = new ArrayList<>();

    public Frame() {
        super("MainWindow");
        setSize(900, 900);
        setLocation(500, 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu menu = new Menu(this);
        add(menu);
        setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Frame();
        uploadProducts(All);
    }

    public static void uploadProducts(ArrayList<Category> All) throws FileNotFoundException {
        File file = new File("Products.txt");
        Scanner scanner = new Scanner(file);
        String category = null;
        int index_category = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (Objects.equals(line, "kategoria")) {
                category = scanner.nextLine();
                All.add(new Category(category));
            } else {
                for (int i = 0; i < All.size(); i++) {
                    if (Objects.equals(category, All.get(i).getNameCategory())) {
                        index_category = i;
                        break;
                    }
                }
                String unit = scanner.nextLine();
                if (unit.equals("kilogramy")) {
                    All.get(index_category).addProduct(line, "kilogramy", numbers_format.doubles, 0);
                } else if (unit.equals("metry")){
                    All.get(index_category).addProduct(line, "metry", numbers_format.doubles, 0);
                } else if (unit.equals("sztuki")) {
                    All.get(index_category).addProduct(line, "sztuki", numbers_format.integers, 0);
                } else{
                    All.get(index_category).addProduct(line, "litry", numbers_format.doubles, 0);
                }
            }
        }
    }
}




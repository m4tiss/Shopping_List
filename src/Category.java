import java.util.ArrayList;

public class Category {
    private final String nameCategory;
    private final ArrayList<Product> products;
    public Category(String name_cat) {
        nameCategory = name_cat;
        products = new ArrayList<>();
    }
    public void addProduct(String name_product, String uni, numbers_format form, double how_many) {
        products.add(new Product(name_product, uni, form, how_many));
    }

    public Product getProduct(int index) {

        return products.get(index);
    }

    public int size() {

        return products.size();
    }

    public void removeProduct(int index) {

        products.remove(index);
    }


    public String getNameCategory() {

        return nameCategory;
    }
}

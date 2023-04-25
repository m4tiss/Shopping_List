
enum numbers_format {
    integers,
    doubles
}

public class Product {

    private final String name_product;
    private final String units;
    private final numbers_format format;
    private double amount;

    public Product(String name, String uni, numbers_format form, double how_many) {
        name_product = name;
        units = uni;
        format = form;
        amount = 0;
        if (form == numbers_format.integers) {
            amount += (int) Math.round(how_many);
        } else {
            amount += how_many;
        }
    }

    public double getAmount() {

        return amount;
    }

    public numbers_format getFormat() {

        return format;
    }

    public String getUnits() {

        return units;
    }

    public void addAmount(double additional) {
        if (format == numbers_format.integers) {
            amount += (int) Math.round(additional);
        } else {
            amount += additional;
        }
    }

    public String getNameProduct() {
        return name_product;
    }
}

package beans;

/**
 * Created by Hamza on 11/6/2016.
 * Contact Hamza for problems
 */
public class ProductsBean {
    String product_name;
    String manufacturer;
    String model;
    String family;
    String announced_date;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAnnounced_date() {
        return announced_date;
    }

    public void setAnnounced_date(String announced_date) {
        this.announced_date = announced_date;
    }
}

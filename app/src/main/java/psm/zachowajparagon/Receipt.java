package psm.zachowajparagon;

/**
 * Created by Olszak on 29.05.2017.
 */

public class Receipt {

    private String title;
    private String desc;
    private String image;
    private String price;
    private String shop;
    private String category;
    private String guarantee;


    public Receipt() {
    }

    public Receipt(String title, String desc, String image, int price, String place, String category, int guarantee) {
        this.title = title;
        this.desc = desc;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }
}

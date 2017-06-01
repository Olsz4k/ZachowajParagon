package psm.zachowajparagon;

/**
 * Created by Olszak on 29.05.2017.
 */

public class Receipt {

    private String title;
    private String desc;
    private String image;
    private int price;
    private String place;
    private String category;
    private int guarantee;


    public Receipt() {
    }

    public Receipt(String title, String desc, String image, int price, String place, String category, int guarantee) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.place = place;
        this.category = category;
        this.guarantee = guarantee;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }
}

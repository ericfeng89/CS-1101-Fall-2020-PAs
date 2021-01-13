public class ItemToPurchase {
    private String itemName = "";
    private int itemPrice = 0;
    private int itemQuantity = 0;

    /* getters and setters */
    public String getName() {
        return itemName;
    }

    public int getPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public void setName(String userName) {
        itemName = userName;
    }

    public void setPrice(int userPrice) {
        itemPrice = userPrice;
    }

    public void setQuantity(int userQuantity) {
        itemQuantity = userQuantity;
    }
}

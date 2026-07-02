public class Order {
    private String orderId;
    private String product;
    private int quantity;

    // Jackson'ın JSON'dan nesne üretebilmesi için boş constructor şart
    public Order() {}

    public Order(String orderId, String product, int quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    // Getter/Setter'lar Jackson'ın alanlara erişmesi için gerekli
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', product='" + product + "', quantity=" + quantity + "}";
    }
}
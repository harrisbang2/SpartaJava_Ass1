public class Items extends Product {

    Float price;	// 상품 가격

    Items(String name, Float price, String description) {
        super(name, description);
        this.price = price;
    }
}

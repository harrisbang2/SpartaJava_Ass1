import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderView {
    // 메뉴
    private Map<String, List<Product>> menus;
    // 상품메뉴
    private Map<String, List<Items>> menuItems;
    // 장바구니
    private List<Items> cart;
    // 전체 가격
    private double totalPrice;
    // 주문 번호
    private int orderNumber;

    public OrderView() {
        menus = new HashMap<>();
        menuItems = new HashMap<>();
        cart = new ArrayList<>();
        totalPrice = 0.0;
        orderNumber = 0;
        // 메뉴 및 상품메뉴 초기화
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        List<Product> mainMenus = new ArrayList<>();
        mainMenus.add(new Product("Burgers", "앵거스 비프 통살을 다져만든 버거"));
        mainMenus.add(new Product("Frozen Custard", "매장에서 신선하게 만드는 아이스크림"));
        mainMenus.add(new Product("Drinks", "매장에서 직접 만드는 음료"));
        mainMenus.add(new Product("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주"));

        List<Product> orderMenus = new ArrayList<>();
        orderMenus.add(new Product("Order", "장바구니를 확인 후 주문합니다."));
        orderMenus.add(new Product("Cancel", "진행중인 주문을 취소합니다."));
        orderMenus.add(new Product("Order List", "대기/완료된 주문목록을 조회합니다."));

        menus.put("Main", mainMenus);
        menus.put("Order", orderMenus);

        List<Items> burgersMenus = new ArrayList<>();
        burgersMenus.add(new Items("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Items("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Items("Shroom Burger", 9.4f, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"));
        burgersMenus.add(new Items("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgersMenus.add(new Items("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));

        List<Items> frozenCustardMenu = new ArrayList<>();
        frozenCustardMenu.add(new Items("딸기", 2.0f, "딸딸딸딸딸딸딸딸딸딸기!"));
        frozenCustardMenu.add(new Items("초코", 2.0f, "초코초코초코초코초코초코초코초코"));
        frozenCustardMenu.add(new Items("바닐라", 2.0f, "바닐라바닐라바닐라바닐라바닐라바닐라"));

        List<Items> drinksMenu = new ArrayList<>();
        drinksMenu.add(new Items("콜라", 1.0f, "콜라"));
        drinksMenu.add(new Items("사이다", 1.0f, "사이다"));
        drinksMenu.add(new Items("환타", 1.0f, "환타"));

        List<Items> beerMenu = new ArrayList<>();
        beerMenu.add(new Items("카스!", 3.0f, "카스!"));
        beerMenu.add(new Items("아사히!", 3.0f, "아사히!!"));

        menuItems.put("Burgers", burgersMenus);
        menuItems.put("Frozen Custard", frozenCustardMenu);
        menuItems.put("Drinks", drinksMenu);
        menuItems.put("Beer", beerMenu);
    }


     //메뉴 조회
    public List<Product> getMenus(String key) {
        return menus.get(key);
    }

    /**
     * 상품메뉴 조회
     * @param key 조회할 상품메뉴 키값
     * @return 조회된 상품메뉴 목록
     */
    public List<Items> getMenuItems(String key) {
        return menuItems.get(key);
    }

    public Map<String, List<Items>> getMenuItemMap() {
        return menuItems;
    }

    public List<Items> getCart() {
        return cart;
    }

    public void addMenu(String key, String description) {
        menus.get("Main").add(new Product(key, description));
        menuItems.put(key, new ArrayList<>());
    }

    public void addMenuItem(String key, Items newItem) {
        menuItems.get(key).add(newItem);
    }

    public String getMainMenuName(int id) {
        List<Product> mainMenus = menus.get("Main");
        for (Product mainMenu : mainMenus) {
            if (mainMenu.id == id) {
                return mainMenu.name;
            }
        }
        return "";
    }

    /**
     * 장바구니에 상품메뉴 추가
     * @param menuItem 장바구니에 추가할 상품메뉴
     */
    public void addToCart(Items menuItem) {
        cart.add(menuItem);
        totalPrice += menuItem.price;
    }

    /**
     * 장바구니 출력
     */
    public void displayAllItem() {
        System.out.println("[ 전체 상품 목록 ]");
        menuItems.forEach((key, value) -> {
            System.out.println(" [ " + key + " Menu ]");
            for(Items item: value) {
                System.out.println(item.id + ". " + item.name + "   | " + item.price + " | " + item.description);
            }
        });
    }

    public void displayCart() {
        for (Items item : cart) {
            System.out.println(item.name + "   | " + item.price + " | " + item.description);
        }
    }

    /**
     * 장바구니 전체가격 조회
     * @return 장바구니 전체가격
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 신규 주문번호 조회
     * @return 신규 주문번호
     */
    public int generateOrderNumber() {
        orderNumber++;
        return orderNumber;
    }

    /**
     * 장바구니 초기화
     */
    public void resetCart() {
        cart.clear();
        totalPrice = 0.0;
    }
}
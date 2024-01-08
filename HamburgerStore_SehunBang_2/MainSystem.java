import java.util.List;
import java.util.Scanner;

public class MainSystem {
    // 메뉴 구성
    // 메인메뉴(홈) - 메뉴 - 상품메뉴

    private static OrderView menuView;
    private static OrderManagement OrderController;
    public void Start() {
        menuView = new OrderView();
        OrderController = new OrderManagement();
        displayMainMenu();
    }

    /**
     * 메인메뉴 출력
     */
    private static void displayMainMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        List<Product> mainMenus = menuView.getMenus("Main");	// 메뉴 컨텍스트에서 메인메뉴 조회
        int nextNum = printMenu(mainMenus, 1);					// 메인메뉴 출력

        System.out.println("[ ORDER MENU ]");
        List<Product> orderMenus = menuView.getMenus("Order");	// 메뉴 컨텍스트에서 주문메뉴 조회
        printMenu(orderMenus, nextNum);								// 주문메뉴 출력

        handleMainMenuInput(); // 메인메뉴 입력처리
    }
    /**
     * 메뉴 목록 출력
     * @param menus : 출력할 메뉴 리스트
     * @param num : 출력중인 전체 순번
     * @return 출력 후 전체 순번
     */
    private static int printMenu(List<Product> menus, int num) {
        for (int i=0; i<menus.size(); i++, num++) {		// menus 목록에 있는 메뉴 출력 (전체 순번값인 num 값도 ++)
            System.out.println(num + ". " + menus.get(i).name + "   | " + menus.get(i).description); // ex. 0.메뉴이름 | 메뉴설명
        }
        return num;
    }
    /**
     * 메인메뉴 입력처리
     */
    private static void handleMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        // 메인메뉴 사이즈 조회
        int mainMenuSize = menuView.getMenus("Main").size();
        // 주문메뉴 사이즈 조회
        int orderMenuSize = menuView.getMenus("Order").size();

        if (input == 0) {
            displayManagementMenu();
        } else if (input <= mainMenuSize) {
            displayMenu(menuView.getMenus("Main").get(input - 1));
        } else if (input <= mainMenuSize + orderMenuSize) {
            int orderInput = input - mainMenuSize;
            switch (orderInput) {
                case 1:
                    displayOrderMenu();
                    break;
                case 2:
                    handleCancelMenuInput();
                    break;
                case 3:
                    handleListMenuInput();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    handleMainMenuInput();
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleMainMenuInput();
        }
    }


    //메뉴에 있는 상품메뉴 목록 출력
    //@param menu 출력할 메뉴

    private static void displayMenu(Product menu) {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("[ " + menu.name + " MENU ]");
        // 메뉴에 있는 상품메뉴 목록 조회
        List<Items> items = menuView.getMenuItems(menu.name);
        // 상품메뉴 목록 출력
        printMenuItems(items);
        // 상품메뉴 입력 처리
        handleMenuItemInput(items);
    }

    private static void displayManagementMenu() {
        System.out.println("SHAKESHACK BURGER 관리 메뉴에 오신걸 환영합니다.");
        OrderController.displayMainMenu();
        handleCommandInput();
    }

    private static void handleCommandInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 0) {
            displayMainMenu();
        } else if (input >= 1 && input <= 4) {
            switch (input) {
                case 1:
                    OrderController.displayWaitingOrdersAndProcess();
                    break;
                case 2:
                    OrderController.displayCompletedOrders();
                    break;
                case 3:
                    String menuName = getMenuName();
                    Items newItem = OrderController.createMenuItem();
                    menuView.addMenuItem(menuName, newItem);
                    break;
                case 4:
                    menuView.displayAllItem();
                    System.out.print("삭제할 상품 ID: ");
                    int itemId = scanner.nextInt();
                    OrderController.deleteMenuItems(menuView.getMenuItemMap(), itemId);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        }
        displayManagementMenu();
    }

    private static String getMenuName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("[ 메뉴 목록 ]");
        List<Product> mainMenus = menuView.getMenus("Main");
        printMenu(mainMenus, 1);
        System.out.println(mainMenus.size()+1 + ". 신규 메뉴");
        System.out.print("메뉴 ID: ");
        int menuId = scan.nextInt();
        if (menuId <= mainMenus.size()) {
            return menuView.getMainMenuName(menuId);
        } else {
            System.out.print("신규 메뉴이름: ");
            String newMenuName = scan.next();
            System.out.print("신규 메뉴설명: ");
            String newMenuDescription = scan.next();
            menuView.addMenu(newMenuName, newMenuDescription);
            return newMenuName;
        }
    }

    private static void handleMenuItemInput(List<Items> items) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input >= 1 && input <= items.size()) { 	// 입력값 유효성 검증
            Items selectedItem = items.get(input-1);	// 선택한 상품메뉴 조회
            displayConfirmation(selectedItem);		// 선택한 상품메뉴 확인 문구 출력
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleMenuItemInput(items);	// 상품메뉴 입력 처리 재수행
        }
    }

    private static void printMenuItems(List<Items> items) {
        for (int i=0; i<items.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + items.get(i).name + "   | " + items.get(i).price + " | " + items.get(i).description);
        }
    }

    /**
     * 선택한 상품메뉴 확인 문구 출력
     * @param menuItem 선택한 상품메뉴
     */
    private static void displayConfirmation(Items menuItem) {
        System.out.println(menuItem.name + "   | " + menuItem.price + " | " + menuItem.description);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleConfirmationInput(menuItem);	// 확인여부 입력 처리
    }

    /**
     * 확인여부 입력 처리
     * @param menuItem 확인한 상품메뉴
     */
    private static void handleConfirmationInput(Items menuItem) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {								// 1. 확인 입력시
            menuView.addToCart(menuItem);			// 선택한 상품을 컨텍스트의 장바구니에 추가
            System.out.println("장바구니에 추가되었습니다.");
            displayMainMenu();							// 메인메뉴 출력하며 처음으로 돌아가기
        } else if (input == 2) {						// 2. 취소 입력시
            displayMainMenu();							// 바로 메인메뉴 출력하며 처음으로 돌아가기
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleConfirmationInput(menuItem);			// 잘못된 입력시 다시 확인여부 입력 처리 재수행
        }
    }

    /**
     * 주문메뉴1. 주문진행 메뉴 출력
     */
    private static void displayOrderMenu() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        menuView.displayCart();			// 컨텍스트에서 장바구니 목록 출력
        System.out.println("[ Total ]");
        System.out.println("W " + menuView.getTotalPrice() + "\n");	// 컨텍스트에서 전체 가격 조회하여 출력
        System.out.println("1. 주문      2. 메뉴판");
        handleOrderMenuInput();				// 주문진행 입력 처리
    }
    /**
     * 주문메뉴1. 주문진행 입력w 처리
     */
    private static void handleOrderMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            displayOrderComplete();	// 1. 주문 입력시 주문완료 처리
        } else if (input == 2) {
            displayMainMenu();		// 2. 메뉴판 입력시 메인메뉴 출력하며 돌아가기
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleOrderMenuInput();	// 잘못된 입력시 주문진행 입력처리 재수행
        }
    }
    /**
     * 주문메뉴1. 주문 입력시 주문완료 처리
     */
    private static void displayOrderComplete() {
        int orderNumber = menuView.generateOrderNumber(); 		// 컨텍스트에서 신규 주문번호 조회
        List<Items> cart = menuView.getCart();
        Double totalPrice = menuView.getTotalPrice();
        System.out.println("요구사항을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        OrderController.addCartToOrder(orderNumber, message, cart, totalPrice);
        System.out.println("주문이 완료되었습니다!\n");
        System.out.println("대기번호는 [ " + orderNumber + " ] 번 입니다.");
        resetCartAndDisplayMainMenu();		// 장바구니 초기화 후 메인메뉴 출력
    }
    /**
     * 장바구니 초기화 후 메인메뉴 출력
     */
    private static void resetCartAndDisplayMainMenu() {
        menuView.resetCart();		// 컨텍스트에서 장바구니 초기화
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        try {
            Thread.sleep(3000); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayMainMenu();		// 메인메뉴 출력하며 돌아가기
    }

    /**
     * 주문메뉴2. 주문취소 메뉴 출력
     */
    private static void handleCancelMenuInput() {
        System.out.println("주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleCancelConfirmationInput();	// 주문취소 확인 입력값 처리
    }

    private static void handleListMenuInput() {
        OrderController.displayWaitingOrders();
        OrderController.displayCompletedOrders();
        displayMainMenu();
    }

    /**
     * 주문메뉴2. 주문취소 확인 입력값 처리
     */
    private static void handleCancelConfirmationInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            menuView.resetCart();	// 장바구니 초기화
            System.out.println("주문이 취소되었습니다.");
            displayMainMenu();			// 메인메뉴 출력하며 돌아가기
        } else if (input == 2) {
            displayMainMenu();			// 메인메뉴 출력하며 돌아가기
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleCancelConfirmationInput();	// 주문취소 확인 입력값 처리 재수행
        }
    }
}
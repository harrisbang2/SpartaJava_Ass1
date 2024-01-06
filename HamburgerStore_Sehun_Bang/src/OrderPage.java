import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderPage {
    Scanner scan = new Scanner(System.in);
    OrderHelper help = new OrderHelper();
    // start function
    public void Start(){
        //Program starts
        print_main_page();
        //getting the input
        int a = getinput(scan.nextLine());
        // moving page according to the input 받은 입력에 따라 어느 페이지로 이동할지
        help.PageMover(a);
        // moving page according to the input 받은 입력에 따라 어느 페이지로 이동할지
        check_if_Reset_request(help.getReset_request());
    }
    // 다시 메인 페이지로 이동할지 안할지
    private void check_if_Reset_request(boolean b) {
        if(b == false){
            help.reset_resetButton();
        }else {
            if(!help.GetjustPurchase()){
                ask_to_go_OrderView(); //
            }
            help.Reset_Skip_Order_Request();
            Start();
        }

    }

// 장바구니 추가후 Order_view 갈지 물어복기
//// 설계 미스 ㅠ
   private void ask_to_go_OrderView() {
        System.out.println("Order page 로 이동 하시겠습니까? 1.y     2.n ");
        String temp = scan.nextLine();
        if(temp.equals("y")||temp.equals("1")){
            System.out.println("order page 로 이동 합니다");
            help.View_Orders();
        }
        else {
            System.out.println("메인 페이지로 이동합니다");
            Start();

        }
    }


    /// introduction page 메인 페이지
    public void print_main_page(){
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.print("[ SHAKESHACK MENU ]\n" +
                "1. Burgers         | 앵거스 비프 통살을 다져만든 버거\n" +
                "2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림\n" +
                "3. Drinks          | 매장에서 직접 만드는 음료\n" +
                "4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주\n" +
                "\n" +
                "[ ORDER MENU ]\n" +
                "5. Order       | 장바구니를 확인 후 주문합니다.\n" +
                "6. Cancel      | 진행중인 주문을 취소합니다.\n");
    }
    //geeting the input
     int getinput(String string){
        int a = 0;
        switch(string){
            case "0":  CkeckTotalProfit(); // 비밀 총수익 페이지
            break;
            case "1":
            case "burger":
            case "burgers":
            case "Burger":
            case "Burgers":  a = 1; // 버거 페이지
                break;
            case "2":
            case "forzen":
            case "Forzen":
            case "forzen custard":
            case "Forzen Custard": a = 2; // Forzen Custard 페이지
                break;
            case "3":
            case "Drinks":
            case "drinks":
            case "drink":
            case "Drink":
                a = 3;// Drink 페이지
                break;
            case "4":
            case "Beer":
            case "beer":
                a = 4;// Beer 페이지
                break;
            case "5":
            case "Order":
            case "order":
                a = 5;// Order_view 페이지
                break;
            case "6":
            case "Cancel":
            case "cancel":
                a = 6; //  프로그램 종료 페이지
                break;
            default:    //  잘못 쳤을때 다시 치게 하기
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
                return getinput(scan.nextLine());
        }
        return a;
    }

    private void CkeckTotalProfit() {
        //총수익
        float total_profit = help.getTotal_profit();
        System.out.println("[ 총 판매금액 현황 ]");
        System.out.println("현재까지 총 판매된 금액은 [ W "+total_profit+" ] 입니다.");
        System.out.println("\n1. 돌아가기");
        if(scan.nextLine().equals("1")){
            Start();
        }
        else{
            CkeckTotalProfit();
        }

    }


}

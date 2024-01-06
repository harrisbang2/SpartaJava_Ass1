import java.util.*;

public class OrderHelper {
    Scanner scan = new Scanner(System.in); // 스캐너
    List<Product> list = new ArrayList<>(); // 장바구니 (임시),
    Product burger = new Burger(); // 햄버거
    Product frozen = new Frozen_Custard();// 아이스크림
    Product drink = new Drink();// 음료
    private int waitnum = 1;   // 대기열
    private boolean Reset_request = true; //뭔가 다른 헹동들을 마쳤을때 메인 페이지로 돌아갈지 말지.
    boolean justPurchase = false; // 대기열
    float total_profit=0; // 총수익
    // 메이지 이동을 위한 function
    public void PageMover(int a){
        switch(a){
            //Compulsory
            case 1: BurgerPage(); //버거 페이지
                break;
            case 2: Frozen_Custard_Page(); //아이스크림 페이지
                break;
            case 3: Drinks_Page(); //음료 페이지
                break;
            case 4:  Beer_Page(); //음료(술) 페이지
                break;
            case 5: View_Orders(); //장바구니 페이지
                break;
            case 6: Exit_Message(); //종료 페이지
                break;
            default:
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
        }
    }


    ////////////////////////////////////////Beer_Page//////////////////////////////////////
    private void Beer_Page() {
        System.out.println("\n" +
                "1. Cass         | W 3.0 | 카스! 카스! 카스!\n" +
                "2. Asahi        | W 3.0 | 아사이!\n");
        pickBeer(scan.nextLine());
    }
/*                         받은 입력으로 술/음료 정의함                          */
    private void pickBeer(String s) {
        switch(s){
            case "1":
            case "Cass":
            case "cass":
                print_add_Drink("Cass",12);
                break;
            case "2":
            case "Asahi":
            case "asahi":
                print_add_Drink("Asahi",13);
                break;
            default:
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
                pickDrink(scan.nextLine());
        }
    }

    //////////////////////////// View_Orders page ///////////////////////////////////////
    public void View_Orders() {
        this.Reset_request = false;
        float total_price =0f;
        ///////// 쇼핑백이 비어있을시
        if(list.isEmpty()){
            System.out.println("쇼핑백에 아무 상품도 없어요 ㅠㅠ");
        }
        ///////// 쇼핑백에 워가 있을시
        else{
            System.out.println("[ Orders ]");
            // 새로운 list NewList 를 만듬
            // 새로운 NewList 는 중복은 넣지 않게 코딩함, 중복이 있으면 해당 Product 에 count++ 함
            List <Product>NewList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                //addding price
                total_price += list.get(i).getprice();
                //adding count
                Product a = list.get(i);
                for(int j=0;j<list.size();j++){
                    //아이디가 같을 경우
                 if(list.get(i).getId() == list.get(j).getId()){
                     //가격이 같을 경우
                     //Upgrade 로 인해 가격이 다를 경우에는 다른 취급을 함.
                     // 예를 들면
                     // 버거이름1 | W 7,6 | 2개 | 설명...
                     // 버거이름1 | W 5,6 | 1개 | 설명...
                     if(list.get(i).getprice()==list.get(j).getprice()){
                     a.AddCount();
                     }
                 }
                }
                boolean contains = false;
                if(!NewList.isEmpty()){
                    for(int z = 0;z<NewList.size();z++){
                        if(NewList.get(z).getId()==a.getId()){
                            if(NewList.get(z).getprice()==a.getprice()){
                                contains = true;
                            }
                        }
                    }
                    // 중복이 아닐 병우 추가.
                    if(!contains){
                        NewList.add(a);
                    }
                }
                else {
                    NewList.add(a);
                }
            }
            // 이제 NewList 있는 것들을 보여주기
            for(int i =0;i<NewList.size();i++){
                String s = i+1+". "+NewList.get(i).getname()+"  | "+NewList.get(i).getprice()+" | "+" | "+NewList.get(i).getCount()+" 개 | "+NewList.get(i).getDescriptions();
                System.out.println(s);
            }
            System.out.println("[ Total ] :"+total_price+"\n 1.주문   2.메뉴판");
            String s = scan.nextLine();
            // 결제가 되었을때
            if(s.equals("1")||s.equals("주문")){
                System.out.println("총 금액 :"+total_price+"결제 되었습니다.");
                list = new ArrayList<>();
                System.out.println("대기번호는 [ "+waitnum+" ] 번 입니다.");
                waitnum++;
                total_profit += total_price;
                System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
                // 3 초 대기
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("thread 안됨");
                    throw new RuntimeException(e);
                }
                // 다시 메인 화면이로 이동
                Request_restart();
                // Orderview Page 이동 물어보기 스킵
                Skip_Order_Request();
            }
            else if(s.equals("2")||s.equals("메뉴판")){
                System.out.println("메인 페이지로 돌아갑니다");
                for(int i = 0;i<NewList.size();i++){
                    NewList.get(i).DivCount();
                    list.get(i).DivCount();
                }
                this.Reset_request = true;
                Skip_Order_Request();
            }
            else{
                View_Orders();
            }

        }
    }
////////////////////////////Drinks_Page//////////////////////////////////////////////////////////////////
    private void Drinks_Page() {
        System.out.println("\n" +
                "1. Coke         | W 1.0 | Cock 아니고 Coke!\n" +
                "2. Spirte       | W 2.0 | 사이다 스트라이트!\n" +
                "3. Fanta        | W 2.0 | 오렌지맛 fanta\n");
        pickDrink(scan.nextLine());
    }
    /*                   받은 입력으로 음료(including 술) 정의                   */
    private void pickDrink(String string) {
        switch(string){
            case "1":
            case "Coke":
                print_add_Drink("Coke",9);
                break;
            case "2":
            case "Spirte":
                print_add_Drink("Spirte",10);
                break;
            case "3":
                print_add_Drink("Fanta",11);
                break;
            default:
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
                pickDrink(scan.nextLine());
        }
    }
    private void print_add_Drink(String string, int id) {
        drink = new Drink(string,id);
        System.out.println(drink.getname()+"   | W"+drink.getprice()+" | "+drink.getDescriptions());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n 1. 확인        2. 취소");
        String s = scan.nextLine();
        this.justPurchase = false;
        if(Confirmation(s,drink.getname(),id)==true){
            list.add(drink);
        }
        else {
            this.justPurchase = true;
            Request_restart();
        }
    }

    ////////////////////////////Frozen_Custard_Page ///////////////////////////////////
    private void Frozen_Custard_Page() {
        System.out.println("\n" +
                "1.Chocolate     | W 2.0 | 초코치키차카토코!\n" +
                "2.Strawberry    | W 2.0 | 딸딸딸딸딸딸딸딸기!!!!!\n" +
                "3.Vanila        | W 2.0 | 바닐라!\n");
        pickFrozen_Custard(scan.nextLine());
    }
    /*                   받은 입력으로 아이스크림 정의                   */
    private void pickFrozen_Custard(String string) {
        switch(string){
            case "1":
            case "Chocolate":
            case "chocolate":
                print_add_Frozen_Custard("Chocolate",6);
                break;
            case "2":
            case "Strawberry":
            case "strawberry":
                print_add_Frozen_Custard("Strawberry",7);
                break;
            case "3":
            case "Vanila":
            case "vanila":
                print_add_Frozen_Custard("Vanila",8);
                break;
            default:
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
                pickFrozen_Custard(scan.nextLine());
        }
    }

    private void print_add_Frozen_Custard(String string, int id) {
        frozen = new Frozen_Custard(string,id);
        System.out.println(frozen.getname()+"   | W"+frozen.getprice()+" | "+frozen.getDescriptions());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n 1. 확인        2. 취소");
        String s = scan.nextLine();
        if(s.equals("확인")||s.equals("1")){
            frozen = new Frozen_Custard(string,id);
            list.add(frozen);
            System.out.println(string+"  추가!");
        }
        else if(s.equals("취소")||s.equals("2")){
            System.out.println(string+"  취소");
            justPurchase = true;
            Reset_request =true;
        }
        else{
            System.out.println("알수없는 잆력 다시 적어주세요");
            print_add_Frozen_Custard(string,id);
        }
    }
///////////////////////////////////burger pages///////////////////////////////////
    private void BurgerPage() {
        System.out.println("\n" +
                "1.ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거\n" +
                "2.SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거\n" +
                "3.Shroom Burger | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거\n" +
                "3.Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거\n" +
                "4.Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거" +
                "\n");
        pickBurger(scan.nextLine());

    }

    ////////////받은 입력을 통해서 버거
    private void pickBurger(String string) {
        switch(string){
            case "1":
            case "ShackBurger":
            case "shackburger":
            case "Shackburger":
                print_add_burger("ShackBurger",1);
                break;
            case "2":
            case "SmokeShack":
            case "smokeshack":
            case "Smokeshack":
                print_add_burger("SmokeShack",2);
                break;
            case "3":
            case "Shroom Burger":
            case "shroom burger":
            case "Shroom burger":
                print_add_burger("Shroom Burger",3);
                break;
            case "4":
            case "Cheeseburger":
            case "cheeseburger":
            case "CheeseBurger":
                print_add_burger("CheeseBurger",4);
                break;
            case "5":
            case "Hamburger":
            case "hamburger":
                print_add_burger("Hamburger",5);
                break;
            default:
                System.out.println("알수없는 입력입니다 숫자 혹은 이름을 넣어주세요");
                pickBurger(scan.nextLine());
        }
    }
    /*                   버거 넣기/업글 물어보기                     */
    private void print_add_burger(String string,int id) {
        boolean is_extra=false;
        burger = new Burger(string,id,is_extra);
        System.out.println(burger.getname()+"   | W"+burger.getprice()+" | "+burger.getDescriptions());
        /*             사이즈 업 할지 물어보기             */
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        float price = burger.getprice()+2;
        System.out.println("1. Single(W "+burger.getprice()+")        2. Double(W "+price+")");
        String s = scan.nextLine();
        /*         사이즈 업일경유 버거를 새로 만들고 가격을 올림       */
        if(s.equals("2")){
            is_extra=true;
        }
        burger = new Burger(string,id,is_extra);
        //
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n 1. 확인        2. 취소");
        s = scan.nextLine();
       if(Confirmation(s,burger.getname(),id)==true){
           list.add(burger);
       }
       else {
           Request_restart();
       }
    }

    private boolean Confirmation(String s,String name,int id) {
        if(s.equals("확인")||s.equals("1")){
            System.out.println(name+"  추가!");
            return true;
        }
        else if(s.equals("취소")||s.equals("2")){
            System.out.println(name+"  취소");
            justPurchase = true;
            return false;
        }
        else{
            System.out.println("알수없는 잆력 다시 적어주세요");
            return false;
        }
    }
/*                   취소 페이지 / 프로그램종료 페이지                      */
    private void Exit_Message() {
        System.out.println("주문을 취소 하시겠습니까? y/n or 1/2");
        String a = scan.nextLine();
        System.out.println(a);
        //////// y 시 종료
        if(a.equals("y")||a.equals("1")){
            list=new ArrayList<>();
            System.out.println("주문이 취소 되었습니다");
            this.Reset_request = false;
            System.exit(0);
        }
        //////// Request_restart(); 다시 메인 페이지로 이동
        else if(a.equals("n")){
            Request_restart();
        }
        //////// 입력 다시 받기 위해 재가동
        else Exit_Message();
    }
    //////// 메인페이지로 이동 할때 Reset_request 가 true 면 다시 메인 페이지 가동 하도록
    private void Request_restart() {
        Reset_request = true;
    }
    //////// True 상태인 Reset_request 다시 원상 복구.(무한 루프 방지)
    public void reset_resetButton() {
        Reset_request = false;
    }
    ////////  OrderPage 에서 ,Reset_request 상태를 받아가기
    public boolean getReset_request(){
        return Reset_request;
    }
    ////////  결제시 view_order 이동 물어보기가 있음(설계 미스)
    ////////  그래서 view_order 이동 물어보기 스킵을 위한 boolean ture = 스킵, false = view_order 물어보기
    public boolean GetjustPurchase() {
        return justPurchase;
    }
    ////////  OrderPage 에서 설정하기 위한 function
    public void Skip_Order_Request(){
        this.justPurchase = true;
    }
    ////////  justPurchase 원상복구,
    public void Reset_Skip_Order_Request(){
        this.justPurchase = false;
    }
    ////////  저장한 총수익(판매된) 금액을 float 에 담아줌
    public float getTotal_profit() {
        return this.total_profit;
    }
}

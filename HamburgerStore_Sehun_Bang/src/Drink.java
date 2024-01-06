public class Drink extends Product{
    public Drink(){

    }
    public Drink(String name,int id){
        setname(name);
        setId(id);
        setprice(2.0f);
        // 아이디 값에 따라 물건 설정
        switch (id){
            case 9:
                setDescriptions("콜라!");
                break;
            case 10:
                setDescriptions("사이다!!!!!");
                break;
            case 11:
                setDescriptions("환타!");
                break;
            case 12:
                setprice(3.0f);
                setDescriptions("카스!");
                break;
            case 13:
                setprice(3.0f);
                setDescriptions("아사히!!!!!");
                break;
            default:
                setDescriptions("뭐지 이 음료는?");
        }
    }
}

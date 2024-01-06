public class Frozen_Custard extends Product{
    public Frozen_Custard(){

    }
    public Frozen_Custard(String name,int id){
        setname(name);
        setId(id);
        int a = id;
        setprice(2.0f); // 모든 아이스크림이 2처넘 입니당
        // 아이디 값에 따라 물건 설정
        switch (id){
            case 6:
                setDescriptions("초코치키차카토코!");
                break;
            case 7:
                setDescriptions("딸딸딸딸딸딸딸딸기!!!!!");
                break;
            case 8:
                setDescriptions("바닐라!");
                break;
            default:
                setDescriptions("뭐지 이 아이스크림음?");
        }
    }
}

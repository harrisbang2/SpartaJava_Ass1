public class Burger extends Product{
public Burger(){

}
public Burger(String name,int id,boolean isextra){
    setname(name);
    setId(id);
    int a = id;
    // 아이디 값에 따라 물건 설정 + isextra 에 따라 사이즈 업 인지 확인
    switch (a){
        case 1:
            setprice(6.9f);
            check_size_upgrade(isextra);
            setDescriptions("토마토, 양상추, 쉑소스가 토핑된 치즈버거");
            break;
        case 2:
            setprice(8.9f);
            check_size_upgrade(isextra);
            setDescriptions("베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
            break;
        case 3:
            setprice(9.4f);
            check_size_upgrade(isextra);
            setDescriptions("몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거");
            break;
        case 4:
            setprice(6.9f);
            check_size_upgrade(isextra);
            setDescriptions("포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
            break;
        case 5:
            setprice(5.4f);
            check_size_upgrade(isextra);
            setDescriptions("비프패티를 기반으로 야채가 들어간 기본버거");
            break;
        default:
            setId(0);
            setprice(1f);
    }

}
// 사이즈 없일시 + W 2
public void check_size_upgrade(boolean fact){
    if(fact){
        size_upgrade(); // 사이즈 없일시 + W 2
    }
}
}

package hello.core.singletonTest;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

//    public void order(String name, int price) {
//        System.out.println("name = " + name + "price = " + price);
//        this.price = price; // 여기가 문제!
//    }

    // 무상태로 변경한 코드 -> 값을 그냥 바로 반환 해버림. 해당 오브젝트가 값을 가지고 있지 않음.(stateless)
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}

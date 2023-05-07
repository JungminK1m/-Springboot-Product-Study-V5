package shop.mtcoding.productapp_v5.model.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
    /*
     * Criteria 클래스의 용도
     * pageNum 과 pageAmount 값을 같이 전달하는 용도지만
     * 생성자를 통해서 기본값을 1페이지, n개로 지정해서 처리함.
     */

    private int pageNum; // 페이지 번호
    private int pageAmount; // 한 페이지 당 몇 개?

    // 생성자 - 매개변수 X
    public Criteria() {
        this(1, 3);
    }

    // 생성자2 - 매개변수 O
    public Criteria(int pageNum, int pageAmount) {
        this.pageNum = pageNum;
        this.pageAmount = pageAmount;
    }
}

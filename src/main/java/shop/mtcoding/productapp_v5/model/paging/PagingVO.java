package shop.mtcoding.productapp_v5.model.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingVO {
    private int page; // 현재 페이지 번호
    private int total; // 전체 게시물 수
    private int size = 3; // 페이지당 게시물 수
    private int pageSize; // 페이지 사이즈 (페이지 넘버 수)

    // 현재 페이지가 속한 페이지 그룹에서 첫 번째 페이지 번호를 구함
    public int getStartPage() {
        return ((page - 1) / pageSize) * pageSize + 1;
    }

    // 현재 페이지가 속한 페이지 그룹에서 마지막 페이지 번호를 구함
    public int getEndPage() {
        return (((page - 1) / pageSize) + 1) * pageSize;
    }

    // 전체 페이지 수와 페이지당 게시물 수를 이용하여, 실제 마지막 페이지 번호를 구함
    public int getRealEndPage() {
        int realEnd = (int) (Math.ceil((double) total / size));
        return realEnd < getEndPage() ? realEnd : getEndPage();
    }

    // 현재 페이지의 게시물 시작 인덱스를 구함
    public int getOffset() {
        return (page - 1) * size;
    }

    // 페이지당 게시물 수를 반환함
    public int getLimit() {
        return size;
    }
}

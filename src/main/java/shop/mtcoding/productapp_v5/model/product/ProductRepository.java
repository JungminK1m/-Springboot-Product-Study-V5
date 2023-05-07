package shop.mtcoding.productapp_v5.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.productapp_v5.dto.orders.OrdersDto;
import shop.mtcoding.productapp_v5.dto.product.ProductReqDto.ProductSaveDto;
import shop.mtcoding.productapp_v5.model.orders.Orders;
import shop.mtcoding.productapp_v5.model.paging.Criteria;

@Mapper
public interface ProductRepository {

    public Product findById(Integer productId);

    public List<Product> findAll();

    public Product findByProductName(String productName);

    public int insert(ProductSaveDto productSaveDto);

    public int update(Product product);

    public int deleteById(Integer productId);

    // 구매 시에 product QTY가 차감 되어야 함
    public void productQtyUpdate(OrdersDto ordersDto);

    // 구매 취소시 prouduct QTY 다시 증가
    public void productQtyReupdate(Orders orders);

    // ajax 중복체크를 위한 메서드
    public Product findByName(String productName);

    // 페이징 - 상품 전체 목록 조회 쿼리
    public List<Product> pagingProductList(Criteria criteria);

    // 페이징 - 상품 총 갯수 조회 쿼리
    public Integer pagingProductCount();
}

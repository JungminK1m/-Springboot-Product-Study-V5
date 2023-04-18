package shop.mtcoding.productapp_v4.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.productapp_v4.dto.orders.OrdersDto;
import shop.mtcoding.productapp_v4.dto.product.ProductReqDto.ProductSaveDto;
import shop.mtcoding.productapp_v4.model.orders.Orders;

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

}

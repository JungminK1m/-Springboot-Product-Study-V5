package shop.mtcoding.productapp_v5.model.orders;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.productapp_v5.dto.orders.AdminOrdersListDto;
import shop.mtcoding.productapp_v5.dto.orders.OrdersDto;

@Mapper
public interface OrdersRepository {

    // 2개 이상 @Param 붙이기
    public void insert(@Param("ordersDto") OrdersDto ordersDto, @Param("userId") Integer userId);

    public Orders findById(Integer ordersId);

    // 해당 userId를 가진 유저의 구매목록 전체를 보여줌
    public List<Orders> findAll(Integer usersId);

    // 관리자가 전체 유저의 구매목록을 보여줌
    public List<AdminOrdersListDto> adminFindALl();

    public void orderUpdatebyProductQty(Orders orders);

    public void deleteById(Integer ordersId);
}

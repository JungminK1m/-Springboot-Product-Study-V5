package shop.mtcoding.productapp_v5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.productapp_v5.dto.orders.OrdersDto;
import shop.mtcoding.productapp_v5.model.orders.Orders;
import shop.mtcoding.productapp_v5.model.orders.OrdersRepository;
import shop.mtcoding.productapp_v5.model.product.ProductRepository;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Integer 구매하기(OrdersDto ordersDto, Integer userId) {

        // 구매를 하면 product qty가 차감되어야 함
        productRepository.productQtyUpdate(ordersDto);

        /*
         * 구매버튼 누르면 insert 됨
         * 누가 구매했는 지 필요하기 때문에 userId도 같이 insert 해야 함
         */
        ordersRepository.insert(ordersDto, userId);

        return userId;

    }

    @Transactional
    public Integer 구매취소하기(Integer ordersId) {

        Orders orders = ordersRepository.findById(ordersId);
        productRepository.productQtyReupdate(orders);

        // 주문 정보 삭제
        ordersRepository.deleteById(ordersId);

        Integer userId = orders.getUserId();

        return userId;
    }

}
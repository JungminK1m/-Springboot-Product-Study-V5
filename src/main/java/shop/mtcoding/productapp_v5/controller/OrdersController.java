package shop.mtcoding.productapp_v5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.productapp_v5.dto.orders.AdminOrdersListDto;
import shop.mtcoding.productapp_v5.dto.orders.OrdersDto;
import shop.mtcoding.productapp_v5.enums.ResponseEnum;
import shop.mtcoding.productapp_v5.handler.exception.CustomException;
import shop.mtcoding.productapp_v5.model.orders.Orders;
import shop.mtcoding.productapp_v5.model.orders.OrdersRepository;
import shop.mtcoding.productapp_v5.model.product.Product;
import shop.mtcoding.productapp_v5.model.product.ProductRepository;
import shop.mtcoding.productapp_v5.model.user.User;
import shop.mtcoding.productapp_v5.service.OrdersService;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    private OrdersService ordersService;

    // 구매 목록 페이지
    @GetMapping("/ordersList/{userId}")
    public String orderListForm(@PathVariable Integer userId, Model model) {

        User principal = (User) session.getAttribute("principal");

        // 로그인 안한 사람이 주문목록 보려고 시도할 시
        if (principal == null) {
            throw new CustomException(ResponseEnum.NO_ACCESS_TO_ORDERSLIST);
        }

        // 로그인 했지만 나 아닌 다른 사람의 주문목록 보려고 시도할 시
        // ! <- 논리 부정 연산자
        if (!principal.getUserId().equals(userId)) {
            throw new CustomException(ResponseEnum.NO_ACCESS_TO_ORDERSLIST);
        }

        List<Orders> ordersList = ordersRepository.findAll(userId);
        model.addAttribute("orderedProduct", ordersList);

        return "orders/ordersList";
    }

    // 상품 구매하기
    // 어떤 상품을 구매했는 지 알아야해서 주소에 productId가 필요함(?) <--확인하기
    @PostMapping("/orders/{productId}")
    public String order(@PathVariable Integer productId, OrdersDto ordersDto) {

        // 로그인 한 사람만 구매할 수 있음
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException(ResponseEnum.PRINCIPAL_DOSE_NOT_EXIST);
        }

        // 상품수량보다 구매수량이 더 많으면 안됨
        Product productPS = productRepository.findById(productId);
        if (productPS.getProductQty() - ordersDto.getOrdersQty() < 0) {
            throw new CustomException(ResponseEnum.NO_MORE_THAN_PRODUCT_QTY);
        }

        Integer userId = ordersService.구매하기(ordersDto, principal.getUserId());

        return "redirect:/ordersList/" + userId;

    }

    // 유저 - 구매 취소하기
    @PostMapping("/ordersList/delete")
    public String deleteOrder(Integer ordersId, Integer productId) {

        // 로그인 한 사람만
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException(ResponseEnum.PRINCIPAL_DOSE_NOT_EXIST);
        }

        Integer userId = ordersService.구매취소하기(ordersId);

        return "redirect:/ordersList/" + userId;
    }

    // 관리자 - 유저 구매 목록 확인 페이지
    @GetMapping("/admin/userOrdersList")
    public String adminOrdersList(Model model) {

        // 관리자 로그인 한 사람만 접근 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException(ResponseEnum.ADMIN_LOGIN_FAIL);
        }

        List<AdminOrdersListDto> orderedList = ordersRepository.adminFindAll();
        model.addAttribute("orderedList", orderedList);

        return "orders/adminOrdersList";
    }
}

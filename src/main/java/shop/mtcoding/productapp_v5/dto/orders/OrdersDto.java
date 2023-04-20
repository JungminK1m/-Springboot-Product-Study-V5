package shop.mtcoding.productapp_v5.dto.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDto {

    private String ordersName;
    private Integer ordersPrice;
    private Integer ordersQty;
    private Integer productId;

}

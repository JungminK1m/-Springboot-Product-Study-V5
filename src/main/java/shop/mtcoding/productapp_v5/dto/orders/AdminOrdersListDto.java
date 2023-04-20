package shop.mtcoding.productapp_v5.dto.orders;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminOrdersListDto {
    private String userName;
    private String ordersName;
    private Integer ordersPrice;
    private Integer ordersQty;
    private Timestamp createdAt;
}

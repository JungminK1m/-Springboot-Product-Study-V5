package shop.mtcoding.productapp_v5.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReqDto {

    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer productQty;

    @Getter
    @Setter
    public static class ProductSaveDto {
        private String productName;
        private Integer productPrice;
        private Integer productQty;
    }

    @Getter
    @Setter
    public static class ProductUpdateDto {
        private Integer productId;
        private String productName;
        private Integer productPrice;
        private Integer productQty;
    }
}

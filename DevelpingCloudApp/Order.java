
package shopmall;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Integer qty;
    private String productName;

    @PostPersist
    public void onPostPersist(){
        OrderPlaced orderPlaced = new OrderPlaced(); // 도메인 이벤트 한 건을 준비
        BeanUtils.copyProperties(this, orderPlaced); // 값을 설정, 동일한 필드명에 대하여 복사
        orderPlaced.publishAfterCommit();   // 발사 -> kafka로 이에 대해서는 abstract event에서 구현하여 biz로직을 간소화함
        //publishAfterCommin -> commit된 이후 publish하라는 명령


    }

    // setter, getter 접근자
    // 값 크기 최대, 최소 오류 등에 대한 검증 코드 작성을 위해
    // assert productId > 0;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }




}

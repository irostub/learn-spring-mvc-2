package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//오브젝트 검증을 위해서 다음과 같은 어노테이션을 사용한다.
//다만 그 사용성이 복잡하기에 권장하지 않는 방법. 단순히 검증로직을 따로 빼내어서 작성하는 편이 더 나은 방법이라는 판단이 선다.
//@ScriptAssert(lang="javascript", script="_this.price * _this.quantity >= 10000")
public class Item {

    @NotNull(groups = {UpdateCheck.class})
    private Long id;

    @NotBlank(message="공백은 넣을 수 없습니다.", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min=1_000, max = 1_000_000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

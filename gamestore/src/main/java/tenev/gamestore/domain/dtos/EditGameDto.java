package tenev.gamestore.domain.dtos;

import java.math.BigDecimal;

public class EditGameDto {

    private Integer id;
    private BigDecimal price;

    public EditGameDto() {
    }

    public EditGameDto(Integer id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

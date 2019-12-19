package tenev.gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private String title;
    private String trailer;
    private String thumbnail;
    private Double size;
    private BigDecimal price;
    private String description;
    private LocalDate date;
    private Set<User> users;

    public Game() {
    }

    @Column(name = "title", nullable = false, unique = true)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Title does not fit the format.")
    @Size(min = 3, max = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "trailer", nullable = false)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String youtubeVideoID) {
        this.trailer = youtubeVideoID;
    }

    @Column(name = "thumbnail", unique = true)
    @Pattern(regexp = "(http)(.*)")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String imageThumbnailURL) {
        this.thumbnail = imageThumbnailURL;
    }

    @Column(name = "size", nullable = false)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Column(name = "price", nullable = false, scale = 2, precision = 11)
    @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description", nullable = false,length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToMany (mappedBy = "games", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

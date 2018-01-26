package pl.wat.prz.engine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "title-empty")
    @Size(max = 20, message = "title-max")
    private String name;
    @Size(max = 200, message = "description-max")
    private String description;
    private byte[] image;
    private Date pdate;
    private int points;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Picture{" +
                "  name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user='" + user + '\'' +
                ", blob='" + image + '\'' +
                '}';
    }

    public String to64encode() {
        return Base64.encodeBase64String(this.image);
    }
}

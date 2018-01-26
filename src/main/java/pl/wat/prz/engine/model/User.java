package pl.wat.prz.engine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nick;
    private String mail;
    private String lastname;
    private String firstname;
    private String description;
    @OneToMany(mappedBy = "user")
    private List<Picture> pictures = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "  nick='" + nick + '\'' +
                ", mail='" + mail + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

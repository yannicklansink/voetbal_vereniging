package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.EnumRole;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_authority")
    @SequenceGenerator(
            name = "seq_authority",
            initialValue = 5
    )
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;


    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }


}

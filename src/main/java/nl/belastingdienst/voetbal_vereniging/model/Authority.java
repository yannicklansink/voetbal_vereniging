package nl.belastingdienst.voetbal_vereniging.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityKey.class)
@NoArgsConstructor
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public Authority(String username, String authority, User user) {
        this.username = username;
        this.authority = authority;
        this.user = user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }



}

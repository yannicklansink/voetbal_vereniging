package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


public class AuthorityKey implements Serializable {

    private String username;

    private String authority;

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityKey that = (AuthorityKey) o;
        return username.equals(that.username) &&
                authority.equals(that.authority);
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




}

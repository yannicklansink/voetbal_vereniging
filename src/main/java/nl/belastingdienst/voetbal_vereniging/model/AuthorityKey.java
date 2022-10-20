package nl.belastingdienst.voetbal_vereniging.model;


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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AuthorityKey that = (AuthorityKey) object;
        return this.username.equals(that.username) && authority.equals(that.authority);
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String setAuthority) {
        this.authority = setAuthority;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String setUsername) {
        this.username = setUsername;
    }




}

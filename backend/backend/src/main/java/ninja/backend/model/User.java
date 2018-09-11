package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Role> ROLE = new PropertyPath<>("role");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD_HASH = new PropertyPath<>("passwordHash");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @Size(min = 3, max = 128)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = 6, max = 128)
    @Column(name = "passwordHash")
    private String passwordHash;

    public User() {
    }

    public User(Long id, Role role, String username, String passwordHash) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public User(Role role, String username, String passwordHash) {
        this.role = role;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.role != null && other.role != null && !this.role.equals(other.role))
            return false;
        if (this.username != null && other.username != null && !this.username.equals(other.username))
            return false;
        if (this.passwordHash != null && other.passwordHash != null && !this.passwordHash.equals(other.passwordHash))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.passwordHash == null) ? 0 : this.passwordHash.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User[" + "this.id=" + this.id + ", this.role=" + this.role + ", this.username=" + this.username + "]";
    }

}

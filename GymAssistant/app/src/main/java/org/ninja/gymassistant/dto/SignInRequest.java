package org.ninja.gymassistant.dto;


import android.support.annotation.Size;

import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;



public class SignInRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(value = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*&@%+/_'!#$^?:.(\\)\\[\\]{}~\\-]{8,}$")
    private String password;

    private SignInRequest() {
    }

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SignInRequest other = (SignInRequest) obj;
        if (this.username != null && other.username != null && !this.username.equals(other.username))
            return false;
        if (this.password != null && other.password != null && !this.password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignInRequest[" + "this.username=" + this.username + "]";
    }

}


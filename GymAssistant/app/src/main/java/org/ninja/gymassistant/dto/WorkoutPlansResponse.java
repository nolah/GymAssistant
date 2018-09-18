package org.ninja.gymassistant.dto;


import android.support.annotation.Size;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;


public class WorkoutPlansResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Boolean active;

    private WorkoutPlansResponse() {
    }

    public WorkoutPlansResponse(Long id, Long userId, String name, Boolean active) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WorkoutPlansResponse other = (WorkoutPlansResponse) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.userId != null && other.userId != null && !this.userId.equals(other.userId))
            return false;
        if (this.name != null && other.name != null && !this.name.equals(other.name))
            return false;
        if (this.active != null && other.active != null && !this.active.equals(other.active))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutPlansResponse[" + "this.id=" + this.id + ", this.userId=" + this.userId + ", this.name=" + this.name + ", this.active=" + this.active + "]";
    }

}


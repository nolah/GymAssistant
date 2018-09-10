package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "WorkoutPlan")
public class WorkoutPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<User> USER = new PropertyPath<>("user");
    public static final PropertyPath<Boolean> ACTIVE = new PropertyPath<>("active");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.PERSIST)
    private final Set<Workout> workouts = new LinkedHashSet<>();

    public WorkoutPlan() {
    }

    public WorkoutPlan(Long id, User user, Boolean active) {
        this.id = id;
        this.user = user;
        this.active = active;
    }

    public WorkoutPlan(User user, Boolean active) {
        this.user = user;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WorkoutPlan other = (WorkoutPlan) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.user == null && other.user == null) || (this.user != null && other.user != null && this.user.getId() == null && other.user.getId() == null)
                || this.user.getId().equals(other.user.getId())))
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
        result = prime * result + ((this.user == null || this.user.getId() == null) ? 0 : this.user.getId().hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutPlan[" + "this.id=" + this.id + ", this.user=" + (this.user == null ? this.user : this.user.getId()) + ", this.active=" + this.active + "]";
    }

}

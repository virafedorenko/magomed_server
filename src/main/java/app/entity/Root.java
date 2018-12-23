package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roots")
public class Root implements Serializable {
    // TODO: 28.11.2018 map properties, add list of peaks and related user
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private int height;
    @Column
    private int length;

    @OneToMany(mappedBy = "root")
    @JsonIgnore
    private List<Peak> peaks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Root() {
    }

    public Root(String name, int height, int length) {
        this.name = name;
        this.height = height;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public List<Peak> getPeaks() {
        return peaks;
    }

    public void setPeaks(List<Peak> peaks) {
        this.peaks = peaks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        app.entity.Root root = (app.entity.Root) o;
        return Objects.equals(id, root.id) &&
                Objects.equals(name, root.name) &&
                Objects.equals(height, root.height) &&
                Objects.equals(length, root.length);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, height, length);
    }
}


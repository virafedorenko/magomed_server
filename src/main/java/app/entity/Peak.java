package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "peaks")

public class Peak implements Serializable {

    // TODO: 28.11.2018 map properties to database fields
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private int height;
    @ManyToOne
    @JoinColumn(name = "root_id")
    private Root root;

    public Peak() {
    }

    public Peak(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String idRoot) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        app.entity.Peak peak = (app.entity.Peak) o;
        return Objects.equals(id, peak.id) &&
                Objects.equals(name, peak.name) &&
                Objects.equals(height, peak.height);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, height);
    }
}


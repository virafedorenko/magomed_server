package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tracking_events")
public class TrackingEvent {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column(name = "sname")
    private String name;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    @JsonIgnore
    private TrackingObject object;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TrackingObject getObject() {
        return object;
    }

    public void setObject(TrackingObject object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackingEvent)) return false;

        TrackingEvent that = (TrackingEvent) o;

        if (!getId().equals(that.getId())) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TrackingEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

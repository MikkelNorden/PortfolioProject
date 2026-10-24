package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "entries")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Entry {

    @Id
    @GeneratedValue
    @Column(name = "entry_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "entry_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @ToString.Exclude
    private UserList list;
}
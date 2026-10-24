package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "followers")
    private int followers;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private List<UserList> lists = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (creationTime == null) {
            creationTime = LocalDateTime.now();
        }
    }

    public void addList(UserList list) {
        lists.add(list);
        list.setOwner(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAuthor(this);
    }
}
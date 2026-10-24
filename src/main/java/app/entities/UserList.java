package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserList {

    @Id
    @GeneratedValue
    @Column(name = "list_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "list_description")
    private String description;

    @Column(name = "views")
    private int views;

    @Column(name = "likes")
    private int likes;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User owner;

    @OneToMany(mappedBy = "list")
    @ToString.Exclude
    private List<Entry> entries = new ArrayList<>();

    @OneToMany(mappedBy = "list")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (uploadTime == null) {
            uploadTime = LocalDateTime.now();
        }
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
        entry.setList(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setList(this);
    }
}
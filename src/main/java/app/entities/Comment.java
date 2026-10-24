package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private int likes;

    @Column(name = "publish_time")
    private LocalDateTime publishTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User author;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @ToString.Exclude
    private UserList list;

    @PrePersist
    protected void onCreate() {
        if (publishTime == null) {
            publishTime = LocalDateTime.now();
        }
    }
}
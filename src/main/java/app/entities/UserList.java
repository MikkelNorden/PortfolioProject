package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserList {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int views;
    private int likes;
    private LocalDateTime uploadTime;
    private List<Entry> entries;
    private List<Comment> comments;
}
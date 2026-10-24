package app;

import app.config.HibernateConfig;
import app.entities.Comment;
import app.entities.Entry;
import app.entities.User;
import app.entities.UserList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListSystem {

    Scanner scan = new Scanner(System.in);
    private List<UserList> allLists = new ArrayList<>();
    //add a way to get every list from the database

    public void start() {
        System.out.println("Welcome!");
        //print the 20 most liked lists
        int i = 0;
        for(UserList l : allLists) {
            System.out.println(l.getTitle());
            i++;
            if(i == 20) {
                break;
            }
        }
    }

    public void testStart() {
        System.out.println("Welcome!");
        for(int i = 1; i <= 30; i++) {
            UserList list = new UserList(i, "Title " + i, "Desc", 0, 0, LocalDateTime.now(), new User(), new ArrayList<>(), new ArrayList<>());
            allLists.add(list);
        }

        //print the 20 most liked lists
        int i = 0;
        for(UserList l : allLists) {
            System.out.println(l.getTitle());
            i++;
            if(i == 20) {
                break;
            }
            String input = scan.nextLine();
        }
    }

    public void testJPA() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User user1 = new User();
            user1.setUsername("Anna");
            user1.setPassword("test123");
            user1.setFollowers(10);

            em.persist(user1);

            User user2 = new User();
            user2.setUsername("Bob");
            user2.setPassword("test456");
            user2.setFollowers(25);

            em.persist(user2);

            UserList gaming = new UserList();
            gaming.setTitle("Gaming");
            gaming.setDescription("Mine yndlingsspil");
            gaming.setViews(100);
            gaming.setLikes(20);

            user1.addList(gaming);

            em.persist(gaming);

            Entry entry1 = new Entry();
            entry1.setName("Minecraft");
            entry1.setDescription("Et kreativt byggespil");

            Entry entry2 = new Entry();
            entry2.setName("The Witcher 3");
            entry2.setDescription("Et open-world rollespil");

            Entry entry3 = new Entry();
            entry3.setName("Stardew Valley");
            entry3.setDescription("Et hyggeligt farming-spil");

            gaming.addEntry(entry1);
            gaming.addEntry(entry2);
            gaming.addEntry(entry3);

            em.persist(entry1);
            em.persist(entry2);
            em.persist(entry3);

            Comment comment = new Comment();
            comment.setContent("God liste! Jeg elsker Minecraft.");
            comment.setLikes(3);

            user2.addComment(comment);
            gaming.addComment(comment);

            em.persist(comment);

            em.getTransaction().commit();

            System.out.println("Testdata gemt");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }

        em.close();
        emf.close();
    }
}
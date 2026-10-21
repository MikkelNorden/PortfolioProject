package app;

import app.entities.Comment;
import app.entities.Entry;
import app.entities.UserList;

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
            UserList list = new UserList(i, "Title " + i, "Desc", 0, 0, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());
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
}
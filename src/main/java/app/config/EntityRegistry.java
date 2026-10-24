package app.config;

import app.entities.Comment;
import app.entities.Entry;
import app.entities.User;
import app.entities.UserList;
import org.hibernate.cfg.Configuration;

final class EntityRegistry {

    private EntityRegistry() {}

    static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserList.class);
        configuration.addAnnotatedClass(Entry.class);
        configuration.addAnnotatedClass(Comment.class);
        // TODO: Add more entities here...
    }
}
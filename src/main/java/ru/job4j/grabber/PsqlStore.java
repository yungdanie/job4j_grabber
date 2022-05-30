package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private final Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            cnn = DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("sqlpost.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PsqlStore psqlStore = new PsqlStore(properties)) {
            psqlStore.save(new Post("Test", "test.com",
                    "Это тестовый пост", LocalDateTime.now()));
            psqlStore.save(new Post("Test2", "test2.com",
                    "Это тестовый пост #2", LocalDateTime.now()));
            List<Post> list = psqlStore.getAll();
            System.out.println(psqlStore.findById(list.get(0).getId()));
            psqlStore.getAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement =
                     cnn.prepareStatement("insert into post(name, text, link, created) values(?, ?, ?, ?)"
                                     .concat(" on conflict (link) do nothing"),
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    post.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    list.add(getPost(set));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from post where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    post = getPost(set);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    private Post getPost(ResultSet set) throws SQLException {
        return new Post(set.getInt("id"),
                set.getString("name"),
                set.getString("link"),
                set.getString("text"),
                set.getTimestamp("created").toLocalDateTime());
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}
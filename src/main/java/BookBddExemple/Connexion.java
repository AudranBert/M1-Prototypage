package BookBddExemple;

import java.sql.*;

public class Connexion {
    private String DBPath = "Chemin aux base de donnée SQLite";
    private Connection connection = null;
    private Statement statement = null;

    public Connexion(String dBPath) {
        DBPath = dBPath;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + requet);
        }
        return resultat;
    }

    public void addBook(Book book) {
        String query = "";
        query += "INSERT INTO BOOK VALUES (";
        query += "'" + book.getBookId() + "', ";
        query += "'" + book.getTitle() + "', ";
        query += "'" + book.getSubTitle() + "', ";
        query += book.getPages() + ", ";
        query += "'" + book.getPublished().toString() + "', ";
        query += "'" + book.getDescription() + "' )";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
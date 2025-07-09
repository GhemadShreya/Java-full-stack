package javaDataDemo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookshopMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookshopService service = new BookshopService();

        while (true) {
            System.out.println("\n===== ONLINE BOOKSHOP =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Author");
            System.out.println("3. Add Category");
            System.out.println("4. Add Book");
            System.out.println("5. Place Order");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    service.addUser(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter author name: ");
                    service.addAuthor(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter category name: ");
                    service.addCategory(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author ID: ");
                    int authorId = sc.nextInt();
                    System.out.print("Category ID: ");
                    int catId = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    service.addBook(title, authorId, catId, price);
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("How many books? ");
                    int n = sc.nextInt();
                    Map<Integer, Integer> bookMap = new HashMap<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Book ID: ");
                        int bookId = sc.nextInt();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        bookMap.put(bookId, qty);
                    }
                    service.placeOrder(userId, bookMap);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}//BookShopMenu


public class BookshopService {

    public void addUser(String name) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println(" User added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthor(String name) {
        String sql = "INSERT INTO authors (name) VALUES (?)";
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("✅ Author added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(String name) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println(" Category added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String title, int authorId, int categoryId, double price) {
        String sql = "INSERT INTO books (title, author_id, category_id, price) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, authorId);
            ps.setInt(3, categoryId);
            ps.setDouble(4, price);
            ps.executeUpdate();
            System.out.println(" Book added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void placeOrder(int userId, Map<Integer, Integer> bookIdQuantityMap) {
        String insertOrder = "INSERT INTO orders (user_id) VALUES (?) RETURNING order_id";
        String insertItem = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseUtil.getConnection()) {
            con.setAutoCommit(false);

            int orderId;
            try (PreparedStatement ps = con.prepareStatement(insertOrder)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                rs.next();
                orderId = rs.getInt("order_id");
            }

            try (PreparedStatement psItem = con.prepareStatement(insertItem)) {
                for (Map.Entry<Integer, Integer> entry : bookIdQuantityMap.entrySet()) {
                    int bookId = entry.getKey();
                    int quantity = entry.getValue();
                    double price = getBookPrice(bookId);

                    psItem.setInt(1, orderId);
                    psItem.setInt(2, bookId);
                    psItem.setInt(3, quantity);
                    psItem.setDouble(4, price);
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }

            con.commit();
            System.out.println(" Order placed successfully (Order ID: " + orderId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getBookPrice(int bookId) throws SQLException {
        String sql = "SELECT price FROM books WHERE book_id = ?";
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("price");
            else throw new SQLException("Book not found");
        }
    }
}//BookshopService



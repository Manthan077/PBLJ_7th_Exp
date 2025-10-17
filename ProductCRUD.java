import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String url = "jdbc:mysql://localhost:3306/companyDB";
    private static final String user = "Root";
    private static final String password = "Abc123";

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public static void createProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product(ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Product inserted successfully.");
        }
    }

    public static void readProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ProductID | ProductName | Price | Quantity");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        }
    }

    public static void updateProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter new Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();

        String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
        conn.setAutoCommit(false);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Update successful.");
            } else {
                conn.rollback();
                System.out.println("No product found with given ProductID.");
            }
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
        conn.setAutoCommit(true);
    }

    public static void deleteProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID=?";
        conn.setAutoCommit(false);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Delete successful.");
            } else {
                conn.rollback();
                System.out.println("No product found with given ProductID.");
            }
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
        conn.setAutoCommit(true);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection conn = getConnection()) {

            while(true) {
                System.out.println("\nProduct Management Menu:");
                System.out.println("1. Create Product");
                System.out.println("2. Read Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch(choice) {
                    case 1 -> createProduct(conn, sc);
                    case 2 -> readProducts(conn);
                    case 3 -> updateProduct(conn, sc);
                    case 4 -> deleteProduct(conn, sc);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

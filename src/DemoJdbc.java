import java.sql.*; // SQL LIB/PACKAGE

public class DemoJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /* IMPORT PACKAGE-1
           LOAD AND REGISTER-2
           CREATE CONNECTION-3
           CREATE STATEMENT-4
           EXECUTE STATEMENT-5
           PROCESS THE RESULTS-6
           CLOSE-7
        */

        // URL FOR CONNECTING WITH DATABASE
        //jdbc:postgresql/sql://localhost:5432/db-name
        String url = "jdbc:postgresql://localhost:5432/demo";
        String uname = "postgres";
        String password = "@meyBagwe1209";

        // OPTIONAL
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(url, uname,password);
        System.out.println("connection established");

        // FETCH NAME QUERY EXECUTE BLOCK
        Statement statement = connection.createStatement();
        String query = "select * from student where sid = 1 ";
        ResultSet result = statement.executeQuery(query);
        result.next();
        String name = result.getString("sname");
        result.next();
        System.out.println("Name fetched from student table: "+ name);
        /* WILL NOT WORK DUE TO QUERY FOR ONLY SID = 1 */
        // String name2 = result.getString("sname");
        // System.out.println("Name fetched 2:"+ name2);


        // FETCH ALL RECORDS OF STUDENT TABLE
        String query2 = "select * from student";
        ResultSet result2 = statement.executeQuery(query2);
        while(result2.next()){
            System.out.print(result2.getInt(1));
            System.out.print(" - "+result2.getString(3));
            System.out.println(" - "+result2.getInt(2));
        }

        // INSERTING DATA
        // String query3 = "Insert into student Values (5, 60, 'John')";
        // boolean status = statement.execute(query3);
        // System.out.println(status);

        // UPDATING DATA
        // String query4 = "Update student SET sname = 'Johnny' where sid=5";
        // statement.execute(query4);

        // DELETING DATA
        // String query5 = "Delete from student where sid = 5";
        // statement.execute(query5);

        // PREPARED STATEMENT (ADVANCED STATEMENT) - DYNAMIC USE
        int sid = 4;
        int marks = 100;
        String sname = "Nitin";
        PreparedStatement pst = connection.prepareStatement("insert into student values (?, ?, ?)");
        pst.setInt(1, sid);
        pst.setInt(2, marks);
        pst.setString(3, sname);
        pst.execute();

        connection.close();
        System.out.println("Closed connection");
    }
}

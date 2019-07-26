
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class dbConnection {


    String host="jdbc:mysql://localhost:3306/jarvis";
    String user="root";
    String pass="";
    
    Connection conn;
         
    
    //initialize the connection
    
     public Connection getConnection(){
        try{
            
            conn=DriverManager.getConnection(host, user, pass);
            JOptionPane.showMessageDialog(null, "Connection Successful");
            return conn;
        }
        
        catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return null;
        }
            
    }
    
    public void insertOrders(String studnumber,String studname, String studtax, String studtotal){
        String q="INSERT into orders(number,name,tax, total) VALUES(?,?,?,?)";
        
        int k=0;
        try{
        PreparedStatement st=getConnection().prepareStatement(q);
        st.setString(1,studnumber);
        st.setString(2,studname);
        st.setString(3,studtax);
        st.setString(4,studtotal);
        
        if(st.executeUpdate()>k){
            JOptionPane.showMessageDialog(null,studnumber+"registered successfully");
        }
        }
        
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            
        }
                
                }  

    public void updateOrders(String studnumber,String studname, String studtax, String studtotal){
        
        String q="UPDATE orders SET name=?, tax=?, total=? WHERE number=?";
        
        try{
            int k=0;
            
            PreparedStatement st=getConnection().prepareStatement(q);
            
            st.setString(1, studname);
            st.setString(2, studtax);
            st.setString(3, studtotal);
            st.setString(4, studnumber);
            
            if(st.executeUpdate()>k)
            {
                JOptionPane.showMessageDialog(null, studnumber+ "Updated Successfully");
            }
        }
        
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }      
    }
    
    public void deleteOrders(String studnumber){
        String q= "DELETE FROM orders WHERE number=?";
        
        try{
            int k=0;
            PreparedStatement st=getConnection().prepareStatement(q);
            
            st.setString(1, studnumber);
            
            if(st.executeUpdate()>k)
            {
                JOptionPane.showMessageDialog(null, studnumber+"Delete Successfull");
            }
        }
        
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
    }
    
    public ResultSet getOrders(){
        
        String q="SELECT * FROM orders";
        
        try
        {
            Statement st=getConnection().createStatement();
            ResultSet rs=st.executeQuery(q);
            
            
            return rs;
        }
        
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
            return null;
        }
        
    }
    
    public ResultSet serachVerification(){
        
        String q ="select * from verification where number=?";
       
        try
        {
            PreparedStatement st=conn.prepareStatement(q);
            ResultSet rs = st.executeQuery(q);
            return rs;
        }
        
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return null;
        }
    }
    
}
            
   
      
    

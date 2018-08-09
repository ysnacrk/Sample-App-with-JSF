package selectRecord;

//Our datatable class
import connection.ConnectionBean;
import intern.internTable;
import javafx.scene.control.Tab;

//Requirements libraries
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped

public class SelectRecord {
    List<internTable>  iTable = new ArrayList<>();


    public List<internTable> getInternTableRecords(){
        ConnectionBean connectionBean = new ConnectionBean();
        Connection connection = connectionBean.getConnection();
        PreparedStatement select = null;

        try{
            select = connection.prepareStatement("SELECT * FROM  intern.internTable");
            ResultSet resultSet = select.executeQuery();

            while(resultSet.next()){
                internTable Table = new internTable();
                Table.setTCKN(resultSet.getString("intern_no"));
                Table.setPassword(resultSet.getString("intern_password"));
                Table.setEmail(resultSet.getString("intern_mail"));
                Table.setSchool(resultSet.getString("intern_school"));
                Table.setTime(resultSet.getInt("intern_time"));

                iTable.add(Table);
            }

        }
        catch (SQLException e){
            System.out.println(e);
        }

        return iTable;


    }


}

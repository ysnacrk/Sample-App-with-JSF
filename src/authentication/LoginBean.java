
import authentication.Compare;
import connection.ConnectionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean @SessionScoped
public class LoginBean {

    private int no;
    private int password;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password){
        this.password = password;
    }

    List<Compare> compareTable = new ArrayList<>();

    public List<Compare> getLoginInfo(){
        ConnectionBean connectionBean = new ConnectionBean();
        Connection connection = connectionBean.getConnection();

        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT intern_no, intern_password FROM staj_takip.intern.intern_table where intern_no = ?");
            preparedStatement.setInt(1, no);
            ResultSet resultSet = preparedStatement.executeQuery() ;

            while(resultSet.next()){
                Compare compare = new Compare();
                compare.setNo("intern_no");
                compare.setPassword("intern_password");

                compareTable.add(compare);

            }

        }catch (SQLException e){
            System.out.println(e);
        }

    }

    public String validateUserLogin() {
        String navResult = "";

        if () {
            navResult = "success?faces-redirect=true";
        } else {
            navResult = "fail?faces-redirect=true";
        }
        return navResult;
    }
}

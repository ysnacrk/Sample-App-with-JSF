
package authentication;

import connection.ConnectionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@ManagedBean
@SessionScoped
public class LoginBean {

    private int no;
    private int password;

    private int dbNo;
    private int dbPassword;

    public int getDbNo() {
        return dbNo;
    }

    public void setDbNo(int dbNo) {
        this.dbNo = dbNo;
    }

    public int getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(int dbPassword) {
        this.dbPassword = dbPassword;
    }

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


    public boolean getLoginInfo(int no , int password) {
        ConnectionBean connectionBean = new ConnectionBean();
        Connection connection = connectionBean.getConnection();

        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT intern_no, intern_password FROM staj_takip.intern.intern_table where intern_no = ? and intern_password = ?");

            preparedStatement.setInt(1,no);
            preparedStatement.setInt(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }

        }catch (SQLException e){
            System.out.println(e);
            return false;
        }

        return false;

    }

    public String validateUserLogin() {

        String navResult = "";

        if (getLoginInfo(no,password)) {
            navResult = "success?faces-redirect=true";
        } else {
            navResult = "fail?faces-redirect=true";
        }
        return navResult;
    }
}

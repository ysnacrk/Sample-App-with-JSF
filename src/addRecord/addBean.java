package addRecord;

import connection.ConnectionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@ManagedBean
@SessionScoped
public class addBean {

    String mail;
    String school;

    int time;
    int no;
    int password;

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }



    public void saveDataBase() throws SQLException {

        ConnectionBean connectionBean = new ConnectionBean();
        Connection connection = connectionBean.getConnection();
        PreparedStatement preparedStatement = null;

        int i  = 0;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO staj_takip.intern.intern_table(intern_password,intern_mail,intern_school,intern_time,intern_no) VALUES (?,?,?,?,?)" );
            preparedStatement.setInt(1 , password );
            preparedStatement.setString(2 , mail);
            preparedStatement.setString(3 , school);
            preparedStatement.setInt(4 , time);
            preparedStatement.setInt(5 , no);

            i = preparedStatement.executeUpdate();
        }
        catch(Exception e){
            System.out.println("hatas" + e);
        }
        finally {

            if(connection!=null){
                connection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }
        if(i>0){
            System.out.println("success");
        }
        else{
            System.out.println("fail");
        }
    }
}

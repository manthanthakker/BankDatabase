/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manth
 */
public class OnlineBankingSystem {

    /**
     * @param args the command line arguments
     */
     static Person person=null;
     static Customer customer=null;
     static Manager manager1=null;
     static Account sacc=null;
     static Account cacc=null;
     static Loan sav_acc=null;
     static EducationLoan educationlocal=null;
     static HomeLoan homeloan=null;
     static CarLoan carloan=null;
     static Manager manager=null;
     static Card card=null;
     static DebitCard dcard=null;
     static CreditCard ccard=null;
     static Connection c=null;
     
    public static void populatecustomer(int cust_id,Connection c)
    {
            try {
            PreparedStatement st1=c.prepareStatement("Select * from Person where id=? ");
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            PreparedStatement st2=c.prepareStatement("Select * from Customer where cust_id=? ");
            st2.setInt(1, cust_id);
            ResultSet rs2=st2.executeQuery();
            PreparedStatement st3=c.prepareStatement("Select * from Manager where mid=? ");
            st3.setInt(1, cust_id);
            ResultSet rs3=st3.executeQuery();
            while(rs.next())
            {
                 int c_id=rs.getInt(1);
                 String fname=rs.getString(2);
                 String lname=rs.getString(3);
                 String dob=rs.getString(4);
                 String address=rs.getString(5);
                 int contact_no=rs.getInt(6);
                 String password=rs.getString(7);
                 String email=rs.getString(8);
                 person=new Person(c_id,fname,lname,dob,address,contact_no,password,email);
                 
                 
                System.out.println(person);
            
            } 
            if(rs2!=null)
            {
                customer=new Customer(rs2.getInt(1));
                System.out.println(customer);
            }
            if(rs3!=null)
            {
                manager=new Manager(rs3.getInt(1));
                System.out.println(manager);
            }
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args) {
       
        
        initialization();
        populatecustomer(1,c);
        Scanner sc=new Scanner(System.in);
        System.out.println(" ONLINE BANKING SYSTEM ");
        System.out.println("Enter Choice");
        System.out.println("1.Login\n2.Signup");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter Customer ID");
                String cust_id=sc.next();
                System.out.println("Enter Password");
                
                String password=sc.next();
                break;
            case 2:
                System.out.println("SIGN UP DETAILS");
                System.out.println("FNAME ");
                String fname=sc.next();
                System.out.println("LNAME ");
                String lname=sc.next();
                System.out.println("Enter Date of Birth");
                String DOB=sc.next();
                System.out.println("Address");
                String address=sc.next();
                System.out.println("Enter Valid Phone Number");
                int phone_number=sc.nextInt();
                System.out.println("Enter E-mail");
                String email=sc.next();
                String passwordsignup="a";
                 String passwordsignup2="b";
                while(!passwordsignup.equals(passwordsignup2))
                {
                    System.out.println("Enter Password");
                    passwordsignup=sc.next();
                    System.out.println("Confirm Password");
                    passwordsignup2=sc.next();
                    System.out.println(" Passwords Do not Match!  ");
                }
                
                
                break;
             
        }
        
        
    }
    public static void initialization()
    {
        try {
			String url = "jdbc:mysql://localhost/bank";
			String pass = "6994";
			String driver = "com.mysql.jdbc.Driver";
			String user = "root";

			Connection c = createConnection(driver, url, user, pass);
			
			System.out.println("Connection Successful");
			
			
                        
                     
		} catch(Exception e) {
			System.out.println("Spitting exception\n" + e);
		} finally {
                }

	}

	
        
    
    // CREATE CONNECTION METHOD
	public static Connection createConnection(String driver, String url, String user, String password) {
		Connection connection = null;
		try 
                {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
                        c=connection;
		} 
                catch(Exception e) {
			System.err.println("Unable to connect to the database due to " + e);
		}
		return connection;
	}
    
    
}

class Person
{
    int id;
    String fname;
    String lname;
    String date;
    String address;
    int contact;
    String password;
    String email;

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", date=" + date + ", address=" + address + ", contact=" + contact + ", password=" + password + ", email=" + email + '}';
    }
    public Person()
    {
        
    }

    public Person(int id, String fname, String lname, String date, String address, int contact, String password, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.address = address;
        this.contact = contact;
        this.password = password;
        this.email = email;
    }
}
class Customer extends Person
{
    
    int cust_id;

    public Customer(int cust_id) {
        this.cust_id = cust_id;
    }
}
class Manager extends Person
{
    int manager_id;

    public Manager(int manager_id) {
        this.manager_id = manager_id;
    }
}
class Account 
{
    int acc_id;
    int acc_no;
    double balance;
    String dos;
    String doe;
    String status;
    
}
class SavingsAccount extends Account
{
    int s_id;
    final double ROI=8.5;
}
class CheckingAccount extends Account
{
    int id;
}

class Card
{
    int cid;
    double cardno;
    double issuedate;
    double expdate;
    int cvv;
    String status;
}
class DebitCard
{
    int did;
}
class CreditCard
{
    int cid;
    double maxcredit;
}
class Loan
{
   int id;
   double amount;
   
}
class EducationLoan extends Loan
{
   int e_id;
   double roi;
   String university;
}
class CarLoan extends Loan
{
    int c_id;
    double roi;
    String car_model;
}
class HomeLoan extends Loan
{
    int h_id;
    double roi;
    String address;
}
class Transaction
{
    int t_id;
    double to_id;
    double from_id;
    double amount;
    String type;
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

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
     
     
    public static void main(String[] args) {
       
        
        initialization();
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
}
class Customer extends Person
{
    int cust_id;
}
class Manager extends Person
{
    int manager_id;
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
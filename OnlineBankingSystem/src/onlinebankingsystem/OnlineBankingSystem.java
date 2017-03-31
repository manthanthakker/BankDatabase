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
     
     static CheckingAccount check_acc=null;
     static SavingsAccount sav_acc=null;
     static Loan loan=null;
     static EducationLoan educationlocal=null;
     static HomeLoan homeloan=null;
     static CarLoan carloan=null;
     static Manager manager=null;
     
     static DebitCard dcard=null;
     static CreditCard ccard=null;
     static Connection c=null;
     
     /// ----- COMPLETED
    public static void populatecustomer(int cust_id,Connection c)
    {
            try {
            PreparedStatement st1=c.prepareStatement("Select * from person where id=?");
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            PreparedStatement st2=c.prepareStatement("Select * from Customer where cust_id=? ");
            st2.setInt(1, cust_id);
            ResultSet rs2=st2.executeQuery();
         //   PreparedStatement st3=c.prepareStatement("Select * from Manager where mid=? ");
          //  st3.setInt(1, cust_id);
           // ResultSet rs3=st3.executeQuery();
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
            if(rs2.next())
            {
                customer=new Customer(rs2.getInt(1));
                System.out.println(customer);
            }
          /*  if(rs3.next())
            {
                manager=new Manager(rs3.getInt(1));
                System.out.println(manager);
            }*/
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void populatesavings(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("Select a.id,a.account_number,a.balance,a.DateOfStart, a.DateOfEnd, a.status  from  personaccount pa, Account a, SavingsAccount s where pa.personid=? and pa.accid=a.id and a.id=s.sid");
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 sav_acc=new SavingsAccount(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 
                 
                System.out.println(sav_acc);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // ONGOING
     public static void populatechecking(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("Select a.id,a.account_number,a.balance,a.DateOfStart, a.DateOfEnd, a.status  from  personaccount pa, Account a, CheckingAccount s where pa.personid=? and pa.accid=a.id and a.id=s.caid");
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 check_acc=new CheckingAccount(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 
                 
                System.out.println(check_acc);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void populatecreditcard(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("select credit_id,maxcredit,card_no,issuedate,expdate,cvv,status from creditcard credit,card c where c.usedby=? and credit.credit_id=c.card_id");
            //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            //nt did, double cardno, String issuedate, String expdate, int cvv, String status
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 ccard=new CreditCard(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                 //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
                 
                System.out.println(ccard);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void populatedebitcard(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("select debit_id,card_no,issuedate,expdate,cvv,status from debitcard debit,card c where c.usedby=? and debit.debit_id=c.card_id");
           //int did, double cardno, String issuedate, String expdate, int cvv, String status
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 dcard=new DebitCard(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                 //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
                 
                System.out.println(dcard);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void populaterecordsforcustomer(int cust_id, Connection c)
    {
        populatecustomer(cust_id,c);
        populatesavings(cust_id,c);
        populatechecking(cust_id,c);
        populatecreditcard(cust_id,c);
        populatedebitcard(cust_id,c);
    }
    public static boolean login(int cust_id, Connection c, String password)
    {
        populaterecordsforcustomer(cust_id,c);
        
        return person.password.equals(password);
    }
    
    public static void register(String firstName, String lastName, String dob,String addr, int phone, String email, String pass ) throws SQLException{
        PreparedStatement p = c.prepareStatement("insert into Person values(?, ?, ?, ?, ?, ?, ?)");
        p.setString(1, firstName);
        p.setString(2, lastName);
        p.setString(3, dob);
        p.setString(4, addr);
        p.setInt(5, phone);
        p.setString(6, pass);
        p.setString(7, email);
        ResultSet rs = p.executeQuery();
        System.out.println("You have registered successfully!");
    }
    
    
    public static boolean checkAcc(int accNum, int custId, int type)
    {
        populaterecordsforcustomer(custId, c);
        if(type==1)
        {
           if (check_acc.acc_no==accNum)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(type==2)
        {
           if (sav_acc.acc_no==accNum)
            {
                return true;
            }
            else
            {
                return false;
            } 
        }
        return false;
    }
    
    public static void initiateDeposit(int custId, int accNum, double amount, int type) throws SQLException{
        
        if(checkAcc(accNum,custId,type))
        {
            PreparedStatement p = c.prepareStatement("update Account set balance = balance+? where acc_no=?");
            p.setDouble(1,amount);
            p.setInt(2, accNum);
            ResultSet rs = p.executeQuery();
            populaterecordsforcustomer(custId,c);
            System.out.println("Your money has been deposited!");
        }
        else
        {
            System.out.println("No such account number exists!");
        }
           
    }
    public static boolean checkBal(int cid, int t)
    {
        populaterecordsforcustomer(cid,c);
        if (t==1)
        {
            if (check_acc.balance>=100)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(t==2)
        {
            if (sav_acc.balance>=100)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
        public static void initiateWithdrawal(int custId, int accNum, double amount, int type) throws SQLException{
        
        if(checkAcc(accNum,custId,type))
        {
            if (checkBal(custId,type))
            {
                PreparedStatement p = c.prepareStatement("update Account set balance = balance-? where acc_no=?");
                p.setDouble(1,amount);
                p.setInt(2, accNum);
                ResultSet rs = p.executeQuery();
                populaterecordsforcustomer(custId,c);
                System.out.println("Withdrawal successful!");
            }
            else
            {
                System.out.println("Low Balance");
            }
            
        }
        else
        {
            System.out.println("No such account number exists!");
        }
           
    }
        
        public static void makeTransfer(int accTo, int accFrom, double amt, int cid,int t1, int t2) throws SQLException
        {
            initiateWithdrawal(cid,accFrom,amt,t1);
            initiateDeposit(cid,accTo,amt,t2);
            populaterecordsforcustomer(cid,c);
            
        }
        
        public static void assignEducationLoan(int cid, double roi,String univ, double amt) throws SQLException
        {
            PreparedStatement p1 = c.prepareStatement("insert into Loan values(?,?,?)");
            p1.setDouble(1,amt);
            p1.setInt(2,cid);
            p1.setString(3, "approved");
            ResultSet rs = p1.executeQuery();
            populaterecordsforcustomer(cid,c);
            PreparedStatement p2 = c.prepareStatement("insert into EducationLoan values(?,?,?)");
            p2.setInt(1,loan.id);
            p2.setDouble(2,roi);
            p2.setString(3,univ);
            populaterecordsforcustomer(cid,c);
            System.out.println("Congratulations! Your loan is sanctioned!");
        }
        
    public static void getcustdetails()
    {
       System.out.println("All Customer Details");
       System.out.println(" Customer Details "+ person);
       System.out.println(" DebitCard Details "+ dcard);
       System.out.println(" Credit Card Details "+ ccard);
       System.out.println(" SavingsAccount Details "+ sav_acc);
       System.out.println("Checking Account Details"+check_acc);
    }
    public static void main(String[] args) throws SQLException {
       
        
       
       initialization();
        Scanner sc=new Scanner(System.in);
        while(true)
        {
        System.out.println(" ONLINE BANKING SYSTEM ");
        System.out.println("Enter Choice");
        System.out.println("1.Login\n2.Signup");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter Customer ID");
                int cust_id=sc.nextInt();
                
                 //populaterecordsforcustomer(cust_id,c);
                 System.out.println("Enter Password");
                  String password=sc.next();
                 if(login(cust_id,c,password))
                 {
                     getcustdetails();
                 }
                 else
                 {
                     System.out.println("Wrong ID and Password");
                 }
                     
                System.out.println("Welcome "+ cust_id +"!");
                System.out.println("Select your choices");
                System.out.println("1.Make a transaction\n2.Loan Department\n3.Card Department\n4.View all details");
                int b=sc.nextInt();
        switch(b)
                {
        case 1:
                             
                             System.out.println("Enter the type of Transaction to be made");
                             System.out.println("1.Deposite\n2.Withdraw\n3.Transfer\n4.Pay Loan Amount");
                              //Do the respective actions
                             int x = sc.nextInt();
                             switch(x)
                             {
                                 case 1:
                                     System.out.println("Enter the account number to make a deposit to");
                                     int account_number;
                                     account_number = sc.nextInt();
                                    System.out.println("Enter the amount");
                                    double amount;
                                    amount = sc.nextDouble();
                                    System.out.println("1.Checking \n2. Savings");
                                    int type;
                                    type=sc.nextInt();
                                    initiateDeposit(cust_id,account_number,amount,type);
                                     break;
                                     
                                 case 2:
                                     System.out.println("Enter the account number to withdraw from");
                                     int accNo;
                                     accNo = sc.nextInt();
                                    System.out.println("Enter the amount");
                                    double amt;
                                    amt = sc.nextDouble();
                                    System.out.println("1.Checking \n2. Savings");
                                    int t;
                                    t=sc.nextInt();
                                    initiateWithdrawal(cust_id,accNo,amt,t);
                                     break;
                                     
                                
                                 case 3:
                                     System.out.println("Enter the account number to make transactions to");
                                    int account_number_to;
                                     account_number_to = sc.nextInt();
                                    System.out.println("Enter the account number to make transactions from");
                                    int account_number_from;
                                    account_number_from = sc.nextInt();
                                    System.out.println("Enter the amount");
                                    double amnt;
                                    amnt = sc.nextDouble();
                                    System.out.println("Account From\n 1.Checking \n2. Savings");
                                    int t1;
                                    t1=sc.nextInt();
                                    System.out.println("Account To\n 1.Checking \n2. Savings");
                                    int t2;
                                    t2=sc.nextInt();
                                    makeTransfer(account_number_to, account_number_from,amnt,cust_id,t1,t2);
                                    break;
                             }
                             
        
                             break;
                             
        case 2:
        System.out.println("Input the type of loan needed ");
        System.out.println("1.Educational Loan\n2.Housing Loan\n3.Car loan");
        int ch=sc.nextInt();
        switch(ch)
        {
            case 1:
                System.out.println("Enter rate of interest");
                double rate1=sc.nextDouble();
                System.out.println("Enter the University");
                String uni=sc.next();
                System.out.println("Enter the needed amount:");
                double amt = sc.nextDouble();
                assignEducationLoan(cust_id,rate1,uni,amt);
                break;
                
            case 2:
                System.out.println("Enter rate of interest");
                double rate2=sc.nextDouble();
                System.out.println("Enter the Address");
                String address=sc.next();
                
                break;
                
            case 3:
                System.out.println("Enter rate of interest");
                double rate3=sc.nextDouble();
                System.out.println("Enter the Car model");
                String car=sc.next();
                break;
    }
        break;
        
        case 3:
            
        System.out.println("Enter the card no");
        double card_no;
        card_no = sc.nextDouble();
        System.out.println("Enter issue date");
        String issue_date;
        issue_date = sc.next();
        System.out.println("Enter Expiry date");
        String exp_date;
        exp_date = sc.next();
        System.out.println("Enter CVV no");
        int cvv;
        cvv = sc.nextInt();
        System.out.println("Status of Card");
        String status;
        status = sc.next();
        // enum value(activated or deactivated)
        System.out.println("Input the type of cards needed ");
        System.out.println("1.Debit card\n2.Credit");
        //choice of card
        String dc=sc.next();
        String cc=sc.next();
        break;
          
        }
                
               /*1. Logic for transaction
                2. logic for loan approval from the manager if applied
                3. logic for card apporval from manager if applied
                4. view all details including acc infor, balance, details etc.
                */
        
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
                
                register(fname,lname,DOB,address,phone_number,email,passwordsignup);
                continue;
             
        }
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




//// WORKING


class SavingsAccount extends Account
{
    int s_id;
     
   
    @Override
    public String toString() {
        return "SavingsAccount{" + "s_id=" + s_id + ", acc_id=" + acc_id + ", acc_no=" + acc_no + ", balance=" + balance + ", dos=" + dos + ", doe=" + doe + ", status=" + status + ", ROI=" + ROI + '}';
    }

    public SavingsAccount(int s_id, int acc_no, double balance, String dos, String doe, String status) {
        
        this.acc_id = s_id;
        this.s_id=s_id;
        this.acc_no = acc_no;
        this.balance = balance;
        this.dos = dos;
        this.doe = doe;
        this.status = status;
    }
    

    
    final double ROI=8.5;
}











class CheckingAccount extends Account
{
    int id;
  

    @Override
    public String toString() {
        return "CheckingAccount{" + "id=" + id + ", acc_id=" + acc_id + ", acc_no=" + acc_no + ", balance=" + balance + ", dos=" + dos + ", doe=" + doe + ", status=" + status + '}';
    }

    public CheckingAccount(int id, int acc_no, double balance, String dos, String doe, String status) {
        this.id = id;
        this.acc_id = acc_id;
        this.acc_no = acc_no;
        this.balance = balance;
        this.dos = dos;
        this.doe = doe;
        this.status = status;
    }

    public CheckingAccount(int id) {
        this.id = id;
    }
}

class Card
{
    int cid;
    double cardno;
    String issuedate;
    String expdate;
    int cvv;
    String status;
    public Card()
    {
        
    }

    public Card(int cid, double cardno, String issuedate, String expdate, int cvv, String status) {
        this.cid = cid;
        this.cardno = cardno;
        this.issuedate = issuedate;
        this.expdate = expdate;
        this.cvv = cvv;
        this.status = status;
    }
}
class DebitCard extends Card
{
    int did;
    
  

    @Override
    public String toString() {
        return "DebitCard{" + "did=" + did + ", cardno=" + cardno + ", issuedate=" + issuedate + ", expdate=" + expdate + ", cvv=" + cvv + ", status=" + status + '}';
    }

    public DebitCard(int did, double cardno, String issuedate, String expdate, int cvv, String status) {
        this.did = did;
        this.cardno = cardno;
        this.issuedate = issuedate;
        this.expdate = expdate;
        this.cvv = cvv;
        this.status = status;
    }

    public DebitCard(int did) {
        this.did = did;
    }
}
class CreditCard extends Card
{
    
    
    double maxcredit;
   
   

    @Override
    public String toString() {
        return "CreditCard{" + "cid=" + cid + ", maxcredit=" + maxcredit + ", cardno=" + cardno + ", issuedate=" + issuedate + ", expdate=" + expdate + ", cvv=" + cvv + ", status=" + status + '}';
    }

    public CreditCard(int cid, double maxcredit, double cardno, String issuedate, String expdate, int cvv, String status) {
        this.cid = cid;
        this.maxcredit = maxcredit;
        this.cardno = cardno;
        this.issuedate = issuedate;
        this.expdate = expdate;
        this.cvv = cvv;
        this.status = status;
    }

    

    public CreditCard(int cid, double maxcredit) {
        this.cid = cid;
        this.maxcredit = maxcredit;
    }
}
class Loan
{
   int id;
   double amount;

    public Loan(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }
    public Loan()
    {
        
    }
   
}
class EducationLoan extends Loan
{
   int e_id;
   double roi;
   String university;

    public EducationLoan(int e_id, double roi, String university) {
        this.e_id = e_id;
        this.roi = roi;
        this.university = university;
    }
}
class CarLoan extends Loan
{
    int c_id;
    double roi;
    String car_model;

    public CarLoan(int c_id, double roi, String car_model) {
        this.c_id = c_id;
        this.roi = roi;
        this.car_model = car_model;
    }
}
class HomeLoan extends Loan
{
    int h_id;
    double roi;
    String address;

    public HomeLoan(int h_id, double roi, String address) {
        this.h_id = h_id;
        this.roi = roi;
        this.address = address;
    }
}
class Transaction
{
    int t_id;
    double to_id;
    double from_id;
    double amount;
    String type;

    public Transaction(int t_id, double to_id, double from_id, double amount, String type) {
        this.t_id = t_id;
        this.to_id = to_id;
        this.from_id = from_id;
        this.amount = amount;
        this.type = type;
    }
}
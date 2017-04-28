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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.HashMap;

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
     static EducationLoan educationloan=null;
     static HomeLoan homeloan=null;
     static CarLoan carloan=null;
     static Manager manager=null;
     static Card card = null;
     static DebitCard dcard=null;
     static CreditCard ccard=null;
     static Connection c=null;
     static boolean ismanager=false;
     static private String bankemail="manthanthakker40@gmail.com";
     static private String bankpass="$#Nalini26044377";
     static Scanner sc=new Scanner(System.in);
     /// ALL POPULATE METHODS 
    public static void populatecustomer(int cust_id,Connection c)
    {
            try {
            PreparedStatement st1=c.prepareStatement("Select * from person where id=?;");
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            PreparedStatement st2=c.prepareStatement("Select * from Customer where cust_id=? ;");
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
                 String contact_no=rs.getString(6);
                 String password=rs.getString(7);
                 String email=rs.getString(8);
                 
                 person=new Person(c_id,fname,lname,dob,address,contact_no,password,email);
                 
                 
                //System.out.println(person);
            
            } 
            if(rs2.next())
            {
                customer=new Customer(rs2.getInt(1));
               // System.out.println(customer);
            }
     
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void populatesavings(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("Select a.id,a.account_number,a.balance,a.DateOfStart, a.DateOfEnd, a.status  from  personaccount pa, Account a, SavingsAccount s where pa.personid=? and pa.accid=a.id and a.id=s.sid;");
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
             
            
            while(rs.next())
            {
                 
                 
                sav_acc=new SavingsAccount(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 
                 
             
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // ONGOING
     public static void populatechecking(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("Select a.id,a.account_number,a.balance,a.DateOfStart, a.DateOfEnd, a.status  from  personaccount pa, Account a, CheckingAccount s where pa.personid=? and pa.accid=a.id and a.id=s.caid;");
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 check_acc=new CheckingAccount(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 
                 
              //  System.out.println(check_acc);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void populatecreditcard(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("select credit_id,maxcredit,card_no,issuedate,expdate,cvv,status from creditcard credit,card c where c.usedby=? and credit.credit_id=c.card_id;");
            //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
            //int s_id, int acc_no, double balance, String dos, String doe, String status)
            //nt did, double cardno, String issuedate, String expdate, int cvv, String status
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 ccard=new CreditCard(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                 //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
                 
              //  System.out.println(ccard);
            
            } 
           
           } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void populatedebitcard(int cust_id,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("select debit_id,card_no,issuedate,expdate,cvv,status from debitcard debit,card c where c.usedby=? and debit.debit_id=c.card_id;");
           //int did, double cardno, String issuedate, String expdate, int cvv, String status
            st1.setInt(1, cust_id);
            ResultSet rs=st1.executeQuery();
            
            while(rs.next())
            {
                 
                 
                 dcard=new DebitCard(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                 //int cid, double maxcredit, double cardno, double issuedate, double expdate, int cvv, String status
                 
          //      System.out.println(dcard);
            
            } 
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void populateEducationLoan(int cust_id, Connection c)
    {
        try{
            //int loan_id, double amount, double amount_paid, double amount_left, double roi, String university
        //int loan_id, double amount, double amount_paid, double amount_left, double roi, String car_model
        PreparedStatement p1=c.prepareStatement("select loanid,amount,amountpaid,amountleft,roi,university,status from \n" +
                                        "EducationLoan e \n" +
                                        "Join Loan l on\n" +
                                        "l.loanid=e.eid\n" +
                                        "and l.cust_id=?;");
        p1.setInt(1, cust_id);
        ResultSet rs=p1.executeQuery();
                while(rs.next())
                {
                    
                      educationloan=new EducationLoan(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7));
                }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in Educational Loan populate() "+ e);
        }
    }
    public static void populateCarLoan(int cust_id, Connection c)
    {
        try{
         
        PreparedStatement p1=c.prepareStatement("select loanid,amount,amountpaid,amountleft,roi,CarModel,status from \n" +
                                        "CarLoan c \n" +
                                        "Join Loan l on\n" +
                                        "l.loanid=c.cid\n" +
                                        "and l.cust_id=?;");
        p1.setInt(1, cust_id);
        ResultSet rs=p1.executeQuery();
                while(rs.next())
                {
                    
                      carloan=new CarLoan(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7));
                }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in Car Loan populate() "+ e);
        }
    }
    public static void populateHomeLoan(int cust_id, Connection c)
    {
        try{
         
        PreparedStatement p1=c.prepareStatement("select loanid,amount,amountpaid,amountleft,roi,address,status from \n" +
                                        "HomeLoan h \n" +
                                        "Join Loan l on\n" +
                                        "l.loanid=h.hid\n" +
                                        "and l.cust_id=?;");
        p1.setInt(1, cust_id);
        ResultSet rs=p1.executeQuery();
                while(rs.next())
                {
                    
                      homeloan=new HomeLoan(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7));
                }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in Home Loan populate() "+ e);
        }
    }
    public static void populaterecordsforcustomer(int cust_id, Connection c)
    {
        populatecustomer(cust_id,c);
        populatesavings(cust_id,c);
        populatechecking(cust_id,c);
        populatecreditcard(cust_id,c);
        populatedebitcard(cust_id,c);
        populateEducationLoan(cust_id,c);
        populateCarLoan(cust_id,c);
        populateHomeLoan(cust_id,c);
        populateifmanager(cust_id,c);
       
    }
    public static void populateifmanager(int cust_id,Connection c)
    {
        try{
         
        PreparedStatement p1=c.prepareStatement("select * from manager where mid=?");
        p1.setInt(1, cust_id);
        ResultSet rs=p1.executeQuery();
                if(rs.next())
                {
                    ismanager=true;
                }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in populateifmanager "+ e);
        }
    }
    
    // ALL POPULATE METHOD ENDS -- CALL POPULATERECORDSFORCUSTOMER
    // RETURNS: 1 -> if  loggedin as customer
    //          2-> if loggedin as manager 
    //          -1-> if not a valid customer
    public static int login(int cust_id, Connection c, String password)
    {
        Scanner sc=new Scanner(System.in);
        populaterecordsforcustomer(cust_id,c);
        
        if(ismanager)
        {
            System.out.println("Do you want to login as \n1.Customer\n2.Manager");
            int n=sc.nextInt();
            if(n==2&&person.password.equals(password))
            {
                return 2;
            }
            
            populaterecordsforcustomer(cust_id,c);;
        }
        
        
        if(person.password.equals(password))
        { populaterecordsforcustomer(cust_id,c);
            return 1;
        }
        else
            return -1;
    }
    
    // REGISTERS A NEW PERSON
    public static void register(String firstName, String lastName, String dob, String addr, String phone, String email, String pass) throws SQLException{
        PreparedStatement p = c.prepareStatement("insert into Person(fname,lname,dob,address,contact_no,password,emailId) values(?, ?, ?, ?, ?, ?, ?);");
        p.setString(1, firstName);
        p.setString(2, lastName);
        p.setString(3, dob);
        p.setString(4, addr);
        p.setString(5, phone);
        p.setString(6, pass);
        p.setString(7, email);
        p.executeUpdate();
        
        int id=returns_last_id();
        PreparedStatement p2=c.prepareStatement("insert into Customer values (?);");
        p2.setInt(1,id);
        p2.executeUpdate();
        System.out.println("You have registered successfully! ");
        populaterecordsforcustomer(id,c);
        System.out.println("Your Cust_id is : "+id+" Please Note it down!! Don't Share Your Password and Login CustId to Anyone! *T&C");
        
        String msg="Dear "+firstName+",\nThank you for Choosing Bank Of America! Your Registeration Details are as follows!\n\n"+person.toString()+"\n\n\n\n\n\n\n\nRegards,\nBank of America";
        Mailer.send("manthanthakker40@gmail.com","$#Nalini26044377",email,"Congratulation! You have Succesfully registered with Bank Of America!",msg);  
        System.out.println("An e-mail has been sent to you specifying your Cust_ID and Password");
        
        
    }
    // RETURNS THE LAST AUTOINCREMENTED ID
    public static int returns_last_id()
    {
            int id=0;
            try
            {
            PreparedStatement p1a=c.prepareStatement("Select last_insert_id();");
            ResultSet rr=p1a.executeQuery();
            id=0;
            if(rr.next())
            {
             id=rr.getInt(1);
            }
            //System.out.println("loan id is"+id);
            
            }catch(Exception e)
            {
                System.out.println("Exception in Returns_Last_id() "+e);
            }
            return id;
    }
    // CREATES A SAVING ACCOUNT FOR THE MENTIONED CUSTOMER ID0
    public static void createsavingsacc(int cust_id,Connection c ) throws SQLException{
        PreparedStatement p = c.prepareStatement("insert into Account(account_number,balance,dateofstart,dateofend,status) values(?, ?, ?, ?, ?);");
       
        double acc_no=Math.round(Math.random()*1234.0);
        p.setDouble(1,acc_no);
        p.setDouble(2,0.0);
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);   
        p.setString(3,modifiedDate);
        p.setString(4,modifiedDate);
        p.setString(5,"Activated");
        
        p.executeUpdate();
        
        PreparedStatement p1=c.prepareStatement("Select last_insert_id();");
        ResultSet rr=p1.executeQuery();
        int id=0;
        if(rr.next())
        {
            id=rr.getInt(1);
        }
        
        
        
        populaterecordsforcustomer(cust_id, c);
        PreparedStatement p2=c.prepareStatement("insert into SavingsAccount values (?,8.9);");
        
        p2.setInt(1,id);
        p2.executeUpdate();
         PreparedStatement p4=c.prepareStatement("insert into personaccount values (?,?)");
         p4.setInt(2,cust_id);
         p4.setInt(1,id);
        p4.executeUpdate();
        System.out.println("You have Successfully Opened Savings Account "+id);
         populaterecordsforcustomer(cust_id, c);
        String msg="Dear "+person.fname+",\nThank you for Choosing Bank Of America! SuccessFully Opened Saving Account! Account Details are:\n\n"+sav_acc.toString()+"\n\n\n\n\n\n\n\nRegards,\nBank of America";
        Mailer.send("manthanthakker40@gmail.com","$#Nalini26044377",person.email,"Congratulation! You have Succesfully registered with Bank Of America!",msg);  
        System.out.println("An e-mail has been sent to you specifying your account details! Make Sure You deposit Some money to use our Fantastic Services!");
       populaterecordsforcustomer(cust_id, c);
    }
    public static void createcurrentacc(int cust_id,Connection c ) throws SQLException{
        PreparedStatement p = c.prepareStatement("insert into Account(account_number,balance,dateofstart,dateofend,status) values(?, ?, ?, ?, ?);");
       
        double acc_no=Math.round(Math.random()*134.0);
        p.setDouble(1,acc_no);
        p.setDouble(2,0.0);
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);   
        p.setString(3,modifiedDate);
        p.setString(4,modifiedDate);
        p.setString(5,"Activated");
        
        p.executeUpdate();
        
        PreparedStatement p1=c.prepareStatement("Select last_insert_id();");
        ResultSet rr=p1.executeQuery();
        int id=0;
        if(rr.next())
        {
            id=rr.getInt(1);
        }
     //   PreparedStatement p3=c.prepareStatement("insert into CheckingAccount");
        
        
        populaterecordsforcustomer(cust_id, c);
        PreparedStatement p2=c.prepareStatement("insert into CheckingAccount values (?);");
        p2.setInt(1,id);
        p2.executeUpdate();
         PreparedStatement p4=c.prepareStatement("insert into personaccount values (?,?)");
         p4.setInt(2,cust_id);
         p4.setInt(1,id);
        p4.executeUpdate();
        System.out.println("You have Successfully Opened Checkings Account with "+id);
         populaterecordsforcustomer(cust_id, c);
        String msg="Dear "+person.fname+",\nThank you for Choosing Bank Of America! SuccessFully Opened Checkings Account! Account Details are:\n\n"+sav_acc.toString()+"\n\n\n\n\n\n\n\nRegards,\nBank of America";
        Mailer.send("manthanthakker40@gmail.com","$#Nalini26044377",person.email,"Congratulation! You have Succesfully opened Checkings Account with Bank Of America!",msg);  
        System.out.println("An e-mail has been sent to you specifying your account details! Make Sure You deposit Some money to use our Fantastic Services!");
       populaterecordsforcustomer(cust_id, c);
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
        
        //if(checkAcc(accNum,custId,type))
        //{
            PreparedStatement p =c.prepareStatement("update Account set balance = balance+? where account_number=?;");
            p.setDouble(1,amount);
            p.setInt(2, accNum);
            if(p.executeUpdate()>0)
            {
                System.out.println("Your money has been deposited!");
            }
            else
            {
                System.out.println("Your money has not been deposited!");
            }
            populaterecordsforcustomer(custId,c);
         
        //}
       // else
        //{
          //  System.out.println("No such account number exists!");
        //}
           
    }
    public static boolean checkBal(int cid, int t)// t==1 for checking and t==2 for saving
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
        public static boolean initiateWithdrawal(int custId, int accNum, double amount, int type){
        try{
        int flag=0;
        Account acc=null;
        if(type==1)
            acc=check_acc;
        else
            acc=sav_acc;
            
            if (checkBal(custId,type))
            {
                PreparedStatement p = c.prepareStatement("update Account set balance = ? where account_number=?;");
                if(acc.balance-amount<=100)
                {
                    System.out.println("Can't Perform Transaction!! Insufficient Funds! Mandatory to Keep a minimum of 100$ In your account!");
                    return false;
                }
                    
                p.setDouble(1,acc.balance-amount);
               
                p.setInt(2, accNum);
                flag=p.executeUpdate();
                populaterecordsforcustomer(custId,c);
                System.out.println("Withdrawal successful!");
            }
            else
            {
                System.out.println("Low Balance");
            }
            
        
        
        if(flag>=1)
        {
            return true;
        }
        else
        {
            return false;
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception in initiateWithrawl()"+e);
        }
        return false;
           
    }
        
        public static void makeTransfer(int accTo, int accFrom, double amt, int cid,int t1, int t2) throws SQLException
        {
            if(initiateWithdrawal(cid,accFrom,amt,t1))
            initiateDeposit(cid,accTo,amt,t2);
            
            populaterecordsforcustomer(cid,c);
            
        }
        
        
         public static void assignDebitcard(int cid, String issue_date, String exp_date, Double Card_no, int cvv) throws SQLException
         {
                PreparedStatement p1 = c.prepareStatement("insert into Card(card_no,issuedate,expdate,cvv,status,usedby) values(?,?,?,?,?,?);");
                p1.setDouble(1,Card_no);
                p1.setString(2,issue_date);
                p1.setString(3,exp_date);
                p1.setInt(4,cvv);
                p1.setString(5, "Deactivated");
                p1.setInt(6,cid);
                p1.executeUpdate();
               // populaterecordsforcustomer(cid,c);
                int id=returns_last_id();
                PreparedStatement p2 = c.prepareStatement("insert into debitcard values(?);");
                p2.setInt(1,id);
                p2.executeUpdate();
                populaterecordsforcustomer(cid,c);
                System.out.println("Your request has been Submitted! You will be issued a card at your home address and will receive a email once it is activated on"+person.email+"+!\n Please remember your details "+dcard );
                

         }
         
         public static void assignCreditcard(int cid, String issue_date, String exp_date, Double Card_no, int cvv,Double max_c) throws SQLException
         {
                max_c=sav_acc.balance*1.5;
                PreparedStatement p1 = c.prepareStatement("insert into Card(card_no,issuedate,expdate,cvv,status,usedby) values(?,?,?,?,?,?);");
                p1.setDouble(1,Card_no);
                p1.setString(2,issue_date);
                p1.setString(3,exp_date);
                p1.setInt(4,cvv);
                p1.setString(5, "Deactivated");
                p1.setInt(6,cid);
                p1.executeUpdate();
               // populaterecordsforcustomer(cid,c);
                int id=returns_last_id();
                PreparedStatement p2 = c.prepareStatement("insert into creditcard values(?,?);");
                p2.setInt(1,id);
                p2.setDouble(2,max_c);
                p2.executeUpdate();
                populaterecordsforcustomer(cid,c);
                System.out.println("Your request has been Submitted! You will be issued a card at your home address and will receive a email once it is activated on"+person.email+"+!\n Please remember your details "+ccard );
          
         }
         
        // LOAN METHODS START
        public static void assignEducationLoan(int cid, double roi,String univ, double amt) throws SQLException
        {
           // System.out.println(cid);
            PreparedStatement p1 = c.prepareStatement("insert into Loan(amount,cust_id,status,amountpaid,amountleft) values(?,?,?,?,?);");
            p1.setDouble(1,amt);
            p1.setInt(2,cid);
            p1.setString(3, "pending");
            p1.setDouble(4,0.0);
            p1.setDouble(5,amt);
            p1.executeUpdate();
            PreparedStatement p1a=c.prepareStatement("Select last_insert_id();");
             ResultSet rr=p1a.executeQuery();
            int id=0;
            if(rr.next())
            {
             id=rr.getInt(1);
            }
            System.out.println("loan id is"+id);
            PreparedStatement p2 = c.prepareStatement("insert into EducationLoan values(?,?,?);");
            p2.setInt(1,id);
            p2.setDouble(2,roi);
            p2.setString(3,univ);
            p2.executeUpdate();
            System.out.println(" Your Request for Educational Loan for Rs"+amt+" has been sent!! \n You shall get a e-mail notification once it is approved! Also,Kindly Pay your Amount on every 6 months else you will be charged according to"+ roi);
            populaterecordsforcustomer(cid,c);
            
        }
        
         public static void assignHomeLoan(int cid, double roi,String addr, double amt) throws SQLException
        {
           PreparedStatement p1 = c.prepareStatement("insert into Loan(amount,cust_id,status,amountpaid,amountleft) values(?,?,?,?,?);");
            p1.setDouble(1,amt);
            p1.setInt(2,cid);
            p1.setString(3, "pending");
            p1.setDouble(4,0.0);
            p1.setDouble(5,amt);
            p1.executeUpdate();
            populaterecordsforcustomer(cid,c);
            
            int id=returns_last_id();
            PreparedStatement p2 = c.prepareStatement("insert into HomeLoan values(?,?,?);");
            p2.setInt(1,id);
            p2.setDouble(2,roi);
            p2.setString(3,addr);
            p2.executeUpdate();
            populaterecordsforcustomer(cid,c);
            System.out.println(" Your Request for Home Loan for Rs"+amt+" has been sent!! \n You shall get a e-mail notification once it is approved! Also,Kindly Pay your Amount on every 6 months else you will be charged according to"+ roi);
        }
         
          public static void assignCarLoan(int cid, double roi,String car, double amt) throws SQLException
        {
             PreparedStatement p1 = c.prepareStatement("insert into Loan(amount,cust_id,status,amountpaid,amountleft) values(?,?,?,?,?);");
            p1.setDouble(1,amt);
            p1.setInt(2,cid);
            p1.setString(3, "pending");
            p1.setDouble(4,0.0);
            p1.setDouble(5,amt);
            p1.executeUpdate();
           // populaterecordsforcustomer(cid,c);
            
            int id=returns_last_id();
            PreparedStatement p2 = c.prepareStatement("insert into CarLoan values(?,?,?);");
            p2.setInt(1,id);
            p2.setDouble(2,roi);
            p2.setString(3,car);
            p2.executeUpdate();
            populaterecordsforcustomer(cid,c);
            System.out.println(" Your Request for Car Loan for Rs"+amt+" has been sent!! \n You shall get a e-mail notification once it is approved! Also,Kindly Pay your Amount on every 6 months else you will be charged according to"+ roi);
        }
          
         public static void payloan(int lid,int amt)
         {
             try
             {
               
                 boolean flag=initiateWithdrawal(customer.cust_id,sav_acc.acc_no,amt,2);
                 if(!flag)
                 {
                     // checks if savings account has sufficient info!!
                     return;
                 }
                 PreparedStatement p=c.prepareStatement("Update Loan set amountleft=?,amountpaid=? where loanid=?;");
                 p.setInt(3,lid);
                 
                 if(carloan!=null&&carloan.loan_id==lid)
                 {
                        
                        p.setDouble(1,(carloan.amount_left-amt));
                        p.setDouble(2,(carloan.amount_paid+amt));
                         p.executeUpdate();
                         System.out.println("Thank You! Amount of Rs"+amt+" has been repaid to the bank for the educational loan!!");
                        
                        return;
                 }
                 else if(homeloan!=null&&lid==homeloan.loan_id)
                 {
                      p.setDouble(1,(homeloan.amount_left-amt));
                      p.setDouble(2,(homeloan.amount_paid+amt));
                       p.executeUpdate();
                       System.out.println("Thank You! Amount of Rs"+amt+" has been repaid to the bank for the educational loan!!");
                      return;
                 }
                 else if(educationloan!=null&&lid==educationloan.loan_id)
                 {
                      p.setDouble(1,(educationloan.amount_left-amt));
                      p.setDouble(2,(educationloan.amount_paid+amt));
                       p.executeUpdate();
                      System.out.println("Thank You! Amount of Rs"+amt+" has been repaid to the bank for the educational loan!!");
                      return;
                 }
                 System.out.println("The entered Loan ID does not belong to you\n Kidnly, Add correct loan id! ");
             }
             catch(Exception e)
             {
                 System.out.println("Exception in Payloan "+e);
             }
         }
         // LOAN METHODS END
        
         public static Boolean send_email(String to,String subject,String body)
         {
             //String msg="Dear "+person.fname+",\nThank you for Choosing Bank Of America! SuccessFully Opened Saving Account! Account Details are:\n\n"+sav_acc.toString()+"\n\n\n\n\n\n\n\nRegards,\nBank of America";
             Mailer.send(bankemail,bankpass,to,subject,body);  
            //
            return true;
         }
         
    public static void getcustdetails()
    {
       System.out.println("All Customer Details");
       System.out.println(" Customer Details \n\n"+ person);
       if(dcard!=null)
       System.out.println(" DebitCard Details  \n\n"+ dcard);
       if(ccard!=null)
       System.out.println(" Credit Card Details  \n\n"+ ccard);
       if(sav_acc!=null)
       System.out.println(" SavingsAccount Details  \n\n"+ sav_acc);
       if(check_acc!=null)
       System.out.println("Checking Account Details \n\n"+check_acc);
       if(educationloan!=null)
       System.out.println("Education Loan details  \n\n"+educationloan);
       if(homeloan!=null)
       System.out.println("Home Loan details  \n\n"+homeloan);
       if(carloan!=null)
       System.out.println("Car Loan Details  \n\n"+carloan);
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
    public static void initialization()
    {
        try {
			String url = "jdbc:mysql://localhost/bank";
			String pass = "6994";
			String driver = "com.mysql.jdbc.Driver";
			String user = "root";

			Connection c = createConnection(driver, url, user, pass);
			
			//System.out.println("Connection Successful");
			
			
                        
                     
		} catch(Exception e) {
			System.out.println("Spitting exception\n" + e);
		} finally {
                }

	}
    
    public static void loggedinmanager()
    {
        System.out.println("Welcome Manager "+person.fname+"\nWhat would you like to do?\n1.Sanction Loans\n2.Activate Cards");
        int k=sc.nextInt();
         switch (k) {
             case 1:
                // manager_sanctionloans();
                 manager_sanctionsloan();
                 break;
             case 2:
                 manager_activatescard();
                 
                 break;
             default:
                 break;
         }
        
    }
    public static String getEmailId(int cust_id,Connection c)
    {
         try{
           
        PreparedStatement p1=c.prepareStatement("select emailId from person where id=?");
        
        p1.setInt(1, cust_id);
        ResultSet rs=p1.executeQuery();
                while(rs.next())
                {
                    
                      return rs.getString(1);
                }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in Educational Loan populate() "+ e);
        }
         return "";
    }
    
    public static void manager_activatescard()
    {
            System.out.println("Here is a list of people whose card are pending for activation:\n ");
            try
            {
                                
                    PreparedStatement st1=c.prepareStatement("select card_id, card_no,cvv,account_number,balance, dateofstart,card.status \n" +
                                                            "from card, personaccount pa, account a \n" +
                                                            "where card.status=\"Deactivated\"\n" +
                                                            "and card.usedBy=pa.personid\n" +
                                                            "and pa.accid=a.id\n" +
                                                            "order by card_id;");
                    
                    
                    ResultSet rs=st1.executeQuery();
                    HashMap<Integer,String> hm=new HashMap<>();
                    TableBuilder tb = new TableBuilder();
                    tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                    tb.addRow("cardid","cardno","cvv","account_number","balance","dateofstart","cardstatus");
                    tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                    
                      System.out.println();
                    while(rs.next())
                    {
                      String record=""+ rs.getInt(1)+"   "+rs.getDouble(2)+"   "+ rs.getInt(3)+"  "+rs.getDouble(4)+"     "+rs.getDouble(5)+"     "+rs.getString(6)+"    "+rs.getString(7);
                     // System.out.println(record);
                      TableBuilder tb2 = new TableBuilder();
                      tb.addRow("--------------------", "---------", "----------","----------","----------","----------","----------");
                      tb.addRow( ""+rs.getInt(1),""+rs.getDouble(2),""+rs.getInt(3),""+rs.getDouble(4),""+rs.getString(6),""+rs.getString(7));
                      tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                      tb2.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                      tb2.addRow("cardid","cardno","cvv","account_number","balance","dateofstart","cardstatus");
                      tb2.addRow("--------------------", "---------", "----------","----------","----------","----------","----------");
                      tb2.addRow( ""+rs.getInt(1),""+rs.getDouble(2),""+rs.getInt(3),""+rs.getDouble(4),""+rs.getString(6),"Activated");
                      tb2.addRow("----------", "---------", "----------","----------","----------","----------","----------");

                      
                      hm.put(rs.getInt(1),tb2.toString());

                    } 
                   // System.out.println(hm);
                    System.out.println(tb.toString());
                    System.out.println("Sir, Please select the card_id you want to activate!\n");
                    int k=sc.nextInt();
                    PreparedStatement st2=c.prepareStatement("update card \n" +
                                                                "set status=\"Activated\"\n" +
                                                                "where card_id=?;\n");
                    st2.setInt(1,k);
                    
                    int count=st2.executeUpdate();
                    if(count>0)
                    {
                        
                        System.out.println("You have successfully activated Card_Id"+k);
                        String msg="Dear Sir,\nThank you for Choosing Bank Of America! SuccessFully Activated Card! Your Card Details are:+"+hm.get(k)+"+\n\n\n\n\n\n\n\nRegards,\nBank of America";
                        send_email(getEmailId(k,c),"BANK OF AMERICA: SUCCESFULL ACTIVATION OF CARD",msg);
                    }
                    else
                    {
                        System.out.println("Sorry, there are security issues! You cant update at this moment!");
                    }
                    
            }
            catch(Exception e)
            {
                System.out.println("Exception in manager_activatescard()"+e);
            }
            
            
    }
    public static void manager_sanctionsloan()
    {
        System.out.println("List of Loans pending to sanction/not approve\n");
         try {
            PreparedStatement st1=c.prepareStatement("Select loan.loanid,loan.amount,cust_id,a.balance,a.dateofstart,loan.status\n" +
                                                    "from loan,personaccount pa, account a \n" +
                                                    "where loan.status LIKE \"pending\"\n" +
                                                    "and loan.cust_id=pa.personid\n" +
                                                    "and a.id=pa.accid;"); 
            ResultSet rs=st1.executeQuery();
                     TableBuilder tb = new TableBuilder();
                    tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                    tb.addRow("loanid","loanamount","customerid","balance","startdate","loanstatus");
                    tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
            while(rs.next())
            {     
                 TableBuilder tb2 = new TableBuilder();
                      tb.addRow("--------------------", "---------", "----------","----------","----------","----------","----------");
                      tb.addRow( ""+rs.getInt(1),""+rs.getDouble(2),""+rs.getInt(3),""+rs.getDouble(4),""+rs.getString(5),""+rs.getString(6));
                      tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
                      

                
                 
            } 
            System.out.println(tb.toString());
            System.out.println("Please select the loan for which you want to make a decision\n");
            int k=sc.nextInt();
            System.out.println("Based on your analyis, on behalf of our Bank would you like to sanction loan ?\n1.Yes\n2.No\n");
            int decision=sc.nextInt();
            if(decision==1)
            {
                    PreparedStatement st2=c.prepareStatement("update loan set status=\"sanctioned\"\n" +
                                                                "where loanid=?;");
                    String msg="Dear Sir,\nThank you for Choosing Bank Of America! We have Sanctioned Your Loan with loan id: "+k+"+\n\n\n\n\n\n\n\nRegards,\nBank of America";
                       // send_email(getEmailId(k,c),"BANK OF AMERICA: LOAN SANCTIONED",msg);
                    st2.setDouble(1,k);
                    int count=st2.executeUpdate();
                    if(count>0)
                    {
                        System.out.println("You have successfully approved Loan   "+k);
                    }
                    else
                    {
                        System.out.println("Sorry, there are security issues! You cant update at this moment!");
                    }
            }
            else
            {
                System.out.print("State reason for rejection:   ");sc.nextLine();
                String reason="Not Approved ( "+sc.nextLine()+ ")";
                PreparedStatement st2=c.prepareStatement("update loan set status=?\n" +
                                                                "where loanid=?;");
                    st2.setDouble(2,k);
                    st2.setString(1,reason);
                    int count=st2.executeUpdate();
                    if(count>0)
                    {
                        String msg="Dear Sir,\nThank you for Choosing Bank Of America! We cannot Sanction Your Loan loan id: "+k+" at this time!!\n Reason for rejection:"+reason+"+\n\n\n\n\n\n\n\nRegards,\nBank of America";
                        //send_email(getEmailId(k,c),"BANK OF AMERICA: LOAN NOT SANCTIONED",msg);
                        System.out.println("You have successfully approved Loan   "+k);
                    }
                    else
                    {
                        System.out.println("Sorry, there are security issues! You cant update at this moment!");
                    }
            }
            
           
            } catch (SQLException ex) {
            Logger.getLogger(OnlineBankingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public static void main(String[] args) throws SQLException {
       
        
        //Mailer.send("manthanthakker40@gmail.com","$#Nalini26044377","facts4you2@gmail.com","Sent From Database Project","Yes! Bitches! E-mail API!! ");  
        initialization();
        
        while(true)
        {
                System.out.println(" \t\t\t\t\t\t\tONLINE BANKING SYSTEM DATABASE\t\t");
                //System.out.println("Enter Choice:");
                System.out.println("1.Login\n2.Signup");
                int choice=sc.nextInt();
                 switch(choice)
                 {
                    case 1://case for login and signup
                    {  
                                System.out.println("Enter Customer ID");
                                int cust_id;
                                 try{
                                         cust_id=sc.nextInt();
                                   
                                        //populaterecordsforcustomer(cust_id,c);
                                      System.out.println("Enter Password");
                                      String password=sc.next();
                                      int login_process=login(cust_id,c,password);
                                    switch (login_process) {
                                        case 1:
                                            getcustdetails();
                                            break;
                                        case 2:
                                            loggedinmanager();
                                            continue;
                                                    
                                            
                                            
                                        default:
                                            System.out.println("Wrong ID and Password");
                                            continue;
                                    }
                               }
                               catch(Exception e)
                               {
                                   System.out.println(" Please Enter Right password or id");
                                   continue;
                               }

                                    System.out.println("Welcome "+ person.fname +"!");
                                    int choice2=-1;
                                    do
                                    {
                                        
                                    
                                     System.out.println("Select your choices");
                                    System.out.println("1.Make a transaction\n2.Loan Department\n3.View all details\n4.Account Creation\n5.CardDept");
                                   
                                  
                                        int b=sc.nextInt();
                                    switch(b)
                                    {
                                        
                                                case 1:
                                                {
                                                // MAKE A TRANSACTION 
                                                         System.out.println("Enter the type of Transaction to be made");
                                                         System.out.println("1.Deposit\n2.Withdraw\n3.Transfer\n4.Pay Loan Amount");
                                                         int x = sc.nextInt();
                                                         System.out.println("Select Account which account You want to operate with?\n1.Savings Account\n2.Checkings Account");//sc.next();
                                                         int type=sc.nextInt();
                                                         int acc=-1;
                                                         if(type==1&&sav_acc!=null)
                                                             acc=sav_acc.acc_no;
                                                         else if(type==2&&check_acc!=null)
                                                             acc=check_acc.acc_no;
                                                         else
                                                         {
                                                             System.out.println("\nYou Don't Have Such an Account!! Please, Enter a valid Input!\n");
                                                             break;
                                                         }
                                                          //Do the respective actions
                                                         
                                                         switch(x)
                                                         {
                                                             case 1:
                                                                
                                                                int account_number = acc;
                                                                System.out.println("Enter the amount");
                                                                double amount;
                                                                amount = sc.nextDouble();
                                                                System.out.println("amount is "+amount);
                                                                //System.out.println("1.Checking \n2. Savings");
                                                                //int type;
                                                                if(type==1)
                                                                    type=2;
                                                                else
                                                                    type=1;
                                                                initiateDeposit(cust_id,account_number,amount,type);
                                                                 break;

                                                             case 2:
                                                                // System.out.println("Enter the account number to withdraw from");
                                                                 int accNo=acc;
                                                                 //accNo = sc.nextInt();
                                                                System.out.println("Enter the amount");
                                                                double amt;
                                                                amt = sc.nextDouble();
                                                                //System.out.println("1.Checking \n2. Savings"); 
                                                                int t=0;
                                                                if(type==1)
                                                                    t=2;
                                                                else
                                                                    t=1;
                                                               
                                                               // t=sc.nextInt();
                                                                initiateWithdrawal(cust_id,accNo,amt,t);
                                                                 break;


                                                             case 3:
                                                                 System.out.println("Enter the account number to make transactions to");
                                                                int account_number_to;
                                                                 account_number_to = sc.nextInt();
                                                                //System.out.println("Enter the account number to make transactions from");
                                                                int account_number_from=acc;
                                                               // account_number_from = sc.nextInt();
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
                                                             case 4:
                                                                 System.out.println("Select loan which You want to pay: \n1.Educational Loan\n2.Car Loan\n3.Home Loan\n");
                                                                 int loan_type=sc.nextInt();
                                                                 int loan_id=-1;
                                                                 if(loan_type==1&&educationloan!=null)
                                                                 {
                                                                     loan_id=educationloan.loan_id;
                                                                 }
                                                                 else if(loan_type==2&&carloan!=null)
                                                                 {
                                                                     loan_id=carloan.loan_id;
                                                                 }
                                                                 else if(loan_type==3&&homeloan!=null)
                                                                 {
                                                                     loan_id=homeloan.loan_id;
                                                                 }
                                                                 else
                                                                 {
                                                                     System.out.println("You dont have such a Loan!! Please Enter A valid Input Sir!");
                                                                     break;
                                                                 }
                                                                 
                                                                 System.out.println("Enter Amount to pay");
                                                                 int loanamount=sc.nextInt();
                                                                 payloan(loan_id,loanamount);
                                                                 populaterecordsforcustomer(cust_id,c);
                                                                 break;
                                                             
                                                         }


                                                         break;
                                                }// END OF TRANSACTION
                                                    case 2:// START OF LOAN
                                                    System.out.println("Input the type of loan needed ");
                                                    System.out.println("1.Educational Loan\n2.Housing Loan\n3.Car loan");
                                                    int ch=sc.nextInt();
                                                    switch(ch)
                                                    {
                                                        case 1:
                                                            //System.out.println("Enter rate of interest");
                                                            //double rate1=sc.nextDouble();
                                                            if(educationloan!=null)
                                                            {
                                                                System.out.println("You already Have a Educational Loan! According to Bank Policy You cannot have more than one Loan of same type!!");
                                                                break;
                                                            }
                                                            
                                                            double rate1=10;
                                                            System.out.println("Enter the University");sc.nextLine();
                                                            String uni=sc.nextLine();
                                                            System.out.println("Enter the needed amount:");
                                                            double amt = sc.nextDouble();
                                                            assignEducationLoan(cust_id,rate1,uni,amt);
                                                            break;

                                                        case 2:
                                                           // System.out.println("Enter rate of interest");
                                                              if(homeloan!=null)
                                                            {
                                                                System.out.println("You already Have a Home Loan! According to Bank Policy You cannot have more than one Loan of same type!!");
                                                                break;
                                                            }
                                                            double rate2=12;
                                                            System.out.println("Enter the Address");
                                                            String address=sc.next();
                                                            System.out.println("Enter the needed amount:");
                                                            double amnt = sc.nextDouble();
                                                            assignHomeLoan(cust_id,rate2,address,amnt);
                                                            break;

                                                        case 3:
                                                          //  System.out.println("Enter rate of interest");
                                                              if(carloan!=null)
                                                            {
                                                                System.out.println("You already Have a Car Loan! According to Bank Policy You cannot have more than one Loan of same type!!");
                                                                break;
                                                            }
                                                            double rate3=15;
                                                            System.out.println("Enter the Car model");
                                                            String car=sc.next();
                                                            System.out.println("Enter the needed amount:");
                                                            double amount = sc.nextDouble();
                                                            assignCarLoan(cust_id,rate3,car,amount);
                                                            break;
                                                     }
                                                     break;// END OF LOAN

                                                    case 5:// CARD NUMBER // DEPRECATED FUNCTIONALITY AS IT IS A OFFLINE PROCEDURE
                                                    {

                                                            System.out.println("Enter the card no");
                                                            double card_no;
                                                            card_no = sc.nextDouble();
                                                          //  System.out.println("Enter issue date");
                                                            Date date = new Date();
                                                            String issue_date= new SimpleDateFormat("yyyy-MM-dd").format(date);
                                                            
                                                           
                                                            //System.out.println("Enter Expiry date");
                                                            //Date exp_date;
                                                            
                                                            Date expdate=new Date();
                                                            Calendar c = Calendar.getInstance();
                                                            c.setTime(date);
                                                            c.add(Calendar.YEAR, 3);
                                                            expdate = c.getTime();
                                                            String exp_date= new SimpleDateFormat("yyyy-MM-dd").format(expdate);
                                                            System.out.println("Enter CVV no");
                                                            int cvv;
                                                            cvv = sc.nextInt();
                                                            
                                                            String status="approved";
                                                            
                                                           
                                                            System.out.println("Input the type of card needed ");
                                                            System.out.println("1.Debit card\n2.Credit");
                                                            //choice of card
                                                            int c1=sc.nextInt();
                                                                    switch(c1)
                                                                    { 
                                                                        case 1:
                                                                       // assignDebitcard(cust_id,card_no,issue_date,exp_date,cvv);
                                                                        if(dcard==null)
                                                                        assignDebitcard(cust_id,issue_date,exp_date,card_no,cvv);
                                                                        else
                                                                        {
                                                                            System.out.println("Sir, You can have only one debit card!! "
                                                                                    + "Your registered debit card details are\n"+dcard);
                                                                        }
                                                                       // int cid, String issue_date, String exp_date, Double Card_no, int cvv
                                                                        break;

                                                                        case 2:
                                                                            //int cid, String issue_date, String exp_date, Double Card_no, int cvv,Double max_c
                                                                    //    assignCreditcard(cust_id,card_no,issue_date,exp_date,cvv,max_c);
                                                                            if(ccard==null)
                                                                            assignCreditcard(cust_id,issue_date,exp_date,card_no,cvv,1000.0);
                                                                            else
                                                                            {
                                                                                System.out.println("Sir, You can have only one debit card!! \n Your registered credit card details are\n"+ccard);
                                                                            }


                                                                       // int cid, String issue_date, String exp_date, Double Card_no, int cvv,Double max_c;
                                                                        break;
                                                                    }
                                                        }
                                                    break;// CARD END
                                                    case 3: getcustdetails();
                                                    break;
                                                    
                                                    case 4:
                                                                 System.out.println("Select one type:\n1.savings\n2.current");
                                                                 int n=Integer.parseInt(sc.next());
                                                                 if(n==1&&sav_acc==null)
                                                                 {
                                                                     createsavingsacc(cust_id,c);
                                                                 }
                                                                 else if(n==2&&check_acc==null)
                                                                 {
                                                                     createcurrentacc(cust_id,c);
                                                                 }
                                                                 else
                                                                 {
                                                                     System.out.println("Sorry! You already have that type of account! You cannot create another one!");
                                                                 }
                                                                populaterecordsforcustomer(cust_id,c);
                                                                 

                                                    

                                    }
                                    
                                           /*1. Logic for transaction
                                            2. logic for loan approval from the manager if applied
                                            3. logic for card apporval from manager if applied
                                            4. view all details including acc infor, balance, details etc.
                                            */

                                           System.out.println("Want to exit? \n1.exit\n2.Continue with other transcation");
                                           choice2=sc.nextInt();
                                    }while(choice2==2);
                                    System.out.println(" Thank You for Choosing Bank of America!! Happy Banking! ");
                                    System.exit(0);
                    }
                    case 2:
                    {
                                    
                                    System.out.println("SIGN UP DETAILS");
                                    System.out.println("FNAME ");
                                    String fname=sc.next();
                                    System.out.println("LNAME ");
                                    String lname=sc.next();
                                    System.out.println("Enter Date of Birth");
                                    String DOB=sc.next();
                                    System.out.println("Enter Address");sc.nextLine();
                                    String address=sc.nextLine();
                                    
                                    System.out.println("Enter Valid Phone Number");
                                    String phone_number=sc.next();
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
                                        //System.out.println(" Passwords Do not Match!  ");
                                    }

                                    register(fname,lname,DOB,address,phone_number,email,passwordsignup);
                                    break;
                    }
               }
        }
        
    }
    
	
        
    
    
    
    
}

























































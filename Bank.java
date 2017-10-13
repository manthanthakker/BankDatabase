/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
package bank;
import java.util.*;
/**
 *
 * @author facts
 */
public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ONLINE BANKING SYSTEM\n");
        System.out.println("Enter Choice");
        System.out.println("1.Login\n2.Sign-up");
        int a = sc.nextInt();
        switch(a)
                {
            
            case 1:
                System.out.println("Enter the username");
                String user = sc.next();
                System.out.println("Enter the password");
                String pass;
               // You have to do the following:-
               //1. compare the password to the password in the database
               //2. give access if password is correct
                pass = sc.next();
                
                
                System.out.println("Welcome "+ user +"!");
                System.out.println("Select your choices");
                System.out.println("1.Make a transaction\n2.Loan Department\n3.Card Department\n4.View all details");
                int b=sc.nextInt();
        switch(b)
                {
        case 1:
                             System.out.println("Enter the account number to make transactions to");
                             double account_number_to;
                                    account_number_to = sc.nextDouble();
                             System.out.println("Enter the account number to make transactions from");
                             double account_number_from;
                                    account_number_from = sc.nextDouble();
                             System.out.println("Enter the amount");
                             double amount;
                                    amount = sc.nextDouble();
                             System.out.println("Enter the type of Transaction to be made");
                             System.out.println("1.Deposite\n2.Withdraw\n3.Transfer\n4.Pay Loan Amount");
                              //Do the respective actions
                             
        
                             break;
                             
        case 2:
                    System.out.println("Input the type of loan needed ");
        System.out.println("1.Educational Loan\n2.Housing Loan\n3.Car loan");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter rate of interest");
                double rate1=sc.nextDouble();
                System.out.println("Enter the University");
                String uni=sc.next();
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
                System.out.println("Enter your First name ");
                String fname=sc.next();
                System.out.println("Enter your Last name ");
                String lname=sc.next();
                System.out.println("Enter Date of Birth");
                String DOB=sc.next();
                System.out.println("Enter the Address");
                String address=sc.next();
                System.out.println("Enter Valid Phone Number");
                int phone_number=sc.nextInt();
                System.out.println("Enter E-mail");
                String email;
                email = sc.next();
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
    
}

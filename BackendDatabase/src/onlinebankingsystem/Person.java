/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebankingsystem;

/**
 *
 * @author manth
 */
public class Person
{
    int id;
    String fname;
    String lname;
    String date;
    String address;
    String contact;
    String password;
    String email;

    Person(int c_id, String fname, String lname, String dob, String address, String contact_no, String password, String email) {
        this.id = c_id;
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.address = address;
        this.contact = contact;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        //"Person{" + "id=" + id + "\n fname=" + fname + "\n lname=" + lname + "\n date=" + date + "\n address=" + address + "\n contact=" + contact + "\n password=" + password + "\n email=" + email + '}';
        TableBuilder tb = new TableBuilder();
            tb.addRow("-----------", "----------", "-----------","-----------","-----------","-----------","-----------","-----------");
            tb.addRow("cust_id", "fname", "lname","Password","E-mail");
            tb.addRow("-----------", "----------", "-----------","-----------","-----------","-----------","-----------","-----------");
            tb.addRow(""+id, ""+fname, ""+lname,""+password,""+email);
            tb.addRow("-----------", "----------", "-----------","-----------","-----------","-----------","-----------","-----------");
            
 
            return "\n"+tb.toString();
            
    }
    public Person()
    {
        
    }

    
}

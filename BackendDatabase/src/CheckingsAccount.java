
import onlinebankingsystem.Account;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manth
 */
public class CheckingsAccount {
    
}
class CheckingAccount extends Account
{
    int id;
  

    @Override
    public String toString() {
        return "CheckingAccount{" + "id=" + id + "\n acc_id=" + acc_id + "\n acc_no=" + acc_no + "\n balance=" + balance + "\n dos=" + dos + "\n doe=" + doe + "\n status=" + status + '}';
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
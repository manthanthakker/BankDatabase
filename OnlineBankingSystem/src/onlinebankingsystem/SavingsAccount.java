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
class SavingsAccount extends Account
{
    int s_id;
     
   
    @Override
    public String toString() {
        //return "SavingsAccount{" + "s_id=" + s_id + "\n acc_id=" + acc_id + "\n acc_no=" + acc_no + "\n balance=" + balance + "\n dos=" + dos + "\n doe=" + doe + "\n status=" + status + "\n ROI=" + ROI + '}';
        
        TableBuilder tb = new TableBuilder();
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            tb.addRow("account No","Balance","Opened AccountOn","ROI");
            tb.addRow("--------------------", "---------", "----------","----------","----------","----------");
            tb.addRow( ""+acc_no,""+balance,""+doe,""+ROI);
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            
 
            return "\n"+tb.toString();
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
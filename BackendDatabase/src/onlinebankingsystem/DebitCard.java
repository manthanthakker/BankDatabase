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
class DebitCard extends Card
{
    int did;
    
  

    @Override
    public String toString() {
         TableBuilder tb = new TableBuilder();
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            tb.addRow("cardno","issuedate","exp-date","cvv","status");
            tb.addRow("--------------------", "---------", "----------","----------","----------","----------");
            tb.addRow( ""+cardno,""+issuedate,""+expdate,""+cvv,""+status);
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            
 
            return "\n"+tb.toString();
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




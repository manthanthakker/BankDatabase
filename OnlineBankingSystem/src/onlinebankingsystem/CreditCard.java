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
class CreditCard extends Card
{
    
    
    double maxcredit;
   
   

    @Override
    public String toString() {
        // "CreditCard{" + "cid=" + cid + "\n maxcredit=" + maxcredit + "\n cardno=" + cardno + "\n issuedate=" + issuedate + "\n expdate=" + expdate + "\n cvv=" + cvv + "\n status=" + status + '}'; TableBuilder tb = new TableBuilder();
            TableBuilder tb = new TableBuilder();
            tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
            tb.addRow("cardno","maxcredit","issuedate","exp-date","cvv","status");
            tb.addRow("--------------------", "---------", "----------","----------","----------","----------","----------");
            tb.addRow( ""+cardno,""+maxcredit,""+issuedate,""+expdate,""+cvv,""+status);
            tb.addRow("----------", "---------", "----------","----------","----------","----------","----------");
            
 
            return "\n"+tb.toString();
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

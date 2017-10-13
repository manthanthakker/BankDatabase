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
class HomeLoan extends Loan
{
    int loan_id;
    double amount;
    double amount_paid;
    double amount_left;
    double roi;
    String address;
    String status="";

    @Override
    public String toString() {
        //return "HomeLoan{" + "loan_id=" + loan_id + "\n amount=" + amount + "\n amount_paid=" + amount_paid + "\n amount_left=" + amount_left + "\n roi=" + roi + "\n address=" + address + '}';
        TableBuilder tb = new TableBuilder();
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            tb.addRow("loanid","LOAN-Amount","paidamt","amountleft","ROI","Property Address","status(Comments)");
            tb.addRow("--------------------", "---------", "----------","----------","----------","----------");
            tb.addRow( ""+loan_id,""+amount,""+amount_paid,""+amount_left,""+roi,""+address,""+status);
            tb.addRow("----------", "---------", "----------","----------","----------","----------");
            
 
            return "\n"+tb.toString();
    }

    public HomeLoan(int loan_id, double amount, double amount_paid, double amount_left, double roi, String address,String status) {
        this.loan_id = loan_id;
        this.amount = amount;
        this.amount_paid = amount_paid;
        this.amount_left = amount_left;
        this.roi = roi;
        this.address = address;
        this.status=status;
    }

   
}
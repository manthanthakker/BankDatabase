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
public class Card
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
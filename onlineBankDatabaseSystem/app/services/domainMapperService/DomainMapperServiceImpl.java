package services.domainMapperService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import domains.*;

/**
 * Created by Manthan_personal on 10/12/17.
 */
public class DomainMapperServiceImpl implements DomainMapper{

    static private Connection c;
    static int safariid;
    static String name;
    static ArrayList<MaterialProvision> materialsused=new ArrayList();
    static ArrayList<CountryCityVisit> citiesvisited=new ArrayList();

    public void populaterowsmaterials(int sid,Connection c)
    {
        try {
            PreparedStatement st1=c.prepareStatement("Select m.id,m.description,m.weight,p.quantity,p.recommendation from Material m, provision p where p.needs=m.id and p.neededBy=?");
            st1.setInt(1, sid);
            ResultSet rs=st1.executeQuery();
            while(rs.next())
            {
                int materialid=rs.getInt(1);
                String descriptions=rs.getString(2);

                double weights=rs.getDouble(3);
                int quantity=rs.getInt(4);
                String recommendation=rs.getString(5);
                materialsused.add(new MaterialProvision(materialid,descriptions,weights,recommendation));
                //System.out.println(materialsused);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DomainMapperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void populaterowsvisits(int sid,Connection c)
    {
        try
        {
            PreparedStatement st2=c.prepareStatement("Select c.id,c.name,v.dayofvisit,co.name,co.id,v.id from visit v, city c, country co where c.partof=co.id and v.visits=c.id and v.visitedBy=?");
            st2.setInt(1, sid);
            ResultSet rs=st2.executeQuery();
            while(rs.next())
            {
                int cid=rs.getInt(1);
                String cityName=rs.getString(2);
                int dayOfVisit=rs.getInt(3);
                String countryName=rs.getString(4);
                int countryid=rs.getInt(5);
                int visitid=rs.getInt(6);
                // st2.setInt(1, sid);

                CountryCityVisit cv=new CountryCityVisit(cid,cityName,dayOfVisit,countryName,countryid,visitid);
                citiesvisited.add(cv);
                //System.out.println(citiesvisited+"  asdasd");
            }
        }
        catch(Exception e)
        {
           System.out.println("Exception in populaterowsvisits "+e);
        }
        finally {
            if(c!=null){
                try {
                    c.close();
                } catch (SQLException e) {
                    System.out.print("Erro in closing connection");
                }
            }
        }
    }
    /*Develop a method that adds a new visit to the safari. The method has three parameters: the city name, the country name and the day of visit.*/
    public void insertnewvisit(String cityname,String countryname,int dayofvisit) throws SQLException
    {
        try {
            PreparedStatement newrecord = c.prepareStatement("insert into visit(visits,visitedby,dayofvisit) (select c.id,?,? from country co,city c where c.partof=co.id and co.name=? and c.name=?)");
            newrecord.setInt(1, safariid);
            newrecord.setInt(2, dayofvisit);
            newrecord.setString(3, countryname);
            newrecord.setString(4, cityname);
            int k = newrecord.executeUpdate();
            if (k != 0) {
                System.out.println("Inserted");
            } else {
                System.out.println("Not Inserted");
            }
        }
        finally {
        if(c!=null){
            try {
                c.close();
            } catch (SQLException e) {
                System.out.print("Erro in closing connection");
            }
        }
    }

    }

    /*
    Develop a method that modifies the recommendation and quantity of a material for the safari where the material is specified by its description.
    You may assume that no two materials needed by a safari have two different materials with the same description.
    The method has three parameters: the description, the recommendation, and the quantity.
    */

    public void modifyrecords(String description, String recommendation, int quantity) throws SQLException
    {
        try {
            PreparedStatement modify = c.prepareStatement("update provision p set p.recommendation=?, p.quantity=? where exists(select * from safari s, material m where s.id=? and m.description=? and p.neededby=s.id and p.needs=m.id)");
            modify.setString(1, recommendation);
            modify.setInt(2, quantity);
            modify.setInt(3, safariid);
            modify.setString(4, description);
            int k = modify.executeUpdate();
            if (k > 0) {
                System.out.println("Updated");
            } else {
                System.out.println("Not updated");
            }
        }catch (Exception e){
            System.out.println("Exception in modifyrecords "+e);
        }
        finally {
        if(c!=null){
            try {
                c.close();
            } catch (SQLException e) {
                System.out.print("Erro in closing connection");
            }
        }
    }

    }

}

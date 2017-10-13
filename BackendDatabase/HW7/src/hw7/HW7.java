/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manth
 */
public class HW7 {

    public HW7(int safariid,Connection c)
    {
        this.c=c;
        this.safariid=safariid;
        populaterowsmaterials(safariid,c);
        populaterowsvisits(safariid,c);
        System.out.println("All dpone safely");
    }
    
    public static void populaterowsmaterials(int sid,Connection c)
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
            Logger.getLogger(HW7.class.getName()).log(Level.SEVERE, null, ex);
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
            
        }
    }
     /*Develop a method that adds a new visit to the safari. The method has three parameters: the city name, the country name and the day of visit.*/
    public void insertnewvisit(String cityname,String countryname,int dayofvisit) throws SQLException
    {
        PreparedStatement newrecord=c.prepareStatement("insert into visit(visits,visitedby,dayofvisit) (select c.id,?,? from country co,city c where c.partof=co.id and co.name=? and c.name=?)");
        newrecord.setInt(1,safariid);
        newrecord.setInt(2,dayofvisit);
        newrecord.setString(3,countryname);
        newrecord.setString(4,cityname);
        int k=newrecord.executeUpdate();
        if(k!=0)
        {
            System.out.println("Inserted");
        }
        else
        {
            System.out.println("Not Inserted");
        }
        
    }
    
    /*
    Develop a method that modifies the recommendation and quantity of a material for the safari where the material is specified by its description. 
    You may assume that no two materials needed by a safari have two different materials with the same description. 
    The method has three parameters: the description, the recommendation, and the quantity.
    */
    
    public void modifyrecords(String description, String recommendation, int quantity) throws SQLException
    {
        PreparedStatement modify=c.prepareStatement("update provision p set p.recommendation=?, p.quantity=? where exists(select * from safari s, material m where s.id=? and m.description=? and p.neededby=s.id and p.needs=m.id)");
        modify.setString(1,recommendation);
        modify.setInt(2,quantity);
        modify.setInt(3,safariid);
        modify.setString(4,description);
        int k=modify.executeUpdate();
        if(k>0)
        {
            System.out.println("Updated");
        }
        else
        {
            System.out.println("Not updated");
        }
          
    }
    
    static private Connection c;
    static int safariid;
    static String name;
    static ArrayList<MaterialProvision> materialsused=new ArrayList();
    static ArrayList<CountryCityVisit> citiesvisited=new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try
        {
        String url="jdbc:mysql://localhost/ass1";
        String pass="6994";
        String driver="com.mysql.jdbc.Driver";
        String user="root";
       
       Connection c=createConnection(driver,url,user,pass);
       HW7 h=new HW7(1,c);
        
       for(MaterialProvision p: materialsused)
       {
           System.out.println(p.toString());
       }
   
       for(CountryCityVisit cv1:citiesvisited)
       {
           System.out.println(cv1.toString());
       }
       //h.insertnewvisit("HARYANA","INDIA",3);
       h.modifyrecords("ex", "required", 200);
      //select * from provision p, material m, safari s where p.neededby=s.id and s.id=1 and p.needs=m.id;// for query 2
        }
        catch(Exception e)
        {
             System.out.println("Spitting exception\n"+ e);
        }
        finally
        {
            connection.close();
        }
       
    }
    
 public static Connection createConnection(String driver, String url, String user, String password) {
	Connection connection=null;
        try {
            
	    Class.forName(driver);
	    connection = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
	    System.err.println("Unable to connect to the database due to " + e);
	}
      return connection;  
     }    
}

class CountryCityVisit
{
    private int cid;
    private String cityName;
    private int dayOfVisit;
    private String countryName;
    private int countryid;
    private int visitid;

    public CountryCityVisit(int cid, String cityName, int dayOfVisit, String countryName, int countryid, int visitid) {
        this.cid = cid;
        this.cityName = cityName;
        this.dayOfVisit = dayOfVisit;
        this.countryName = countryName;
        this.countryid = countryid;
        this.visitid = visitid;
    }

    @Override
    public String toString() {
        return "CountryCityVisit{" + "cid=" + cid + ", cityName=" + cityName + ", dayOfVisit=" + dayOfVisit + ", countryName=" + countryName + ", countryid=" + countryid + ", visitid=" + visitid + '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getDayOfVisit() {
        return dayOfVisit;
    }

    public void setDayOfVisit(int dayOfVisit) {
        this.dayOfVisit = dayOfVisit;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public int getVisitid() {
        return visitid;
    }

    public void setVisitid(int visitid) {
        this.visitid = visitid;
    }
}

class MaterialProvision
{

    public MaterialProvision(int materialid, String recommendation, double weights, String descriptions) {
        this.materialid = materialid;
        this.recommendation = recommendation;
        this.weights = weights;
        this.descriptions = descriptions;
    }
    // m.id,m.description,m.weight,p.quantity,p.recommendation
    private int materialid;
    private String recommendation;
    private double weights;
    private String descriptions;

    @Override
    public String toString() {
        return "MaterialProvision{" + "materialid=" + materialid + ", recommendation=" + recommendation + ", weights=" + weights + ", descriptions=" + descriptions + '}';
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
   

/*
 class Material
    {
        private int id;
        private String description;
        private double weight;

        @Override
        public String toString() {
            return "Material{" + "id=" + id + ", description=" + description + ", weight=" + weight + '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
    class Provision
    {
        private int needs;
        private int neededby;
        private int quantity;
        private String recommendations;

        @Override
        public String toString() {
            return "Provision{" + "needs=" + needs + ", neededby=" + neededby + ", quantity=" + quantity + ", recommendations=" + recommendations + '}';
        }

        public int getNeeds() {
            return needs;
        }

        public void setNeeds(int needs) {
            this.needs = needs;
        }

        public int getNeededby() {
            return neededby;
        }

        public void setNeededby(int neededby) {
            this.neededby = neededby;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getRecommendations() {
            return recommendations;
        }

        public void setRecommendations(String recommendations) {
            this.recommendations = recommendations;
        }
    }
    class Country 
    {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "Country{" + "id=" + id + ", name=" + name + '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    class City
    {
        private int id;
        private String name;
        private int partof;

        @Override
        public String toString() {
            return "City{" + "id=" + id + ", name=" + name + ", partof=" + partof + '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPartof() {
            return partof;
        }

        public void setPartof(int partof) {
            this.partof = partof;
        }
    }
    class Visit
    {
        private int id;
        private int visits;
        private int visitedBy;
        private int dayOfVisit;

        @Override
        public String toString() {
            return "Visit{" + "id=" + id + ", visits=" + visits + ", visitedBy=" + visitedBy + ", dayOfVisit=" + dayOfVisit + '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVisits() {
            return visits;
        }

        public void setVisits(int visits) {
            this.visits = visits;
        }

        public int getVisitedBy() {
            return visitedBy;
        }

        public void setVisitedBy(int visitedBy) {
            this.visitedBy = visitedBy;
        }

        public int getDayOfVisit() {
            return dayOfVisit;
        }

        public void setDayOfVisit(int dayOfVisit) {
            this.dayOfVisit = dayOfVisit;
        }
    }
*/
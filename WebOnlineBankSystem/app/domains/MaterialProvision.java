package domains;

/**
 * Created by Manthan_personal on 10/12/17.
 */
public class MaterialProvision {

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


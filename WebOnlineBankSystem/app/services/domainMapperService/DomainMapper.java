package services.domainMapperService;

import java.sql.Connection;

/**
 * Created by Manthan_personal on 10/12/17.
 */
public interface DomainMapper {
    public void populaterowsmaterials(int sid,Connection c);
}

import ru.brandanalyst.core.db.provider.BrandProvider;
import ru.brandanalyst.core.model.Brand;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import ru.brandanalyst.indexer.Indexer;
import ru.brandanalyst.searcher.Searcher;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dima
 * Date: 10/12/11
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {    //this is worked test for lucene index and search
    public static void main(String[] args) {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/BAdirty?useUnicode=true&amp;characterEncoding=utf8");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setValidationQuery("select 1");
        SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(ds);
        BrandProvider dataStore = new BrandProvider(jdbcTemplate);

        dataStore.cleanDataStore();
        Brand b1 = new Brand(4,"Gazprom","Gazprom is russian gasoline gaint","www.gazprom.ru",0);
        dataStore.writeBrandToDataStore(b1);
        b1 = new Brand(1,"Microsoft","Microsoft makes bad software","www.microsoft.com",0);
        dataStore.writeBrandToDataStore(b1);
        b1 = new Brand(3,"Apple","Apple makes software too and i-production. На русском","www.apple.com",0);
        dataStore.writeBrandToDataStore(b1);
        b1 = new Brand(2,"Google","Google is better than other search machines","www.google.com",0);
        dataStore.writeBrandToDataStore(b1);

        System.out.println(dataStore.getBrandById(3).getDescription());

        Indexer ind = new Indexer();
        ind.setDirectory("index/");
        ind.setJdbcTemplate(jdbcTemplate);
        try{
            ind.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Searcher searcher = new Searcher();
        searcher.setIndexDir("index/");
        try{
            searcher.getReadyForSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            List<Brand> lst = searcher.searchByDescription("на русском");
            for(Brand b: lst) {
                System.out.println(b.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

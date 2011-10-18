package ru.brandanalyst.core.db.provider;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 09.10.11
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.brandanalyst.core.model.Brand;
import ru.brandanalyst.core.db.mapper.BrandMapper;

import java.util.List;

public class BrandProvider {
    private SimpleJdbcTemplate jdbcTemplate; //
    private BrandMapper brandMapper;

    public BrandProvider(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        brandMapper = new BrandMapper();
    }

    public void cleanDataStore() {
        jdbcTemplate.update("TRUNCATE TABLE Brand");
    }

    public void writeBrandToDataStore(Brand brand) {
        try{
            jdbcTemplate.update("INSERT INTO Brand (Id, Name, Description, Website, branchId) VALUES(?,?,?,?,?);", brand.getId(), brand.getName(),
                brand.getDescription(),brand.getWebsite(),brand.getBranchId());
        } catch (Exception e) {e.printStackTrace();}
    }

    public void writeListOfBrandsToDataStore(List<Brand> brands) {
        for (Brand b : brands) {
            writeBrandToDataStore(b);
        }
    }

    public Brand getBrandByName(String name) {
        List<Brand> list = jdbcTemplate.getJdbcOperations().query("SELECT * FROM Brand WHERE Name = ?", new Object[]{name}, brandMapper);
        return list.get(0);
    }

    public Brand getBrandById(long brand_id) {
        List<Brand> list = jdbcTemplate.getJdbcOperations().query("SELECT * FROM Brand WHERE Id = " + Long.toString(brand_id) , brandMapper);
        return list.get(0);
    }

    public List<Brand> getAllBrands() throws NullPointerException {
        List<Brand> list = jdbcTemplate.getJdbcOperations().query("SELECT * FROM Brand", brandMapper);
        return list;
    }
}

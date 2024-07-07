package com.example.store_cms.repository;

import com.example.store_cms.model.registry.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query(nativeQuery = true, value = "select e.* from store_app_schema.employee e\n" +
            "join store_app_schema.purchase p ON p.electro_id = e.id\n" +
            "where e.position_id = :positionTypeId and p.purchase_date >= (CURRENT_DATE - INTERVAL '1 year')\n" +
            "group by e.id order by count(e.id) desc")
    List<Employee> findEmployeesWithTotalCountProductsSold(Long positionTypeId);

    @Query(nativeQuery = true, value = "select e.* from store_app_schema.employee e\n" +
            "join store_app_schema.purchase p ON p.electro_id = e.id\n" +
            "join store_app_schema.electro_item ei ON ei.id = p.electro_id\n" +
            "where e.position_id = :positionTypeId and p.purchase_date >= (CURRENT_DATE - INTERVAL '1 year')\n" +
            "group by e.id order by sum(ei.price) desc")
    List<Employee> findEmployeesWithTotalPriceProductsSold(Long positionTypeId);

    @Query(nativeQuery = true,
    value = "SELECT e.*\n" +
            "FROM store_app_schema.employee e\n" +
            "JOIN store_app_schema.purchase p ON p.electro_id = e.id\n" +
            "JOIN store_app_schema.electro_item ei ON ei.id = p.electro_id\n" +
            "WHERE e.position_id = 2 \n" +
            "  AND ei.id IN (18, 19, 20, 21, 22)\n" +
            "GROUP BY e.id\n" +
            "ORDER BY COUNT(ei.id) DESC\n" +
            "LIMIT 1;")
    Employee getTopBySmartWatch();
}


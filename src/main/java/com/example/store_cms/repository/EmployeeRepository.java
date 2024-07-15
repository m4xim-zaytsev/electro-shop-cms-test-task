package com.example.store_cms.repository;

import com.example.store_cms.model.registry.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query("SELECT e, SUM(p.electroItem.price), COUNT(p.id) " +
            "FROM Employee e " +
            "JOIN e.purchases p " +
            "WHERE p.purchaseDate BETWEEN :startDate AND :endDate " +
            "GROUP BY e " +
            "ORDER BY COUNT(p.id) DESC")
    List<Object[]> findBestEmployees(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT e, SUM(p.electroItem.price * p.electroItem.count) AS totalSales, SUM(p.electroItem.count) AS itemsSold " +
            "FROM Employee e " +
            "JOIN e.purchases p " +
            "WHERE p.purchaseDate BETWEEN :startDate AND :endDate " +
            "GROUP BY e " +
            "ORDER BY totalSales DESC")
    List<Object[]> findBestEmployeesSales(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT e, SUM(p.electroItem.price * p.electroItem.count) AS totalSales, SUM(p.electroItem.count) AS itemsSold " +
            "FROM Employee e " +
            "JOIN e.purchases p " +
            "WHERE e.positionType.name = 'Младший продавец-консультант' " +
            "AND p.electroItem.electroType.name = 'Умные часы' " +
            "GROUP BY e " +
            "ORDER BY totalSales DESC")
    List<Object[]> findBestJuniorSalespersonForSmartWatches();
}


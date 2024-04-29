package com.example.oms.studentproject.Repository;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Projection.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Long> {

                                               //DB column, Parameter passed
    @Query(value = "select * from student where name like %:name%",nativeQuery = true)
    Page findByName(String name, Pageable pageable);

    @Query(value = "select * from student where location like %:location%",nativeQuery = true)
    Page findByLocation(Pageable pageable, String location);

    @Query(value = "select * from student where CONCAT(name,'',last_name)like %:userName%",nativeQuery = true)
    Page searchByFirstNameAndLastName(String userName, Pageable pageable);


    @Query(value = "select name as name,email as email,password as password,location as location from student",nativeQuery= true)
    Page<Projection> findByProjection(Pageable pageable);
}

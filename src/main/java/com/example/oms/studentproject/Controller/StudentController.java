package com.example.oms.studentproject.Controller;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Model.request.StudentRequest;
import com.example.oms.studentproject.Model.response.CustomEntityResponse;
import com.example.oms.studentproject.Model.response.EntityResponse;
import com.example.oms.studentproject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/SaveOrUpdate")
    public ResponseEntity<?> SaveOrUpdate(@RequestBody StudentRequest studentRequest) {
        try {

            return new ResponseEntity(new EntityResponse(studentService.SaveOrUpdate(studentRequest), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);


        }

    }

    @GetMapping("/getAllRecords")
    public ResponseEntity<?> getAllRecords(@RequestParam(required = false)String name) {
        try {



            return new ResponseEntity(new EntityResponse(studentService.getAllRecords(), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);

        }


    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Long studentId) {
        try {

            return new ResponseEntity(new EntityResponse(studentService.findById(studentId), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);

        }


    }


    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Long studentId) {

        try {

            return new ResponseEntity(new EntityResponse(studentService.deleteById(studentId), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);


        }


    }


    @PutMapping("/statusChange")
    public ResponseEntity<?> statusChange(@RequestParam Long studentId)
    {
        try{

         return new ResponseEntity(new EntityResponse(studentService.statusChange(studentId),0),HttpStatus.OK);


        }catch(Exception e)
        {

         return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

        }

    }

    @GetMapping("/searchByName")
    public ResponseEntity<?> searchByName(@RequestParam(required = false,defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false,defaultValue = "5") Integer pageSize,
                                          @RequestParam(name = "name",required = false) String name,
                                          @RequestParam(name = "sortBy",required = false) String sortBy


                                          )
    {
       try{
//         creates pageable object
           Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));




           return new ResponseEntity(new EntityResponse(studentService.searchByName(name,pageable),0),HttpStatus.OK);

       }catch(Exception e)
       {

         return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

       }

    }

    @GetMapping("/searchByLocation")
    public ResponseEntity<?> searchByLocation(@RequestParam(required = false,defaultValue = "0")Integer pageNo,
                                              @RequestParam(required = false,defaultValue = "5")Integer pageSize,
                                              @RequestParam(required = false,name="location") String location)
    {
       try{

           Pageable pageable = PageRequest.of(pageNo,pageSize);

           return new ResponseEntity(new EntityResponse(studentService. searchByLocation(pageable,location),0),HttpStatus.OK);


       }catch (Exception e)
       {

           return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

       }

    }



@GetMapping("/searchByFirstNameAndLastName")
    public ResponseEntity<?> searchByFirstNameAndLastName(@RequestParam(defaultValue = "0",required = false) Integer pageNo,
                                                          @RequestParam(defaultValue = "0",required = false)Integer pageSize,
                                                          @RequestParam(name = "userName",required = false)String userName)
{
  try{

      Pageable pageable = PageRequest.of(pageNo,pageSize);

      return new ResponseEntity(new EntityResponse(studentService.searchByFirstNameAndLastName(pageable,userName),0),HttpStatus.OK);

  }catch(Exception e)
  {

      return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);


  }



}


  @GetMapping("projection")
  public ResponseEntity<?> projection (@RequestParam(defaultValue = "0",required = false) Integer pageNo,
                           @RequestParam(defaultValue = "3",required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

       try{

           return new ResponseEntity(new EntityResponse(studentService.getByProjection(pageable),0),HttpStatus.OK);


       }catch (Exception e)
       {

           return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

       }


    }


    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestParam(value = "file",required = false)MultipartFile[] file,String to,String[] cc,
                                      String subject,String body)
    {
        try{

            return new ResponseEntity(new EntityResponse(studentService.sendEmail(file,to,cc,subject,body),0),HttpStatus.OK);

        }catch (Exception e)
        {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

        }




    }

}



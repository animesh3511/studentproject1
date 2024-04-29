package com.example.oms.studentproject.Serviceimpl;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Model.request.StudentRequest;
import com.example.oms.studentproject.Projection.Projection;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Object SaveOrUpdate(StudentRequest studentRequest) {

        if (studentRepository.existsById(studentRequest.getStudentId())) {
            Student student = studentRepository.findById(studentRequest.getStudentId()).get();
            student.setStudentId(studentRequest.getStudentId());
            student.setContact(studentRequest.getContact());
            student.setEmail(studentRequest.getEmail());
            student.setLocation(studentRequest.getLocation());
            student.setName(studentRequest.getName());
            student.setPassword(studentRequest.getPassword());
            studentRepository.save(student);
            return "student updated";
        } else {
            Student student = new Student();
            student.setStudentId(studentRequest.getStudentId());
            student.setPassword(studentRequest.getPassword());
            student.setName(studentRequest.getName());
            student.setLastName(studentRequest.getLastName());
            student.setEmail(studentRequest.getEmail());
            student.setLocation(studentRequest.getLocation());
            student.setContact(studentRequest.getContact());
            student.setIsActive(true);
            student.setIsDeleted(false);

            studentRepository.save(student);
            

            return "student saved";


        }


    }

    @Override
    public Object getAllRecords() {
        return studentRepository.findAll();
    }




    @Override
    public Object findById(Long studentId) throws Exception {

        if (studentRepository.existsById(studentId))
        {

            Student student = studentRepository.findById(studentId).get();
            return student;

        }
        else
        {

           throw new Exception("student not found");


        }



    }

    @Override
    public Object deleteById(Long studentId) {

        if(studentRepository.existsById(studentId))
        {
           //Student student = studentRepository.findById(studentId).get();
            studentRepository.deleteById(studentId);
            return "student deleted succesfully";
        }
        else
        {

         return "student not found";


        }


    }

    @Override
    public Object statusChange(Long studentId) throws Exception {

        if(studentRepository.existsById(studentId))
        {

            Student student = studentRepository.findById(studentId).get();

            if (student.getIsActive())
            {
                student.setIsActive(false);
                studentRepository.save(student);
                return "student is not active";
            }
            else
            {
                student.setIsActive(true);
                return "student is active";

            }



        }
        else
        {

            throw new Exception("student not found");


        }




    }

    @Override
    public Object searchByName(String name, Pageable pageable) {

    if(name!=null && !name.isEmpty())
    {

        return studentRepository.findByName(name,pageable);

    }
    else
    {

        return studentRepository.findAll(pageable);

    }




    }

    @Override
    public Object searchByLocation(Pageable pageable, String location) {

        if(location != null && !location.isEmpty())
        {

            return studentRepository.findByLocation(pageable,location);

        }
        else
        {

          return studentRepository.findAll(pageable);
        }


    }

    @Override
    public Object searchByFirstNameAndLastName(Pageable pageable, String userName) {

        if(userName!=null && !userName.isEmpty())
        {

            return studentRepository.searchByFirstNameAndLastName(userName,pageable);

        }
        else
        {

            return studentRepository.findAll(pageable);

        }



    }

    @Override
    public Object getByProjection(Pageable pageable) {


         //Page<Projection> byProjection = studentRepository.findByProjection(pageable);
        //return byProjection;

         Page<Projection> byProjection= studentRepository.findByProjection(pageable);
         return byProjection;

    }



    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Object sendEmail(MultipartFile[] file, String to, String[] cc, String subject, String body) throws Exception {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for(int i=0;i<file.length;i++)
            {

             mimeMessageHelper.addAttachment(

                 file[i].getOriginalFilename(),
                 new ByteArrayResource(file[i].getBytes())

             );

            }
            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";

        }catch(Exception e)
        {

            throw new Exception(e);

        }

    }


}

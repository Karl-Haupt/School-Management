package za.ac.cput.controller.student;
/*
 This is the Student Address controller
 Mponeng Ratego
 216178991
*/


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.api.student.StudentAddressAPI;
import za.ac.cput.service.student.StudentAddressImp;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.service.student.StudentAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/studentAddress/")
@Slf4j
public class StudentAddressController  {

    private final StudentAddressService studentAddressService;

   @Autowired
   public StudentAddressController(StudentAddressImp studentAddressService) {
      this.studentAddressService= studentAddressService;
   }

   @PostMapping("save")
   public ResponseEntity<StudentAddress> save(@Valid @RequestBody StudentAddress studentAddress) {
      log.info("Request to save Student Address: {}", studentAddress);
      StudentAddress save = studentAddressService.save(studentAddress);
      return ResponseEntity.ok(save);
   }



   @GetMapping("read/{studentId}")
   public ResponseEntity<StudentAddress> read(@PathVariable String studentId) {
      StudentAddress read = studentAddressService.read(studentId).orElseThrow(() ->
              new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Address Not found"));
      return ResponseEntity.ok(read);
   }

   @GetMapping("findAll")
   public ResponseEntity<List<StudentAddress>> findAll() {
      List<StudentAddress> findAllList = this.studentAddressService.findAll();
      return ResponseEntity.ok(findAllList);
   }

   @DeleteMapping("delete")
   public ResponseEntity<Void> delete(StudentAddress studentAddress) {
       log.info("Request to delete Student Address: {}", studentAddress);
      this.studentAddressService.delete(studentAddress);
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("delete/{studentId}")
   public ResponseEntity<Void> deleteById(@PathVariable String studentId) {
      Optional<StudentAddress> readStudentAddress = this.studentAddressService.read(studentId);
       
      if(readStudentAddress.isPresent()) {

         this.studentAddressService.deleteById(studentId);
      }
      return ResponseEntity.noContent().build();
   }
}

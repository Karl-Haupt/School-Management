

package za.ac.cput.controller.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.student.Student;
import za.ac.cput.service.student.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/student")
@Slf4j
public class StudentController
{
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }
    @PostMapping("save")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student)
    {
        Student save = studentService.save(student);
        return ResponseEntity.ok(save);
    }
    @GetMapping("read/{studentId}")
    public ResponseEntity<Student> readById(@PathVariable String studentId)
    {
        Student read = studentService.read(studentId).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(read);
    }
    @GetMapping ("readAll")
    public ResponseEntity<List<Student>> readAll()
    {
        List<Student> allStudents = this.studentService.readAll();
        return ResponseEntity.ok(allStudents);
    }

    @DeleteMapping("deleteById/{studentId}")
    public ResponseEntity<Void> deleteById(@PathVariable String studentId)
    {
        Optional<Student> readStudent = this.studentService.read(studentId);
        if(readStudent.isPresent())
        {
            studentService.deleteById(readStudent.get().getStudentId());
        }
        return ResponseEntity.noContent().build();
    }


}

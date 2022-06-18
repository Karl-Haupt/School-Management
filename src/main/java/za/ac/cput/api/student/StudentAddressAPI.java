package za.ac.cput.api.student;

/*
 This is the Student Address API
 Mponeng Ratego
 216178991
*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.service.location.ICityService;
import za.ac.cput.service.student.StudentAddressService;
import za.ac.cput.service.student.StudentService;


@Component
public class StudentAddressAPI {
    private final StudentAddressService studentAddressService;
    private final StudentService studentService;
    private final ICityService iCityService;

    private City city;


    @Autowired public StudentAddressAPI(StudentAddressService studentAddressService, StudentService studentService,
                                        ICityService iCityService)
    {
        this.studentAddressService = studentAddressService;
        this.studentService = studentService;
        this.iCityService = iCityService;
    }

    public StudentAddress save(StudentAddress studentAddress){

       this.studentService.read(studentAddress.getStudentId())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Id not Found"));
        this.iCityService.read(city.getId())
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
                return this.studentAddressService.save(studentAddress);

    }

}

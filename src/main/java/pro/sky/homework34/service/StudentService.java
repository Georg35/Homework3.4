package pro.sky.homework34.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.homework34.model.Faculty;
import pro.sky.homework34.model.Student;
import pro.sky.homework34.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student find(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        logger.info("Was invoked method for find student");
        if (student == null) {
            logger.error("There is no student with id = {}", id);
        }

        return student;
    }


    public Student changeStudentInfo(Student student) {
        logger.info("Was invoked method for change student info");
        return studentRepository.save(student);
    }

    public  void removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        studentRepository.deleteById(id);
        logger.info("Was invoked method for remove student");
        if (student == null) {
            logger.error("There is no student with id = {}", id);
        }

    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for getAllStudents");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByAge(Integer age) {
        logger.info("Was invoked method for findStudentByAge");
        Collection<Student> student= studentRepository.findStudentByAge(age);
        if (student == null) {
            logger.error("There is no student with age = {}", age);
        }

        return student;
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetweenIgnoreCase(min, max);
    }
    public Faculty findFacultyByStudent(long id){
        logger.info("Was invoked method for findFacultyByStudent");
        return studentRepository.getReferenceById(id).getFaculty();
    }

    public List<Integer> countNumberOfAllStudents (){
        return studentRepository.countNumberOfAllStudents();
    }

    public List<Double> countAvgAge (){
        return studentRepository.countAverageAge();
    }
    public List<Student> countLastFiveStudents(){
        return studentRepository.getLastFiveStudents();
    }
}

package pro.sky.homework34.service;

import org.springframework.stereotype.Service;
import pro.sky.homework34.model.Faculty;
import pro.sky.homework34.model.Student;
import pro.sky.homework34.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student find(Long id) {
        return studentRepository.findById(id).get();
    }


    public Student changeStudentInfo(Student student) {
        return studentRepository.save(student);
    }

    public  void removeStudent(Long id) {
        studentRepository.deleteById(id);

    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByAge(Integer age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetweenIgnoreCase(min, max);
    }
    public Faculty findFacultyByStudent(long id){
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

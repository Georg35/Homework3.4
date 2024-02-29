package pro.sky.homework34.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.homework34.model.Faculty;
import pro.sky.homework34.model.Student;
import pro.sky.homework34.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void removeStudent(Long id) {
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
        Collection<Student> student = studentRepository.findStudentByAge(age);
        if (student == null) {
            logger.error("There is no student with age = {}", age);
        }

        return student;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetweenIgnoreCase(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        logger.info("Was invoked method for findFacultyByStudent");
        return studentRepository.getReferenceById(id).getFaculty();
    }

    public List<Integer> countNumberOfAllStudents() {
        return studentRepository.countNumberOfAllStudents();
    }

    public List<Double> countAvgAge() {
        return studentRepository.countAverageAge();
    }

    public List<Student> countLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }

    public List<String> getStudentsStartingWithA() {
        List<Student> students = studentRepository.findAll();

        List<String> namesStartingWithA = students.stream()
                .map(Student::getName)
                .filter(name -> name != null && name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        return namesStartingWithA;


    }

    public Double getAverageAge() {
        List<Student> students = studentRepository.findAll();

        double averageAge = students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
        return averageAge;

    }

    public Integer returnNumber() {

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        return sum;

    }


    public void printStudentNamesParallel() {
        Thread t1 = new Thread(() -> {
            System.out.println(studentRepository.findAll().get(0));
            System.out.println(studentRepository.findAll().get(1));
        });

        Thread t2 = new Thread(() -> {
            System.out.println(studentRepository.findAll().get(2));
            System.out.println(studentRepository.findAll().get(3));
        });

        Thread t3 = new Thread(() -> {
            System.out.println(studentRepository.findAll().get(4));
            System.out.println(studentRepository.findAll().get(5));
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public synchronized void printName(int i) {
        System.out.println(studentRepository.findAll().get(i).getName());
    }

    public synchronized void synchronizedPrint() {
            printName(0);
            printName(1);
        Thread thread1 = new Thread(() -> {
            printName(2);
        });

        Thread thread2 = new Thread(() -> {
            printName(3);
        });

        Thread thread3 = new Thread(() -> {
            printName(4);
        });

        Thread thread4 = new Thread(() -> {
       printName(5);
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


package pro.sky.homework34.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.homework34.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAge (Integer age);
    Collection<Student> findByAgeBetweenIgnoreCase(int min, int max);
}

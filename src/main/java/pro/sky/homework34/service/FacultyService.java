package pro.sky.homework34.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.homework34.model.Faculty;
import pro.sky.homework34.model.Student;
import pro.sky.homework34.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }


    public Faculty changeFacultyInfo(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findFacultyByColorOrName(String color, String name) {
        return facultyRepository.findFacultyByColorOrNameContainsIgnoreCase(color, name);
    }
    public Collection<Faculty>findFacultyByName (String name){
        return facultyRepository.findFacultyByNameIgnoreCase(name);

    }
    public Collection<Faculty>findFacultyByColor (String color){
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }
    public Collection<Faculty>findAllFaculties(){
        return facultyRepository.findAll();
    }

    public Collection<Student> findStudentByFaculty(long id) {
        return facultyRepository.getReferenceById(id).getStudents();
    }
}

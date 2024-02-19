package pro.sky.homework34.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.homework34.model.Faculty;
import pro.sky.homework34.model.Student;
import pro.sky.homework34.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Autowired
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        Faculty faculty= facultyRepository.findById(id).orElse(null);
        logger.info("Was invoked method for findFaculty");
        if (faculty == null) {
            logger.error("There is no faculty with id = {}", id);
        }

        return faculty;
    }


    public Faculty changeFacultyInfo(Faculty faculty) {
        logger.info("Was invoked method for change changeFacultyInfo");
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        facultyRepository.deleteById(id);
        logger.info("Was invoked method for removeFaculty");
        if (faculty == null) {
            logger.error("There is no faculty with id = {}", id);

        }
    }

    public Collection<Faculty> findFacultyByColorOrName(String color, String name) {
        logger.info("Was invoked method for findFacultyByColorOrName");
        return facultyRepository.findFacultyByColorOrNameContainsIgnoreCase(color, name);
    }
    public Collection<Faculty>findFacultyByName (String name) {
        logger.info("Was invoked method for findFacultyByName");
        return facultyRepository.findFacultyByNameIgnoreCase(name);

    }
    public Collection<Faculty>findFacultyByColor (String color) {
        logger.info("Was invoked method for findFacultyByColor");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }
    public Collection<Faculty>findAllFaculties(){
        logger.info("Was invoked method for findAllFaculties");
        Collection<Faculty> faculties = facultyRepository.findAll();
        if (faculties == null) {
            logger.error("There is no any faculties ");
        }
        if (faculties.isEmpty()) {
            logger.warn("No faculties found, add a new faculty");
        }
        return faculties;
    }

    public Collection<Student> findStudentByFaculty(long id) {
        logger.info("Was invoked method for findStudentByFaculty");
        return facultyRepository.getReferenceById(id).getStudents();
    }
}

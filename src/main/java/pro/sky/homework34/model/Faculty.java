package pro.sky.homework34.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="faculties")

public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private  String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Collection<Student> getStudents() {
        return students;
    }

}

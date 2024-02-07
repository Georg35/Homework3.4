package pro.sky.homework34.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.homework34.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findAvatarById (Long studentId);
}

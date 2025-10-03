package by.gago.exporter.repository;

import by.gago.exporter.entity.FilesDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepo extends JpaRepository<FilesDAO, Long> {
    FilesDAO findByFilePath(String absolutePath);
    List<FilesDAO> findAllByFundNumberAndInventoryNumber(String fundNumber, String inventoryNumber);
}

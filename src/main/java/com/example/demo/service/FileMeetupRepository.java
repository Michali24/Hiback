    package com.example.demo.service;
    import com.example.demo.modle.FileMeetup;
    import org.springframework.data.jpa.repository.JpaRepository;
    import java.util.List;

    public interface FileMeetupRepository extends JpaRepository<FileMeetup,Long> {
        List<FileMeetup> findByGalleryCategory_Id(Long categoryId);
    }

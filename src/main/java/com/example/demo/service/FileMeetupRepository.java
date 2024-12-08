    package com.example.demo.service;

    import com.example.demo.modle.FileMeetup;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;

    public interface FileMeetupRepository extends JpaRepository<FileMeetup,Long> {

    }

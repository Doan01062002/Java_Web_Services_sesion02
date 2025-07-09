package com.example.session02.repository;

import com.example.session02.model.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    // Tìm suất chiếu theo ID phim
    List<Showtime> findByMovieId(Long movieId);

    // Tìm suất chiếu theo ID phòng chiếu
    List<Showtime> findByScreenRoomId(Long screenRoomId);

    // Tìm suất chiếu theo ngày
    @Query("SELECT s FROM Showtime s WHERE DATE(s.startTime) = :showDate")
    List<Showtime> findByShowDate(@Param("showDate") LocalDate showDate);

    // Tìm suất chiếu với các bộ lọc kết hợp
    @Query("SELECT s FROM Showtime s WHERE " +
            "(:movieId IS NULL OR s.movie.id = :movieId) AND " +
            "(:screenRoomId IS NULL OR s.screenRoom.id = :screenRoomId) AND " +
            "(:showDate IS NULL OR DATE(s.startTime) = :showDate)")
    List<Showtime> findByFilters(@Param("movieId") Long movieId,
                                 @Param("screenRoomId") Long screenRoomId,
                                 @Param("showDate") LocalDate showDate);
}
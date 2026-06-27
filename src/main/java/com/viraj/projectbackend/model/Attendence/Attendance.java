package com.viraj.projectbackend.model.Attendence;



import com.viraj.projectbackend.model.Subject.Subject;
import com.viraj.projectbackend.model.User.User;
import jakarta.persistence.*;
import lombok.*;




import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "total_lectures")
    private Integer totalLectures = 0;

    @Column(name = "attended_lectures")
    private Integer attendedLectures = 0;
}

package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="daily_tasks")
@Data
public class DailyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long taskId;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "done_date")
    private Date doneDate;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "is_deleted")
    private int isDeleted;

    public Long getId(){
        return taskId;
    }

}

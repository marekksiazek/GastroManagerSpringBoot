package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "name")
    private String taskTitle;

    @Column(name = "assigned_worker")
    private Long assignedWorker;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private int isDeleted;

}

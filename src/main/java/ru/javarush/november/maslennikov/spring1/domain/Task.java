package ru.javarush.november.maslennikov.spring1.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "todo",name = "task")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated
    private Status status;
}

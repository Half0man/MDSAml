package com.admsoft.mdsaml;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="clients")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "users_id", nullable = false)

    private User user;

    @OneToMany(mappedBy = "client")
    private List<TransactionFile>transactionFilesSet;


}

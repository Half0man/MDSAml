package com.admsoft.mdsaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.ObjectOutputStream;

@Getter
@Setter
@Entity
@Table(name="bayesData")
public class BayesData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column
    File naiveBeysModel;
}

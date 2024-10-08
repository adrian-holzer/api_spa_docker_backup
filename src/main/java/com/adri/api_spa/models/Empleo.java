package com.adri.api_spa.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleo")
public class Empleo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleo")
    private Long idEmpleo;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @Lob
    @NotBlank(message = "La descripcion es obligatoria")
    @Column(name = "descripcion",length = 30000)
    private String descripcion;



    @Enumerated(EnumType.STRING)
    private EstadoEmpleo estado=EstadoEmpleo.ACTIVO;



    @OneToMany(mappedBy = "empleo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Postulacion> postulaciones; // Relación con Postulacion

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



}

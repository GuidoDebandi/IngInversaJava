package entities;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "domicilio")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class Domicilio {

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private int numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_localidad")
    private Localidad localidad;


}

package entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class Autor extends Base{

    @Column(name="nombre")
    private String nombre;

    @Column(name ="apellido")
    private String apellido;

    @Column(name ="biografia")
    private String biografia;

}
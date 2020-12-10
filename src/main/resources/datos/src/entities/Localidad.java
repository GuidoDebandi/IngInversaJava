package entities;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "localidad")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Audited
public class Localidad {

    @Audited
    @Column(name = "denominacion")
    private String denominacion;

}

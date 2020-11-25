package entities;

import lombok.Builder;

import javax.persistence.*;


import lombok.Data;
import org.hibernate.envers.Audited;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "persona")
@Builder
@Data
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    public final int asd;

    @Column(name ="nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "persona_libro",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros = new ArrayList<Libro>();

    public void caminar(){

    }
    public String contar(int x){
        return "asd";
    }

    public abstract void cantar(){

    }
}


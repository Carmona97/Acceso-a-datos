package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "public", catalog = "empleados")
public class EntidadEmpleados {
    private int empno;
    private String nombre;
    private String puesto;
    private Integer depno;
    private EntidadDepartamentos departamentosByDepno;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "empno", nullable = false)
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 10)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "puesto", nullable = true, length = 15)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Basic
    @Column(name = "depno", nullable = true)
    public Integer getDepno() {
        return depno;
    }

    public void setDepno(Integer depno) {
        this.depno = depno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadEmpleados that = (EntidadEmpleados) o;
        return empno == that.empno && Objects.equals(nombre, that.nombre) && Objects.equals(puesto, that.puesto) && Objects.equals(depno, that.depno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, nombre, puesto, depno);
    }

    @ManyToOne
    @JoinColumn(name = "depno", referencedColumnName = "depno", insertable = false, updatable = false)
    public EntidadDepartamentos getDepartamentosByDepno() {
        return departamentosByDepno;
    }

    public void setDepartamentosByDepno(EntidadDepartamentos departamentosByDepno) {
        this.departamentosByDepno = departamentosByDepno;
    }
}

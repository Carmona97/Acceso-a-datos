package org.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados", schema = "public", catalog = "empleados")
public class EntidadEmpleados {
    private int empno;
    private String nombre;
    private String puesto;
    private Integer depno;
    private EntidadDepartamentos departamentosByDepno;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "empno")
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "puesto")
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Basic
    @Column(name = "depno", insertable=false, updatable=false)
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

        if (empno != that.empno) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;
        if (depno != null ? !depno.equals(that.depno) : that.depno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empno;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        result = 31 * result + (depno != null ? depno.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "depno", referencedColumnName = "depno")
    public EntidadDepartamentos getDepartamentosByDepno() {
        return departamentosByDepno;
    }

    public void setDepartamentosByDepno(EntidadDepartamentos departamentosByDepno) {
        this.departamentosByDepno = departamentosByDepno;
    }
}

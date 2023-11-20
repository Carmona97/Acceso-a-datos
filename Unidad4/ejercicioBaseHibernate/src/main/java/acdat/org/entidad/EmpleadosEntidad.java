package acdat.org.entidad;

public class EmpleadosEntidad {
    private int empno;
    private String nombre;
    private String puesto;
    private Integer depno;
    private DepartamentosEntidad departamentosByDepno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

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

        EmpleadosEntidad that = (EmpleadosEntidad) o;

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

    public DepartamentosEntidad getDepartamentosByDepno() {
        return departamentosByDepno;
    }

    public void setDepartamentosByDepno(DepartamentosEntidad departamentosByDepno) {
        this.departamentosByDepno = departamentosByDepno;
    }
}

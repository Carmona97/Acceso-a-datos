package entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Usuarios",schema = "public", catalog = "ventaDeCoches")
public class Usuario {
        @Id
        @Column(name = "idUsuario", nullable = false)
        private long idUsuario;

        @Basic
        @Column(name = "Nombre", nullable = false, length = 48)
        private String nombreUsuario;
        @Basic
        @Column(name = "FechaCumpleanyos", nullable = false)
        private Date cumpleanos;

        public long getIdUsuario() {
                return idUsuario;
        }

        public void setIdUsuario(long idUsuario) {
                this.idUsuario = idUsuario;
        }

        public String getNombreUsuario() {
                return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
                this.nombreUsuario = nombreUsuario;
        }

        public Date getCumpleanos() {
                return cumpleanos;
        }

        public void setCumpleanos(Date cumpleanos) {
                this.cumpleanos = cumpleanos;
        }
}

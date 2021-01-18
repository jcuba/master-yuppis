package com.yuppis.vuelos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_tickets")
public class Tickets implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itineraryID;

    @NotBlank(message = "Ingrese la Fecha de salida !")
    private String fechaSalida;

    @NotBlank(message = "Ingrese la Fecha de llegada !")
    private String fechaLlegada;

    @NotBlank(message = "Ingrese la hora de salida!")
    private String horaSalida;

    @NotBlank(message = "Ingrese la hora de llegada!")
    private String horaLlegada;

    @NotBlank(message = "Ingrese la Ciudad origen !")
    private String ciudadOrigen;

    @NotBlank(message = "Ingrese la Ciudad destino !")
    private String ciudadDestino;

    @NotBlank(message = "Ingrese nombre de pasajero !")
    private String nomPasajero;

    @NotBlank(message = "Ingrese edad de pasajero !")
    @Size(min=1, max = 2, message = "El tamaño maximo es de 2" )
    @Column(length = 2, nullable = false)
    private String edadPasajero;

    private boolean bodegaEquipaje;

    @NotBlank(message = "Ingrese el precio del boleto !")
    @Size(min=4, max = 7, message = "El tamaño maximo es de 4" )
    @Column(length = 7, nullable = false)
    private String precio;
}


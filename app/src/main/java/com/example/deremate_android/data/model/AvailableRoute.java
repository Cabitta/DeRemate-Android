package com.example.deremate_android.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRoute {

    private Long id; // TODO: ver si eliminar

    private String state; // TODO: ver si eliminar

    private String address;

    private Date initDateTime = new Date();

    private Date endDateTime;

    private String delivery; // TODO: String cambiar por CLASE

    private String client; // TODO: String cambiar por CLASE

    private String paquete; // TODO: String cambiar por CLASE
}

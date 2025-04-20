package com.example.deremate_android.data.repository;

import android.util.Log;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RouteRepositoryInMemory implements RouteRepository{
    private final List<AvailableRoute> availableRoutes;
    @Inject
    public  RouteRepositoryInMemory(){
        availableRoutes = new ArrayList<>();
        inizializeRoutes();
    }

    private void inizializeRoutes() {
        availableRoutes.add(new AvailableRoute(1L, "direccion 1111", "cliente1", "paquete1"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 2222", "cliente2", "paquete2"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 3333", "cliente3", "paquete3"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 4444", "cliente4", "paquete4"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 5555", "cliente5", "paquete5"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 6666", "cliente6", "paquete6"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 7777", "cliente7", "paquete7"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 8888", "cliente8", "paquete8"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 9999", "cliente9", "paquete9"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 1000", "cliente10", "paquete10"));
        availableRoutes.add(new AvailableRoute(1L, "direccion 1100", "cliente11", "paquete11"));
    }

    @Override
    public List<AvailableRoute> getAllAvailableRoutes() {
        return new ArrayList<>(availableRoutes);
    }
}

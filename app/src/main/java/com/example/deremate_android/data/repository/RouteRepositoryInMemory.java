package com.example.deremate_android.data.repository;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class RouteRepositoryInMemory implements RouteRepository{

    private final List<AvailableRoute>

    rutas = new ArrayList<>();


    //private void initializeAvai

    @Override
    public List<AvailableRoute> getAllRoutes() {
        return Collections.emptyList();
    }

    @Override
    public List<AvailableRoute> getAllAvailableByState(String state) {
        return Collections.emptyList();
    }
}

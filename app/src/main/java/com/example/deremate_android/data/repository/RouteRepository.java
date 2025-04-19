package com.example.deremate_android.data.repository;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.List;

public interface RouteRepository {
    List<AvailableRoute> getAllRoutes();

    List<AvailableRoute> getAllAvailableByState(String state);
}

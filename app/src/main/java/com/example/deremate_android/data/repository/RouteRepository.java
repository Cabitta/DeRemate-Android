package com.example.deremate_android.data.repository;

import com.example.deremate_android.data.model.Route;

import java.util.List;

public interface RouteRepository {
    List<Route> getAllRoutes();

    List<Route> getAllAvailableByState(String state);
}

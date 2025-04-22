package com.example.deremate_android.data.repository;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.ArrayList;
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
        availableRoutes.add(new AvailableRoute("Avenida 123", "Juan", "Pérez", "james.moore.wayne@example-pet-store.com", "A2", 1, 3));
    }

    @Override
    public List<AvailableRoute> getAllAvailableRoutes() {
        return new ArrayList<>(availableRoutes);
    }
}

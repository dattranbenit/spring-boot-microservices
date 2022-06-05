package clientregister.service;

import clientregister.entity.ClientDetail;

import java.util.List;

public interface ClientDetailService {
    void add(ClientDetail clientDetail);

    List<ClientDetail> getAll();
}

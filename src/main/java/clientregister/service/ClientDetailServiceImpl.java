package clientregister.service;

import clientregister.dao.ClientDetailRepository;
import clientregister.entity.ClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClientDetailServiceImpl implements ClientDetailService {

    @Autowired
    ClientDetailRepository clientDetailRepository;

    @Override
    public void add(ClientDetail clientDetail) {
        clientDetail.setClientSecret(new BCryptPasswordEncoder().encode(clientDetail.getClientSecret()));
        clientDetailRepository.save(clientDetail);
    }

    @Override
    public List<ClientDetail> getAll() {
        return clientDetailRepository.findAll();
    }
}

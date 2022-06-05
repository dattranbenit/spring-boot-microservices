package clientregister.controller;

import clientregister.entity.ClientDetail;
import clientregister.service.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@PermitAll
@RestController
public class ClientController {

    @Autowired
    ClientDetailService clientDetailService;

    @PostMapping("/clientdetail")
    public ClientDetail addClient(@RequestBody ClientDetail clientDetail) {
        clientDetailService.add(clientDetail);
        return  clientDetail;
    }

    @GetMapping("/clientdetails")
    public List<ClientDetail> getAll() {
        return clientDetailService.getAll();
    }
}

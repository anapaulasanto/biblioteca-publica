package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.RentalRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repository;

    public RentalDTO save(RentalDTO rentalDTO) {
        var userOptional = repository.findById(rentalDTO.getUserId());

        if (userOptional.isEmpty()) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.user.notfound", "Usuário não encontrado!");
        }

        // Verificar o conteúdo do Optional para garantir que temos um User
        User user = userOptional.get().getUser();
        System.out.println("Usuário encontrado: " + user); // Depuração para confirmar o tipo de user

        var rentalEntity = MapperUtil.parseObject(rentalDTO, Rental.class);

        // Aqui você deve garantir que user seja do tipo User
        rentalEntity.setUser(user);  // Passando o User para o método setUser() da Rental

        var savedRental = repository.save(rentalEntity);
        return MapperUtil.parseObject(savedRental, RentalDTO.class);
    }



    public List<RentalDTO> findAll(){
        var listRentals = repository.findAll();
        return MapperUtil.parseListObjects(listRentals, RentalDTO.class);
    }

    public Rental findById(Long id){
        var rentalEntity = repository.findById(id);
        if(rentalEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.findbyid.notfound",
                    "Aluguel não encontrado!");
        }

        return repository.findById(id).get();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<RentalDTO> findByUserId(Long userId) {
        var rentals = repository.findByUserId(userId);
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }
}

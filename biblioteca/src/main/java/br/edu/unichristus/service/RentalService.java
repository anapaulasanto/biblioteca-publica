package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.model.Rental;
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

    public RentalDTO save(RentalDTO rentalDTO){
        var rentalEntity = MapperUtil.parseObject(rentalDTO, Rental.class);
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

}

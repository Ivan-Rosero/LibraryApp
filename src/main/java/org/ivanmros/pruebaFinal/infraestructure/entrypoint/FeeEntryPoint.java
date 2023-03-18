package org.ivanmros.pruebaFinal.infraestructure.entrypoint;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.fee.dto.FeeDTO;
import org.ivanmros.pruebaFinal.domain.usecase.FeeUseCase;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.FeeDBO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class FeeEntryPoint {

    private final FeeUseCase feeUseCase;

    @GetMapping
    public ResponseEntity<?> getAllFees(){
        List<FeeDTO> fees = feeUseCase.findAllFees();
        try{
            return new ResponseEntity<>(fees, HttpStatus.OK);
        }catch (NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findFeeByUserId(@PathVariable(name = "userId") String userId){
        try{
            List <FeeDTO> listFeesByUser = feeUseCase.findFeeByUserId(userId);
            return new ResponseEntity<>(listFeesByUser, HttpStatus.OK);
        }catch (NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
//        return ResponseEntity.status(HttpStatus.FOUND).body(
//                feeUseCase.findFeeByUserId(userId)
//                        .stream()
//                        .map(totalFees -> new FeeDTO())
//                        .toList()
//        );
    }



}

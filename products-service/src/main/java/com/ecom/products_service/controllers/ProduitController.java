package com.ecom.products_service.controllers;

import com.ecom.products_service.dto.ProduitGetDto;
import com.ecom.products_service.dto.ProduitPostDto;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.modele.User;
import com.ecom.products_service.repository.ProduitRepository;
import com.ecom.products_service.service.Impl.ProduitServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class ProduitController {
   private final ProduitServiceImpl produitService;
    private final UserServiceImpl userService;
    private final UserTransformer userTransformer;




    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Produit>> getSellerProducts(@PathVariable Long sellerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long idSeller = userService.findByUsername(userDetails.getUsername()).id();
        List<Produit> products = produitService.getProductsBySeller(sellerId);
        return ResponseEntity.ok(products);
    }
    @PostMapping("/")
    public ResponseEntity<ProduitPostDto> save(@RequestBody ProduitPostDto dto){
        log.info("Adding new Product");
        return new ResponseEntity<>(produitService.save(dto), HttpStatus.CREATED);
    }

    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<ProduitPostDto> saveProductBySeller(@RequestBody ProduitPostDto dto, @PathVariable Long sellerId){
        try {
            UserDto sellerDto = userService.findById(sellerId);
            if (sellerDto == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            ProduitPostDto createdProduct = produitService.saveProductBySeller(dto, sellerDto);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ProduitGetDto> update(@RequestBody ProduitGetDto dto, @PathVariable Long id){
        log.info("Received request to update product with ID: {}", id);
        return ResponseEntity.ok(produitService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}*/

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {
    private final UserOpenFeing userOpenFeing;
    private final ProduitServiceImpl produitService;
        private final ProduitRepository produitRepository;
        private UserOpenFeing UserOpenFeing;
 public ProduitController(UserOpenFeing userOpenFeing, ProduitServiceImpl produitService, ProduitRepository produitRepository) {
     this.userOpenFeing = userOpenFeing;
     this.produitService = produitService;
     this.produitRepository = produitRepository;
 }
    @GetMapping("/")
    public ResponseEntity<List<ProduitGetDto>> findAll(){
        return ResponseEntity.ok(produitService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProduitGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produitService.findById(id));
    }
        @GetMapping("/feing/produits")
        public List<Produit> allProduits(){

            List<Produit> Produits = produitRepository.findAll();
            List<Long> Id= new ArrayList<>();

            List<User>  users = UserOpenFeing.findAll();


            List<Produit>  com = new ArrayList<>();


            for (Produit p: Produits) {
                for (User c : users)
                    if (c.getId() == p.getId()) {
                        p.setSeller(c);
                        break;
                    }
                for (Produit pr : Produits)
                    if (pr.getId() == p.getId()) {
                        pr.setId(p.getId());
                    }
                com.add(p);
            }
            return com;
        }

        @GetMapping("/feing/id/{id}")
        public Produit produitById(@PathVariable Long id){

            Produit cm = produitRepository.findById(id).get();
            User cl= UserOpenFeing.findById(cm.getId());
            cm.setSeller(cl);

            Optional<Produit> p = produitRepository.findById(cm.getId());
            cm.setId(p.get().getId());

            return cm;
        }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitPostDto> save(@RequestBody ProduitPostDto dto){
        return new ResponseEntity<>(produitService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitGetDto> update(@RequestBody ProduitGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(produitService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produitRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package com.ecom.products_service.service.Impl;

import com.ecom.products_service.dto.FournisseurGetDto;
import com.ecom.products_service.dto.StockGetDto;
import com.ecom.products_service.dto.StockPostDto;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.entity.Stock;
import com.ecom.products_service.enums.StatutStock;
import com.ecom.products_service.repository.StockRepository;
import com.ecom.products_service.service.facade.StockService;
import com.ecom.products_service.transformer.StockTransformer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private final StockRepository stockDao;
    private final StockTransformer stockTransformer;
    @Override
    public StockGetDto findById(Long id) {
        log.info("Fetching stock by ID: {}", id);
        return stockDao.findById(id)
                .map(stockTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("Stock not found with ID: {}", id);
                    return new RuntimeException("Unable to find a Stock with the given Id : "+id);
                });
    }

    @Override
    public List<StockGetDto> findAll() {
        log.info("Fetching all stocks");
        List<Stock> stocks=stockDao.findAll();
        if(stocks.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return stockTransformer.toDto(stocks);
    }


    public StockPostDto save(StockPostDto dto) {

        log.info("Creating new stock with Quantte: {}", dto.Quantite());

        if(stockDao.findByProduits_Id(Long.valueOf(dto.Quantite())).isPresent())
        {
            log.warn("Attempted to create a duplicate stock with Quantite: {}", dto.Quantite());
            throw new RuntimeException("This stock name already exists");
        }
        try {
            Stock stock = stockTransformer.toEntityPost(dto);
            stock.setStatut(StatutStock.ENVOYE);
            log.info("Successfully created stock with Quantte: {}", dto.Quantite());
            return stockTransformer.toDtoPost(stockDao.save(stock));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating stock with quantite: {}", dto.Quantite(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the stock."+ ex);
        }
    }

    @Override
    public StockGetDto save(StockGetDto dto) {

        log.info("Creating new stock with quantite: {}", dto.Quantite());

        if(stockDao.findById(Long.valueOf(dto.Quantite())).isPresent())
        {
            log.warn("Attempted to create a duplicate stock with Quantite: {}", dto.Quantite());
            throw new RuntimeException("This stock Quantite already exists");
        }
        try {
            Stock stock = stockTransformer.toEntity(dto);
            stock.setStatut(StatutStock.ENVOYE);
            log.info("Successfully created stock with Quantite: {}", dto.Quantite());
            return stockTransformer.toDto(stockDao.save(stock));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating stock with Quantite: {}", dto.Quantite(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the stock."+ ex);
        }
    }

    @Override
    public StockGetDto update(StockGetDto dto, Long id) {
        id = dto.id();
        StockGetDto existingStockDto = findById(id);
        Stock existingStock = stockTransformer.toEntity(dto);
        existingStock.setId(dto.id());
        existingStock.setQuantite(dto.Quantite());
        existingStock.setStatut(dto.statutStock());
        existingStock.setProduits(dto.produits());


        log.info("Successfully updated stock with ID: {}", dto.id());
        return stockTransformer.toDto(stockDao.save(existingStock));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting stock with ID: {}", id);
        StockGetDto foundStock = findById(id);
        stockDao.deleteById(foundStock.id());

        log.info("Successfully deleted stock with ID: {}", id);
    }
}
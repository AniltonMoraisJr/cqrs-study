package br.com.beautique.mssync.services.impl;

import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.repositories.BeautyProcedureRepository;
import br.com.beautique.mssync.services.BeautyProcedureService;
import br.com.beautique.mssync.utils.SyncLogger;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

  private final BeautyProcedureRepository beautyProcedureRepository;

  public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository) {
    this.beautyProcedureRepository = beautyProcedureRepository;
  }

  @Override
  public void saveBeautyProcedure(BeautyProcedureDto beautyProcedure) {
    try{
      SyncLogger.info("Saving beautyProcedure: {}", beautyProcedure.toString());
      beautyProcedureRepository.save(beautyProcedure);
    } catch (Exception e) {
      SyncLogger.error("Error while saving beautyProcedure: {}", e.getMessage());
      SyncLogger.trace(Arrays.toString(e.getStackTrace()));
    }
  }
}

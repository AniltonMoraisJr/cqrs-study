package br.com.msbeautique.api.controllers;

import br.com.msbeautique.api.dtos.BeautyProceduresDto;
import br.com.msbeautique.api.services.BeautyProceduresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beauty-procedures")
public class BeautyProceduresController {

  private final BeautyProceduresService beautyProceduresService;

  public BeautyProceduresController(BeautyProceduresService beautyProceduresService) {
    this.beautyProceduresService = beautyProceduresService;
  }

  @PostMapping("/")
  public ResponseEntity<BeautyProceduresDto> create(@RequestBody BeautyProceduresDto request) {
    return ResponseEntity.ok(beautyProceduresService.create(request));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BeautyProceduresDto> update(@PathVariable long id, @RequestBody BeautyProceduresDto request) {
    return ResponseEntity.ok(beautyProceduresService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    beautyProceduresService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

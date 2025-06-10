package br.com.msbeautique.api.controllers;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.services.AppointmentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

  private final AppointmentsService appointmentsService;

  public AppointmentsController(AppointmentsService appointmentsService) {
    this.appointmentsService = appointmentsService;
  }

  @PostMapping("/")
  public ResponseEntity<AppointmentsDto> create(@RequestBody AppointmentsDto request) {
    return ResponseEntity.ok(appointmentsService.create(request));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<AppointmentsDto> create(@PathVariable long id, @RequestBody AppointmentsDto request) {
    return ResponseEntity.ok(appointmentsService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    appointmentsService.delete(id);
    return ResponseEntity.ok().build();
  }
}

package br.com.msbeautique.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class AppointmentsEntity extends BaseEntity{

  @Column(nullable = false)
  private LocalDateTime dateTime;

  @Column(nullable = false)
  private boolean appointmentOpen;

    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private CustomerEntity customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "beauty_procedure_id")
  private BeautyProceduresEntity beautyProcedure;

}

package br.com.msbeautique.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@Table(name = "customers")
public class CustomerEntity extends BaseEntity{
  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 100)
  private String phone;

  @JsonIgnore
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<AppointmentsEntity> appointments;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerEntity that = (CustomerEntity) o;
    return Objects.equals(name, that.name) && Objects.equals(email, that.email)
        && Objects.equals(phone, that.phone) && Objects.equals(appointments,
        that.appointments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, phone, appointments);
  }
}

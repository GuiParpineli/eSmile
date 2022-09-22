package com.esmile.appEsmile.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String lastname;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

  //  @OneToOne
  //  @JoinColumn(name = "id_appuser")
  //  private AppUser user;

}

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
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "id_appuser")
    private AppUser user;

    public Patient(@NonNull String name, @NonNull String lastname, @NonNull String cpf, Address address, AppUser user) {
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
        this.user = user;
    }
}

package com.esmile.appEsmile.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String lastname;
    @Column(nullable = false, length = 8)
    private String cro;

    @OneToOne
    @JoinColumn(name = "id_appuser")
    private AppUser user;

}

    package com.example.SpringAppV3.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Collection;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;
        @OneToMany(mappedBy = "role")
        private Collection<User> users;

        @ManyToMany
        @JoinTable(
                name = "roles_privileges",
                joinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "privilege_id", referencedColumnName = "id"))
        private Collection<Privilege> privileges;
    }
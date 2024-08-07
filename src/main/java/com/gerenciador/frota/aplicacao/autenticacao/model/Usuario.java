package com.gerenciador.frota.aplicacao.autenticacao.model;

import com.gerenciador.frota.aplicacao.gerenciador.model.Filial;
import com.gerenciador.frota.aplicacao.gerenciador.model.Organizacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO", schema = "SC_AUTENTICACAO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO", nullable = false)
    private String nome;

    @Email
    @Column(name = "EMAIL_USUARIO", nullable = false)
    private String email;

    @Column(name = "LOGIN_USUARIO")
    private String login;

    @Column(name = "SENHA_USUARIO")
    private String password;

    @Column(name = "CARGO_USUARIO")
    private  String cargo;

    @Column(name = "STATUS_USUARIO")
    private boolean status;

    @Column(name = "ROLE_USUARIO")
    private UserRole role = UserRole.ADMIN;

    @ManyToOne
    private Organizacao organizacao;

    @ManyToOne
    private Filial filial;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

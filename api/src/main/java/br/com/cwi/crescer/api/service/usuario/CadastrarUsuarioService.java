package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.domain.AuthProvider;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.CadastroUsuarioMapper;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.validator.CadastroUsuarioRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {
    private static final String URL_PERFIL_DEFAULT = "https://media.discordapp.net/attachments/438500690491342861/787154328322310224/imagem_logo.png";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioMapper cadastroUsuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CadastroUsuarioRequestValidator validator;

    public void register(CadastroUsuarioRequest cadastroUsuarioRequest) {
        validator.accept(cadastroUsuarioRequest);
        Usuario usuario = cadastroUsuarioMapper.apply(cadastroUsuarioRequest);
        usuario.setProvider(AuthProvider.LOCAL);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setFotoPerfil(URL_PERFIL_DEFAULT);
        usuarioRepository.save(usuario);
    }
}

package co.samtel.usuario.service.impl;

import co.samtel.usuario.dao.UsuarioDao;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.utils.exception.ApplicationException;
import co.samtel.usuario.utils.mapper.UsuarioMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static co.samtel.usuario.constant.Constant.ERROR_SERVICIO;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.deleteById;

@ApplicationScoped
public class UsuarioServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    //Inyección de dependencias
    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    UsuarioDao usuarioDao;

    @Inject
    Usuario usuario;

    @Transactional
    public UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia crearUsuarioImpl");
        UsuarioTypeResponse usuarioTypeResponses;
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuarioDao.persist(usuario);
            usuarioTypeResponses = usuarioMapper.usuarioEntityToTypeResponse(usuario);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al crearUsuarioImpl " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
        LOG.info("Finaliza crearUsuarioImpl");
        return usuarioTypeResponses;
    }

    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser) {
        LOG.info("Inicia listarUsuarioImpl");
        try {
            Usuario usuario = usuarioDao.findById(idtblUser.longValue());
            UsuarioTypeResponse response = usuarioMapper.usuarioEntityToTypeResponse(usuario);
            LOG.info("Finaliza listarUsuarioImpl");
            return Collections.singletonList(response);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al listarUsuarioImpl");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public void eliminarUsuario(Integer idtblUser){
        LOG.info("Inicia eliminarUsuarioImpl");
        try{
            usuarioDao.deleteById(idtblUser.longValue());
        }catch(ApplicationException e){
            LOG.error("Se presento un error al eliminarUsuarioImpl");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
        LOG.info("Finaliza eliminarUsuarioImpl");
    }

    @Transactional
    public List<UsuarioTypeResponse> actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia actualizarUsuarioImpl");
        UsuarioTypeResponse usuarioTypeResponses = null;
        try {
            usuario.setName(usuarioTypeInput.getName());
            usuario.setLastname(usuarioTypeInput.getLastname());

            usuarioTypeResponses = usuarioMapper.usuarioEntityToTypeResponse(usuario);
            LOG.info("Finaliza actualizarUsuarioImpl");

            return Collections.singletonList(usuarioTypeResponses);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al actualizarUsuarioImpl");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

}
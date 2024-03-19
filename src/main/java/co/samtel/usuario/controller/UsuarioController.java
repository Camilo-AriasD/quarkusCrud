package co.samtel.usuario.controller;

import co.samtel.usuario.constant.Constant;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.contract.V1UsuarioApi;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.impl.UsuarioServiceImpl;
import co.samtel.usuario.utils.exception.ApplicationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsuarioController implements V1UsuarioApi {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Inject
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public Response crearUsuario(UsuarioTypeInput usuarioTypeInput){
        LOG.info("Inicia crearUsuarioController");
        UsuarioTypeResponse usuarioType = null;
        try{
            usuarioType = usuarioServiceImpl.crearUsuario(usuarioTypeInput);
        }catch(ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + " crearUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza crearUsuarioController");
        return Response.status(Response.Status.CREATED).entity(usuarioType).build();
    }

    @Override
    public Response listarUsuario(Integer idtblUser) {
        LOG.info("Inicia listarUsuarioController");
        Usuario usuarioType = null;
        try{
            usuarioType = (Usuario) usuarioServiceImpl.listarUsuario(idtblUser);
        }catch(ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + " listarUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza listarUsuarioController");
        return Response.status(Response.Status.OK).entity(usuarioType).build();
    }

    @Override
    public void eliminarUsuario(Integer idtblUser){
        usuarioServiceImpl.eliminarUsuario(idtblUser);
    }

    @Override
    public Response actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput){
        return (Response) usuarioServiceImpl.actualizarUsuario(idtblUser, usuarioTypeInput);
    }

}
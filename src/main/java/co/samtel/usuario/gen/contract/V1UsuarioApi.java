package co.samtel.usuario.gen.contract;

import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;




import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


@Path("/v1/es")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2024-03-19T11:29:14.507341600-05:00[America/Bogota]")
public interface V1UsuarioApi {

    @PUT
    @Path("/updateUsuario/{idtbl_user}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response actualizarUsuario(@PathParam("idtbl_user") @Valid UsuarioTypeInput usuarioTypeInput);

    @POST
    @Path("/crearUsuario")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response crearUsuario(@Valid UsuarioTypeInput usuarioTypeInput);

    @DELETE
    @Path("/deleteUsuario/{idtbl_user}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response eliminarUsuario(@PathParam("idtbl_user") @Valid UsuarioTypeInput usuarioTypeInput);

    @GET
    @Path("/listUsuario/{idtbl_user}")
    @Produces({ "application/json" })
    Response listarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser);
}

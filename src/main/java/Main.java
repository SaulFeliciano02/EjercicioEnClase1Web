import spark.ModelAndView;
import spark.Session;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        port(5050);

        staticFiles.location("/publico/");

        get("/prueba/", ((request, response) -> {
            return "hello";
        }));

        get("/crearCookie/:nombreCookie/:valor", (request, response)->{
            //creando cookie en para un minuto
            response.cookie("/", request.params("nombreCookie"), request.params("valor"), 3600, false); //incluyendo el path del cookie.

            return "Cookie creado con exito...";
        });

        /*before("/rutas/*",(request, response) -> {
            Persona person=request.session(true).attribute("persona");
            if(person==null){
                //parada del request, enviando un codigo.
                //return renderFreemarker(null, "/formularioejemplo.ftl");
            }
        });*/

        get("/formulario/", (request, response) -> new FreeMarkerEngine().render(new ModelAndView(null, "formularioejemplo.ftl")));

        get("/autenticar/:persona/:password", (request, response)->{
            //
            Session session=request.session(true);

            //
            Persona person= null;//FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));
            if(request.params("persona").equalsIgnoreCase("marco") && request.params("password").equalsIgnoreCase("123")){
                //Buscar el usuario en la base de datos..
                person = new Persona("marco", "123");
            }else{
                halt(401,"Credenciales no validas...");
            }

            session.attribute("marco", person);
            //redireccionado a la otra URL.
            response.redirect("/zonaadmin/?param1=adasdasd");

            return "";
        });
    }

    public static String renderFreemarker(Map<String, Object> model, String templatePath) {
        return new FreeMarkerEngine().render(new ModelAndView(model, templatePath));
    }
}

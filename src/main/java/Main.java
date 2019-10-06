import spark.ModelAndView;
import spark.Session;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //port(5050);
        ArrayList<Persona> misPersonas = new ArrayList<>();
        staticFiles.location("/publico/");

        get("/prueba/", ((request, response) -> {
            return "hello";
        }));

        get("/formulario/", (request, response) -> {
            return renderFreemarker(null, "formularioejemplo.ftl");//ew FreeMarkerEngine().render(new ModelAndView(null, "formularioejemplo.ftl"))
        });

        get("/mensajeBienvenida/", (request, response) -> {
            System.out.println("Whatsaaaa");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("persona", misPersonas.get(0));
            return renderFreemarker(attributes, "mensajebienvenida.ftl");
        });

        post("/autenticar/", (request, response)->{
            Persona person= null;
            if(request.queryParams("persona").equalsIgnoreCase("marco") && request.queryParams("password").equalsIgnoreCase("123")){
                //Buscar el usuario en la base de datos..
                Session session=request.session(true);
                person = new Persona("marco", "123");
                session.attribute("persona", person);
                misPersonas.add(person);

            }else{
                halt(401,"Credenciales no validas...");
            }


            //redireccionado a la otra URL.
            response.redirect("/mensajeBienvenida/");

            return "";
        });

        //Aplicando filtros
        new Filtros().filtros();
    }

    public static String renderFreemarker(Map<String, Object> model, String templatePath) {
        return new FreeMarkerEngine().render(new ModelAndView(model, templatePath));
    }


}

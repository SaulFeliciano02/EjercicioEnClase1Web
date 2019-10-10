import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import static spark.Spark.*;

public class Filtros {

    public void filtros(){
        before((request, response) -> {
            Persona persona=request.session(true).attribute("persona");
            try{
                System.out.println(persona.getNombre());
            }catch(NullPointerException e){

            }

            if(persona==null && !request.pathInfo().equalsIgnoreCase("/formulario/")
            && !request.pathInfo().equalsIgnoreCase("/autenticar/")){
                //parada del request, enviando un codigo.
                response.redirect("/formulario/");
            }
            else{
                //
            }
        });

        before("/formulario/", (request, response) -> {
            Persona persona=request.session(true).attribute("persona");
            if(persona != null){
                response.redirect("/mensajeBienvenida/");
            }
        });
    }

}

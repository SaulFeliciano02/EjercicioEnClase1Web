public class Persona {
    private String nombre;
    private String password;

    public Persona(){

    }

    public Persona(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getPassword(){
        return this.password;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

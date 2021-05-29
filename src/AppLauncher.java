import tools.DB_Con;

public class AppLauncher {

    public static void main(String [] app){
        DB_Con con = new DB_Con();
        con.initCon("192.168.0.103",3306,"Jervx","helloworld","OLFUQC");
        new App().main(app);
    }

}

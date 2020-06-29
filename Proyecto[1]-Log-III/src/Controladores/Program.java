package Controladores;

/**
 * @author JuanZea - JUAN DAVID ZEA ACEVEDO
 */
public class Program {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        // Inicialización
        Configuracion conf = new Configuracion();
        conf.plantilla("inicio");
        do {
            conf.plantilla("principal");
            int ans = conf.escuchar(4);
            switch (ans) {
                default: {
                    continue;
                }
                case 0: {
                    break;
                }
                case 1: {
                    // Crear matriz
                    conf.crear();
                    continue;
                }
                case 2: {
                    // Ver matrices
                    conf.ver();
                    continue;
                }
                case 3: {
                    // Configuración
                    conf.operar();
                    continue;
                }
                case 4: {
                    // Configuración
                    conf.actualizar();
                    continue;
                }
            }
            break;
        } while (true);
        conf.plantilla("despedida");
    }
}
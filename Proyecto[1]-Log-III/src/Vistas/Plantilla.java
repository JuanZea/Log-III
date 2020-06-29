package Vistas;

import Modelo.MatrizEnTripletas;

import java.util.ArrayList;

public class Plantilla {
    public static final String AMARILLO = "\u001B[33m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String MORADO = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String ITALICA = "\u001B[3m";
    public static final String NEGRITA = "\u001B[1m";
    public static final String RESET = "\u001B[0m";

    public void inicio() {
        System.out.println(AMARILLO + "******************");
        System.out.println(RESET + "MATRICES DISPERSAS");
        System.out.println(AMARILLO + "******************" + RESET);
    }

    public void principal() {
        System.out.println(AMARILLO + "Menú Principal" + RESET);
        System.out.println("1. Crear matriz");
        System.out.println("2. Ver matrices");
        System.out.println("3. Operar matrices");
        System.out.println("4. Configuración");
        System.out.println(ROJO + "0. Salir" + RESET);
    }

    public void ver(ArrayList[] inv) {
        System.out.println(AMARILLO + "Vista de matrices" + " | " + MORADO + "En total hay [" + inv[0].size() + "] matrices" + RESET);
        System.out.println("1. Matrices en tripletas" + AMARILLO + "\t[" + inv[1].size() + "]" + RESET);
        System.out.println("2. Matrices en forma 1" + AMARILLO + "\t\t[" + inv[2].size() + "]" + RESET);
        System.out.println("3. Matrices en forma 2" + AMARILLO + "\t\t[" + inv[3].size() + "]" + RESET);
        this.defecto(1);
    }

    public void operar() {
        System.out.println(AMARILLO + "Operar matrices" + " | " + MORADO + "Elija la operación que quiera ejecutar" + RESET);
        System.out.println("1. Sumar matrices" + RESET);
        this.defecto(1);
    }

    public void operar(int i, ArrayList<String> nom) {
        switch (i) {
            case 1: {
                System.out.println(AMARILLO + "Sumar matrices" + " | " + MORADO + "Elija la primera matriz a sumar" + RESET);
                this.ver(1, "Matriz en tripletas", null, nom);
                break;
            }
            case 2: {
                System.out.println(AMARILLO + "Sumar matrices" + " | " + MORADO + "Elija la segunda matriz a sumar" + RESET);
                this.ver(1, "Matriz en forma 1", null, nom);
                break;
            }
        }
    }

    public void editar() {
        System.out.println(AMARILLO + "EDITAR" + RESET + " | " + MORADO + "Seleccione la operación que quiera ejecutar" + RESET);
        System.out.println("1. Agregar dato");
        System.out.println("2. Eliminar dato");
        this.defecto(1);
    }

    public void ver(int caso, String tipo, ArrayList<MatrizEnTripletas> inv, ArrayList<String> nom) {
        switch (caso) {
            case 0: {
                System.out.println(AMARILLO + "INVENTARIO VACÍO\n" +
                        RESET + "Selecciona el tipo de matriz" + AMARILLO + " (" + tipo + ") " + RESET + "en la configuración y créala en la opción 1 del menú principal" + RESET);
                this.defecto(0);
                break;
            }
            case 1: {
                System.out.println(AMARILLO + tipo + " | " + MORADO + "En total hay [" + nom.size() + "] matrices, elija la que desee operar" + RESET);
                for (int i = 0; i < nom.size(); i++) {
                    System.out.println((i + 1) + ". " + nom.get(i));
                }
                this.defecto(2);
                break;
            }
        }

    }

    public void configuracion(ArrayList conf_string) {
        System.out.println(AMARILLO + "Configuración" + RESET + " | " + MORADO + "Las combinaciones de opciones booleanas que no tengan sentido no se permitirán" + RESET);
        System.out.println("1. Establecer tipo de matriz\t\t\t\t" + conf_string.get(0));
        System.out.println("2. Establecer datos manualmente\t\t\t\t" + conf_string.get(1));
        System.out.println("3. Establecer dimensión manualmente\t\t\t" + conf_string.get(2));
        System.out.println("4. Establecer cantidad de datos manualmente\t" + conf_string.get(3));
        System.out.println("5. Establecer configuración predeterminada\t" + conf_string.get(4));
        System.out.println(ROJO + "0. Volver (Menú principal)" + RESET);
    }

    public void operaciones_individuales(int tipo, String matriz) {
        System.out.println(NEGRITA + AMARILLO + matriz.toUpperCase() + RESET);
        System.out.println("1. Mostrar");
        System.out.println("2. Imprimir");
        System.out.println("3. Sumar datos de la triangular superior izquierda");
        System.out.println("4. Transponer");
        System.out.println("5. Convertir");
        System.out.println("6. Editar");
        this.defecto(2);
    }

    public void transpuesta() {
        System.out.println(AMARILLO + "EXITOSO" + RESET + " | " + MORADO + "La operación transponer fue ejecutada correctamente" + RESET);
    }

    public void convertir(int tipo) {
        System.out.println(AMARILLO + "CONVERTIR" + RESET + " | " + MORADO + "Seleccione a que tipo de matriz desea que se convierta" + RESET);
        switch (tipo) {
            case 1: {
                System.out.println("1. Hacia matriz en forma 1" + RESET);
                System.out.println("2. Hacia matriz en forma 2" + RESET);
                break;
            }
            case 2: {
                System.out.println("1. Hacia matriz en tripletas" + RESET);
                System.out.println("2. Hacia matriz en forma 2" + RESET);
                break;
            }
            case 3: {
                System.out.println("1. Hacia matriz en tripletas" + RESET);
                System.out.println("2. Hacia matriz en forma 1" + RESET);
                break;
            }
        }
    }

    public void mostrar_Mt() {
        System.out.println(AMARILLO + "-------------------------------" + RESET);
        System.out.println(AMARILLO + "id. " + ROJO + "( " + MORADO + "Fila" + RESET + ", " + MORADO + "Columna" +
                RESET + ", " + MORADO + "Valor" + ROJO + " )" + RESET);
        System.out.println(AMARILLO + "-------------------------------" + RESET);
    }

    public void mostrar_Mt(int i, int f, int c, Object v) {
        System.out.println(AMARILLO + i + "." + ROJO + " ( " + MORADO + f + RESET + ", " + MORADO + c + RESET + ", " +
                MORADO + v + ROJO + " )" + RESET);
    }

    public void sumarTriangular_SupI_Mt(int suma) {
        System.out.println(AMARILLO + "Sumar Triangular Superior Izquierda" + RESET);
        System.out.println("El resultado de la suma es: " + NEGRITA + MORADO + suma + RESET);
    }

    public void datos(int[] dim) {
        if (dim != null) {
            System.out.println(AMARILLO + "Creación" + " | " +
                    MORADO + "Ingrese un dato para guardar en la matriz" + RESET + " | " +
                    CYAN + "Digite '0' para finalizar\n" +
                    AMARILLO + "Dimensión: (" + dim[0] + ", " + dim[1] + ")" + RESET);
        } else {
            System.out.println(AMARILLO + "Creación" + " | " +
                    MORADO + "Ingrese un dato para guardar en la matriz" + RESET + " | " +
                    CYAN + "Digite '0' para finalizar" + RESET);
        }
    }

    public void dato(int i) {
        switch (i) {
            case 0: {
                System.out.println(AMARILLO + "Asignación" + " | " +
                        MORADO + "Ingrese un dato para asignar en la matriz" + RESET);
                this.defecto(2);
                break;
            }
            case 1: {
                System.out.println(AMARILLO + "Eliminación" + " | " +
                        MORADO + "Ingrese un dato para asignar en la matriz" + RESET);
                this.defecto(2);
                break;
            }
        }

    }


    public void dimension() {
        System.out.println(AMARILLO + "Creación" + " | " +
                MORADO + "Ingrese las dimensiones de la matriz" + RESET);
    }

    public void eje(int i) {
        switch (i) {
            case 0: {
                System.out.print(VERDE + "Fila: " + RESET);
                break;
            }
            case 1: {
                System.out.print(VERDE + "Columna: " + RESET);
                break;
            }
            case 2: {
                System.out.print(VERDE + "Valor: " + RESET);
                break;
            }
            case 3: {
                System.out.println(AMARILLO + "Dato guardado" + RESET + " | " +
                        MORADO + "Para detenerse ingrese '0'" + VERDE);
                break;
            }
        }
    }

    public void creada(boolean i) {
        if (i) {
            System.out.println(AMARILLO + "EXITOSO" + RESET + " | " + MORADO + "La matriz se creó correctamente" + RESET);
        } else {
            System.out.println(ROJO + "FALLIDO" + RESET + " | " + MORADO + "La matriz no se creó" + RESET);
        }
    }

    public void convertida(boolean i) {
        if (i) {
            System.out.println(AMARILLO + "EXITOSO" + RESET + " | " + MORADO + "La matriz se convirtió correctamente" + RESET);
        } else {
            System.out.println(ROJO + "FALLIDO" + RESET + " | " + MORADO + "La matriz no se convirtió" + RESET);
        }
    }

    public void nombre() {
        System.out.print(AMARILLO + "NOMBRE" + RESET + " | ");
        System.out.println(MORADO + "Póngale un nombre a la matriz para diferenciarla" + RESET);
        System.out.println(ROJO + "0. Cancelar (Menú principal)" + RESET);
        System.out.print(VERDE + "Nombre: " + RESET);
    }

    public void previa(int tipo, int cantidad, int[] dim) {
        String nombre = null;
        switch (tipo) {
            case 1: {
                nombre = "Matriz en tripletas";
                break;
            }
            case 2: {
                nombre = "Matriz en forma 1";
                break;
            }
            case 3: {
                nombre = "Matriz en forma 2";
                break;
            }
        }
        System.out.println(ITALICA + nombre + " | " + cantidad + " | (" + dim[0] + ", " + dim[1] + ")" + RESET);
    }

    /**
     * @param i 0: Presione Enter, 1: Volver al menú principal, 2: Regresar
     */
    public void defecto(int i) {
        switch (i) {
            case -1:{
                System.out.println(AMARILLO + NEGRITA + "Adiós" + MORADO + ITALICA + "\t\t\tby: JuanZea");
                break;
            }
            case 0: {
                System.out.println(ROJO + "[Presione ENTER para regresar]" + RESET);
                break;
            }
            case 1: {
                System.out.println(ROJO + "0. Volver (Menú principal)" + RESET);
                break;
            }
            case 2: {
                System.out.println(ROJO + "0. Regresar" + RESET);
            }
        }
    }

    public void dimension(int dim) {
        switch (dim) {
            case 0: {
                System.out.print(VERDE + "Ingrese la cantidad de filas: " + RESET);
                break;
            }
            case 1: {
                System.out.print(VERDE + "Ingrese la cantidad de columnas: " + RESET);
                break;
            }
        }
    }

    public void cantidad(int[] dim) {
        if (dim != null) {
            System.out.print(AMARILLO + "Cantidad" + RESET + " | ");
            System.out.println(MORADO + "Ingrese una cantidad de datos que quepa en la matriz de " +
                    "(" + dim[0] + ", " + dim[1] + ")" + RESET);
            System.out.println(ROJO + "0. Volver (Configuración)" + RESET);
            System.out.print(VERDE + "n = " + RESET);
        } else {
            System.out.print(AMARILLO + "Cantidad" + RESET + " | ");
            System.out.println(MORADO + "Ingrese una cantidad de datos para la matriz" + RESET);
            System.out.println(ROJO + "0. Volver (Configuración)" + RESET);
            System.out.print(VERDE + "n = " + RESET);
        }
    }

    public void tipo() {
        System.out.print(AMARILLO + "Menú: Tipo de matriz" + RESET + " | ");
        System.out.println(MORADO + "Elija un tipo de matriz" + RESET);
        System.out.println("1. Matriz en tripletas");
        System.out.println("2. Matriz en forma 1");
        System.out.println("3. Matriz en forma 2");
        System.out.println(ROJO + "0. Volver (Configuración)" + RESET);
    }

    public void acierto(int id) {
        switch (id) {
            case 0: {
                System.out.println(VERDE + NEGRITA + "¡Las matrices se han sumado correctamente!" + RESET);
                break;
            }
            case 1: {
                System.out.println(VERDE + NEGRITA + "¡El dato se asignó correctamente!" + RESET);
                break;
            }
            case 2: {
                System.out.println(VERDE + NEGRITA + "¡El dato se eliminó correctamente!" + RESET);
                break;
            }
        }
    }

    /**
     * Aquí acomodo los errores pa' que no estorben
     *
     * @param id 0: Respuesta fuera de rango, 1: Respuesta no numérica, 2: Respuesta negativa, 3: Dato ya asignado, 4: Matriz no cuadrada, 5: Matrices de dimensiones diferentes
     */
    public void error(int id) {
        switch (id) {
            case 0: {
                System.out.println(ROJO + "La respuesta no esta en el rango de opciones. Intenta otra vez." + RESET);
                break;
            }
            case 1: {
                System.out.println(ROJO + "La respuesta debe ser numérica. Intenta otra vez." + RESET);
                break;
            }
            case 2: {
                System.out.println(ROJO + "La respuesta debe ser positiva. Intenta otra vez." + RESET);
                break;
            }
            case 3: {
                System.out.println(ROJO + "Ya hay un dato guardado en esa posición. Intenta otra vez." + RESET);
                break;
            }
            case 4: {
                System.out.println(ROJO + "¡Para realizar la operación la matriz debe ser cuadrada!" + RESET);
                break;
            }
            case 5: {
                System.out.println(ROJO + "¡Para sumar matrices estas deben tener las mismas dimensiones!" + RESET);
                break;
            }
        }
    }

    public ArrayList traducir(ArrayList conf_old) {
        ArrayList<String> conf_string = new ArrayList();
        int conf1 = (int) conf_old.get(0);
        boolean conf2 = (boolean) conf_old.get(1);
        boolean conf3 = (boolean) conf_old.get(2);
        boolean conf4 = (boolean) conf_old.get(3);
        boolean conf5 = (boolean) conf_old.get(4);
        switch (conf1) {
            case 1: {
                conf_string.add(AMARILLO + "[Matriz en tripletas]" + RESET);
                break;
            }
            case 2: {
                conf_string.add(AMARILLO + "[Matriz en forma 1]" + RESET);
                break;
            }
            case 3: {
                conf_string.add(AMARILLO + "[Matriz en forma 2]" + RESET);
                break;
            }
        }
        if (conf2) {
            conf_string.add(VERDE + "[ACTIVADO]" + RESET);
        } else {
            conf_string.add(ROJO + "[DESACTIVADO]" + RESET);
        }
        if (conf3) {
            conf_string.add(VERDE + "[ACTIVADO]" + RESET);
        } else {
            conf_string.add(ROJO + "[DESACTIVADO]" + RESET);
        }
        if (conf4) {
            conf_string.add(VERDE + "[ACTIVADO]" + RESET);
        } else {
            conf_string.add(ROJO + "[DESACTIVADO]" + RESET);
        }
        if (conf5) {
            conf_string.add(VERDE + "[PREDETERMINADO]" + RESET);
        } else {
            conf_string.add(MORADO + "[PERSONALIZADO]" + RESET);
        }
        return conf_string;
    }
}

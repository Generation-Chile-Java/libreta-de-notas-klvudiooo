import java.util.*;

public class LibretaDeNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, ArrayList<Double>> calificaciones = new HashMap<>();

        // Validar cantidad de alumnos
        int cantidadDeAlumnos;
        do {
            System.out.println("Ingrese el número de alumnos: ");
            cantidadDeAlumnos = sc.nextInt();
            if (cantidadDeAlumnos <= 0) {
                System.out.println("El número es inválido. Intente nuevamente.");
            }
        } while (cantidadDeAlumnos <= 0);

        // Validar cantidad de notas
        int cantidadDeNotas;
        do {
            System.out.println("Ingrese el número de notas por alumno: ");
            cantidadDeNotas = sc.nextInt();
            if (cantidadDeNotas <= 0) {
                System.out.println("El número es inválido. Intente nuevamente.");
            }
        } while (cantidadDeNotas <= 0);

        sc.nextLine(); // limpiar buffer

        // Ingreso de nombres y notas
        for (int i = 0; i < cantidadDeAlumnos; i++) {
            System.out.print("\nNombre del alumno #" + (i + 1) + ": ");
            String nombre = sc.nextLine();
            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 0; j < cantidadDeNotas; j++) {
                double nota;
                do {
                    System.out.print("Nota " + (j + 1) + " (entre 1.0 y 7.0): ");
                    nota = sc.nextDouble();
                    if (nota < 1.0 || nota > 7.0) {
                        System.out.println("❌ La nota debe ser entre 1.0 y 7.0");
                    }
                } while (nota < 1.0 || nota > 7.0);
                notas.add(nota);
            }
            sc.nextLine(); // limpiar buffer
            calificaciones.put(nombre, notas);
        }

        // Calcular estadísticas
        HashMap<String, Double> promedios = new HashMap<>();
        HashMap<String, Double> maximos = new HashMap<>();
        HashMap<String, Double> minimos = new HashMap<>();

        double sumaGeneral = 0;
        int totalNotas = 0;

        for (String nombre : calificaciones.keySet()) {
            ArrayList<Double> notas = calificaciones.get(nombre);
            double suma = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            for (Double nota : notas) {
                suma += nota;
                if (nota > max) max = nota;
                if (nota < min) min = nota;
            }

            double promedio = suma / notas.size();
            promedios.put(nombre, promedio);
            maximos.put(nombre, max);
            minimos.put(nombre, min);

            sumaGeneral += suma;
            totalNotas += notas.size();
        }

        double promedioCurso = sumaGeneral / totalNotas;

        // Menú interactivo
        int opcion;
        do {
            System.out.println("\n📋 Menú de opciones:");
            System.out.println("1. Mostrar el promedio de notas por estudiante");
            System.out.println("2. Verificar si una nota es aprobatoria o reprobatoria");
            System.out.println("3. Comparar nota con el promedio del curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n📊 Promedios por estudiante:");
                    for (String nombre : calificaciones.keySet()) {
                        System.out.println(nombre + ": Promedio = " + promedios.get(nombre)
                                + ", Máxima = " + maximos.get(nombre)
                                + ", Mínima = " + minimos.get(nombre));
                    }
                    break;

                case 2:
                    System.out.print("Nombre del estudiante: ");
                    String nombre2 = sc.nextLine();

                    if (calificaciones.containsKey(nombre2)) {
                        System.out.print("Ingrese la nota a evaluar (1.0 - 7.0): ");
                        double notaEval = sc.nextDouble();
                        if (notaEval >= 4.0) {
                            System.out.println("Aprobado");
                        } else {
                            System.out.println("Reprobado");
                        }
                        sc.nextLine(); // limpiar
                    } else {
                        System.out.println("⚠️ Estudiante no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del estudiante: ");
                    String nombre3 = sc.nextLine();

                    if (calificaciones.containsKey(nombre3)) {
                        System.out.print("Ingrese la nota a comparar (1.0 - 7.0): ");
                        double notaComp = sc.nextDouble();
                        if (notaComp > promedioCurso) {
                            System.out.println("La nota está sobre el promedio del curso (" + promedioCurso + ")");
                        } else if (notaComp < promedioCurso) {
                            System.out.println("La nota está bajo el promedio del curso (" + promedioCurso + ")");
                        } else {
                            System.out.println("La nota es igual al promedio del curso (" + promedioCurso + ")");
                        }
                        sc.nextLine(); // limpiar
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}




import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws IOException {
        clearConsole();
        showTitle(_title, ANSI_PURPLE);
        GestorUser gestor = new GestorUser();
        GestorRevistaLibro gestorR = new GestorRevistaLibro();
        List<User> usuarios = gestor.leerCSV("usuarios.csv");
        List<Venta> ventas = gestor.leerVentas("ventasPrestamos.csv");
        List<IRevistaLibro> inventario = gestorR.leerRevistaLibro("inventario.csv");
        final int MAX_LIBROS = 3;
        final int MAX_DIAS = 30;
        final int MAX_LIBROS_Premium = 5;
        final int MAX_DIAS_Premium = 50;
        int librosPrestados = 0;
        while (true) {
            printSpaceSeparated("1)", "Modo Registro", ANSI_YELLOW);
            printSpaceSeparated("2)", "Ingresar/Salir", ANSI_YELLOW);
            printSpaceSeparated("3)", "Modo eleccion", ANSI_YELLOW);
            printSpaceSeparated("4)", "Modo Prestamos", ANSI_YELLOW);
            printSpaceSeparated("5)", "Modo perfil", ANSI_YELLOW);

            var answer = formLabel("Por favor seleccione una opción", ANSI_CYAN);
            if (answer.equals("2")) {
                consoleWriteLine("Haz salido del programa.");
                break;
            } else if (answer.equals("1")) {
                String username = formLabel("Nombre usuario", ANSI_CYAN);
                String contraseña = formLabel("Contraseña", ANSI_CYAN);
                boolean createsContent = formLabel("Eres premium? (y/n)", ANSI_CYAN, s -> true,
                        s -> s.equals("y"));
                User user = new User(username, contraseña, createsContent);
                usuarios.add(user);
                gestor.guardarCSV(usuarios, "usuarios.csv");
            } else if (answer.equals("3")) {
                boolean createsContent = formLabel("Eres premium? (y/n)", ANSI_CYAN, s -> true,
                        s -> s.equals("y"));
                gestorR.imprimirInventario(inventario);
                if (createsContent == true) {
                    if (librosPrestados < MAX_LIBROS_Premium) {
                        System.out.println(
                                "Ingresa el ID del producto que deseas vender (Ingresa 0 para salir del modo venta): ");
                        int index = sc.nextInt();

                        if (index == 0) {
                            break;
                        }

                        inventario.stream()
                                .filter(p -> p.getId() == index)
                                .findAny();

                        IRevistaLibro producto = inventario.stream()
                                .filter(p -> p.getId() == index)
                                .findAny()
                                .orElse(null);

                        if (producto != null) {
                            System.out.println("Ingresa la cantidad a vender: ");
                            String name = producto.getNombre();
                            int vend = sc.nextInt();
                            int cand = producto.getCantidad();
                            double tot = vend * producto.getPrecio();

                            if (vend <= cand) {
                                producto.setCantidad(cand - vend);
                                producto.setCantidadVendida(vend);

                                Venta venta = new Venta(name, vend, tot, LocalDate.now());
                                ventas.add(venta);
                                librosPrestados++;

                            } else {
                                System.out.println("Cantidad no disponible, lo sentimos");
                            }

                        } else {
                            System.out.println("Lo sentimos el ID que proporcionaste no existe");
                        }

                    } else {
                        consoleWrite("Ya excedio el limite de libros a prestar.");
                    }
                    gestor.guardarCSV2(ventas, "ventas.csv");
                    gestorR.guardarCSV(inventario, "inventario.csv");
                } else {
                    if (librosPrestados < MAX_LIBROS) {
                        System.out.println(
                                "Ingresa el ID del producto que deseas vender (Ingresa 0 para salir del modo venta): ");
                        int index = sc.nextInt();

                        if (index == 0) {
                            break;
                        }

                        inventario.stream()
                                .filter(p -> p.getId() == index)
                                .findAny();

                        IRevistaLibro producto = inventario.stream()
                                .filter(p -> p.getId() == index)
                                .findAny()
                                .orElse(null);

                        if (producto != null) {
                            System.out.println("Ingresa la cantidad a vender: ");
                            String name = producto.getNombre();
                            int vend = sc.nextInt();
                            int cand = producto.getCantidad();
                            double tot = vend * producto.getPrecio();

                            if (vend <= cand) {
                                producto.setCantidad(cand - vend);
                                producto.setCantidadVendida(vend);

                                Venta venta = new Venta(name, vend, tot, LocalDate.now());
                                ventas.add(venta);
                                librosPrestados++;

                            } else {
                                System.out.println("Cantidad no disponible, lo sentimos");
                            }

                        } else {
                            System.out.println("Lo sentimos el ID que proporcionaste no existe");
                        }

                    } else {
                        writeErrorMessage("Ya excedio el limite de libros a prestar.");
                    }
                    gestor.guardarCSV2(ventas, "ventas.csv");
                    gestorR.guardarCSV(inventario, "inventario.csv");
                }
            } else if (answer.equals("4")) {
                boolean createsContent = formLabel("Eres premium? (y/n)", ANSI_CYAN, s -> true,
                        s -> s.equals("y"));
                if (createsContent == true) {
                    System.out.println("Por cuantos dias quieres el prestamo: MAX: 50");
                    int dias = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Hora de entrega: AM/PM");
                    String hora = sc.nextLine();
                    gestorR.imprimirInventario(inventario);
                    System.out.println("Seleccione direccion de envio.");
                    String direccion = sc.nextLine();
                    System.out.println("Los dias que tendra el prestamos son: " + dias + "Su hora de entrega es " + hora
                            + "Y con direccion a: " + direccion);
                } else {
                    System.out.println("Por cuantos dias quieres el prestamo: MAX: 30");
                    int dias = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Definir sucursal para recoger.");
                    String sucursal = sc.nextLine();
                    System.out.println("A que hora pasara por el prestamo: 12 o 24.");
                    String hora = sc.nextLine();
                    System.out.println("Los dias que tendra el prestamos son: " + dias + "Su sucursal de entrega es "
                            + sucursal + "y pasara por el prestamo despues de " + hora + "horas");
                }
            } else if (answer.equals("5")) {
                boolean createsContent = formLabel("Eres premium? (y/n)", ANSI_CYAN, s -> true,
                        s -> s.equals("y"));
                if (createsContent == true) {
                    consoleWrite("Tienes cupon de 15 dias extras. ");
                } else {

                }
            }
        }
    }

    public static void writeErrorMessage(Object message) {
        consoleWriteLine(message, ANSI_WHITE, ANSI_RED_BACKGROUND);
    }

    public static void writeSuccessMessage(Object message) {
        consoleWriteLine(message, ANSI_WHITE, ANSI_GREEN_BACKGROUND);
    }

    public static void showTitle(ArrayList<Object> titulo, String foreground) {
        for (Object line : titulo) {
            consoleWriteLine(line, foreground);
        }
    }

    public static <T> void progressBar(Object prefix, Iterable<Object> frames) {
        progressBar(prefix, frames, 3000, ANSI_YELLOW);
    }

    public static <T> void progressBar(Object prefix, Iterable<Object> frames, long durationInMs) {
        progressBar(prefix, frames, durationInMs, ANSI_YELLOW);
    }

    public static <T> void progressBar(Object prefix, Iterable<Object> frames, long durationInMs, Object color) {
        final long START_MS = Clock.systemUTC().instant().toEpochMilli();
        boolean startAgain = true;

        System.out.print(String.format("%s%s", color, prefix));
        while (startAgain) {
            for (Object frame : frames) {
                final String stringFrame = frame.toString();
                System.out.print(stringFrame);

                final long CURRENT_MS = Clock.systemUTC().instant().toEpochMilli();
                if (CURRENT_MS - START_MS > durationInMs) {
                    System.out.println(ANSI_RESET);
                    startAgain = false;
                    break;
                }

                try {
                    Thread.sleep(200, 0);
                } catch (Exception e) {
                }

                for (int i = 0; i < stringFrame.length(); i++) {
                    System.out.print("\b \b"); // \b is a not destructive backspace, that's why we need the space
                }
            }
        }

    }

    public static <T> void progressBar(Object prefix, Iterable<Object> frames, BooleanSupplier endCondition,
            Object color) {
        System.out.print(String.format("%s%s", color, prefix));
        while (endCondition.getAsBoolean()) {
            for (Object frame : frames) {
                final String stringFrame = frame.toString();
                System.out.print(stringFrame);

                try {
                    Thread.sleep(200, 0);
                } catch (Exception e) {
                }

                for (int i = 0; i < stringFrame.length(); i++) {
                    System.out.print("\b \b"); // \b is a not destructive backspace, that's why we need the space
                }
            }
        }
        System.out.println(ANSI_RESET);
    }

    public static <T> void showInTable(T item, Function<T, LinkedHashMap<Object, Object>> convertToMap) {
        Integer maxLength = null;
        var rows = convertToMap.apply(item);
        if (maxLength == null) {
            maxLength = rows.keySet().stream()
                    .map(o -> o.toString().length())
                    .max(Integer::compare)
                    .orElse(0) + 1;
        }

        for (Object rowName : rows.keySet()) {
            int spacing = maxLength - rowName.toString().length() + rows.get(rowName).toString().length();
            var format = "%" + spacing + "s";
            consoleWrite(rowName + ":", ANSI_CYAN);
            consoleWriteLine(String.format(format, rows.get(rowName)));
        }
    }

    public static <T> void showInTable(Iterable<T> list,
            Function<T, LinkedHashMap<Object, Object>> convertToMap) {
        Integer maxLength = null;
        for (T itemT : list) {
            var rows = convertToMap.apply(itemT);
            if (maxLength == null) {
                maxLength = rows.keySet().stream()
                        .map(o -> o.toString().length())
                        .max(Integer::compare)
                        .orElse(0) + 1;
            }
            for (Object rowName : rows.keySet()) {
                int spacing = maxLength - rowName.toString().length() + rows.get(rowName).toString().length();
                var format = "%" + spacing + "s";
                consoleWrite(rowName + ":", ANSI_CYAN);
                consoleWriteLine(String.format(format, rows.get(rowName)));
            }
            consoleWriteLine(SUB_DIVIDER);
        }
    }

    public static ArrayList<Object> _title = new ArrayList<>() {
        {

            add("  ___   _   _      _   _         _                     ");
            add(" | _ ) (_) | |__  | | (_)  ___  | |_   ___   __   __ _ ");
            add(" | _ \\ | | | '_ \\ | | | | / _ \\ |  _| / -_) / _| / _` |.");
            add(" |___/ |_| |_.__/ |_| |_| \\___/  \\__| \\___| \\__| \\__,_|");
        }
    };
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    static final Scanner sc = new Scanner(System.in);

    static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    static final int DIVIDER_LENGTH = 32;
    static final int DIVIDER_HALF_LENGTH = DIVIDER_LENGTH / 2;
    static final String DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "=");
    static final String SUB_DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "-");
    static final Iterable<Object> FRAMES = new ArrayList<>() {
        {
            add("-");
            add("\\");
            add("|");
            add("/");
        }
    };

    public static String formLabel(Object label, String color) {
        consoleWrite(label + ": ");
        return waitForInputColored(color);
    }

    public static String formLabel(Object label, String color, Function<String, Boolean> check) {
        String input = "";
        do {
            input = formLabel(label, color);
        } while (!check.apply(input));

        return input;
    }

    public static <T> T formLabel(Object label, String color, Function<String, Boolean> check,
            Function<String, T> conv) {
        String input = formLabel(label, color, check);
        return conv.apply(input);
    }

    public static String waitForInputColored(String color) {
        System.out.print(color);
        String s = readLine();
        System.out.print(ANSI_RESET);
        return s;
    }

    public static void printSpaceSeparated(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ", color);
        consoleWriteLine(option.toString());
    }

    public static void printSpaceSeparatedFor(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ");
        consoleWriteLine(option.toString(), color);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void consoleWriteLine(Object message) {
        consoleWriteLine(message, "", "");
    }

    public static void consoleWriteLine(Object message, String foreground) {
        consoleWriteLine(message, foreground, "");
    }

    public static void consoleWriteLine(Object message, String foreground, String background) {
        System.out.println(background + foreground + message + ANSI_RESET);
    }

    public static void consoleWrite(Object message) {
        consoleWrite(message, "", "");
    }

    public static void consoleWrite(Object message, String foreground) {
        consoleWrite(message, foreground, "");
    }

    public static void consoleWrite(Object message, String foreground, String background) {
        System.out.print(background + foreground + message + ANSI_RESET);
    }

    public static String readLine() {
        return sc.nextLine();
    }
}

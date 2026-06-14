package com.biblioteca.util;
import com.biblioteca.util.DataManager;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManager {

    public static void guardar(
            Object objeto,
            String ficheiro) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(
                                     ficheiro))) {

            out.writeObject(objeto);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static Object carregar(
            String ficheiro) {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(
                                     ficheiro))) {

            return in.readObject();

        } catch (Exception e) {

            return null;
        }
    }
}
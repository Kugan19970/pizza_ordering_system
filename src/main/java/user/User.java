package user;

import model.Pizza;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Pizza> favoritePizzas;

    public User(String username) {
        this.username = username;
        this.favoritePizzas = loadFavorites();
    }

    public String getUsername() {
        return username;
    }

    public List<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
        saveFavorites();
    }

    public void removeFavoritePizza(Pizza pizza) {
        favoritePizzas.remove(pizza);
        saveFavorites();
    }

    private void saveFavorites() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(username + "_favorites.dat"))) {
            oos.writeObject(favoritePizzas);
        } catch (IOException e) {
            System.err.println("Error saving favorites: " + e.getMessage());
        }
    }

    private List<Pizza> loadFavorites() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username + "_favorites.dat"))) {
            return (List<Pizza>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

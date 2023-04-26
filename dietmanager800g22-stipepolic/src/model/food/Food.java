package model.food;

public interface Food {
    void setName(String name);

    String getName();

    double getCalories();

    double getFat();

    double getCarbs();

    double getProtein();

    String toString();
}
